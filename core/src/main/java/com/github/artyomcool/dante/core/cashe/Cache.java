package com.github.artyomcool.dante.core.cashe;

import com.github.artyomcool.dante.core.query.Row;

import javax.annotation.Nullable;

/**
 * Cache for identity of entities. Identity means that the same entity (entity with the same ID) will be represented
 * with the same object.
 *
 * @param <E> entity
 */
public interface Cache<E> {

    /**
     * Reads an id from the <b>row</b> by the index <b>columnIndex</b> and returns an entity from the cache with this
     * id or <b>null</b> if there is no such entity in cache.
     *
     * @param row entity row
     * @param columnIndex column index for id field
     * @return cached entity or <b>null</b>
     */
    @Nullable
    E get(Row row, int columnIndex);

    /**
     * Puts the entity into the cache.
     * @param entity entity to cache
     */
    void put(E entity);

    /**
     * Removes the entity from the cache. Does nothing if there is no such entity in the cache.
     * @param entity entity to remove from cache
     */
    void remove(E entity);

    /**
     * Clears cache.
     */
    void clear();

}
