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

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SqlHelper
import com.github.artyomcool.dante.core.dao.Dao
import com.github.artyomcool.dante.core.dao.DaoMaster
import com.github.artyomcool.dante.core.dao.DaoRegistry
import com.github.javaparser.JavaParser
import com.google.testing.compile.JavaFileObjects

import javax.tools.*
import java.nio.charset.Charset

class BaseDanteTest {

    private static int WINDOW_SIZE = 8  //small value for tests

    SQLiteDatabase database

    Map thisObjects = [:]

    AndroidMethodHandler.AndroidMethodDelegate delegate = new AndroidMethodHandler.AndroidMethodDelegate() {
        @Override
        Object handle(String owner, String name, String desc, Object... args) {
            if (owner == 'android/database/sqlite/SQLiteClosable' && name == 'close') {
                return null
            }
            if (owner == 'android/database/sqlite/SQLiteQuery') {
                switch (name) {
                    case '<init>':
                        String query = args[2]
                        def argsArray = new Object[query.count('?')]
                        thisObjects[args[0]] = [db: args[1], query: query, args: argsArray]
                        return null

                    case 'fillWindow':
                        def thisObj = thisObjects[args[0]]
                        boolean requery = args[4]
                        int from = args[2]
                        if (args[3] != args[2]) {
                            throw new UnsupportedOperationException()
                        }
                        if (requery) {
                            thisObjects[args[1]]['cursor'] =  database.rawQuery(thisObj['query'] as String, thisObj['args'] as String[])
                        }
                        thisObjects[args[1]]['offset'] = from

                        return thisObjects[args[1]]['cursor'].count
                }
            }
            if (owner == 'android/database/CursorWindow') {
                switch (name) {
                    case '<init>':
                        thisObjects[args[0]] = [:]
                        return null
                    case 'getLong':
                        int pos = thisObjects[args[0]]['offset'] + (args[1] as Integer)
                        thisObjects[args[0]]['cursor'].moveToPosition(pos)
                        return thisObjects[args[0]]['cursor'].getLong(args[2] as Integer)
                    case 'getDouble':
                        int pos = thisObjects[args[0]]['offset'] + (args[1] as Integer)
                        thisObjects[args[0]]['cursor'].moveToPosition(pos)
                        return thisObjects[args[0]]['cursor'].getDouble(args[2] as Integer)
                    case 'isNull':
                        int pos = thisObjects[args[0]]['offset'] + (args[1] as Integer)
                        thisObjects[args[0]]['cursor'].moveToPosition(pos)
                        return thisObjects[args[0]]['cursor'].isNull(args[2] as Integer)
                    case 'getString':
                        int pos = thisObjects[args[0]]['offset'] + (args[1] as Integer)
                        thisObjects[args[0]]['cursor'].moveToPosition(pos)
                        return thisObjects[args[0]]['cursor'].getString(args[2] as Integer)
                    case 'getStartPosition':
                        return thisObjects[args[0]]['offset']
                    case 'getNumRows':
                        return Math.min(thisObjects[args[0]]['cursor'].count, BaseDanteTest.WINDOW_SIZE)
                }
            }
            if (owner == 'android/database/sqlite/SQLiteProgram') {
                switch (name) {
                    case 'getDatabase':
                        return thisObjects[args[0]]['db']
                    case 'getBindArgs':
                        return thisObjects[args[0]]['args']
                }
            }
            if (name.equals("<init>") || name.equals("<clinit>")) {
                return null
            }
            throw new UnsupportedOperationException("Unsupported operation: " + owner + "#" + name + "/" + desc)
        }
    }

    BaseDanteTest() {
        AndroidMethodHandler.setDelegate(delegate);

        database = SqlHelper.createInMemory()

        DaoRegistry.metaClass.loadClass = { def name ->
            delegate.class.classLoader.loadClass(name)
        }
        DaoRegistry.metaClass.dao = { def name ->
            def result = dao(loadClass(name))
            result.class.metaClass.newInstance = {
                loadClass(name).newInstance()
            }
            result
        }
        DaoRegistry.metaClass.queries = { def name ->
            queries(loadClass(name))
        }
        DaoRegistry.metaClass.entity = { def name ->
            entity(loadClass(name))
        }

        DaoMaster.metaClass.loadClass = { def name ->
            delegate.delegate.loadClass(name)
        }
        DaoMaster.metaClass.dao = { def name ->
            delegate.delegate.dao(name)
        }
        DaoMaster.metaClass.queries = { def name ->
            delegate.delegate.queries(name)
        }
        DaoMaster.metaClass.entity = { def name ->
            delegate.delegate.entity(name)
        }
    }

