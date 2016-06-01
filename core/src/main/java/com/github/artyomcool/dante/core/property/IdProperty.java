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

package com.github.artyomcool.dante.core.property;

import android.database.sqlite.SQLiteStatement;

public abstract class IdProperty<E> extends DelegatingProperty<E> {

    private final boolean autoincrement;

    protected IdProperty(Property<E> delegate, boolean preventAutoincrement) {
        super(delegate);
        this.autoincrement = !preventAutoincrement && isInteger(delegate);
    }

    @Override
    public void bind(SQLiteStatement statement, int index, E entity) {
        if (isNull(entity)) {
            statement.bindNull(index);
        } else {
            super.bind(statement, index, entity);
        }
    }

    @Override
    public String getColumnExtraDefinition() {
        String result = "PRIMARY KEY";
        if (autoincrement) {
            result += " AUTOINCREMENT";
        }
        return result;
    }

    @Override
    public String getDefaultValue() {
        return null;
    }

    protected abstract boolean isNull(E entity);

    public abstract void afterInsert(E entity, long rowId);

    private static boolean isInteger(Property<?> property) {
        return property.getColumnType().equals(IntegerNumbersProperty.TYPE);
    }

}
