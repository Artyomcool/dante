package com.github.artyomcool.dante

import android.database.Cursor
import com.github.artyomcool.dante.core.dao.Dao
import com.github.artyomcool.dante.core.dao.DaoRegistry
import org.junit.Test

import java.sql.SQLException

@SuppressWarnings(["GroovyAssignabilityCheck", "GroovyAccessibility"])
class IndexTest extends BaseDanteTest {

    @Test
    void simpleIndex() {
        DaoRegistry registry = generateRegistry("""
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                    @Index
                    String text;

                }
            """)

        master(registry)

        Cursor cursor = database.rawQuery("PRAGMA index_info('IDX_TEXT')", null)
        assert cursor.moveToNext()
    }

    @Test
    void uniqueIndex() {
        DaoRegistry registry = generateRegistry("""
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                    @Index(unique = true)
                    String text;

                }
            """
        )

        Dao dao = dao(registry)
        def t = dao.newInstance()
        t.text = 'hi'
        dao.insert(t)

        t = dao.newInstance()
        t.text = 'hi'
        try {
            dao.insert(t)
            throw new IllegalStateException("Constaint exception should be thrown");
        } catch (SQLException e) {
            assert e.getMessage().contains('UNIQUE constraint failed: T.TEXT')
        }

        Cursor cursor = database.rawQuery("PRAGMA index_info('IDX_TEXT')", null)
        assert cursor.moveToNext()
    }

    @Test
    void indexSince() {
        DaoRegistry t1 = generateRegistry("""
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                    String text;

                }
            """
        )
        DaoRegistry t2 = generateRegistry("""
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
        )

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
        DaoRegistry registry = generateRegistry("""
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
        )

        master(registry)

        Cursor cursor = database.rawQuery("PRAGMA index_info('CUSTOM_TEXT')", null)
        assert cursor.moveToNext()
    }

    @Test
    void compoundIndex() {
        DaoRegistry registry = generateRegistry("""
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @CompoundIndex(fields = {
                    @Field(name = "text"),
                    @Field(name = "number", order = Field.Sort.DESC),
                })
                @Entity
                public class T {

                    @Id
                    Long id;

                    String text;

                    int number;

                }
            """
        )

        master(registry)

        Cursor cursor = database.rawQuery("PRAGMA index_info('IDX_TEXT_NUMBER')", null)
        assert cursor.moveToNext()
        assert cursor.getString(2) == 'TEXT'
        assert cursor.moveToNext()
        assert cursor.getString(2) == 'NUMBER'

        verifyUnique('IDX_TEXT_NUMBER', false)
    }

    @Test
    void compoundIndexes() {
        DaoRegistry registry = generateRegistry("""
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @CompoundIndexes({
                    @CompoundIndex(
                        name = "MAGIC",
                        unique = true,
                        fields = {
                            @Field(name = "text"),
                            @Field(name = "number")
                        }
                    ),
                    @CompoundIndex(fields = {
                        @Field(name = "number"),
                        @Field(name = "text")
                    })
                })
                @Entity
                public class T {

                    @Id
                    Long id;

                    String text;

                    int number;

                }
            """
        )

        master(registry)

        Cursor cursor = database.rawQuery("PRAGMA index_info('MAGIC')", null)
        assert cursor.moveToNext()
        assert cursor.getString(2) == 'TEXT'
        assert cursor.moveToNext()
        assert cursor.getString(2) == 'NUMBER'

        cursor = database.rawQuery("PRAGMA index_info('IDX_NUMBER_TEXT')", null)
        assert cursor.moveToNext()
        assert cursor.getString(2) == 'NUMBER'
        assert cursor.moveToNext()
        assert cursor.getString(2) == 'TEXT'

        verifyUnique('MAGIC', true)
        verifyUnique('IDX_NUMBER_TEXT', false)
    }

    private void verifyUnique(String name, boolean unique) {
        Cursor cursor = database.rawQuery("PRAGMA index_list(T)")
        while (cursor.moveToNext()) {
            if (cursor.getString(1) == name) {
                def uniqueInt = unique ? 1 : 0
                assert cursor.getInt(2) == uniqueInt
                return
            }
        }
        throw new IllegalStateException("No such index")
    }
}
