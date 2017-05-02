package com.github.artyomcool.dante

import com.github.artyomcool.dante.core.dao.Dao
import com.github.artyomcool.dante.core.dao.DaoRegistry
import org.junit.Test

import java.sql.SQLException

@SuppressWarnings(["GroovyAssignabilityCheck", "GroovyAccessibility"])
class CrudTest extends BaseDanteTest {

    @Test
    void insert() {
        def dao = simplestDao()

        dao.insert(dao.newInstance())

        assert dao.selectAll().size() == 1
    }

    @Test
    void insertBatch() {
        def dao = simplestDao()

        dao.insert(Arrays.asList(dao.newInstance(), dao.newInstance()))

        assert dao.selectAll().size() == 2
        assert dao.selectAll().collect { it.id } == [1, 2]
    }

    @Test
    void notNullString() {
        DaoRegistry registry = generateRegistry(
                """
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
        )

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
    void update() {
        def t =
                """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                    String text;

                }
            """

        def daoT = dao(generateRegistry(t))

        def e = daoT.newInstance()
        e.text = '1'
        daoT.insert(e)
        e.text = '2'
        daoT.update(e)
        e.text = '3'

        daoT = dao(generateRegistry(t))

        assert daoT.selectAll()[0].text == '2'
    }

    @Test
    void updateBatch() {
        def t = """
                package test;

                import com.github.artyomcool.dante.annotation.*;

                @Entity
                public class T {

                    @Id
                    Long id;

                    String text;

                }
            """

        def daoT = dao(generateRegistry(t))

        def e = [daoT.newInstance(), daoT.newInstance()]
        e[0].text = '01'
        e[1].text = '11'
        daoT.insert(e)
        e[0].text = '02'
        e[1].text = '12'
        daoT.update(e)
        e[0].text = '03'
        e[1].text = '13'

        daoT = dao(generateRegistry(t))

        assert daoT.selectAll().collect({ it.text }) == ['02', '12']
    }

    @Test
    void delete() {
        def dao = simplestDao()
        def e = dao.newInstance()
        e.id = 7

        dao.insert(e)
        dao.delete(e)

        assert dao.selectAll().isEmpty()
    }

    @Test
    void deleteBatch() {
        def dao = simplestDao()
        def e = [dao.newInstance(), dao.newInstance()]
        dao.insert(e)
        dao.delete(e)

        assert dao.selectAll().isEmpty()
    }

    @Test
    void deleteString() {

        def dao = dao(generateRegistry(
                """
                    package test;

                    import com.github.artyomcool.dante.annotation.*;

                    @Entity
                    public class T {

                        @Id(iWillSetIdByMySelf = true)
                        String text;

                    }
                """
        ))
        def e = dao.newInstance()
        e.text = '7'

        dao.insert(e)
        dao.delete(e)

        assert dao.selectAll().isEmpty()
    }
}
