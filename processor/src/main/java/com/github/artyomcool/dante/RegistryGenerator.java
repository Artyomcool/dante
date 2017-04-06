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

package com.github.artyomcool.dante;

import com.github.artyomcool.dante.annotation.Entity;
import com.github.artyomcool.dante.annotation.Migration;
import com.github.artyomcool.dante.annotation.Queries;
import com.github.artyomcool.dante.core.dao.DaoRegistry;
import com.github.artyomcool.dante.core.dao.EntityInfo;
import com.github.artyomcool.dante.core.dao.MigrationInfo;
import com.squareup.javapoet.*;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;

public class RegistryGenerator {

    private final RoundEnvironment roundEnvironment;
    private final ProcessingEnvironment processingEnv;
    private final List<GeneratorError> errors = new ArrayList<>();

    private int maxVersion = 1;

    public RegistryGenerator(RoundEnvironment roundEnvironment, ProcessingEnvironment processingEnv) {
        this.roundEnvironment = roundEnvironment;
        this.processingEnv = processingEnv;
    }

    public ProcessingEnvironment getProcessingEnv() {
        return processingEnv;
    }

    public void generate() throws IOException {
        try {
            Map<String, GeneratedDao> generatedEntities = new HashMap<>();
            List<GeneratedQuery> generatedQueries = new ArrayList<>();
            List<ClassName> generatedMigrations = new ArrayList<>();

            roundEnvironment.getElementsAnnotatedWith(Entity.class).forEach(e -> {
                try {
                    DaoGenerator generator = new DaoGenerator(this, e);
                    GeneratedDao generated = generator.generate();
                    generatedEntities.put(generated.getQualifiedName(), generated);

                    maxVersion = Math.max(maxVersion, generated.getMaxVersion());
                } catch (IOException exception) {
                    throw new UncheckedIOException(exception);
                }
            });
            roundEnvironment.getElementsAnnotatedWith(Queries.class).forEach(e -> {
                try {
                    QueriesGenerator generator = new QueriesGenerator(this, e, generatedEntities);
                    generatedQueries.add(generator.generate());
                } catch (IOException ex) {
                    throw new UncheckedIOException(ex);
                }
            });
            roundEnvironment.getElementsAnnotatedWith(Migration.class).forEach(e -> {
                try {
                    MigrationGenerator generator = new MigrationGenerator(this, (TypeElement) e);
                    GeneratedMigration generated = generator.generate();
                    generatedMigrations.add(generated.getClassName());

                    maxVersion = Math.max(maxVersion, generated.getMaxVersion());
                } catch (IOException ex) {
                    throw new UncheckedIOException(ex);
                }
            });

            MethodSpec.Builder initDaoBuilder = MethodSpec.methodBuilder("initDao")
                    .addModifiers(Modifier.PROTECTED)
                    .addAnnotation(Override.class)
                    .returns(ParameterizedTypeName.get(ClassName.get(List.class), ParameterizedTypeName.get(ClassName.get(EntityInfo.class), WildcardTypeName.subtypeOf(Object.class))))
                    .addParameter(sqliteDatabase(), "db")
                    .addStatement("$T<$T<?>> result = new $T<$T<?>>()", List.class, EntityInfo.class, ArrayList.class, EntityInfo.class);

            generatedEntities.values().forEach(e -> {
                CodeBlock.Builder codeBuilder = CodeBlock.builder()
                        .add("{\n")
                        .indent()
                        .addStatement("$T dao = new $T(db)", e.getDao(), e.getDao())
                        .add("result.add(\n$>new $T<$T>($T.class, dao)", EntityInfo.class, e.getEntity(), e.getEntity());

                codeBuilder.indent().indent();
                generatedQueries.stream()
                        .filter(q -> q.getDao().equals(e))
                        .forEach(q -> codeBuilder.add("\n.query($T.class, new $T(dao))", q.getInterface(), q.getImplementation()));
                codeBuilder.unindent().unindent().add("\n");

                CodeBlock block = codeBuilder
                        .add("$<);\n")
                        .unindent()
                        .add("}\n")
                        .build();

                initDaoBuilder.addCode(block);
            });

            initDaoBuilder
                    .addStatement("return result");

            MethodSpec.Builder initCustomMigrations = MethodSpec.methodBuilder("initCustomMigrations")
                    .addModifiers(Modifier.PROTECTED)
                    .addAnnotation(Override.class)
                    .returns(ParameterizedTypeName.get(ClassName.get(List.class), WildcardTypeName.subtypeOf(MigrationInfo.class)));

            boolean hasMigrations = !generatedMigrations.isEmpty();

            if (hasMigrations) {
                CodeBlock.Builder builder = CodeBlock.builder();
                for (int i = 0; i < generatedMigrations.size() - 1; i++) {
                    builder.add("new $T(),\n", generatedMigrations.get(i));
                }
                builder.add("new $T()", generatedMigrations.get(generatedMigrations.size() - 1));
                CodeBlock code = builder.build();

                initCustomMigrations.addStatement("return $T.asList($>\n$L$<\n)", Arrays.class, code);
            } else {
                initCustomMigrations.addStatement("return $T.emptyList()", Collections.class);
            }


            TypeSpec.Builder registryBuilder = TypeSpec.classBuilder("DefaultRegistry")
                    .superclass(DaoRegistry.class)
                    .addModifiers(Modifier.PUBLIC);
            for (GeneratorError error : errors) {
                registryBuilder.addStaticBlock(
                        CodeBlock.builder()
                                .addStatement("GEN_ERROR($S, $S)", error.getError(), getElementName(error.getElement()))
                                .build()
                );
            }
            TypeSpec spec = registryBuilder
                    .addField(FieldSpec.builder(
                            Integer.TYPE,
                            "CURRENT_DB_VERSION",
                            Modifier.PUBLIC,
                            Modifier.STATIC,
                            Modifier.FINAL)
                            .initializer("$L", maxVersion)
                            .build())
                    .addMethod(initDaoBuilder.build())
                    .addMethod(initCustomMigrations.build())
                    .build();


            JavaFile file = JavaFile.builder("com.github.artyomcool.dante", spec)
                    .indent("    ")
                    .build();

            file.writeTo(processingEnv.getFiler());
        } catch (UncheckedIOException e) {
            throw e.getCause();
        }
    }

