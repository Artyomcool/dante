package com.github.artyomcool.dante.core.query;

import com.github.artyomcool.dante.annotation.DbQueries;
import com.github.artyomcool.dante.annotation.Query;
import com.github.artyomcool.dante.core.cashe.Cache;

/**
 * Interface for handling big amount of querying entities without storing them (they are still cached).
 *
 * @param <E> entity
 * @param <T> produced result
 * @see Cache
 * @see DbQueries
 * @see Query
 * @see QueryImpl
 */
public interface Acceptor<E, T> {

    /**
     * Will be called after query was executed.
     * @param count count of selected entities
     */
    void acceptStart(int count);

    /**
     * Will be called for every entity that was selected.
     * @param next next entity
     */
    void acceptNext(E next);

    /**
     * Will be called after all entities was accepted by {@link #acceptNext(Object)}.
     * Result will be returned from query method.
     * @return result of iterating entities, e.g. filtered entities, some aggregation, etc.
     */
    T acceptFinish();

}
