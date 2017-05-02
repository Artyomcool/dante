package com.github.artyomcool.dante

import org.junit.Test

import java.util.concurrent.Callable

@SuppressWarnings(["GroovyAssignabilityCheck", "GroovyAccessibility"])
class TxTest extends BaseDanteTest {

    @Test
    void runInTxSuccess() {
        def dao = simplestDao()

        dao.runInTx(new Runnable() {
            @Override
            void run() {
                dao.insert(dao.newInstance())
            }
        })

        assert dao.selectAll().size() == 1
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

        assert dao.selectAll().isEmpty()
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
        assert dao.selectAll().size() == 1
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

        assert dao.selectAll().isEmpty()
    }

}
