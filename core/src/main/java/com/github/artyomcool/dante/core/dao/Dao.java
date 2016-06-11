/*
 * Copyright (c)  2015-2016, Artyom Drozdov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.artyomcool.dante.core.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.github.artyomcool.dante.core.property.IdProperty;
import com.github.artyomcool.dante.core.property.Property;
import net.jcip.annotations.NotThreadSafe;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Base class for all DAO. In most cases should be accessed through {@link DaoMaster#dao(Class)} with entity class.
 * Usable for inserting, updating and deleting entities. In most cases shouldn't be used for direct queries.
 * Use {@link DaoMaster#queries(Class)} for that.
 * @param <E>
 */
@SuppressWarnings("unused")
@NotThreadSafe
public abstract class Dao<E> {

    private final StringBuilder tmp = new StringBuilder();

    private final LongWeakValueIdentityHashMap<E> cache = new LongWeakValueIdentityHashMap<>();

    private final SQLiteDatabase db;

    private final int sinceVersion;

    private int idColumnIndex = -1;

    @Nullable
    private Property<E>[] properties = null;

    @Nullable
    private String selectQuery = null;

    @Nullable
    private SQLiteStatement insertStatement = null;

    @Nullable
    private SQLiteStatement updateStatement = null;

    @Nullable
    private SQLiteStatement deleteByIdStatement = null;

    protected Dao(SQLiteDatabase db, int sinceVersion) {
        this.db = db;
        this.sinceVersion = sinceVersion;
    }

    protected abstract String getTableName();

    protected abstract List<Property<E>> getProperties();

    protected abstract IdProperty<E> getIdProperty();

    protected abstract E createEntity();

    private Property<E>[] propertiesArray() {
        if (this.properties == null) {
            List<Property<E>> propertiesList = getProperties();
            @SuppressWarnings("unchecked")
            Property<E>[] properties = new Property[propertiesList.size()];
            int i = 0;
            for (Property<E> property : propertiesList) {
                properties[i++] = property;
            }
            this.properties = properties;
        }
        return properties;
    }

    /**
     * Returns DB version in which this field where presented. Used for automatic migration.
     * @return DB version since field exists
     */
    public int getSinceVersion() {
        return sinceVersion;
    }

    /**
     * Creates one entity from current row of cursor. In case the entity with the same id was already created,
     * it will be just returned, without re-reading from cursor.
     * @param cursor initialized cursor for perform reading values
     * @return a new entity initialized from cursor columns or the old entity with the same id
     */
    public E fromCursor(Cursor cursor) {
        ensureIdColumnIndex();
        long id = cursor.getLong(idColumnIndex);    //FIXME it is not true for String primary key
        E result = getFromCache(id);

        if (result != null) {
            return result;
        }

        result = createEntity();
        Property<E>[] properties = propertiesArray();
        for (int i = 0; i < properties.length; i++) {
            properties[i].readFromCursor(cursor, i, result);
        }
        return result;
    }

    private void allColumns(StringBuilder builder) {
        for (Property<E> property : propertiesArray()) {
            builder.append(property.getColumnName()).append(',');
        }
        builder.setLength(builder.length() - 1);
    }

    public Cursor select(String where, String... params) {
        return db.rawQuery(getSelect(where), params);
    }

    public List<E> selectList(String where, String... params) {
        Cursor cursor = select(where, params);
        try {
            if (cursor.getCount() == 0) {
                return Collections.emptyList();
            }
            List<E> result = new ArrayList<>(cursor.getCount());
            while (cursor.moveToNext()) {
                result.add(fromCursor(cursor));
            }
            return result;
        } finally {
            cursor.close();
        }
    }

    @Nullable
    public E selectUnique(String where, String... params) {
        Cursor cursor = select(where, params);
        try {
            if (cursor.getCount() > 1) {
                throw new IllegalStateException(cursor.getCount() + " elements matches \"" + where + "\"; " + Arrays.toString(params));
            }
            if (!cursor.moveToNext()) {
                return null;
            }
            return fromCursor(cursor);
        } finally {
            cursor.close();
        }
    }

    public void insert(E entity) {
        SQLiteStatement insertStatement = ensureInsertQuery();

        Property<E>[] properties = propertiesArray();
        insert(entity, insertStatement, properties);
    }

    public void insert(Iterable<E> elements) {
        SQLiteStatement insertStatement = ensureInsertQuery();

        boolean needTransaction = !db.inTransaction();
        if (needTransaction) {
            db.beginTransaction();
        }
        try {
            Property<E>[] properties = propertiesArray();
            for (E e : elements) {
                insert(e, insertStatement, properties);
            }
            if (needTransaction) {
                db.setTransactionSuccessful();
            }
        } finally {
            if (needTransaction) {
                db.endTransaction();
            }
        }
    }

    private void insert(E entity, SQLiteStatement insertStatement, Property<E>[] properties) {
        for (int i = 0; i < properties.length; i++) {
            properties[i].bind(insertStatement, i, entity);
        }
        long id = insertStatement.executeInsert();
        getIdProperty().afterInsert(entity, id);
        toCache(id, entity);
    }

