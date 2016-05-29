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

import com.github.artyomcool.dante.core.dao.DaoRegistry
import com.google.testing.compile.JavaFileObjects

import javax.tools.*
import java.nio.charset.Charset

class AbstractAptTest {

    DaoRegistry generateRegistry(List sources) {
        def objects = sources.collect { JavaFileObjects.forSourceLines(it.fullClassName, it.sourceFile) }
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
}
