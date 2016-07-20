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

package com.github.artyomcool.dante

import android.content.ContentResolver
import android.database.CharArrayBuffer
import android.database.ContentObserver
import android.database.Cursor
import android.database.DataSetObserver
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteStatement
import android.net.Uri
import android.os.Bundle
import com.sun.rowset.CachedRowSetImpl
import groovy.sql.Sql

import java.sql.SQLException
import java.sql.Types

import static android.database.Cursor.*

class SqlHelper {

    static SQLiteDatabase createInMemory() {

        def sql = Sql.newInstance(url: 'jdbc:sqlite::memory:', driver: 'org.sqlite.JDBC')

        def dbVersion = 0;

        return [
                rawQuery        : { String where, String[] params ->
                    CachedRowSetImpl resultSet = new CachedRowSetImpl()
                    sql.query(where, params as List<String>) {
                        resultSet.populate(it)
                    }
                    return new Cursor() {
                        @Override
                        int getCount() {
                            int row = resultSet.row
                            resultSet.last()
                            int result = resultSet.row
                            if (row == 0) {
                                resultSet.beforeFirst()
                            } else {
                                resultSet.absolute(row)
                            }
                            return result
                        }

                        @Override
                        int getPosition() {
                            return resultSet.row - 1
                        }

                        @Override
                        boolean move(int offset) {
                            return resultSet.relative(offset)
                        }

                        @Override
                        boolean moveToPosition(int position) {
                            return resultSet.absolute(position + 1)
                        }

                        @Override
                        boolean moveToFirst() {
                            return resultSet.first()
                        }

                        @Override
                        boolean moveToLast() {
                            return resultSet.last()
                        }

                        @Override
                        boolean moveToNext() {
                            return resultSet.next()
                        }

                        @Override
                        boolean moveToPrevious() {
                            return resultSet.previous()
                        }

                        @Override
                        boolean isFirst() {
                            return resultSet.first
                        }

                        @Override
                        boolean isLast() {
                            return resultSet.last
                        }

                        @Override
                        boolean isBeforeFirst() {
                            return resultSet.beforeFirst
                        }

                        @Override
                        boolean isAfterLast() {
                            return resultSet.afterLast
                        }

                        @Override
                        int getColumnIndex(String columnName) {
                            try {
                                return resultSet.findColumn(columnName) - 1
                            } catch (SQLException ignored) {
                                return -1
                            }
                        }

                        @Override
                        int getColumnIndexOrThrow(String columnName) throws IllegalArgumentException {
                            try {
                                return resultSet.findColumn(columnName) - 1
                            } catch (SQLException e) {
                                throw new IllegalArgumentException(e)
                            }
                        }

                        @Override
                        String getColumnName(int columnIndex) {
                            return resultSet.metaData.getColumnName(columnIndex + 1)
                        }

                        @Override
                        String[] getColumnNames() {
                            (1..getColumnCount()).collect { getColumnName(it) }
                        }

                        @Override
                        int getColumnCount() {
                            return resultSet.metaData.columnCount
                        }

                        @Override
                        byte[] getBlob(int columnIndex) {
                            return resultSet.getBlob(columnIndex + 1).binaryStream.bytes
                        }

                        @Override
                        String getString(int columnIndex) {
                            return resultSet.getString(columnIndex + 1)
                        }

                        @Override
                        void copyStringToBuffer(int columnIndex, CharArrayBuffer buffer) {
                            throw new UnsupportedOperationException()
                        }

                        @Override
                        short getShort(int columnIndex) {
                            return resultSet.getShort(columnIndex + 1)
                        }

                        @Override
                        int getInt(int columnIndex) {
                            return resultSet.getInt(columnIndex + 1)
                        }

                        @Override
                        long getLong(int columnIndex) {
                            return resultSet.getLong(columnIndex + 1)
                        }

                        @Override
                        float getFloat(int columnIndex) {
                            return resultSet.getFloat(columnIndex + 1)
                        }

                        @Override
                        double getDouble(int columnIndex) {
                            return resultSet.getDouble(columnIndex + 1)
                        }

                        @Override
                        int getType(int columnIndex) {
                            def type = resultSet.metaData.getColumnType(columnIndex + 1)
                            switch (type) {
                                case Types.BIGINT:
                                case Types.TINYINT:
                                case Types.SMALLINT:
                                case Types.INTEGER:
                                    return FIELD_TYPE_INTEGER
                                case Types.FLOAT:
                                case Types.REAL:
                                case Types.DOUBLE:
                                    return FIELD_TYPE_FLOAT
                                case Types.VARCHAR:
                                    return FIELD_TYPE_STRING
                                case Types.BLOB:
                                    return FIELD_TYPE_BLOB
                                case Types.NULL:
                                    return FIELD_TYPE_NULL
                            }
                            throw new IllegalArgumentException("Unknown type: $type")
                        }

                        @Override
                        boolean isNull(int columnIndex) {
                            return resultSet.getObject(columnIndex + 1) == null
                        }

                        @Override
                        void deactivate() {
                        }

                        @Override
                        boolean requery() {
                            return true
                        }

                        @Override
                        void close() {
                            resultSet.close()
                        }

                        @Override
                        boolean isClosed() {
                            return resultSet.isClosed()
                        }

                        @Override
                        void registerContentObserver(ContentObserver observer) {
                            throw new UnsupportedOperationException()
                        }

                        @Override
                        void unregisterContentObserver(ContentObserver observer) {
                            throw new UnsupportedOperationException()
                        }

                        @Override
                        void registerDataSetObserver(DataSetObserver observer) {
                            throw new UnsupportedOperationException()
                        }

                        @Override
                        void unregisterDataSetObserver(DataSetObserver observer) {
                            throw new UnsupportedOperationException()
                        }

                        @Override
                        void setNotificationUri(ContentResolver cr, Uri uri) {
                            throw new UnsupportedOperationException()
                        }

                        @Override
                        boolean getWantsAllOnMoveCalls() {
                            throw new UnsupportedOperationException()
                        }

                        @Override
                        Bundle getExtras() {
                            throw new UnsupportedOperationException()
                        }

                        @Override
                        Bundle respond(Bundle extras) {
                            throw new UnsupportedOperationException()
                        }
                    }
                },
                execSQL         : { String s ->
                    sql.execute(s)
                },
                compileStatement: { String s ->
                    def params = []
                    return [
                            execute: {
                                sql.execute(s, params)
                            },
                            executeInsert: {
                                sql.executeInsert(s, params)[0][0]  //TODO not true for already defined id
                            },
                            bindNull     : { int index ->
                                params[index - 1] = null
                            },
                            bindLong     : { int index, long value ->
                                params[index - 1] = value
                            },
                            bindDouble   : { int index, double value ->
                                params[index - 1] = value
                            },
                            bindString   : { int index, String value ->
                                if (value == null) {
                                    throw new IllegalArgumentException("the bind value at index " + index + " is null");
                                }
                                params[index - 1] = value
                            },
                            bindBlob     : { int index, byte[] value ->
                                if (value == null) {
                                    throw new IllegalArgumentException("the bind value at index " + index + " is null");
                                }
                                params[index - 1] = value
                            }
                    ] as SQLiteStatement
                },
                getVersion : {
                    dbVersion
                },
                setVersion: {
                    dbVersion = it
                },
                beginTransaction: {},
                setTransactionSuccessful: {},
                endTransaction: {}
        ] as SQLiteDatabase
    }

}
