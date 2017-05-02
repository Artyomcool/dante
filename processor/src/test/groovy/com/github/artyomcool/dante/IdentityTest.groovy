package com.github.artyomcool.dante

import com.github.artyomcool.dante.core.dao.DaoMaster
import com.github.artyomcool.dante.core.dao.DaoRegistry
import org.junit.Test

@SuppressWarnings(["GrMethodMayBeStatic", "GroovyAssignabilityCheck"])
class IdentityTest extends BaseDanteTest {

    @Test
    void integerIdentity() {
        DaoRegistry registry = generateRegistry("""
                package test;

                import java.util.List;
                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                    @DbQueries
                    public interface TestQuery {

                        @Query(where = "id = \$id")
                        T byId(long id);

                    }

                }
            """)

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
        DaoRegistry registry = generateRegistry("""
                package test;

                import java.util.List;
                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id(iWillSetIdByMySelf = true)
                    String id;

                    @DbQueries
                    public interface TestQuery {

                        @Query(where = "id = \$id")
                        T byId(String id);

                    }

                }
            """)
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
}
