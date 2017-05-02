package com.github.artyomcool.dante.core.query;

import android.database.sqlite.SQLiteDatabase;

import javax.annotation.Nullable;
import java.util.Arrays;

public class SQLiteStringQueryEntityIteratorFactory<E> implements EntityIteratorFactory<E> {

    private final SQLiteDatabase db;
    private final String query;
    private final RowReader<E> rowReader;

    public SQLiteStringQueryEntityIteratorFactory(SQLiteDatabase db, RowReader<E> rowReader, String query) {
        this.db = db;
        this.query = query;
        this.rowReader = rowReader;
    }

    @Override
    public EntityIterator<E> requery(@Nullable Object[] args) {
        int length = args == null ? 0 : args.length;
        final String[] strArgs = new String[length];

        for (int i = 0; i < length; i++) {
            strArgs[i] = String.valueOf(args[i]);
        }

        return new RowEntityIterator<E>(new SQLiteStringQueryIterator(db, query, strArgs), rowReader) {
            @Override
            public String toString() {
                return query + "{" + Arrays.asList(strArgs) + "}";
            }
        };
    }

}
