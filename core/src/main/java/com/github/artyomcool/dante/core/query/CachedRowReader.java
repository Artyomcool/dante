package com.github.artyomcool.dante.core.query;

import com.github.artyomcool.dante.core.cashe.Cache;

public class CachedRowReader<E> implements RowReader<E>{

    private final RowReader<E> reader;
    private final Cache<E> cache;
    private final int idIndex;

    public CachedRowReader(RowReader<E> reader, Cache<E> cache, int idIndex) {
        this.reader = reader;
        this.cache = cache;
        this.idIndex = idIndex;
    }

    @Override
    public E readEntity(Row cursor) {
        E e = cache.get(cursor, idIndex);
        if (e != null) {
            return e;
        }
        e = reader.readEntity(cursor);
        cache.put(e);
        return e;
    }

    public int idIndex() {
        return idIndex;
    }

    public void put(E e) {
        cache.put(e);
    }

    public void remove(E e) {
        cache.remove(e);
    }

    public void clear() {
        cache.clear();
    }
}
