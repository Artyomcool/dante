package com.github.artyomcool.dante.core.query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteStringQueryIterator extends SQLiteQueryCursorIterator {

    private final SQLiteDatabase db;
    private final String where;
    private final Cursor cursor;

    public SQLiteStringQueryIterator(SQLiteDatabase db, String query, String[] args) {
        this.db = db;
        this.where = query;
        this.cursor = db.rawQuery(query, args);
    }

    @Override
    protected Cursor getCursor() {
        return cursor;
    }

    @Override
    public void close() {
        cursor.close();
    }
}
