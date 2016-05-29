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

package org.powermock.tests.utils.impl

import com.github.artyomcool.dante.AnnotationProcessor
import com.github.artyomcool.dante.core.dao.DaoRegistry
import com.google.testing.compile.JavaFileObjects
import javassist.CtClass
import javassist.Modifier
import org.junit.runner.notification.RunNotifier
import org.powermock.core.transformers.MockTransformer
import org.powermock.core.transformers.impl.ClassMockTransformer
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.common.internal.impl.AbstractCommonPowerMockRunner
import org.powermock.modules.junit4.common.internal.impl.JUnit4TestSuiteChunkerImpl

import javax.tools.FileObject
import javax.tools.ForwardingJavaFileManager
import javax.tools.JavaFileManager
import javax.tools.JavaFileObject
import javax.tools.StandardLocation
import javax.tools.ToolProvider
import java.nio.charset.Charset

class AptRunner extends PowerMockRunner {
    AptRunner(Class<?> klass) throws Exception {
        super(klass)
        def field = AbstractCommonPowerMockRunner.declaredFields.find {
            it.name == 'suiteChunker'
        }
        field.setAccessible(true)
        field.set(this, new JUnit4TestSuiteChunkerImpl(klass, PowerMockRunner.getRunnerDelegateImplClass(klass)){
            @Override
            protected MockClassLoaderFactory getMockClassLoaderFactory(Class<?> testClass, String[] preliminaryClassesToLoadByMockClassloader, String[] packagesToIgnore, MockTransformer[] extraMockTransformers) {
                def merger = new ArrayMergerImpl()
                extraMockTransformers = merger.mergeArrays(MockTransformer, extraMockTransformers, [new ClassMockTransformer(){
                    @Override
                    protected void setAllConstructorsToPublic(CtClass clazz) {
                        clazz.declaredConstructors.each {
                            it.body = "{}"
                            it.setModifiers(Modifier.setPublic(it.modifiers))
                        }
                    }
                }] as MockTransformer[])

                preliminaryClassesToLoadByMockClassloader = merger.mergeArrays(String,
                        preliminaryClassesToLoadByMockClassloader,
                        ['android.*'] as String[]
                )

                packagesToIgnore = merger.mergeArrays(String, packagesToIgnore, ['javax.*'] as String[])

                return super.getMockClassLoaderFactory(testClass, preliminaryClassesToLoadByMockClassloader, packagesToIgnore, extraMockTransformers)
            }
        })
    }
}
