package com.github.artyomcool.dante

import com.github.artyomcool.dante.core.EntityInfo
import com.github.artyomcool.dante.core.Property
import org.junit.Ignore
import org.junit.Test

@SuppressWarnings(["GrMethodMayBeStatic", "GroovyAssignabilityCheck"])
class MigrationTest extends BaseDanteTest {

    @Test
    void upgradeAddEntity() {
        def t1 = """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T1 {

                    @Id
                    Long id;

                }
            """

        def t2 = """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity(sinceVersion = 2)
                public class T2 {

                    @Id
                    Long id;

                }
            """

        def registry = generateRegistry(t1)
        master(registry)

        assert database.version == 1

        registry = generateRegistry(t1, t2)
        def daoMaster = master(registry)
        assert database.version == 2

        def dao1 = daoMaster.dao('test.T1')
        def dao2 = daoMaster.dao('test.T2')

        def e1 = dao1.newInstance()
        dao1.insert(e1)

        def e2 = dao2.newInstance()
        dao2.insert(e2)

        assert dao1.selectAll()[0] == e1
        assert dao2.selectAll()[0] == e2

        assert currentDbVersion(daoMaster) == 2
    }

    @Test
    void upgradeAddProperty() {
        def t1 = """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                }
            """

        def t2 = """
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

        def registry = generateRegistry(t1)
        def daoT = dao(registry)

        assert database.version == 1

        def e1 = daoT.newInstance()
        daoT.insert(e1)
        assert e1.id

        registry = generateRegistry(t2)
        def daoMaster = master(registry)
        daoT = daoMaster.dao('test.T')
        assert database.version == 2

        def e2 = daoT.selectAll()[0]
        assert e2.id == e1.id
        assert !e2.newField

        assert currentDbVersion(daoMaster) == 2
    }

    @Test
    void upgradeAddNotNullProperty() {
        def t1 = """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                }
            """

        def t2 = """
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

        def registry = generateRegistry(t1)
        def daoT = dao(registry)

        assert database.version == 1

        def e1 = daoT.newInstance()
        daoT.insert(e1)
        assert e1.id

        registry = generateRegistry(t2)
        def daoMaster = master(registry)
        daoT = daoMaster.dao('test.T')
        assert database.version == 2

        def e2 = daoT.selectAll()[0]
        assert e2.id == e1.id
        assert e2.newField == 0
        assert e2.newField2 == 0
        assert e2.newField3 == null

        assert currentDbVersion(daoMaster) == 2
    }

    @Test
    void upgradeCustomMigration() {
        def t1 = """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                }
            """

        def t2 = """
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

        def migration = """
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

        def registry = generateRegistry(t1)
        def dao = dao(registry)

        assert database.version == 1

        def e1 = dao.newInstance()
        dao.insert(e1)
        assert e1.id

        registry = generateRegistry(t2, migration)
        def daoMaster = master(registry)
        assert database.version == 2

        assert daoMaster.loadClass('test.M').dbVersion == 1

        def e2 = daoMaster.dao('test.T').selectAll()[0]
        assert e2.id == e1.id
        assert e2.newField == 0

        assert currentDbVersion(daoMaster) == 2
    }

    @Test
    void downgrade() {
        def t1 = """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T1 {

                    @Id
                    Long id;

                }
            """

        def t2 = """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity(sinceVersion = 2)
                public class T2 {

                    @Id
                    Long id;

                }
            """

        def t2Down = """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T2 {

                    @Id
                    Long id;

                }
            """

        def registry = generateRegistry(t1)
        master(registry)

        assert database.version == 1

        registry = generateRegistry(t1, t2)
        def daoMaster = master(registry)
        assert database.version == 2

        def dao1 = daoMaster.dao('test.T1')
        def dao2 = daoMaster.dao('test.T2')

        def e1 = dao1.newInstance()
        dao1.insert(e1)

        def e2 = dao2.newInstance()
        dao2.insert(e2)

        registry = generateRegistry(t1, t2Down)
        daoMaster = master(registry)
        assert database.version == 1

        dao1 = daoMaster.dao('test.T1')
        dao2 = daoMaster.dao('test.T2')

        assert dao1.selectAll().isEmpty()
        assert dao2.selectAll().isEmpty()
    }

}
