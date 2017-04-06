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
import com.github.artyomcool.dante.core.Property;

import java.util.*;

public abstract class DaoRegistry implements Registry {

    private final List<Dao<?>> daoList = new ArrayList<>();
    private final Map<Class<?>, Object> queriesMap = new HashMap<>();
    private final Map<Class<?>, Dao<?>> daoMap = new HashMap<>();

    private boolean initialized = false;

    public void init(SQLiteDatabase db) {
        if (initialized) {
            throw new IllegalStateException("Already initialized");
        }

        List<EntityInfo<?>> entityInfos = initDao(db);
        for (EntityInfo<?> info : entityInfos) {
            daoList.add(info.getDao());
            daoMap.put(info.getEntityClass(), info.getDao());
            queriesMap.putAll(info.getQueries());
        }

        initialized = true;
    }

    public List<Dao<?>> getDao() {
        checkInit();
        return Collections.unmodifiableList(daoList);
    }

    private void checkInit() {
        if (!initialized) {
            throw new IllegalStateException("Not initialized yet");
        }
    }

    protected abstract List<EntityInfo<?>> initDao(SQLiteDatabase db);

    protected abstract List<? extends MigrationInfo> initCustomMigrations();

    public int getVersion() {
        int version = 1;
        for (Dao<?> dao : daoList) {
            version = Math.max(dao.getSinceVersion(), version);
            for (Property property : dao.getProperties()) {
                version = Math.max(property.getSinceVersion(), version);
                version = Math.max(property.getIndexedSince(), version);
            }
        }
        return version;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T queries(Class<T> clazz) {
        return (T) queriesMap.get(clazz);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Dao<T> dao(Class<T> clazz) {
        return (Dao<T>) daoMap.get(clazz);
    }

}
