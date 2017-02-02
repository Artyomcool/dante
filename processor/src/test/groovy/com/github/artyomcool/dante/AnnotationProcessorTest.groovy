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
import java.util.concurrent.Callable

@SuppressWarnings(["GroovyAssignabilityCheck", "GroovyAccessibility"])
@RunWith(AptRunner)
class AnnotationProcessorTest extends AbstractAptTest {

    SQLiteDatabase database

    @Before
    void init() {
        database = SqlHelper.createInMemory()
        DaoMaster.metaClass.loadClass = { def name ->
            delegate.delegate.class.classLoader.loadClass(name)
        }
        DaoMaster.metaClass.dao = { def name ->
            def result = dao(loadClass(name))
            result.class.metaClass.newInstance = {
                loadClass(name).newInstance()
            }
            result
        }
        DaoMaster.metaClass.queries = { def name ->
            queries(loadClass(name))
        }
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
    void insert() {
        def dao = simplestDao()

        dao.insert(dao.newInstance())

        assert dao.selectUnique('') != null
    }

    @Test
    void insertBatch() {
        def dao = simplestDao()

        dao.insert(Arrays.asList(dao.newInstance(), dao.newInstance()))

        assert dao.selectList('').size() == 2
        assert dao.selectList('').collect {it.id} == [1, 2]
    }

    @Test
    void update() {
        def t = [
                fullClassName: "test.T",
                sourceFile: """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                    String text;

                }
            """
        ]

        def daoT = dao(generateRegistry([t]))

        def e = daoT.newInstance()
        e.text = '1'
        daoT.insert(e)
        e.text = '2'
        daoT.update(e)
        e.text = '3'

        daoT = dao(generateRegistry([t]))

        assert daoT.selectUnique('').text == '2'
    }

    @Test
    void updateBatch() {
        def t = [
                fullClassName: "test.T",
                sourceFile: """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                    String text;

                }
            """
        ]

        def daoT = dao(generateRegistry([t]))

        def e = [daoT.newInstance(), daoT.newInstance()]
        e[0].text = '01'
        e[1].text = '11'
        daoT.insert(e)
        e[0].text = '02'
        e[1].text = '12'
        daoT.update(e)
        e[0].text = '03'
        e[1].text = '13'

        daoT = dao(generateRegistry([t]))

        assert daoT.selectList('').collect({it.text}) == ['02', '12']
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

        def testQueryClass = master.loadClass('test.T$TestQuery')
        def queries = registry.queries(testQueryClass)
        assert testQueryClass.isAssignableFrom(queries.class)
    }

    @Test
    void rxQuery() {
        DaoRegistry registry = generateRegistry([[
                                                         fullClassName: "test.T",
                                                         sourceFile: """
                package test;

                import java.util.List;
                import rx.Observable;
                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;
                    String text;

                    @Queries(T.class)
                    public interface TestQuery {

                        @Query(where = "id=\$id")
                        Observable<T> byId(long id);

                        @Query(where = "id > \$fromId")
                        Observable<List<T>> byTexts(long fromId);

                    }

                }
            """
                                                 ]])
        DaoMaster master = new DaoMaster({database}, registry)
        master.init()

        def testQueryClass = master.loadClass('test.T$TestQuery')
        def queries = master.queries('test.T$TestQuery')
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
        DaoMaster master = master(registry)

        def queries = master.queries('test.T$TestQuery')

        def dao = master.dao('test.T')
        def e = dao.newInstance()
        e.id = 1
        dao.insert(e)

        assert queries.byId(7) == null

        e = dao.newInstance()
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
        DaoMaster master = master(registry)

        def dao = master.dao('test.T')

        def inserted = []

        (1..10).each {
            def e = dao.newInstance()
            e.id = it
            e.text = "text $it"
            dao.insert(e)
            inserted << e
        }

        def queries = master.queries('test.T$TestQuery')
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
        DaoMaster master = master(registry)

        def dao = master.dao('test.T')

        def inserted = []

        (1..10).each {
            def e = dao.newInstance()
            e.id = it
            e.value = it * 10
            dao.insert(e)
            inserted << e
        }

        def queries = master.queries('test.T$TestQuery')
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
        DaoMaster master = master(registry)

        def dao = master.dao('test.T')

        def inserted = []

        (1..10).each {
            def e = dao.newInstance()
            e.id = it
            e.value = it
            dao.insert(e)
            inserted << e
        }

        def queries = master.queries('test.T$TestQuery')
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
        DaoMaster master = master(registry)

        def queries = master.queries('test.T$TestQuery')

        def dao = master.dao('test.T')
        def e = dao.newInstance()
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
        DaoMaster master = master(registry)

        def queries = master.queries('test.T$TestQuery')

        def dao = master.dao('test.T')
        def e = dao.newInstance()
        e.id = 'str'
        dao.insert(e)

        def q1 = queries.byId('str')
        def q2 = queries.byId('str')

        assert q1.is(q2) && q1.is(e)
    }

    @Test
    void delete() {
        def dao = simplestDao()
        def e = dao.newInstance()
        e.id = 7

        dao.insert(e)
        dao.delete(e)

        assert dao.selectList('').isEmpty()
    }

    @Test
    void deleteString() {
        def t = [
                fullClassName: "test.T",
                sourceFile: """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id(iWillSetIdByMySelf = true)
                    String text;

                }
            """
        ]

        def dao = dao(generateRegistry([t]))
        def e = dao.newInstance()
        e.text = '7'

        dao.insert(e)
        dao.delete(e)

        assert dao.selectList('').isEmpty()
    }

    @Test
    void clear() {
        def dao = simplestDao()
        def e = dao.newInstance()
        e.id = 7

        dao.insert(e)
        dao.clear()

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

        def master = master(registry)
        def dao = master.dao('test.T')
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

        def dao = dao(registry)
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

        def dao = dao(registry)

        dao.insert(dao.newInstance())

        def t1 = dao.newInstance()
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
        master(registry)

        assert database.version == 1

        registry = generateRegistry([t1, t2])
        def daoMaster = master(registry)
        assert database.version == 2

        def dao1 = daoMaster.dao('test.T1')
        def dao2 = daoMaster.dao('test.T2')

        def e1 = dao1.newInstance()
        dao1.insert(e1)

        def e2 = dao2.newInstance()
        dao2.insert(e2)

        assert dao1.selectUnique('') == e1
        assert dao2.selectUnique('') == e2

        assert currentDbVersion(daoMaster) == 2
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
        def daoT = dao(registry)

        assert database.version == 1

        def e1 = daoT.newInstance()
        daoT.insert(e1)
        assert e1.id

        registry = generateRegistry([t2])
        def daoMaster = master(registry)
        daoT = daoMaster.dao('test.T')
        assert database.version == 2

        def e2 = daoT.selectUnique('')
        assert e2.id == e1.id
        assert !e2.newField

        assert currentDbVersion(daoMaster) == 2
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
        def daoT = dao(registry)

        assert database.version == 1

        def e1 = daoT.newInstance()
        daoT.insert(e1)
        assert e1.id

        registry = generateRegistry([t2])
        def daoMaster = master(registry)
        daoT = daoMaster.dao('test.T')
        assert database.version == 2

        def e2 = daoT.selectUnique('')
        assert e2.id == e1.id
        assert e2.newField == 0
        assert e2.newField2 == 0
        assert e2.newField3 == null

        assert currentDbVersion(daoMaster) == 2
    }

    @Test
    void upgradeCustomMigration() {
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

                }
            """
        ]

        def migration = [
                fullClassName: "test.M",
                sourceFile   : """
                package test;

                import android.database.sqlite.*;
                import com.github.artyomcool.dante.annotation.*;

                @Migration
                public class M {

                    private static int dbVersion = -1;

                    @Migration.OnVersion(2)
                    public void initWithCount(SQLiteDatabase db) {
                        dbVersion = db.getVersion();
                    }

                }
            """
        ]

        def registry = generateRegistry([t1])
        def dao = dao(registry)

        assert database.version == 1

        def e1 = dao.newInstance()
        dao.insert(e1)
        assert e1.id

        registry = generateRegistry([t2, migration])
        def daoMaster = master(registry)
        assert database.version == 2

        assert daoMaster.loadClass('test.M').dbVersion == 1

        def e2 = daoMaster.dao('test.T').selectUnique('')
        assert e2.id == e1.id
        assert e2.newField == 0

        assert currentDbVersion(daoMaster) == 2
    }

    @Test
    void downgrade() {
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

        def t2Down = [
                fullClassName: "test.T2",
                sourceFile   : """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T2 {

                    @Id
                    Long id;

                }
            """
        ]

        def registry = generateRegistry([t1])
        master(registry)

        assert database.version == 1

        registry = generateRegistry([t1, t2])
        def daoMaster = master(registry)
        assert database.version == 2

        def dao1 = daoMaster.dao('test.T1')
        def dao2 = daoMaster.dao('test.T2')

        def e1 = dao1.newInstance()
        dao1.insert(e1)

        def e2 = dao2.newInstance()
        dao2.insert(e2)

        registry = generateRegistry([t1, t2Down])
        daoMaster = master(registry)
        assert database.version == 1

        dao1 = daoMaster.dao('test.T1')
        dao2 = daoMaster.dao('test.T2')

        assert dao1.selectList("").isEmpty()
        assert dao2.selectList("").isEmpty()
    }

    private static currentDbVersion(def daoMaster) {
        daoMaster.loadClass('com.github.artyomcool.dante.DefaultRegistry').CURRENT_DB_VERSION
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

        Dao dao = dao(registry)

        def entity = dao.newInstance()
        try {
            dao.insert(entity)
            throw new IllegalStateException('Should be aborted due to constraint violation')
        } catch (SQLException ignored) {
            //expected
        }

        entity.text = 'test'
        dao.insert(entity)
    }

    @Test
    void simpleIndex() {
        DaoRegistry registry = generateRegistry([[
            fullClassName: "test.T",
            sourceFile   : """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                    @Index
                    String text;

                }
            """
        ]])

        master(registry)

        Cursor cursor = database.rawQuery("PRAGMA index_info('IDX_TEXT')", null)
        assert cursor.moveToNext()
    }

    @Test
    void indexSince() {
        DaoRegistry t1 = generateRegistry([[
            fullClassName: "test.T",
            sourceFile   : """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                    String text;

                }
            """
        ]])
        DaoRegistry t2 = generateRegistry([[
            fullClassName: "test.T",
            sourceFile   : """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                    @Index(sinceVersion = 2)
                    String text;

                }
            """
        ]])

        master(t1)

        try {
            database.rawQuery("PRAGMA index_info('IDX_TEXT')", null)
            throw new IllegalStateException('Should be aborted due to no results')
        } catch (SQLException ignored) {
            //expected
        }

        def daoMaster = master(t2)
        database.rawQuery("PRAGMA index_info('IDX_TEXT')", null)

        assert currentDbVersion(daoMaster) == 2
    }

    @Test
    void customIndexName() {
        DaoRegistry registry = generateRegistry([[
            fullClassName: "test.T",
            sourceFile   : """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                    @Index(name = "CUSTOM_TEXT")
                    String text;

                }
            """
        ]])

        master(registry)

        Cursor cursor = database.rawQuery("PRAGMA index_info('CUSTOM_TEXT')", null)
        assert cursor.moveToNext()
    }

    @Test
    void runInTxSuccess() {
        def dao = simplestDao()

        dao.runInTx(new Runnable() {
            @Override
            void run() {
                dao.insert(dao.newInstance())
            }
        })

        assert dao.selectUnique('') != null
    }

    @Test
    void runInTxFail() {
        def dao = simplestDao()
        try {
            dao.runInTx(new Runnable() {
                @Override
                void run() {
                    dao.insert(dao.newInstance())
                    throw new RuntimeException("Expected")
                }
            })
        } catch (RuntimeException e) {
            assert e.message == "Expected"
        }

        assert dao.selectUnique('') == null
    }

    @Test
    void callInTxSuccess() {
        def dao = simplestDao()

        def result = dao.callInTx(new Callable() {
            @Override
            Object call() throws Exception {
                dao.insert(dao.newInstance())
                return 'result'
            }
        })

        assert result == 'result'
        assert dao.selectUnique('') != null
    }

    @Test
    void callInTxFail() {
        def dao = simplestDao()
        try {
            dao.callInTx(new Callable() {
                @Override
                Object call() throws Exception {
                    dao.insert(dao.newInstance())
                    throw new RuntimeException("Expected")
                }
            })
        } catch (RuntimeException e) {
            assert e.message == "Expected"
        }

        assert dao.selectUnique('') == null
    }

    @Test
    void extension() {
        def r = [
        [
            fullClassName: "test.B",
            sourceFile   : """
                package test;

                import java.util.List;
                import com.github.artyomcool.dante.annotation.*;

                public class B {

                    @Id
                    public Long id;
                    public String text;

                }
            """
        ],
        [
            fullClassName: "test.T",
            sourceFile   : """
                package test;

                import java.util.List;
                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T extends B {

                    public int someNewData;

                }
            """
        ]]
        DaoRegistry registry = generateRegistry(r)

        def dao1 = dao(registry)
        def t = dao1.newInstance()
        t.someNewData = 7
        t.text = 'test'

        dao1.insert(t)

        registry = generateRegistry(r)
        def dao2 = dao(registry)
        def t2 = dao2.selectUnique('')

        assert t != t2
        assert t.id == t2.id
        assert t.text == t2.text
        assert t.someNewData == t2.someNewData
    }

    private DaoMaster master(DaoRegistry registry) {
        def result = new DaoMaster({database}, registry)
        result.init()

        result
    }

    private Dao dao(DaoRegistry registry) {
        return master(registry).dao('test.T')
    }

    private Dao simplestDao() {
        dao(simplestRegistry())
    }

    private DaoRegistry simplestRegistry() {
        generateRegistry([[
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
        ]])
    }

}