    DaoRegistry generateRegistry(String... sources) {
        def objects = sources.collect { String code ->
            def unit = JavaParser.parse(code)
            def packageName = unit.packageDeclaration.get()?.name?.toString()
            def className = unit.getType(0).name.toString()
            def fullName = packageName == null ? className : "$packageName.$className"

            JavaFileObjects.forSourceLines(fullName, code)
        }
        def compiler = ToolProvider.getSystemJavaCompiler();

        def generatedSouces = []
        def generatedClasses = []

        def stdFileManager = compiler.getStandardFileManager({}, Locale.ENGLISH, Charset.forName("UTF-8"))
        JavaFileManager inMemoryFileManager = Class.forName("com.google.testing.compile.InMemoryJavaFileManager").newInstance(stdFileManager) as JavaFileManager

        JavaFileManager fileManager = new ForwardingJavaFileManager(inMemoryFileManager) {
            JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, String className, JavaFileObject.Kind kind, FileObject sibling) {
                switch (location.name) {
                    case StandardLocation.SOURCE_OUTPUT.name:
                        generatedSouces << className
                        break
                    case StandardLocation.CLASS_OUTPUT.name:
                        generatedClasses << className
                        break
                }
                return super.getJavaFileForOutput(location, className, kind, sibling)
            }
        }

        def diagnostics = []

        def task = compiler.getTask(null, fileManager, { diagnostics << it }, [], [], objects);

        task.setProcessors([new AnnotationProcessor()])

        try {
            if (!task.call()) {
                throw new IllegalStateException("Compilation error");
            }

            ClassLoader loader = new ClassLoader(getClass().getClassLoader()) {
                @Override
                Class<?> findClass(String name) throws ClassNotFoundException {
                    if (name in generatedClasses) {
                        def bytes = fileManager.getJavaFileForOutput(
                                [getName: { 'CLASS_OUTPUT' }] as JavaFileManager.Location,
                                name,
                                JavaFileObject.Kind.CLASS,
                                null
                        ).openInputStream().bytes
                        return defineClass(name, bytes, 0, bytes.length)
                    }
                    throw new ClassNotFoundException("Class not found: " + name)
                }
            }

            println getSources(inMemoryFileManager, generatedSouces)
            return newInstance("com.github.artyomcool.dante.DefaultRegistry", loader) as DaoRegistry
        } catch (Exception e) {
            def exception = "Compilation error.\n====== Sources =======\n${getSources(inMemoryFileManager, generatedSouces)}\n\n======= Diagnostic logs ======\n${diagnostics.join('\n')}\n\n"
            throw new IllegalStateException(exception, e);
        }
    }

    static newInstance(String className, ClassLoader loader) {
        return loader.loadClass(className).newInstance()
    }

    static getSources(JavaFileManager fileManager, List<String> generatedSources) {
        generatedSources.collect {
            "====== File $it ======\n" + fileManager.getJavaFileForOutput(
                    [getName: { 'SOURCE_OUTPUT' }] as JavaFileManager.Location,
                    it,
                    JavaFileObject.Kind.SOURCE,
                    null
            ).getCharContent(true)
        }.join('\n\n')
    }

    DaoMaster master(DaoRegistry registry) {
        def result = new DaoMaster({ database }, registry)
        result.init()

        result
    }

    Dao dao(DaoRegistry registry) {
        return master(registry).dao('test.T')
    }

    Dao simplestDao() {
        dao(simplestRegistry())
    }

    DaoRegistry simplestRegistry() {
        generateRegistry(
                """
                    package test;

                    import com.github.artyomcool.dante.annotation.*;

                    @Entity
                    public class T {

                        @Id
                        Long id;

                    }
                """
        )
    }

    public static currentDbVersion(def daoMaster) {
        daoMaster.loadClass('com.github.artyomcool.dante.DefaultRegistry').CURRENT_DB_VERSION
    }
}
