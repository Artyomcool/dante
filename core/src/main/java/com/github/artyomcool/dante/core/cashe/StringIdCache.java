package com.github.artyomcool.dante.core.cashe;

import com.github.artyomcool.dante.core.query.Row;

/**
 * Abstract implementation of {@link Cache} for {@link String} ids.
 * @param <E> entity
 */
public abstract class StringIdCache<E> implements Cache<E> {

    private final ObjectWeakValueIdentityHashMap<String, E> delegate = new ObjectWeakValueIdentityHashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public E get(Row row, int columnIndex) {
        return delegate.get(row.getString(columnIndex));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(E entity) {
        delegate.put(getId(entity), entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(E entity) {
        delegate.remove(getId(entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        delegate.removeAll();
    }

    /**
     * Gets an id from the <b>entity</b>.
     * @param entity entity
     * @return id
     */
    protected abstract String getId(E entity);

}
