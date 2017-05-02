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

import com.github.artyomcool.dante.annotation.CompoundIndex;
import com.github.artyomcool.dante.annotation.CompoundIndexes;
import com.github.artyomcool.dante.annotation.Field;
import com.github.artyomcool.dante.annotation.Id;
import com.github.artyomcool.dante.core.EntityInfo;
import com.squareup.javapoet.*;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import java.util.*;
import java.util.stream.IntStream;

import static com.github.artyomcool.dante.Methods.implement;
import static com.github.artyomcool.dante.PropertyGenerationHelper.isPrimitiveOrWrapper;
import static com.github.artyomcool.dante.RegistryGenerator.getPackage;
import static com.github.artyomcool.dante.StreamUtils.join;
import static com.github.artyomcool.dante.TypeNames.*;
import static javax.lang.model.element.Modifier.PROTECTED;
import static javax.lang.model.element.Modifier.PUBLIC;

public class DaoGenerator implements Generator {

    private static final Class<com.github.artyomcool.dante.core.CompoundIndex> COMPOUND_INDEX_CLASS = com.github.artyomcool.dante.core.CompoundIndex.class;

    private final EntityContext context;
    private final RegistryGenerator registryGenerator;

    public DaoGenerator(RegistryGenerator generator, EntityContext context) {
        this.registryGenerator = generator;
        this.context = context;
    }

    public GenerationResult generate() {
        verifyIdStatement();

        TypeSpec.Builder daoClass = TypeSpec.classBuilder(context.getClassName() + "_Dao_")
                .addOriginatingElement(context.getElement())
                .addModifiers(Modifier.PUBLIC)
                .superclass(context.getAbstractDaoType())
                .addField(genCompoundIndexes())
                .addMethod(genConstructor())
                .addMethod(genTableName())
                .addMethod(genGetCompoundIndexes())
                .addMethod(genBind())
                .addMethod(genBindId())
                .addMethod(genUpdateRowId());

        TypeSpec typeSpec = daoClass.build();
        String packageName = getPackage(context.getElement());

        int maxVersion = getMaxVersion();
        return new GenerationResult(packageName, typeSpec, maxVersion);
    }

    private int getMaxVersion() {
        int version = context.getAnnotation().sinceVersion();
        OptionalInt max = IntStream.concat(
                context.getFields().stream().mapToInt(PropertyGenerationHelper::sinceVersion),
                context.getFields().stream().mapToInt(PropertyGenerationHelper::indexSince)).max();
        if (max.isPresent()) {
            version = Math.max(version, max.getAsInt());
        }
        return version;
    }

    private FieldSpec genCompoundIndexes() {
        FieldSpec.Builder indexes_ = FieldSpec.builder(
                listOf(COMPOUND_INDEX_CLASS),
                "indexes_",
                Modifier.PRIVATE,
                Modifier.FINAL
        );

        List<CompoundIndex> indexes = new ArrayList<>();
        CompoundIndex aCompoundIndex = context.getElement().getAnnotation(CompoundIndex.class);
        if (aCompoundIndex != null) {
            indexes.add(aCompoundIndex);
        }
        CompoundIndexes aCompoundIndexes = context.getElement().getAnnotation(CompoundIndexes.class);
        if (aCompoundIndexes != null) {
            indexes.addAll(Arrays.asList(aCompoundIndexes.value()));
        }

        if (indexes.isEmpty()) {
            return indexes_
                    .initializer("$T.emptyList()", Collections.class)
                    .build();
        }

        CodeBlock.Builder code = CodeBlock.builder()
                .add("$T.asList(", Arrays.class);
        code.indent();

        join(indexes,
                index -> code.add(buildIndex(index)),
                index -> code.add(",\n")
        );

        code.add("\n");
        code.unindent();
        code.add(")");
        return indexes_
                .initializer(code.build())
                .build();
    }

    private CodeBlock buildIndex(CompoundIndex index) {
        CodeBlock.Builder builder = CodeBlock.builder()
                .add("new $T()", com.github.artyomcool.dante.core.CompoundIndex.Builder.class).indent().indent()
                .add("\n.sinceVersion($L)", index.sinceVersion())
                .add("\n.name($S)", indexName(index))
                .add("\n.unique($L)", index.unique());

        for (Field field : index.fields()) {
            int fieldIndex = fieldIndex(field.name());
            boolean isDesc = field.order() == Field.Sort.DESC;

            builder.add("\n.property(getEntityInfo().getProperties().get($L), $L)", fieldIndex, isDesc);
        }

        return builder.add("\n.build()").unindent().unindent()
                .build();
    }

    private String indexName(CompoundIndex index) {
        if (!index.name().isEmpty()) {
            return index.name();
        }

        StringBuilder result = new StringBuilder("IDX");

        for (Field field : index.fields()) {
            Optional<String> optional = context.columnName(field.name());
            if (optional.isPresent()) {
                result.append("_")
                        .append(optional.get());
            } else {
                result.append("_Field '").append(field.name()).append("' not found");

                registryGenerator.codeGenError(context.getElement(), "Field '" + field.name() + "' not found");
            }
        }

        return result.toString();
    }

