package com.github.artyomcool.dante.core.query;

import javax.annotation.Nullable;

public class RowEntityIterator<E> implements EntityIterator<E> {

    private final IterableRow iterableRow;
    private final RowReader<E> rowReader;

    public RowEntityIterator(IterableRow iterableRow, RowReader<E> rowReader) {
        this.iterableRow = iterableRow;
        this.rowReader = rowReader;
    }

    @Override
    public int getCount() {
        return iterableRow.getCount();
    }

    @Nullable
    @Override
    public E next() {
        if (!iterableRow.next()) {
            return null;
        }
        return rowReader.readEntity(iterableRow);
    }

    @Override
    public void close() {
        iterableRow.close();
    }
}
