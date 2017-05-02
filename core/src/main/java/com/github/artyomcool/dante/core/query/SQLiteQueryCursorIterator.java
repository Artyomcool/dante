package com.github.artyomcool.dante.core.query;

import android.database.Cursor;

public abstract class SQLiteQueryCursorIterator implements IterableRow {

    protected abstract Cursor getCursor();

    @Override
    public boolean next() {
        return getCursor().moveToNext();
    }

    @Override
    public boolean isNull(int column) {
        return getCursor().isNull(column);
    }

    @Override
    public String getString(int column) {
        return getCursor().getString(column);
    }

    @Override
    public byte[] getBlob(int column) {
        return getCursor().getBlob(column);
    }

    @Override
    public long getLong(int column) {
        return getCursor().getLong(column);
    }

    @Override
    public double getDouble(int column) {
        return getCursor().getDouble(column);
    }

    @Override
    public int getCount() {
        return getCursor().getCount();
    }

}