    public void update(E element) {
        SQLiteStatement updateStatement = ensureUpdateQuery();

        Property<E>[] properties = propertiesArray();
        update(element, updateStatement, properties);
    }

    public void update(Iterable<E> elements) {
        SQLiteStatement updateStatement = ensureUpdateQuery();

        boolean needTransaction = !db.inTransaction();
        if (needTransaction) {
            db.beginTransaction();
        }
        try {
            Property<E>[] properties = propertiesArray();
            for (E e : elements) {
                update(e, updateStatement, properties);
            }
            if (needTransaction) {
                db.setTransactionSuccessful();
            }
        } finally {
            if (needTransaction) {
                db.endTransaction();
            }
        }
    }

    public void deleteById(long id) {
        SQLiteStatement sqLiteStatement = ensureDeleteQuery();
        sqLiteStatement.bindLong(0, id);
        sqLiteStatement.execute();
        removeFromCache(id);
    }

    private void update(E element, SQLiteStatement updateStatement, Property<E>[] properties) {
        for (int i = 0; i < properties.length; i++) {
            properties[i].bind(updateStatement, i++, element);
        }
        updateStatement.execute();
    }

    private E getFromCache(long id) {
        return cache.get(id);
    }

    private void toCache(long id, E element) {
        cache.put(id, element);
    }

    private void removeFromCache(long id) {
        cache.remove(id);
    }

    private void ensureIdColumnIndex() {
        if (idColumnIndex == -1) {
            idColumnIndex = getProperties().indexOf(getIdProperty());
        }
    }

    private SQLiteStatement ensureInsertQuery() {
        if (insertStatement == null) {
            tmp.append("INSERT INTO '")
                    .append(getTableName())
                    .append("' (");

            allColumns(tmp);

            tmp.append(") VALUES (");

            for (int i = propertiesArray().length; i > 1; i--) {
                tmp.append("?,");
            }
            tmp.append("?)");

            insertStatement = db.compileStatement(recycle(tmp));
        }
        return insertStatement;
    }

    private SQLiteStatement ensureUpdateQuery() {
        if (updateStatement == null) {
            tmp.append("UPDATE '")
                    .append(getTableName())
                    .append("' SET ");

            for (Property<E> property : propertiesArray()) {
                tmp.append(property.getColumnName()).append(" = ?").append(',');
            }

            tmp.setLength(tmp.length() - 1);

            tmp.append(" WHERE ")
                    .append(getIdProperty().getColumnName())
                    .append(" = ?");

            updateStatement = db.compileStatement(recycle(tmp));
        }
        return updateStatement;
    }

    private SQLiteStatement ensureDeleteQuery() {
        if (deleteByIdStatement == null) {
            tmp.append("DELETE FROM '")
                    .append(getTableName())
                    .append("' WHERE _ROWID_ = ?");

            deleteByIdStatement = db.compileStatement(recycle(tmp));
        }
        return deleteByIdStatement;
    }

    public void createTable() {
        db.execSQL(createTable(false, Integer.MAX_VALUE));
    }

    public void ensureTable(int version) {
        db.execSQL(createTable(true, version));
    }

    public void ensureProperty(Property<?> property, int version) {
        String defaultValue = property.getDefaultValue() == null ? "" : " DEFAULT " + property.getDefaultValue();
        db.execSQL("ALTER TABLE '" + getTableName() + "' " +
                "ADD COLUMN " + property.getColumnName() + " " + property.getColumnType() +
                " " + property.getColumnExtraDefinition() + defaultValue);
    }

    private String createTable(boolean ifNotExists, int version) {
        tmp.append("CREATE TABLE ");
        if (ifNotExists) {
            tmp.append("IF NOT EXISTS ");
        }
        tmp.append('\'').append(getTableName()).append('\'').append('(');
        for (Property<E> property : propertiesArray()) {
            if (property.sinceVersion() > version) {
                continue;
            }
            tmp.append(property.getColumnName()).append(' ')
                    .append(property.getColumnType()).append(' ')
                    .append(property.getColumnExtraDefinition()).append(',');
        }
        tmp.setLength(tmp.length() - 1);
        tmp.append(')');

        return recycle(tmp);
    }

    private String getSelect(String where) {
        if (where.isEmpty() && selectQuery != null) {
            return selectQuery;
        }
        if (selectQuery == null) {
            tmp.append("SELECT ");
            allColumns(tmp);
            tmp.append(" FROM ").append(getTableName()).append(' ');
            selectQuery = tmp.toString();

            if (where.isEmpty()) {
                tmp.setLength(0);
                return selectQuery;
            }
        } else {
            tmp.append(selectQuery);
        }

        if (!where.isEmpty()) {
            tmp.append("WHERE ");
            tmp.append(where);
        }

        return recycle(tmp);
    }

    private static String recycle(StringBuilder tmp) {
        String result = tmp.toString();
        tmp.setLength(0);
        return result;
    }
}
