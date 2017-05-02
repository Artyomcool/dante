package com.github.artyomcool.dante.core.query;

import android.database.sqlite.SQLiteDatabase;

import javax.annotation.Nullable;

public class SQLiteQueryReusableEntityIteratorFactory<E> implements EntityIteratorFactory<E> {

    private final SQLiteQueryReusableIterator queryIterator;
    private final EntityIterator<E> entityIterator;

    public SQLiteQueryReusableEntityIteratorFactory(SQLiteDatabase db, RowReader<E> rowReader, String query) {
        this.queryIterator = new SQLiteQueryReusableIterator(db, query);
        this.entityIterator = new RowEntityIterator<E>(queryIterator, rowReader) {
            @Override
            public String toString() {
                return queryIterator.toString();
            }
        };
    }

    @Override
    public EntityIterator<E> requery(@Nullable Object[] args) {
        queryIterator.reopen(args);
        return entityIterator;
    }

}
