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
import com.github.artyomcool.dante.core.dao.Dao
import com.github.artyomcool.dante.core.dao.DaoMaster
import com.github.artyomcool.dante.core.dao.DaoRegistry
import com.github.artyomcool.dante.core.property.DelegatingProperty
import com.github.artyomcool.dante.core.property.IdProperty
import com.github.artyomcool.dante.core.property.Property
import com.sun.rowset.CachedRowSetImpl
import groovy.sql.Sql
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.tests.utils.impl.AptRunner

import java.sql.SQLException
import java.sql.Types

@SuppressWarnings(["GroovyAssignabilityCheck", "GroovyAccessibility"])
@RunWith(AptRunner)
class AnnotationProcessorTest extends AbstractAptTest {

    SQLiteDatabase database

    @Before
    void init() {
        def sql = Sql.newInstance(url: 'jdbc:sqlite::memory:', driver: 'org.sqlite.JDBC')

        def dbVersion = 0;

        database = [
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
                            executeInsert: {
                                sql.executeInsert(s, params)[0][0]  //TODO not true for already defined id
                            },
                            bindNull     : { int index ->
                                params[index] = null
                            },
                            bindLong     : { int index, long value ->
                                params[index] = value
                            },
                            bindDouble   : { int index, double value ->
                                params[index] = value
                            },
                            bindString   : { int index, String value ->
                                params[index] = value
                            },
                            bindBlob     : { int index, byte[] value ->
                                params[index] = value
                            }
                    ] as SQLiteStatement
                },
                getVersion : {
                    dbVersion
                },
                setVersion: {
                    dbVersion = it
                }
        ] as SQLiteDatabase
    }

    DaoRegistry justId(def idField) {
        def registry = generateRegistry([[
             fullClassName: "test.T",
             sourceFile   : """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    """ + idField + """;

                }
            """
        ]])
        registry.init(database)
        assert registry.dao.size() == 1
        assert registry.dao[0].getProperties().size() == 1

        return registry
    }

    def verifyIntegerRegistry(DaoRegistry registry) {
        Dao<?> dao = registry.dao[0]
        def entity = dao.createEntity()

        IdProperty<?> property = dao.getIdProperty()
        assert property.getColumnType() == 'INTEGER'

        Property<?> delegate = getField(getField(property, 'delegate'), 'delegate')

        try {
            def result = delegate.get(entity)
            throw new IllegalStateException("Should be NPE, but result returned: " + result)
        } catch (NullPointerException ignored) {
            //expected
        }

        assert property.isNull(entity)
        entity.id = 123
        assert !property.isNull(entity)
        assert delegate.get(entity) == 123
    }

    private static Object getField(def obj, def name) {
        def field = DelegatingProperty.declaredFields.find { it.name == name }
        field.setAccessible(true)
        field.get(obj)
    }

    @Test
    void justIdLong() {
        def registry = justId('@Id Long id')
        verifyIntegerRegistry(registry)
    }

    @Test
    void justIdInteger() {
        def registry = justId('@Id Integer id')
        verifyIntegerRegistry(registry)
    }

    @Test
    void justIdShort() {
        def registry = justId('@Id Short id')
        verifyIntegerRegistry(registry)
    }

    @Test
    void justIdByte() {
        def registry = justId('@Id Byte id')
        verifyIntegerRegistry(registry)
    }

    @Test
    void justIdBoolean() {
        def registry = justId('@Id Boolean id')
        //TODO verify boolean registry
    }

    @Test
    void justIdString() {
        def registry = justId('@Id(iWillSetIdByMySelf = true) String id')

        Dao<?> dao = registry.dao[0]
        def entity = dao.createEntity()

        IdProperty<?> idProperty = dao.getIdProperty()
        assert idProperty.getColumnType() == 'TEXT'

        assert idProperty == dao.getProperties()[0]

        assert idProperty.getColumnType() == 'TEXT'

        Property delegate = getField(idProperty, 'delegate')
        assert delegate.get(entity) == null
        entity.id = '123'
        assert delegate.get(entity) == '123'
    }

    @Ignore("byte[] fields are not supported yet")
    @Test
    void justIdBlob() {
        def registry = justId('@Id(iWillSetIdByMySelf = true) byte[] id')
    }

    @Test
    void simpleQuery() {
        DaoRegistry registry = generateRegistry([[
             fullClassName: "test.T",
             sourceFile: """
                package test;

                import java.util.List;
                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;
                    String text;

                    @Queries(T.class)
                    public interface TestQuery {

                        @Query(where = "id=\$id")
                        List<T> byId(long id);

                        @Query(where = "id > \$fromId AND text = \$text")
                        List<T> byTexts(String text, long fromId);

                    }

                }
            """
         ]])
        DaoMaster master = new DaoMaster(registry)
        master.init(database)

        def testQueryClass = registry.class.classLoader.loadClass("test.T\$TestQuery")
        def queries = registry.queries(testQueryClass)
        assert testQueryClass.isAssignableFrom(queries.class)
    }

    @Test
    void complex() {
        DaoRegistry registry = generateRegistry([[
            fullClassName: "test.T",
            sourceFile: """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;
                    String text;
                    long aLong;
                    Long aLong2;
                    Integer integer;
                    int integer2;
                    short aShort;
                    Short aShort2;
                    byte aByte;
                    Byte aByte2;
                    boolean aBoolean;
                    Boolean aBoolean2;
                    //TODO byte[] bytes;

                }
            """
        ]])

        DaoMaster master = new DaoMaster(registry)

        master.init(database)

        def dao = registry.dao[0]

        dao.insert(dao.createEntity())

        def t1 = dao.createEntity()
        t1.integer2 = 7
        dao.insert(t1)

        Cursor cursor = dao.select("where integer2 = 7")
        assert cursor.moveToNext()
        assert cursor.getInt(cursor.getColumnIndex("INTEGER2")) == 7
        assert !cursor.moveToNext()

        cursor = dao.select('')
        assert cursor.moveToNext()
        assert cursor.getInt(cursor.getColumnIndex("INTEGER2")) == 0
        assert cursor.moveToNext()
        assert cursor.getInt(cursor.getColumnIndex("INTEGER2")) == 7
        assert !cursor.moveToNext()
    }

    @Test
    void upgradeAddEntity() {
        def t1 = [
            fullClassName: "test.T1",
            sourceFile   : """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T1 {

                    @Id
                    Long id;

                }
            """
        ]

        def t2 = [
            fullClassName: "test.T2",
            sourceFile   : """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity(sinceVersion = 2)
                public class T2 {

                    @Id
                    Long id;

                }
            """
        ]

        def registry = generateRegistry([t1])
        DaoMaster master = new DaoMaster(registry)
        master.init(database)
        assert database.version == 1


        registry = generateRegistry([t1, t2])
        master = new DaoMaster(registry)
        master.init(database)
        assert database.version == 2

        def e1 = registry.dao[0].createEntity()
        registry.dao[0].insert(e1)

        def e2 = registry.dao[1].createEntity()
        registry.dao[1].insert(e2)

        assert registry.dao[0].selectUnique('') == e1
        assert registry.dao[1].selectUnique('') == e2
    }

}
