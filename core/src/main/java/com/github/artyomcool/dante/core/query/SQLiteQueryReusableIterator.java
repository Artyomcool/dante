package com.github.artyomcool.dante.core.query;

import android.database.Cursor;
import android.database.sqlite.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class SQLiteQueryReusableIterator extends SQLiteQueryCursorIterator {

    private final SQLiteCursor cursor;
    private final Object[] args;
    private final String where;

    public SQLiteQueryReusableIterator(SQLiteDatabase db, String query) {
        final SQLiteQuery[] holder = new SQLiteQuery[1];

        cursor = (SQLiteCursor) db.rawQueryWithFactory(new SQLiteDatabase.CursorFactory() {
            @Override
            public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String s, SQLiteQuery query) {
                holder[0] = query;
                return new SQLiteCursor(masterQuery, s, query);
            }
        }, query, null, "");

        if (holder[0] == null) {
            throw new IllegalStateException("SQLiteQuery has been not initialized");
        }

        try {
            Method getBindArgs = SQLiteProgram.class.getDeclaredMethod("getBindArgs");
            getBindArgs.setAccessible(true);
            args = (Object[]) getBindArgs.invoke(holder[0]);
        } catch (Exception e) {
            throw new IllegalStateException("Can't access bindArgs");
        }

        this.where = query;
    }

    public void reopen(Object[] args) {
        if (args.length > 0) {
            System.arraycopy(args, 0, this.args, 0, args.length);
        }
        cursor.requery();
    }

    @Override
    protected Cursor getCursor() {
        return cursor;
    }

    @Override
    public void close() {
        cursor.setWindow(null);
    }

    @Override
    public String toString() {
        return where + "{" + Arrays.asList(args) + "}";
    }
}
