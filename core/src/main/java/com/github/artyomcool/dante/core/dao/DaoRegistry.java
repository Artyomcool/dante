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

import java.util.*;

public abstract class DaoRegistry implements Registry {

    private final List<AbstractDao<?>> daoList = new ArrayList<>();
    private final Map<Class<?>, Object> queriesMap = new HashMap<>();
    private final Map<Class<?>, AbstractDao<?>> daoMap = new HashMap<>();

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

    public List<AbstractDao<?>> getDao() {
        checkInit();
        return Collections.unmodifiableList(daoList);
    }

    private void checkInit() {
        if (!initialized) {
            throw new IllegalStateException("Not initialized yet");
        }
    }

    protected abstract List<EntityInfo<?>> initDao(SQLiteDatabase db);

    @SuppressWarnings("unchecked")
    @Override
    public <T> T queries(Class<T> clazz) {
        return (T) queriesMap.get(clazz);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> AbstractDao<T> dao(Class<T> clazz) {
        return (AbstractDao<T>) daoMap.get(clazz);
    }
}
