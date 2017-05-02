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
import com.github.artyomcool.dante.core.dao.Dao
import com.github.artyomcool.dante.core.dao.DaoMaster
import com.github.artyomcool.dante.core.dao.DaoRegistry
import org.junit.Test

import java.sql.SQLException
import java.util.concurrent.Callable

@SuppressWarnings(["GroovyAssignabilityCheck", "GroovyAccessibility"])
class MiscTest extends BaseDanteTest {

    @Test
    void clear() {
        def dao = simplestDao()
        def e = dao.newInstance()
        e.id = 7

        dao.insert(e)
        dao.clear()

        assert dao.selectAll().isEmpty()
    }

    @Test
    void entityWithCustomSpecificDao() {
        DaoRegistry registry = generateRegistry("""
                package test;

                import android.database.sqlite.SQLiteDatabase;
                import com.github.artyomcool.dante.core.dao.*;
                import com.github.artyomcool.dante.core.*;

                abstract class SomeDao extends Dao<T> {

                    protected SomeDao(SQLiteDatabase db, EntityInfo<T> info, int sinceVersion) {
                        super(db, info, sinceVersion);
                    }

                    @Override
                    public int getSinceVersion() {
                        return 100;
                    }

                }
            """,
                """
                package test;

                import java.util.List;
                import com.github.artyomcool.dante.annotation.*;

                @Entity(dao = SomeDao.class)
                public class T {

                    @Id
                    Long id;

                }
            """)

        def master = master(registry)
        def dao = master.dao('test.T')
        assert dao.getSinceVersion() == 100
    }

    @Test
    void entityWithCustomGenericDao() {
        DaoRegistry registry = generateRegistry("""
                package test;

                import android.database.sqlite.SQLiteDatabase;
                import com.github.artyomcool.dante.core.dao.*;
                import com.github.artyomcool.dante.core.*;

                abstract class SomeDao<T> extends Dao<T> {

                    protected SomeDao(SQLiteDatabase db, EntityInfo<T> info, int sinceVersion) {
                        super(db, info, sinceVersion);
                    }

                    @Override
                    public int getSinceVersion() {
                        return 100;
                    }

                }
            """,
                """
                package test;

                import java.util.List;
                import com.github.artyomcool.dante.annotation.*;

                @Entity(dao = SomeDao.class)
                public class T {

                    @Id
                    Long id;

                }
            """)

        def dao = dao(registry)
        assert dao.getSinceVersion() == 100
    }

    @Test
    void complex() {
        DaoRegistry registry = generateRegistry("""
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
            """)

        def dao = dao(registry)

        dao.insert(dao.newInstance())

        def t1 = dao.newInstance()
        t1.integer2 = 7
        dao.insert(t1)

        def all = dao.selectAll()
        assert all.size() == 2
        assert all[0].integer2 == 0
        assert all[1].integer2 == 7
    }


    @Test
    void extension() {
        def r = ["""
                package test;

                import java.util.List;
                import com.github.artyomcool.dante.annotation.*;

                public class B {

                    @Id
                    public Long id;
                    public String text;

                }
            """, """
                package test;

                import java.util.List;
                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T extends B {

                    public int someNewData;

                }
            """] as String[]
        DaoRegistry registry = generateRegistry(r)

        def dao1 = dao(registry)
        def t = dao1.newInstance()
        t.someNewData = 7
        t.text = 'test'

        dao1.insert(t)

        registry = generateRegistry(r)
        def dao2 = dao(registry)
        def t2 = dao2.selectAll()[0]

        assert t != t2
        assert t.id == t2.id
        assert t.text == t2.text
        assert t.someNewData == t2.someNewData
    }

}
