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

public class DaoMaster implements Registry {

    private final DaoRegistry delegate;

    public DaoMaster() {
        this(defaultRegistry());
    }

    public DaoMaster(DaoRegistry delegate) {
        this.delegate = delegate;
    }

    public void init(SQLiteDatabase db) {
        delegate.init(db);
    }

    @Override
    public <T> T queries(Class<T> clazz) {
        return delegate.queries(clazz);
    }

    @Override
    public <T> AbstractDao<T> dao(Class<T> clazz) {
        return delegate.dao(clazz);
    }

    //TODO explain what android version has problem with multi-catch
    @SuppressWarnings("TryWithIdenticalCatches")
    private static DaoRegistry defaultRegistry() {
        try {
            return (DaoRegistry) Class.forName("com.github.artyomcool.dante.DefaultRegistry").newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
