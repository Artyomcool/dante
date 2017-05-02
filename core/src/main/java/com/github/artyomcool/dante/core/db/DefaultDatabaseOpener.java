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

package com.github.artyomcool.dante.core.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Default implementation of {@link DatabaseOpener}.
 * Delegates {@link #open} to the {@link Context#openOrCreateDatabase(String, int, SQLiteDatabase.CursorFactory)}.
 */
public class DefaultDatabaseOpener implements DatabaseOpener {

    private final Context context;
    private final String name;

    /**
     * Creates DefaultDatabaseOpener. <b>Note:</b> context will be stored as is, so typically you want to provide
     * application context here.
     *
     * @param context context to perform open database
     * @param name    name of DB
     */
    public DefaultDatabaseOpener(Context context, String name) {
        this.context = context;
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SQLiteDatabase open() {
        return context.openOrCreateDatabase(name, SQLiteDatabase.OPEN_READWRITE, null);
    }
}
