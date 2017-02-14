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
import com.github.artyomcool.dante.core.CompoundIndex;
import com.github.artyomcool.dante.core.Property;
import net.jcip.annotations.NotThreadSafe;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Base class for all DAO. In most cases should be accessed through {@link DaoMaster#dao(Class)} with entity class.
 * Usable for inserting, updating and deleting entities. In most cases shouldn't be used for direct queries.
 * Use {@link DaoMaster#queries(Class)} for that.
 *
 * @param <E>
 */
@SuppressWarnings("unused")
@NotThreadSafe
public abstract class Dao<E> {

    private final StringBuilder tmp = new StringBuilder();

    private final SQLiteDatabase db;

    private final int sinceVersion;

    private int idColumnIndex = -1;

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

    protected abstract List<Property> getProperties();

    protected abstract Property getIdProperty();

    protected abstract List<CompoundIndex> getCompoundIndexes();

    protected abstract E createEntity(Cursor cursor);

    protected abstract void bind(E entity, SQLiteStatement statement);

    protected abstract void bindId(E entity, SQLiteStatement statement, int index);

    protected abstract void updateRowId(E entity, long rowId);

    protected abstract E getFromCache(Cursor cursor, int idColumnIndex);

    protected abstract void putIntoCache(E entity);

    protected abstract void removeFromCache(E entity);

    protected abstract void clearCache();

    /**
     * Returns DB version in which this field where presented. Used for automatic migration.
     *
     * @return DB version since field exists
     */
    public int getSinceVersion() {
        return sinceVersion;
    }

    /**
     * Creates one entity from current row of cursor. In case the entity with the same id was already created,
     * it will be just returned, without re-reading from cursor.
     *
     * @param cursor initialized cursor to perform reading values
     * @return a new entity initialized from the cursor columns or the old entity with the same id
     */
    public E fromCursor(Cursor cursor) {
        E result = getFromCache(cursor, getIdColumnIndex());

        if (result != null) {
            return result;
        }

        result = createEntity(cursor);
        putIntoCache(result);
        return result;
    }

    private int getIdColumnIndex() {
        if (idColumnIndex == -1) {
            idColumnIndex = getProperties().indexOf(getIdProperty());
        }
        return idColumnIndex;
    }

    private void allColumns(StringBuilder builder) {
        for (Property property : getProperties()) {
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

        executeInsert(entity, insertStatement);
    }

    private void executeInsert(E entity, SQLiteStatement insertStatement) {
        bind(entity, insertStatement);
        long id = insertStatement.executeInsert();
        updateRowId(entity, id);
        putIntoCache(entity);
    }

    public void insert(Iterable<E> elements) {
        SQLiteStatement insertStatement = ensureInsertQuery();

        boolean needTransaction = !db.inTransaction();
        if (needTransaction) {
            db.beginTransaction();
        }
        try {
            for (E e : elements) {
                executeInsert(e, insertStatement);
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

        executeUpdate(element, updateStatement);
    }

    private void executeUpdate(E element, SQLiteStatement updateStatement) {
        bind(element, updateStatement);
        bindId(element, updateStatement, getProperties().size() + 1);
        updateStatement.execute();
    }

    public void update(Iterable<E> elements) {
        SQLiteStatement updateStatement = ensureUpdateQuery();

        boolean needTransaction = !db.inTransaction();
        if (needTransaction) {
            db.beginTransaction();
        }
        try {
            for (E e : elements) {
                executeUpdate(e, updateStatement);
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

    public void delete(E entity) {
        SQLiteStatement sqLiteStatement = ensureDeleteQuery();
        bindId(entity, sqLiteStatement, 1);
        sqLiteStatement.execute();
        removeFromCache(entity);
    }

    public void delete(Iterable<E> entities) {
        SQLiteStatement deleteStatement = ensureDeleteQuery();

        boolean needTransaction = !db.inTransaction();
        if (needTransaction) {
            db.beginTransaction();
        }

        try {
            for (E e : entities) {
                executeDelete(e, deleteStatement);
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

    private void executeDelete(E entity, SQLiteStatement deleteStatement) {
        bindId(entity, deleteStatement, 1);
        deleteStatement.execute();
        removeFromCache(entity);
    }

    public void clear() {
        db.execSQL("DELETE FROM '" + getTableName() + "'");
        clearCache();
    }

    public void runInTx(Runnable runnable) {
        db.beginTransaction();
        try {
            runnable.run();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public <T> T callInTx(Callable<T> callable) throws Exception {
        db.beginTransaction();
        try {
            T result = callable.call();

            db.setTransactionSuccessful();
            return result;
        } finally {
            db.endTransaction();
        }
    }

    private SQLiteStatement ensureInsertQuery() {
        if (insertStatement == null) {
            tmp.append("INSERT INTO '")
                    .append(getTableName())
                    .append("' (");

            allColumns(tmp);

            tmp.append(") VALUES (");

            int size = getProperties().size();
            for (int i = 1; i < size; i++) {
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

            for (Property property : getProperties()) {
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
                    .append("' WHERE ")
                    .append(getIdProperty().getColumnName())
                    .append(" = ?");

            deleteByIdStatement = db.compileStatement(recycle(tmp));
        }
        return deleteByIdStatement;
    }

    public void createTable() {
        db.execSQL(createTable(false, Integer.MAX_VALUE));
        ensureIndexes(Integer.MAX_VALUE);
    }

    public void dropTable() {
        db.execSQL(dropTable(true));
    }

    public void ensureTable(int version) {
        db.execSQL(createTable(true, version));
        ensureIndexes(version);
    }

    public void ensureProperty(Property property, int version) {
        String defaultValue = property.getDefaultValue() == null ? "" : " DEFAULT " + property.getDefaultValue();
        db.execSQL("ALTER TABLE '" + getTableName() + "' " +
                "ADD COLUMN " + property.getColumnName() + " " + property.getColumnType() +
                " " + property.getColumnExtraDefinition() + defaultValue);
    }

    private void ensureIndexes(int version) {
        for (Property property : getProperties()) {
            int indexedSince = property.getIndexedSince();
            if (indexedSince == Property.NO_INDEX || indexedSince > version) {
                continue;
            }
            ensureIndex(property, version);
        }
        for (CompoundIndex compoundIndex : getCompoundIndexes()) {
            int indexedSince = compoundIndex.getSinceVersion();
            if (indexedSince <= version) {
                ensureIndex(compoundIndex, version);
            }
        }
    }

    public void ensureIndex(Property property, int version) {
        db.execSQL(
                "CREATE" + (property.isIndexUnique() ? " UNIQUE" : "")
                        + " INDEX IF NOT EXISTS " + property.getIndexName() + " ON " +
                        getTableName() + " (" + property.getColumnName() + ")"
        );
    }

    public void ensureIndex(CompoundIndex index, int version) {
        tmp.append("CREATE");
        if (index.isUnique()) {
            tmp.append(" UNIQUE");
        }
        tmp.append(" INDEX IF NOT EXISTS ")
                .append(index.getName())
                .append(" ON ")
                .append(getTableName())
                .append(" (");

        for (CompoundIndex.Field field : index.getFields()) {
            String columnName = field.getProperty().getColumnName();
            tmp.append(columnName);
            if (field.isDesc()) {
                tmp.append(" DESC");
            }
            tmp.append(",");
        }
        tmp.setLength(tmp.length() - 1);
        tmp.append(")");

        db.execSQL(recycle(tmp));
    }

    private String createTable(boolean ifNotExists, int version) {
        tmp.append("CREATE TABLE ");
        if (ifNotExists) {
            tmp.append("IF NOT EXISTS ");
        }
        tmp.append('\'').append(getTableName()).append('\'').append('(');
        for (Property property : getProperties()) {
            if (property.getSinceVersion() > version) {
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

    private String dropTable(boolean ifExists) {
        tmp.append("DROP TABLE ");
        if (ifExists) {
            tmp.append("IF EXISTS ");
        }
        tmp.append('\'').append(getTableName()).append('\'');
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