    private MethodSpec genBind() {
        MethodSpec.Builder methodBuilder = implement(PROTECTED, "bind")
                .addParameter(context.getTypeName(), "e")
                .addParameter(TypeNames.SQLITE_STATEMENT_CLASS, "statement");
        int index = 1;
        for (VariableElement field : context.getFields()) {
            if (field == context.getIdField()) {
                methodBuilder.addStatement("bindId(e, statement, $L)", index++);
                continue;
            }
            CodeBlock.Builder builder = CodeBlock.builder();
            builder.add("{\n").indent();
            builder.addStatement("$T value = e.$L", field.asType(), field.getSimpleName());
            builder.add(bindProperty(field, index++));
            builder.unindent().add("}\n");

            methodBuilder.addCode(builder.build());
        }
        return methodBuilder.build();
    }

    private MethodSpec genBindId() {
        MethodSpec.Builder methodBuilder = implement(PROTECTED, "bindId")
                .addParameter(context.getTypeName(), "e")
                .addParameter(TypeNames.SQLITE_STATEMENT_CLASS, "statement")
                .addParameter(Integer.TYPE, "index");

        VariableElement idField = context.getIdField();
        TypeName typeName = ClassName.get(idField.asType());
        Name fieldName = idField.getSimpleName();

        methodBuilder.addStatement("$T value = e.$L", typeName, fieldName);

        if (isString(typeName)) {
            methodBuilder.addStatement("statement.bindString(index, value)");
        } else if (isByteArray(typeName)) {
            methodBuilder.addStatement("statement.bindBlob(index, value)");
        } else if (isPrimitiveWrapper(typeName)) {
            methodBuilder
                    .beginControlFlow("if (value == null)")
                    .addStatement("statement.bindNull(index)")
                    .nextControlFlow("else");
            switch (typeName.toString().substring("java.lang.".length())) {
                case "Boolean":
                    methodBuilder.addStatement("statement.bindLong(index, value ? 1 : 0)");
                    break;
                case "Float":
                case "Double":
                    methodBuilder.addStatement("statement.bindDouble(index, value.doubleValue()");
                    break;
                default:
                    methodBuilder.addStatement("statement.bindLong(index, value.longValue())");
                    break;
            }
            methodBuilder.endControlFlow();
        } else if (typeName.isPrimitive()) {
            Id idAnnotation = idField.getAnnotation(Id.class);
            if (idAnnotation.treatZeroAsNull()) {
                methodBuilder
                        .beginControlFlow("if (value == 0)")
                        .addStatement("statement.bindNull(index)")
                        .nextControlFlow("else");

                if (typeName.equals(TypeName.DOUBLE) || typeName.equals(TypeName.FLOAT)) {
                    methodBuilder.addStatement("statement.bindDouble(index, value)");
                } else {
                    methodBuilder.addStatement("statement.bindLong(index, value)");
                }

                methodBuilder.endControlFlow();
            } else {
                if (typeName.equals(TypeName.BOOLEAN)) {
                    methodBuilder.addStatement("statement.bindLong(index, value ? 1 : 0)");
                } else if (typeName.equals(TypeName.DOUBLE) || typeName.equals(TypeName.FLOAT)) {
                    methodBuilder.addStatement("statement.bindDouble(index, value)");
                } else {
                    methodBuilder.addStatement("statement.bindLong(index, value)");
                }
            }
        }

        return methodBuilder.build();
    }

    private MethodSpec genUpdateRowId() {
        MethodSpec.Builder methodBuilder = implement(PROTECTED, "updateRowId")
                .addParameter(context.getTypeName(), "e")
                .addParameter(TypeName.LONG, "rowId");

        VariableElement idField = context.getIdField();
        Name fieldName = idField.getSimpleName();

        if (isPrimitiveOrWrapper(idField)) {
            TypeName typeName = ClassName.get(idField.asType());
            typeName = tryUnwrap(typeName);
            String value = typeName.equals(TypeName.BOOLEAN)
                    ? "rowId != 0"
                    : "(" + typeName + ") rowId";
            methodBuilder.addStatement("e.$L = $L", fieldName, value);
        }

        return methodBuilder.build();
    }

