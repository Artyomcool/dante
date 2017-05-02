package com.github.artyomcool.dante

import com.github.artyomcool.dante.core.dao.DaoMaster
import com.github.artyomcool.dante.core.dao.DaoRegistry
import org.junit.Test

@SuppressWarnings(["GroovyAssignabilityCheck", "GroovyAccessibility"])
class QueryTest extends BaseDanteTest {

    @Test
    void simpleQuery() {
        DaoRegistry registry = generateRegistry("""
                package test;

                import java.util.List;
                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;
                    String text;

                    @DbQueries
                    public interface Queries {

                        @Query(where = "id=\$id")
                        List<T> byId(long id);

                        @Query(where = "id > \$fromId AND text = \$text")
                        List<T> byTexts(String text, long fromId);

                        @Query(where = "text = \$text LIMIT \$limit")
                        List<T> byTextWithLimit(String text, int limit);

                    }

                }
            """)
        DaoMaster master = new DaoMaster({ database }, registry)
        master.init()

        def testQueryClass = master.loadClass('test.T$Queries')
        def queries = registry.queries(testQueryClass)
        assert testQueryClass.isAssignableFrom(queries.class)
    }

    @Test
    void rxQuery() {
        DaoRegistry registry = generateRegistry("""
                package test;

                import java.util.List;
                import rx.Observable;
                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;
                    String text;

                    @DbQueries
                    public interface TestQuery {

                        @Query(where = "id=\$id")
                        Observable<T> byId(long id);

                        @Query(where = "id > \$fromId")
                        Observable<List<T>> byTexts(long fromId);

                    }

                }
            """)
        DaoMaster master = new DaoMaster({ database }, registry)
        master.init()

        def testQueryClass = master.loadClass('test.T$TestQuery')
        def queries = master.queries('test.T$TestQuery')
        assert testQueryClass.isAssignableFrom(queries.class)
    }

    @Test
    void querySingleItem() {
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
            """
        )
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
        DaoRegistry registry = generateRegistry("""
                package test;

                import java.util.List;
                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;
                    String text;

                    @DbQueries
                    public interface TestQuery {

                        @Query(where = "text LIKE \$text LIMIT \$limit")
                        List<T> byTextWithLimit(String text, int limit);

                    }

                }
            """
        )
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
        DaoRegistry registry = generateRegistry("""
                package test;

                import java.util.List;
                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;
                    int value;

                    @DbQueries
                    public interface TestQuery {

                        @Query(where = "value > \$[a + b]")
                        List<T> greaterThenSum(int a, int b);

                    }

                }
            """)
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

}
