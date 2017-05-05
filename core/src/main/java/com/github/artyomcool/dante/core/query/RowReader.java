package com.github.artyomcool.dante.core.query;

/**
 * Interface for reading an entity from the row.
 *
 * @param <E> entity
 * @see Row
 * @see CachedRowReader
 */
public interface RowReader<E> {

    /**
     * Reads an entity from the row.
     *
     * @param row the row to read from
     * @return entity
     */
    E readEntity(Row row);

}
