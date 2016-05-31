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

    public int getSinceVersion() {
        return sinceVersion;
    }

    public E fromCursor(Cursor cursor) {
        ensureIdColumnIndex();
        long id = cursor.getLong(idColumnIndex);
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

    public void insert(E element) {
        SQLiteStatement insertStatement = ensureInsertQuery();

        Property<E>[] properties = propertiesArray();
        for (int i = 0; i < properties.length; i++) {
            properties[i].bind(insertStatement, i, element);
        }
        long id = insertStatement.executeInsert();
        getIdProperty().afterInsert(element, id);
        toCache(id, element);
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
                for (int i = 0; i < properties.length; i++) {
                    properties[i].bind(insertStatement, i, e);
                }
                long id = insertStatement.executeInsert();
                getIdProperty().afterInsert(e, id);
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

    public void update(E element) {
        SQLiteStatement updateStatement = ensureUpdateQuery();

        Property<E>[] properties = propertiesArray();
        for (int i = 0; i < properties.length; i++) {
            properties[i].bind(updateStatement, i++, element);
        }
        updateStatement.execute();
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
                for (int i = 0; i < properties.length; i++) {
                    properties[i].bind(updateStatement, i++, e);
                }
                updateStatement.execute();
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

    private E getFromCache(long id) {
        return cache.get(id);
    }

    private void toCache(long id, E element) {
        cache.put(id, element);
    }

    private void ensureIdColumnIndex() {
        if (idColumnIndex == -1) {
            idColumnIndex = getProperties().indexOf(getIdProperty());
        }
    }

    private SQLiteStatement ensureInsertQuery() {
        if (insertStatement == null) {
            tmp.append("INSERT INTO ")
                    .append(getTableName())
                    .append(" (");

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
                    .append("SET ");

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

    public void createTable() {
        db.execSQL(createTable(false, Integer.MAX_VALUE));
    }

    public void ensureTable(int version) {
        db.execSQL(createTable(true, version));
    }

    public void ensureProperty(Property<?> property, int version) {
        db.execSQL("ALTER TABLE '" + getTableName() + "' " +
                "ADD COLUMN " + property.getColumnName() + " " + property.getColumnType() +
                " " + property.getColumnExtraDefinition());
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

        tmp.append(where);

        return recycle(tmp);
    }

    private static String recycle(StringBuilder tmp) {
        String result = tmp.toString();
        tmp.setLength(0);
        return result;
    }
}