    private String getElementName(Element element) {
        StringBuilder result = new StringBuilder();
        result.append(element.getSimpleName());
        while (element.getEnclosingElement() != null) {
            element = element.getEnclosingElement();
            result.insert(0, element.getSimpleName() + ".");
        }
        return result.toString();
    }

    public String toTableName(String elementName) {
        StringBuilder result = new StringBuilder();
        char prev = elementName.charAt(0);
        result.append(Character.toUpperCase(prev));
        for (int i = 1; i < elementName.length(); i++) {
            char now = elementName.charAt(i);
            if (Character.isUpperCase(now) && !Character.isUpperCase(prev)) {
                result.append('_');
            }
            result.append(Character.toUpperCase(now));
            prev = now;
        }
        return result.toString();
    }

    public static ClassName sqliteDatabase() {
        return ClassName.get("android.database.sqlite", "SQLiteDatabase");
    }

    public static boolean isString(TypeName typeName) {
        return typeName.toString().equals("java.lang.String");
    }

    public static boolean isByteArray(TypeName typeName) {
        return typeName.toString().equals("byte[]");
    }

    public static boolean isPrimitiveWrapper(TypeName typeName) {
        if (typeName.isPrimitive()) {
            return false;
        }
        try {
            typeName.unbox();
            return true;
        } catch (UnsupportedOperationException e) {
            return false;
        }
    }

    public static TypeName tryUnwrap(TypeName typeName) {
        try {
            return typeName.unbox();
        } catch (UnsupportedOperationException e) {
            return typeName;
        }
    }

    private static PackageElement getPackageElement(Element type) {
        while (type.getKind() != ElementKind.PACKAGE) {
            type = type.getEnclosingElement();
        }
        return (PackageElement) type;
    }

    public static String getPackage(Element element) {
        return getPackageElement(element).getQualifiedName().toString();
    }

    public TypeMirror getAnnotationValue(Runnable extractor) {
        try {
            extractor.run();
            throw new IllegalStateException("MirroredTypeException should be thrown");
        } catch (MirroredTypeException e) {
            return e.getTypeMirror();
        }
    }

    public TypeElement getAnnotationElement(Runnable extractor) {
        return (TypeElement) processingEnv.getTypeUtils().asElement(getAnnotationValue(extractor));
    }

    public void codeGenError(Element element, String error) {
        errors.add(new GeneratorError(element, error));
        System.err.println(element + ": " + error);
    }
}
