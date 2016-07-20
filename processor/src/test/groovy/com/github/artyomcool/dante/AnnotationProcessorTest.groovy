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

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.github.artyomcool.dante.core.Property
import com.github.artyomcool.dante.core.dao.Dao
import com.github.artyomcool.dante.core.dao.DaoMaster
import com.github.artyomcool.dante.core.dao.DaoRegistry
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.tests.utils.impl.AptRunner

import java.sql.SQLException

@SuppressWarnings(["GroovyAssignabilityCheck", "GroovyAccessibility"])
@RunWith(AptRunner)
class AnnotationProcessorTest extends AbstractAptTest {

    SQLiteDatabase database

    @Before
    void init() {
        database = SqlHelper.createInMemory()
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

        Property property = dao.idProperty
        assert property.columnType == 'INTEGER'
        assert property.columnName == 'ID'
    }

    @Test
    void justIdLong() {
        def registry = justId('@Id Long id')
        verifyIntegerRegistry(registry)
    }

    @Test
    void treatZeroAsNullIdLong() {
        def registry = justId('@Id(treatZeroAsNull = true) long id')
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

    @Ignore("Cache for booleans is not implemented yet")
    @Test
    void justIdBoolean() {
        def registry = justId('@Id Boolean id')
        //TODO verify boolean registry
    }

    @Test
    void justIdString() {
        def registry = justId('@Id(iWillSetIdByMySelf = true) String strId')

        Dao<?> dao = registry.dao[0]

        Property idProperty = dao.getIdProperty()
        assert idProperty.columnType == 'TEXT'
        assert idProperty.columnName == 'STR_ID'
    }

    @Ignore("Cache for blobs is not implemented yet")
    @Test
    void justIdBlob() {
        def registry = justId('@Id(iWillSetIdByMySelf = true) byte[] id')
        //TODO verify blob
    }

    @Test(expected = RuntimeException)
    void failNoId() {
        generateRegistry([[
            fullClassName: "test.T",
            sourceFile: """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {
                }
            """
        ]])
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

                        @Query(where = "text = \$text LIMIT \$limit")
                        List<T> byTextWithLimit(String text, int limit);

                    }

                }
            """
         ]])
        DaoMaster master = new DaoMaster({database}, registry)
        master.init()

        def testQueryClass = registry.class.classLoader.loadClass('test.T$TestQuery')
        def queries = registry.queries(testQueryClass)
        assert testQueryClass.isAssignableFrom(queries.class)
    }

    @Test
    void querySingleItem() {
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

                    @Queries(T.class)
                    public interface TestQuery {

                        @Query(where = "id = \$id")
                        T byId(long id);

                    }

                }
            """
        ]])
        DaoMaster master = new DaoMaster({database}, registry)
        master.init()

        def testClass = registry.class.classLoader.loadClass('test.T');

        def testQueryClass = registry.class.classLoader.loadClass('test.T$TestQuery')
        def queries = registry.queries(testQueryClass)

        def dao = registry.dao[0]
        def e = testClass.newInstance()
        e.id = 1
        dao.insert(e)

        assert queries.byId(7) == null

        e = testClass.newInstance()
        e.id = 7
        dao.insert(e)

        assert queries.byId(7) == e
    }

    @Test
    void queryWithLimit() {
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

                        @Query(where = "text LIKE \$text LIMIT \$limit")
                        List<T> byTextWithLimit(String text, int limit);

                    }

                }
            """
        ]])
        DaoMaster master = new DaoMaster({database}, registry)
        master.init()

        def testClass = registry.class.classLoader.loadClass('test.T')

        def dao = registry.dao[0]

        def inserted = []

        (1..10).each {
            def e = testClass.newInstance()
            e.id = it
            e.text = "text $it"
            dao.insert(e)
            inserted << e
        }

        def testQueryClass = registry.class.classLoader.loadClass('test.T$TestQuery')
        def queries = registry.queries(testQueryClass)
        List result = queries.byTextWithLimit('text %', 7)

        assert result == inserted[0..6]
    }

    @Test
    void queryWithExpression() {
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
                    int value;

                    @Queries(T.class)
                    public interface TestQuery {

                        @Query(where = "value > \$[a + b]")
                        List<T> greaterThenSum(int a, int b);

                    }

                }
            """
        ]])
        DaoMaster master = new DaoMaster({database}, registry)
        master.init()
        def testClass = registry.class.classLoader.loadClass('test.T')

        def dao = registry.dao[0]

        def inserted = []

        (1..10).each {
            def e = testClass.newInstance()
            e.id = it
            e.value = it * 10
            dao.insert(e)
            inserted << e
        }

        def testQueryClass = registry.class.classLoader.loadClass('test.T$TestQuery')
        def queries = registry.queries(testQueryClass)
        List result = queries.greaterThenSum(10, 20)

        assert result.size() == 7
    }

    @Test
    void queryWithFields() {
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
                    int value;

                    @Queries(T.class)
                    public static abstract class TestQuery {

                        int field = 0;

                        @Query(where = "value > \$[a + (this.field++)]")
                        public abstract List<T> greaterThenAPlusField(int a);

                    }

                }
            """
         ]])
        DaoMaster master = new DaoMaster({database}, registry)
        master.init()
        def testClass = registry.class.classLoader.loadClass('test.T')

        def dao = registry.dao[0]

        def inserted = []

        (1..10).each {
            def e = testClass.newInstance()
            e.id = it
            e.value = it
            dao.insert(e)
            inserted << e
        }

        def testQueryClass = registry.class.classLoader.loadClass('test.T$TestQuery')
        def queries = registry.queries(testQueryClass)
        (0..5).each {
            List result = queries.greaterThenAPlusField(5)
            assert result.size() == (5 - it)
        }

    }

    @Test
    void integerIdentity() {
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

                    @Queries(T.class)
                    public interface TestQuery {

                        @Query(where = "id = \$id")
                        T byId(long id);

                    }

                }
            """
        ]])
        DaoMaster master = new DaoMaster({database}, registry)
        master.init()
        def testClass = registry.class.classLoader.loadClass('test.T')

        def testQueryClass = registry.class.classLoader.loadClass('test.T$TestQuery')
        def queries = registry.queries(testQueryClass)

        def dao = registry.dao[0]
        def e = testClass.newInstance()
        e.id = 1
        dao.insert(e)

        def q1 = queries.byId(1)
        def q2 = queries.byId(1)

        assert q1.is(q2) && q1.is(e)
    }

    @Test
    void stringIdentity() {
        DaoRegistry registry = generateRegistry([[
            fullClassName: "test.T",
            sourceFile: """
                package test;

                import java.util.List;
                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id(iWillSetIdByMySelf = true)
                    String id;

                    @Queries(T.class)
                    public interface TestQuery {

                        @Query(where = "id = \$id")
                        T byId(String id);

                    }

                }
            """
        ]])
        DaoMaster master = new DaoMaster({database}, registry)
        master.init()
        def testClass = registry.class.classLoader.loadClass('test.T')

        def testQueryClass = registry.class.classLoader.loadClass('test.T$TestQuery')
        def queries = registry.queries(testQueryClass)

        def dao = registry.dao[0]
        def e = testClass.newInstance()
        e.id = 'str'
        dao.insert(e)

        def q1 = queries.byId('str')
        def q2 = queries.byId('str')

        assert q1.is(q2) && q1.is(e)
    }

    @Test
    void delete() {
        DaoRegistry registry = generateRegistry([[
            fullClassName: "test.T",
            sourceFile: """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {
                    @Id
                    Long id;
                }
            """
        ]])

        DaoMaster master = new DaoMaster({database}, registry)
        master.init()
        def testClass = registry.class.classLoader.loadClass('test.T')

        def dao = registry.dao[0]
        def e = testClass.newInstance()
        e.id = 7

        dao.insert(e)
        dao.delete(e)

        assert dao.selectList('').isEmpty()
    }

    @Test
    void entityWithCustomSpecificDao() {
        DaoRegistry registry = generateRegistry([
        [
                fullClassName: "test.SomeDao",
                sourceFile: """
                package test;

                import android.database.sqlite.SQLiteDatabase;
                import com.github.artyomcool.dante.core.dao.*;

                abstract class SomeDao extends Dao<T> {

                    protected SomeDao(SQLiteDatabase db, int sinceVersion) {
                        super(db, sinceVersion);
                    }

                    @Override
                    public int getSinceVersion() {
                        return 100;
                    }

                }
            """
        ],
        [
            fullClassName: "test.T",
            sourceFile: """
                package test;

                import java.util.List;
                import com.github.artyomcool.dante.annotation.*;

                @Entity(dao = SomeDao.class)
                public class T {

                    @Id
                    Long id;

                }
            """
        ]])
        DaoMaster master = new DaoMaster({database}, registry)
        master.init()

        def dao = registry.dao[0]
        assert dao.getSinceVersion() == 100
    }

    @Test
    void entityWithCustomGenericDao() {
        DaoRegistry registry = generateRegistry([
        [
            fullClassName: "test.SomeDao",
            sourceFile: """
                package test;

                import android.database.sqlite.SQLiteDatabase;
                import com.github.artyomcool.dante.core.dao.*;

                abstract class SomeDao<T> extends Dao<T> {

                    protected SomeDao(SQLiteDatabase db, int sinceVersion) {
                        super(db, sinceVersion);
                    }

                    @Override
                    public int getSinceVersion() {
                        return 100;
                    }

                }
            """
        ],
        [
            fullClassName: "test.T",
            sourceFile: """
                package test;

                import java.util.List;
                import com.github.artyomcool.dante.annotation.*;

                @Entity(dao = SomeDao.class)
                public class T {

                    @Id
                    Long id;

                }
            """
        ]])
        DaoMaster master = new DaoMaster({database}, registry)
        master.init()

        def dao = registry.dao[0]
        assert dao.getSinceVersion() == 100
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
                    float aFloat;
                    Float aFloat2;
                    double aDouble;
                    Double aDouble2;
                    char aChar;
                    Character aChar2;
                    byte[] bytes;

                }
            """
        ]])

        DaoMaster master = new DaoMaster({database}, registry)
        master.init()

        def testClass = registry.class.classLoader.loadClass('test.T')

        def dao = registry.dao[0]

        dao.insert(testClass.newInstance())

        def t1 = testClass.newInstance()
        t1.integer2 = 7
        dao.insert(t1)

        Cursor cursor = dao.select("integer2 = 7")
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
        DaoMaster master = new DaoMaster({database}, registry)
        master.init()

        assert database.version == 1

        registry = generateRegistry([t1, t2])
        master = new DaoMaster({database}, registry)
        master.init()
        assert database.version == 2

        def test1Class = registry.class.classLoader.loadClass('test.T1')
        def test2Class = registry.class.classLoader.loadClass('test.T2')

        def dao1 = registry.dao.find { it.getTableName() == 'T1' }
        def dao2 = registry.dao.find { it.getTableName() == 'T2' }

        def e1 = test1Class.newInstance()
        dao1.insert(e1)

        def e2 = test2Class.newInstance()
        dao2.insert(e2)

        assert dao1.selectUnique('') == e1
        assert dao2.selectUnique('') == e2
    }

    @Test
    void upgradeAddProperty() {
        def t1 = [
                fullClassName: "test.T",
                sourceFile   : """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                }
            """
        ]

        def t2 = [
                fullClassName: "test.T",
                sourceFile   : """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                    @SinceVersion(2)
                    Long newField;

                }
            """
        ]

        def registry = generateRegistry([t1])
        DaoMaster master = new DaoMaster({database}, registry)
        master.init()
        def testClass = registry.class.classLoader.loadClass('test.T')

        assert database.version == 1

        def e1 = testClass.newInstance()
        registry.dao[0].insert(e1)
        assert e1.id

        registry = generateRegistry([t2])
        master = new DaoMaster({database}, registry)
        master.init()
        assert database.version == 2

        def e2 = registry.dao[0].selectUnique('')
        assert e2.id == e1.id
        assert !e2.newField
    }

    @Test
    void upgradeAddNotNullProperty() {
        def t1 = [
                fullClassName: "test.T",
                sourceFile   : """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                }
            """
        ]

        def t2 = [
                fullClassName: "test.T",
                sourceFile   : """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                    @SinceVersion(value = 2)
                    long newField;

                    @SinceVersion(value = 2)
                    float newField2;

                    @SinceVersion(value = 2)
                    String newField3;

                }
            """
        ]

        def registry = generateRegistry([t1])
        DaoMaster master = new DaoMaster({database}, registry)
        master.init()
        def testClass = registry.class.classLoader.loadClass('test.T')

        assert database.version == 1

        def e1 = testClass.newInstance()
        registry.dao[0].insert(e1)
        assert e1.id

        registry = generateRegistry([t2])
        master = new DaoMaster({database}, registry)
        master.init()
        assert database.version == 2

        def e2 = registry.dao[0].selectUnique('')
        assert e2.id == e1.id
        assert e2.newField == 0
        assert e2.newField2 == 0
        assert e2.newField3 == null
    }

    @Test
    void notNullString() {
        DaoRegistry registry = generateRegistry([[
            fullClassName: "test.T",
            sourceFile: """
                package test;

                import com.github.artyomcool.dante.annotation.*;
                import javax.annotation.Nonnull;

                @Entity
                public class T {

                    @Id
                    Long id;
                    @Nonnull
                    String text;

                }
            """
        ]])

        DaoMaster master = new DaoMaster({database}, registry)
        master.init()
        def testClass = registry.class.classLoader.loadClass('test.T')

        def entity = testClass.newInstance()
        try {
            registry.dao[0].insert(entity)
            throw new IllegalStateException('Should be aborted due to constraint violation')
        } catch (SQLException ignored) {
            //expected
        }

        entity.text = 'test'
        registry.dao[0].insert(entity)
    }

}
