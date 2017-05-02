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

import com.github.artyomcool.dante.annotation.DbQueries;
import com.github.artyomcool.dante.annotation.Entity;
import com.github.artyomcool.dante.annotation.Migration;
import com.github.artyomcool.dante.core.EntityInfo;
import com.github.artyomcool.dante.core.dao.Dao;
import com.github.artyomcool.dante.core.dao.DaoRegistry;
import com.github.artyomcool.dante.core.migration.MigrationInfo;
import com.github.artyomcool.dante.core.query.DbQueriesBase;
import com.squareup.javapoet.*;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.artyomcool.dante.StreamUtils.join;
import static com.github.artyomcool.dante.TypeNames.listOf;
import static com.github.artyomcool.dante.TypeNames.mapOf;
import static com.github.artyomcool.dante.TypeNames.parametrize;

public class RegistryGenerator {

    private final RoundEnvironment roundEnvironment;
    private final ProcessingEnvironment processingEnv;
    private final List<GeneratorError> errors = new ArrayList<>();

    public RegistryGenerator(RoundEnvironment roundEnvironment, ProcessingEnvironment processingEnv) {
        this.roundEnvironment = roundEnvironment;
        this.processingEnv = processingEnv;
    }

    public ProcessingEnvironment getProcessingEnv() {
        return processingEnv;
    }

    public void generate() throws IOException {
        GenerationResults generationResults = new GenerationResults();

        roundEnvironment.getElementsAnnotatedWith(Entity.class).forEach(e -> {
            EntityContext context = new EntityContext(this, e);
            generationResults.entities.put(context.getClassName(), context);
        });

        roundEnvironment.getElementsAnnotatedWith(DbQueries.class).forEach(e ->
                generationResults.queries.add(new QueriesGenerator(this, e, generationResults.entities).generate())
        );

        roundEnvironment.getElementsAnnotatedWith(Migration.class).forEach(e ->
                generationResults.migrations.add(new MigrationGenerator(e).generate())
        );

        TypeSpec spec = generateRegistry(generationResults);
        generationResults.extra.add(new GenerationResult("com.github.artyomcool.dante", spec, 0));

        Collection<JavaFile> files = generationResults.all().map(this::writeResult).collect(Collectors.toList());
        for (JavaFile file : files) {
            file.writeTo(processingEnv.getFiler());
        }
    }

    private JavaFile writeResult(GenerationResult generationResult) {
        return JavaFile.builder(generationResult.getPackageName(), generationResult.getTypeSpec())
                .indent("    ")
                .build();
    }

    private TypeSpec generateRegistry(GenerationResults generationResults) {
        CodeBlock createEntityCodeBlock = build(generationResults.entities.values(), this::createEntity);

        MethodSpec.Builder createEntityInfo = MethodSpec.methodBuilder("createEntityInfo")
                .addModifiers(Modifier.PROTECTED)
                .addAnnotation(Override.class)
                .returns(listOf(parametrize(EntityInfo.class, WildcardTypeName.subtypeOf(Object.class))))
                .addStatement("return $T.asList($>\n$L$<\n)", Arrays.class, createEntityCodeBlock);

        CodeBlock createDaoCodeBlock = build(generationResults.entities.values(), this::createDao);

        MethodSpec.Builder createDao = MethodSpec.methodBuilder("createDaoList")
                .addModifiers(Modifier.PROTECTED)
                .addAnnotation(Override.class)
                .addParameter(TypeNames.SQLITE_DATABASE_CLASS, "db")
                .returns(listOf(parametrize(Dao.class, WildcardTypeName.subtypeOf(Object.class))))
                .addStatement("return $T.asList($>\n$L$<\n)", Arrays.class, createDaoCodeBlock);

        CodeBlock createQueriesCodeBlock = build(generationResults.queries,
                e -> createQueries(e, "result"), "");

        TypeName returnType = mapOf(
                parametrize(Class.class, WildcardTypeName.subtypeOf(Object.class)),
                TypeName.get(DbQueriesBase.class)
        );
        MethodSpec.Builder createQueries = MethodSpec.methodBuilder("createQueries")
                .addModifiers(Modifier.PROTECTED)
                .addAnnotation(Override.class)
                .addParameter(TypeNames.SQLITE_DATABASE_CLASS, "db")
                .returns(returnType)
                .addStatement("$T result = new $T<>()", returnType, HashMap.class)
                .addCode(createQueriesCodeBlock)
                .addStatement("return result");

        CodeBlock createMigrationCodeBlock = build(generationResults.migrations, this::createMigration);

        MethodSpec.Builder createCustomMigrations = MethodSpec.methodBuilder("createCustomMigrations")
                .addModifiers(Modifier.PROTECTED)
                .addAnnotation(Override.class)
                .returns(listOf(WildcardTypeName.subtypeOf(MigrationInfo.class)))
                .addStatement("return $T.asList($>\n$L$<\n)", Arrays.class, createMigrationCodeBlock);

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

        int maxVersion = generationResults.all()
                .mapToInt(GenerationResult::getMaxVersion)
                .max()
                .orElse(1);

        return registryBuilder
                .addField(FieldSpec.builder(
                        Integer.TYPE,
                        "CURRENT_DB_VERSION",
                        Modifier.PUBLIC,
                        Modifier.STATIC,
                        Modifier.FINAL)
                        .initializer("$L", maxVersion)
                        .build())
                .addMethod(createEntityInfo.build())
                .addMethod(createDao.build())
                .addMethod(createQueries.build())
                .addMethod(createCustomMigrations.build())
                .build();
    }