    private CodeBlock bindProperty(VariableElement field, int index) {
        CodeBlock.Builder builder = CodeBlock.builder();

        TypeMirror typeMirror = field.asType();
        TypeName typeName = ClassName.get(typeMirror);
        switch (typeName.toString()) {
            case "boolean":
                return builder
                        .addStatement("statement.bindLong($L, value ? 1 : 0)", index)
                        .build();
            case "java.lang.Boolean":
                return builder.beginControlFlow("if (value == null)")
                        .addStatement("statement.bindNull($L)", index)
                        .nextControlFlow("else")
                        .addStatement("statement.bindLong($L, value ? 1 : 0)", index)
                        .endControlFlow()
                        .build();
            case "byte":
            case "short":
            case "int":
            case "long":
                return builder
                        .addStatement("statement.bindLong($L, value)", index)
                        .build();
            case "java.lang.Byte":
            case "java.lang.Short":
            case "java.lang.Integer":
            case "java.lang.Long":
                return builder.beginControlFlow("if (value == null)")
                        .addStatement("statement.bindNull($L)", index)
                        .nextControlFlow("else")
                        .addStatement("statement.bindLong($L, value)", index)
                        .endControlFlow()
                        .build();
            case "char":
                return builder
                        .addStatement("statement.bindLong($L, (long) value)", index)
                        .build();
            case "java.lang.Character":
                return builder.beginControlFlow("if (value == null)")
                        .addStatement("statement.bindNull($L)", index)
                        .nextControlFlow("else")
                        .addStatement("statement.bindLong($L, (long) value)", index)
                        .endControlFlow()
                        .build();
            case "float":
            case "double":
                return builder
                        .addStatement("statement.bindDouble($L, value)", index)
                        .build();
            case "java.lang.Float":
            case "java.lang.Double":
                return builder.beginControlFlow("if (value == null)")
                        .addStatement("statement.bindNull($L)", index)
                        .nextControlFlow("else")
                        .addStatement("statement.bindDouble($L, value.doubleValue())", index)
                        .endControlFlow()
                        .build();
            case "byte[]":
                return builder.beginControlFlow("if (value == null)")
                        .addStatement("statement.bindNull($L)", index)
                        .nextControlFlow("else")
                        .addStatement("statement.bindBlob($L, value)", index)
                        .endControlFlow()
                        .build();
            case "java.lang.String":
                return builder.beginControlFlow("if (value == null)")
                        .addStatement("statement.bindNull($L)", index)
                        .nextControlFlow("else")
                        .addStatement("statement.bindString($L, value)", index)
                        .endControlFlow()
                        .build();
        }
        registryGenerator.codeGenError(field, "Unsupported type");
        //TODO do not throw
        throw new IllegalArgumentException("Unsupported type: " + typeName);
    }

    private void verifyIdStatement() {
        Element idField = context.getIdField();

        Id annotation = idField.getAnnotation(Id.class);

        TypeName typeName = TypeName.get(idField.asType());
        if (isString(typeName) || isByteArray(typeName)) {
            if (!annotation.iWillSetIdByMySelf()) {
                registryGenerator.codeGenError(idField, "You must generate string and blob ids by yourself because sqlite can't generate them for you, so you should set iWillSetIdByMySelf to true in @Id annotation if you are ok with that");
            }
            return;
        }

        if (typeName.isPrimitive()) {
            if (annotation.iWillSetIdByMySelf() && annotation.treatZeroAsNull()) {
                registryGenerator.codeGenError(idField, "Both iWillSetIdByMySelf and treatZeroAsNull can not be true: it just makes no sense");
                return;
            }
            if (annotation.iWillSetIdByMySelf()) {
                return;
            }
            if (!annotation.treatZeroAsNull()) {
                registryGenerator.codeGenError(idField, "You must generate ids by yourself or use a wrapper class because sqlite will treat non null (including zero) values as ids when you will attempt to insert an entity. You should set iWillSetIdByMySelf to true in @Id annotation if you are ok with that. Alternatively you can set treatZeroAsNull to true");
            }
            return;
        }

        if (annotation.iWillSetIdByMySelf()) {
            registryGenerator.codeGenError(idField, "iWillSetIdByMySelf makes no sense for wrapper classes");
            return;
        }

        if (annotation.treatZeroAsNull()) {
            registryGenerator.codeGenError(idField, "treatZeroAsNull makes no sense for wrapper classes");
        }
    }

    private MethodSpec genTableName() {
        String tableName = context.getTableName();
        return implement(PUBLIC, String.class, "getTableName")
                .addStatement("return $S", tableName)
                .build();
    }

    private MethodSpec genGetCompoundIndexes() {
        return implement(PROTECTED, listOf(COMPOUND_INDEX_CLASS), "getCompoundIndexes")
                .addStatement("return indexes_")
                .build();
    }

    private MethodSpec genConstructor() {
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(TypeNames.SQLITE_DATABASE_CLASS, "db")
                .addParameter(parametrize(EntityInfo.class, context.getTypeName()), "info")
                .addStatement("super(db, info, $L)", context.getAnnotation().sinceVersion())
                .build();
    }

    private VariableElement fieldByName(String name) {
        Optional<VariableElement> result = context.getFields().stream()
                .filter(variableElement -> variableElement.getSimpleName().toString().equals(name))
                .findAny();

        //TODO no throw
        return result
                .orElseThrow(() -> new IllegalArgumentException("Field with name " + name + " not found"));
    }

    private int fieldIndex(String name) {
        return context.getFields().indexOf(fieldByName(name));
    }

}
