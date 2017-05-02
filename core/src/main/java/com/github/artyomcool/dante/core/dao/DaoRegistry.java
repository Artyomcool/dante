/*
 * Copyright (c)  2015-2016, Artyom Drozdov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.artyomcool.dante.core.dao;

import android.database.sqlite.SQLiteDatabase;
import com.github.artyomcool.dante.core.EntityInfo;
import com.github.artyomcool.dante.core.Property;
import com.github.artyomcool.dante.core.Registry;
import com.github.artyomcool.dante.core.migration.MigrationInfo;
import com.github.artyomcool.dante.core.query.DbQueriesBase;

import java.util.*;

import static java.lang.Math.max;

/**
 *
 */
public abstract class DaoRegistry implements Registry {

    private final List<EntityInfo<?>> entitiesList = new ArrayList<>();
    private final Map<Class<?>, EntityInfo<?>> entitiesMap = new HashMap<>();

    private final List<Dao<?>> daoList = new ArrayList<>();
    private final Map<Class<?>, Dao<?>> daoMap = new HashMap<>();

    private final Map<Class<?>, DbQueriesBase> queriesMap = new HashMap<>();

    private final List<MigrationInfo> customMigrations = new ArrayList<>();

    private boolean initialized = false;
    private boolean daoInitialized = false;

    /**
     * Constructs new DaoRegistry. <b>Note:</b> call {@link #init(SQLiteDatabase)} to initialize it.
     * {@link DaoMaster#init()} will call for you.
     *
     * @see #init(SQLiteDatabase)
     * @see DaoMaster#init()
     */
    public DaoRegistry() {
        List<EntityInfo<?>> entityInfos = createEntityInfo();
        for (EntityInfo<?> info : entityInfos) {
            entitiesList.add(info);
            entitiesMap.put(info.getEntityClass(), info);
        }
    }

    /**
     * Initialize registry.
     *
     * @param db database
     */
    public void init(SQLiteDatabase db) {
        if (initialized) {
            throw new IllegalStateException("Already initialized");
        }

        List<? extends Dao<?>> daos = createDaoList(db);
        for (Dao<?> dao : daos) {
            daoList.add(dao);
            daoMap.put(dao.getEntityInfo().getEntityClass(), dao);
        }

        daoInitialized = true;

        queriesMap.putAll(createQueries(db));

        customMigrations.addAll(createCustomMigrations());

        initialized = true;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T queries(Class<T> clazz) {
        checkInit(initialized);
        return (T) queriesMap.get(clazz);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> Dao<T> dao(Class<T> clazz) {
        checkInit(daoInitialized);
        return (Dao<T>) daoMap.get(clazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <E> EntityInfo<E> entity(Class<E> clazz) {
        return (EntityInfo<E>) entitiesMap.get(clazz);
    }

    /**
     * Returns current version of the scheme. Calculated as maximum of dao, properties and property's indexes versions.
     *
     * @return version of the scheme
     */
    public int getVersion() {
        int version = 1;
        for (Dao<?> dao : daoList) {
            version = max(dao.getSinceVersion(), version);
        }
        for (EntityInfo<?> info : entitiesList) {
            for (Property property : info.getProperties()) {
                version = max(max(property.getIndexedSince(), property.getSinceVersion()), version);
            }
        }
        return version;
    }

    /**
     * Returns dao list.
     *
     * @return dao list
     */
    public List<Dao<?>> getDao() {
        checkInit(daoInitialized);
        return Collections.unmodifiableList(daoList);
    }

    /**
     * Returns custom migrations.
     *
     * @return custom migrations
     */
    public List<MigrationInfo> getCustomMigrations() {
        checkInit(initialized);
        return Collections.unmodifiableList(customMigrations);
    }

    /**
     * Prepares information about all entities.
     *
     * @return list of EntityInfo
     */
    protected abstract List<EntityInfo<?>> createEntityInfo();

    /**
     * Prepares all dao.
     *
     * @param db database
     * @return list of dao
     */
    protected abstract List<? extends Dao<?>> createDaoList(SQLiteDatabase db);

    /**
     * Prepares all queries.
     *
     * @param db database
     * @return map of queries implementation
     */
    protected abstract Map<Class<?>, ? extends DbQueriesBase> createQueries(SQLiteDatabase db);

    /**
     * Prepares custom migrations.
     *
     * @return custom migrations
     */
    protected abstract List<? extends MigrationInfo> createCustomMigrations();

    private void checkInit(boolean initialized) {
        if (!initialized) {
            throw new IllegalStateException("Not initialized yet");
        }
    }

}