    private <E> CodeBlock build(Collection<E> iterable, Function<E, CodeBlock> code) {
        return build(iterable, code, ",\n");
    }

    private <E> CodeBlock build(Collection<E> iterable, Function<E, CodeBlock> code, String delimiter) {
        CodeBlock.Builder builder = CodeBlock.builder();
        join(iterable,
                e -> builder.add(code.apply(e)),
                e -> builder.add(delimiter)
        );
        return builder.build();
    }

    private CodeBlock createEntity(EntityContext e) {
        CodeBlock.Builder codeBlock = CodeBlock.builder();
        TypeName rowReader = e.accessRowReader().getTypeName();
        TypeName cache = e.accessCache().getTypeName();

        codeBlock.add("new $T<>($T.class)\n", EntityInfo.Builder.class, e.getTypeName())
                .indent()
                .add(".cache(new $T())\n", cache)
                .add(".rowReader(new $T())\n", rowReader);

        PropertyGenerationHelper helper = new PropertyGenerationHelper(this, e);

        e.getFields().forEach(f -> {
            if (e.getIdField() == f) {
                codeBlock.add(".idProperty($L)\n", helper.buildIdProperty());
            } else {
                codeBlock.add(".property($L)\n", helper.buildProperty(f));
            }
        });

        codeBlock.add(".build()");

        return codeBlock.build();
    }

    private CodeBlock createDao(EntityContext e) {
        return CodeBlock.builder()
                .add("new $T(db, entity($T.class))", e.accessDao().getTypeName(), e.getTypeName())
                .build();
    }

    private CodeBlock createQueries(QueriesGenerator.QueryGenerationResult result, String mapName) {
        CodeBlock.Builder builder = CodeBlock.builder();
        TypeName interfaceName = result.getInterfaceName();
        TypeName typeName = result.getGenerationResult().getTypeName();
        builder.addStatement("$L.put($T.class, new $T(db, this))", mapName, interfaceName, typeName);
        return builder.build();
    }

    private CodeBlock createMigration(GenerationResult result) {
        return CodeBlock.builder()
                .add("new $T()", result.getTypeName())
                .build();
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

    private static class GenerationResults {

        final Map<String, EntityContext> entities = new HashMap<>();
        final List<QueriesGenerator.QueryGenerationResult> queries = new ArrayList<>();
        final List<GenerationResult> migrations = new ArrayList<>();
        final List<GenerationResult> extra = new ArrayList<>();

        Stream<GenerationResult> all() {
            return Stream.of(
                    entities.values().stream().flatMap(s -> s.getResults().stream()),
                    queries.stream().map(QueriesGenerator.QueryGenerationResult::getGenerationResult),
                    migrations.stream(),
                    extra.stream()
            ).flatMap(s -> s);
        }

    }

}
