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

package com.github.artyomcool.dante.core;

public final class Property {

    private final int sinceVersion;
    private final String columnName;
    private final String columnType;
    private final String columnExtraDefinition;
    private final String defaultValue;

    private Property(Builder builder) {
        this.sinceVersion = builder.sinceVersion;
        this.columnName = builder.columnName;
        this.columnType = builder.columnType;
        this.columnExtraDefinition = builder.columnExtraDefinition;
        this.defaultValue = builder.defaultValue;
    }

    public int getSinceVersion() {
        return sinceVersion;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public String getColumnExtraDefinition() {
        return columnExtraDefinition;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public static class Builder {
        private int sinceVersion = 1;
        private String columnName;
        private String columnType;
        private String columnExtraDefinition = "";
        private String defaultValue;

        public Builder sinceVersion(int sinceVersion) {
            this.sinceVersion = sinceVersion;
            return this;
        }

        public Builder columnName(String columnName) {
            this.columnName = columnName;
            return this;
        }

        public Builder columnType(String columnType) {
            this.columnType = columnType;
            return this;
        }

        public Builder columnExtraDefinition(String columnExtraDefinition) {
            this.columnExtraDefinition = columnExtraDefinition;
            return this;
        }

        public Builder defaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public Property build() {
            if (columnName == null) {
                throw new NullPointerException();
            }
            if (columnType == null) {
                throw new NullPointerException();
            }

            return new Property(this);
        }
    }
}
