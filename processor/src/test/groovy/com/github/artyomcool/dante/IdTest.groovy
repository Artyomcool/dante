package com.github.artyomcool.dante

import com.github.artyomcool.dante.core.EntityInfo
import com.github.artyomcool.dante.core.Property
import org.junit.Ignore
import org.junit.Test

@SuppressWarnings(["GrMethodMayBeStatic", "GroovyAssignabilityCheck"])
class IdTest extends BaseDanteTest {

    EntityInfo<?> justId(def idField) {
        def registry = generateRegistry(
                """
                    package test;

                    import com.github.artyomcool.dante.annotation.*;

                    @Entity
                    public class T {

                        """ + idField + """;

                    }
                """
        )
        registry.init(database)

        assert registry.dao.size() == 1

        def entity = registry.entity('test.T')
        assert entity.getProperties().size() == 1

        return entity
    }

    def verifyIntegerRegistry(EntityInfo<?> info) {
        Property property = info.idProperty
        assert property.columnType == 'INTEGER'
        assert property.columnName == 'ID'
    }

    @Test
    void justIdLong() {
        def info = justId('@Id Long id')
        verifyIntegerRegistry(info)
    }

    @Test
    void treatZeroAsNullIdLong() {
        def info = justId('@Id(treatZeroAsNull = true) long id')
        verifyIntegerRegistry(info)
    }

    @Test
    void justIdInteger() {
        def info = justId('@Id Integer id')
        verifyIntegerRegistry(info)
    }

    @Test
    void justIdShort() {
        def info = justId('@Id Short id')
        verifyIntegerRegistry(info)
    }

    @Test
    void justIdByte() {
        def info = justId('@Id Byte id')
        verifyIntegerRegistry(info)
    }

    @Test
    void justIdBoolean() {
        def info = justId('@Id Boolean id')
        verifyIntegerRegistry(info)
    }

    @Test
    void justIdString() {
        def info = justId('@Id(iWillSetIdByMySelf = true) String strId')

        Property idProperty = info.getIdProperty()
        assert idProperty.columnType == 'TEXT'
        assert idProperty.columnName == 'STR_ID'
    }

    @Ignore("Cache for blobs is not implemented yet")
    @Test
    void justIdBlob() {
        def info = justId('@Id(iWillSetIdByMySelf = true) byte[] id')
        //TODO verify blob
    }

    @Test(expected = RuntimeException)
    void failNoId() {
        generateRegistry(
                """
                    package test;

                    import com.github.artyomcool.dante.annotation.*;

                    @Entity
                    public class T {
                    }
                """
        )
    }
}
