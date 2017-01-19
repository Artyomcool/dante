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
import com.github.artyomcool.dante.annotation.Id;
import com.github.artyomcool.dante.annotation.Index;
import com.github.artyomcool.dante.annotation.SinceVersion;
import com.github.artyomcool.dante.core.Property;
import com.github.artyomcool.dante.core.dao.LongWeakValueIdentityHashMap;
import com.github.artyomcool.dante.core.dao.ObjectWeakValueIdentityHashMap;
import com.squareup.javapoet.*;

import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.artyomcool.dante.RegistryGenerator.*;
import static javax.lang.model.element.Modifier.PROTECTED;
import static javax.lang.model.util.ElementFilter.fieldsIn;

public class DaoGenerator {

    private final static List<String> NOT_NULL_ANNOTATIONS = Arrays.asList(
            "org.jetbrains.annotations.NotNull",
            "javax.annotation.Nonnull",
            "edu.umd.cs.findbugs.annotations.NonNull",
            "android.support.annotation.NonNull"
    );

    private final static TypeName CURSOR_CLASS_NAME = ClassName.get("android.database", "Cursor");
    private final static TypeName SQLITE_STATEMENT_CLASS_NAME = ClassName.get("android.database.sqlite", "SQLiteStatement");

    private final RegistryGenerator registryGenerator;
    private final Element entity;
    private final Entity annotation;

    private final TypeName abstractDaoType;
    private final ParameterizedTypeName listOfPropertiesType;
    private final TypeName entityTypeName;
    private final List<VariableElement> fields;
    private final VariableElement idField;
    private final String entityClassName;
    private final String tableName;

    public DaoGenerator(RegistryGenerator registryGenerator, Element entity) {
        this.registryGenerator = registryGenerator;
        this.entity = entity;

        annotation = entity.getAnnotation(Entity.class);

        entityClassName = entity.getSimpleName().toString();
        tableName = registryGenerator.toTableName(entityClassName);

        entityTypeName = TypeName.get(entity.asType());

        TypeElement daoClass = registryGenerator.getAnnotationElement(annotation::dao);

        int typesCount = daoClass.getTypeParameters().size();
        if (typesCount == 0) {
            abstractDaoType = ClassName.get(daoClass);
        } else if (typesCount == 1) {
            ClassName typeName = ClassName.get(daoClass);
            abstractDaoType = ParameterizedTypeName.get(
                    typeName,
                    entityTypeName
            );
        } else {
            throw new IllegalArgumentException("Dao class should have no more then one type parameter: " + daoClass);
        }

        listOfPropertiesType = ParameterizedTypeName.get(
                ClassName.get(List.class),
                ClassName.get(Property.class)
        );

        fields = fieldsIn(entity.getEnclosedElements()).stream()
                .filter(e -> !e.getModifiers().contains(Modifier.TRANSIENT) &&
                        !e.getModifiers().contains(Modifier.STATIC))
                .collect(Collectors.toList());

        List<VariableElement> elementsWithId = fields.stream()
                .filter(e -> e.getAnnotation(Id.class) != null)
                .collect(Collectors.toList());

        if (elementsWithId.isEmpty()) {
            registryGenerator.codeGenError(entity, "No element with @Id annotation");
            throw new IllegalArgumentException("Can't generate entity without id");
        } else if (elementsWithId.size() > 1) {
            registryGenerator.codeGenError(entity, "Multiple elements with @Id annotation");
            throw new IllegalArgumentException("Can't generate entity with multiple ids");
        } else {
            idField = elementsWithId.get(0);
        }
    }

    public Element getEntity() {
        return entity;
    }

    public String getTableName() {
        return tableName;
    }

    public List<VariableElement> getFields() {
        return fields;
    }

    public RegistryGenerator getRegistryGenerator() {
        return registryGenerator;
    }

    public GeneratedDao generate() throws IOException {
        verifyIdStatement();

        TypeSpec.Builder daoClass = TypeSpec.classBuilder(entityClassName + "_Dao_")
                .addOriginatingElement(entity)
                .addModifiers(Modifier.PUBLIC)
                .superclass(abstractDaoType)
                .addField(genCacheField())
                .addField(genIdField())
                .addField(genFields())
                .addMethod(genConstructor())
                .addMethod(genTableName())
                .addMethod(genProperties())
                .addMethod(genIdProperty())
                .addMethod(genCreateEntity())
                .addMethod(genBind())
                .addMethod(genBindId())
                .addMethod(genUpdateRowId())
                .addMethod(genGetFromCache())
                .addMethod(genPutIntoCache())
                .addMethod(genRemoveFromCache())
                .addMethod(genClearCache());

        TypeSpec typeSpec = daoClass.build();
        JavaFile file = JavaFile.builder(getPackage(entity), typeSpec)
                .indent("    ")
                .build();

        file.writeTo(registryGenerator.getProcessingEnv().getFiler());

        int maxVersion = getMaxVersion();
        return new GeneratedDao(this, typeSpec, maxVersion);
    }

    private int getMaxVersion() {
        int version = annotation.sinceVersion();
        OptionalInt max = IntStream.concat(
                fields.stream().mapToInt(this::sinceVersion),
                fields.stream().mapToInt(this::indexSince)).max();
        if (max.isPresent()) {
            version = Math.max(version, max.getAsInt());
        }
        return version;
    }

    private FieldSpec genIdField() {
        return FieldSpec.builder(
                ClassName.get(Property.class),
                "id_",
                Modifier.PRIVATE,
                Modifier.FINAL
        )
                .initializer("$L", genIdInitializer())
                .build();
    }

    private FieldSpec genFields() {

        CodeBlock.Builder code = CodeBlock.builder()
                .add("$T.asList(", Arrays.class);
        code.indent();

        join(fields,
                field -> code.add(buildProperty(field)),
                field -> code.add(",\n")
        );

        code.add("\n");
        code.unindent();
        code.add(")");

        FieldSpec.Builder fields_ = FieldSpec.builder(
                listOfPropertiesType,
                "fields_",
                Modifier.PRIVATE,
                Modifier.FINAL
        );
        return fields_
                .initializer(code.build())
                .build();
    }

    private <E> void join(Iterable<E> iterable, Consumer<E> action, Consumer<E> between) {
        Spliterator<E> spliterator = iterable.spliterator();

        spliterator.tryAdvance(action);
        spliterator.forEachRemaining(between.andThen(action));
    }

    private CodeBlock genIdInitializer() {
        return CodeBlock.builder()
                .add("new $T()", Property.Builder.class).indent().indent()
                .add("\n.columnName($S)", columnName(idField))
                .add("\n.columnType($S)", columnType(idField))
                .add("\n.columnExtraDefinition($S)", idExtraDefinition(idField))
                .add("\n.build()").unindent().unindent()
                .build();
    }

    private CodeBlock buildProperty(VariableElement field) {
        if (field.getAnnotation(Id.class) != null) {
            return CodeBlock.builder().add("id_").build();
        }

        return CodeBlock.builder()
                .add("new $T()", Property.Builder.class).indent().indent()
                .add("\n.sinceVersion($L)", sinceVersion(field))
                .add("\n.columnName($S)", columnName(field))
                .add("\n.columnType($S)", columnType(field))
                .add("\n.columnExtraDefinition($S)", extraDefinition(field))
                .add("\n.defaultValue($S)", defaultValue(field))
                .add("\n.index($L, $S)", indexSince(field), indexName(field))
                .add("\n.build()").unindent().unindent()
                .build();
    }

    private int sinceVersion(VariableElement field) {
        SinceVersion annotation = field.getAnnotation(SinceVersion.class);
        return annotation == null ? 1 : annotation.value();
    }

    private String columnName(VariableElement field) {
        return registryGenerator.toTableName(field.getSimpleName().toString());
    }

    private String columnType(VariableElement field) {
        TypeName fieldTypeName = TypeName.get(field.asType());
        if (isString(fieldTypeName)) {
            return "TEXT";
        }

        if (isByteArray(fieldTypeName)) {
            return "BLOB";
        }

        try {
            fieldTypeName = fieldTypeName.unbox();
        } catch (UnsupportedOperationException ignored) {
        }

        if (fieldTypeName.isPrimitive()) {
            if (fieldTypeName == TypeName.FLOAT || fieldTypeName == TypeName.DOUBLE) {
                return "REAL";
            }
            return "INTEGER";
        }

        registryGenerator.codeGenError(field, "Unsupported field type");
        throw new IllegalArgumentException("Unsupported field type: " + fieldTypeName);
    }

    private String idExtraDefinition(VariableElement field) {
        Id idAnnotation = field.getAnnotation(Id.class);
        boolean autoIncrement = !idAnnotation.preventAutoincrement() && isPrimitiveOrWrapper(field);
        if (autoIncrement) {
            return "PRIMARY KEY AUTOINCREMENT";
        } else {
            return "PRIMARY KEY";
        }
    }

    private String extraDefinition(VariableElement field) {
        return isNullable(field) ? "" : "NOT NULL";
    }

    private String defaultValue(VariableElement field) {
        Optional<String> sinceDefault = getSinceDefault(field);
        if (sinceDefault.isPresent()) {
            return sinceDefault.get();
        }
        if (field.asType().getKind().isPrimitive()) {
            return "0";
        }
        return isNullable(field) ? "NULL" : "''";
    }

    private int indexSince(VariableElement field) {
        Index index = field.getAnnotation(Index.class);
        if (index == null) {
            return Property.NO_INDEX;
        }
        return index.sinceVersion();
    }

    private String indexName(VariableElement field) {
        Index index = field.getAnnotation(Index.class);
        if (index == null) {
            return "";
        }
        String indexName = index.name();
        if (indexName.isEmpty()) {
            return "IDX_" + columnName(field);
        }
        return indexName;
    }

    private Optional<String> getSinceDefault(VariableElement field) {
        SinceVersion sinceVersion = field.getAnnotation(SinceVersion.class);
        if (sinceVersion == null) {
            return Optional.empty();
        }
        String defaultValue = sinceVersion.defaultValue();
        if (defaultValue.equals(SinceVersion.NOT_SPECIFIED)) {
            return Optional.empty();
        }
        return Optional.of(defaultValue);
    }

    private boolean isPrimitiveOrWrapper(VariableElement field) {
        TypeName fieldTypeName = TypeName.get(field.asType());
        return fieldTypeName.isPrimitive() || isPrimitiveWrapper(fieldTypeName);
    }

    private MethodSpec genBind() {
        MethodSpec.Builder methodBuilder = implement(PROTECTED, "bind")
                .addParameter(entityTypeName, "entity")
                .addParameter(SQLITE_STATEMENT_CLASS_NAME, "statement");
        int index = 1;
        for (VariableElement field : fields) {
            if (field == idField) {
                methodBuilder.addStatement("bindId(entity, statement, $L)", index++);
                continue;
            }
            CodeBlock.Builder builder = CodeBlock.builder();
            builder.add("{\n").indent();
            builder.addStatement("$T value = entity.$L", field.asType(), field.getSimpleName());
            builder.add(bindProperty(field, index++));
            builder.unindent().add("}\n");

            methodBuilder.addCode(builder.build());
        }
        return methodBuilder.build();
    }

    private MethodSpec genBindId() {
        MethodSpec.Builder methodBuilder = implement(PROTECTED, "bindId")
                .addParameter(entityTypeName, "entity")
                .addParameter(SQLITE_STATEMENT_CLASS_NAME, "statement")
                .addParameter(Integer.TYPE, "index");

        TypeName typeName = ClassName.get(idField.asType());
        Name fieldName = idField.getSimpleName();

        methodBuilder.addStatement("$T value = entity.$L", typeName, fieldName);

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
                .addParameter(entityTypeName, "entity")
                .addParameter(TypeName.LONG, "rowId");

        Name fieldName = idField.getSimpleName();

        if (isPrimitiveOrWrapper(idField)) {
            TypeName typeName = ClassName.get(idField.asType());
            typeName = tryUnwrap(typeName);
            String value = typeName.equals(TypeName.BOOLEAN)
                    ? "rowId != 0"
                    : "(" + typeName + ") rowId";
            methodBuilder.addStatement("entity.$L = $L", fieldName, value);
        }

        return methodBuilder.build();
    }


    private FieldSpec genCacheField() {
        TypeMirror typeMirror = idField.asType();
        TypeName typeName = tryUnwrap(ClassName.get(typeMirror));
        switch (typeName.toString()) {
            case "boolean":
            case "float":
            case "double":
            case "byte[]":
                throw new UnsupportedOperationException("Not implemented yet");
            case "byte":
            case "int":
            case "short":
            case "long":
            case "char":
                TypeName fieldType = ParameterizedTypeName.get(
                        ClassName.get(LongWeakValueIdentityHashMap.class),
                        entityTypeName
                );
                return FieldSpec.builder(fieldType, "cache", Modifier.PRIVATE, Modifier.FINAL)
                        .initializer("new $T<>()", LongWeakValueIdentityHashMap.class)
                        .build();
            case "java.lang.String":
                fieldType = ParameterizedTypeName.get(
                        ClassName.get(ObjectWeakValueIdentityHashMap.class),
                        ClassName.get(String.class),
                        entityTypeName
                );
                return FieldSpec.builder(fieldType, "cache", Modifier.PRIVATE, Modifier.FINAL)
                        .initializer("new $T<>()", ObjectWeakValueIdentityHashMap.class)
                        .build();
            default:
                registryGenerator.codeGenError(idField, "Unsupported type");
                throw new IllegalArgumentException("Unsupported type: " + typeName);
        }
    }

    private MethodSpec genGetFromCache() {
        MethodSpec.Builder methodBuilder = implement(PROTECTED, entityTypeName, "getFromCache")
                .addParameter(CURSOR_CLASS_NAME, "cursor")
                .addParameter(TypeName.INT, "index");

        TypeMirror typeMirror = idField.asType();
        TypeName typeName = tryUnwrap(ClassName.get(typeMirror));
        switch (typeName.toString()) {
            case "boolean":
                throw new UnsupportedOperationException("Not implemented yet");
            case "byte":
            case "int":
            case "char":
                methodBuilder.addStatement("return cache.get(cursor.getInt(index))");
                break;
            case "short":
                methodBuilder.addStatement("return cache.get(cursor.getShort(index))");
                break;
            case "long":
                methodBuilder.addStatement("return cache.get(cursor.getLong(index))");
                break;
            case "float":
                methodBuilder.addStatement("return cache.get(cursor.getFloat(index))");
                break;
            case "double":
                methodBuilder.addStatement("return cache.get(cursor.getDouble(index))");
                break;
            case "byte[]":
                methodBuilder.addStatement("return cache.get(cursor.getBlob(index))");
                break;
            case "java.lang.String":
                methodBuilder.addStatement("return cache.get(cursor.getString(index))");
                break;
            default:
                registryGenerator.codeGenError(idField, "Unsupported type");
                throw new IllegalArgumentException("Unsupported type: " + typeName);
        }
        return methodBuilder.build();
    }

    private MethodSpec genPutIntoCache() {
        MethodSpec.Builder methodBuilder = implement(PROTECTED, "putIntoCache")
                .addParameter(entityTypeName, "entity");

        methodBuilder.addStatement("cache.put(entity.$L, entity)", idField.getSimpleName());

        return methodBuilder.build();
    }

    private MethodSpec genRemoveFromCache() {
        MethodSpec.Builder methodBuilder = implement(PROTECTED, "removeFromCache")
                .addParameter(entityTypeName, "entity");

        methodBuilder.addStatement("cache.remove(entity.$L)", idField.getSimpleName());

        return methodBuilder.build();
    }

    private MethodSpec genClearCache() {
        MethodSpec.Builder methodBuilder = implement(PROTECTED, "clearCache");

        methodBuilder.addStatement("cache.removeAll()");

        return methodBuilder.build();
    }

    private MethodSpec genCreateEntity() {
        MethodSpec.Builder methodBuilder = implement(PROTECTED, entityTypeName, "createEntity")
                .addParameter(CURSOR_CLASS_NAME, "cursor");
        methodBuilder.addStatement("$T entity = new $T()", entityTypeName, entityTypeName);
        int index = 0;
        for (VariableElement field : fields) {
            Statement statement = readProperty(field, index++);
            methodBuilder.addStatement(statement.getCode(), statement.getArgs());
        }
        methodBuilder.addStatement("return entity");
        return methodBuilder.build();
    }

    private Statement readProperty(VariableElement field, int index) {
        Name fieldName = field.getSimpleName();
        TypeMirror typeMirror = field.asType();
        TypeName typeName = ClassName.get(typeMirror);
        switch (typeName.toString()) {
            case "boolean":
                return new Statement("entity.$L = cursor.getInt($L) != 0", fieldName, index);
            case "java.lang.Boolean":
                return new Statement("entity.$L = cursor.isNull($L) ? null : (cursor.getInt($L) != 0)", fieldName, index, index);
            case "byte":
                return new Statement("entity.$L = (byte) cursor.getInt($L)", fieldName, index);
            case "java.lang.Byte":
                return new Statement("entity.$L = cursor.isNull($L) ? null : (byte) cursor.getInt($L)", fieldName, index, index);
            case "short":
                return new Statement("entity.$L = cursor.getShort($L)", fieldName, index);
            case "java.lang.Short":
                return new Statement("entity.$L = cursor.isNull($L) ? null : cursor.getShort($L)", fieldName, index, index);
            case "int":
                return new Statement("entity.$L = cursor.getInt($L)", fieldName, index);
            case "java.lang.Integer":
                return new Statement("entity.$L = cursor.isNull($L) ? null : cursor.getInt($L)", fieldName, index, index);
            case "long":
                return new Statement("entity.$L = cursor.getLong($L)", fieldName, index);
            case "java.lang.Long":
                return new Statement("entity.$L = cursor.isNull($L) ? null : cursor.getLong($L)", fieldName, index, index);
            case "char":
                return new Statement("entity.$L = (char) cursor.getInt($L)", fieldName, index);
            case "java.lang.Character":
                return new Statement("entity.$L = cursor.isNull($L) ? null : (char) cursor.getInt($L)", fieldName, index, index);
            case "float":
                return new Statement("entity.$L = cursor.getFloat($L)", fieldName, index);
            case "java.lang.Float":
                return new Statement("entity.$L = cursor.isNull($L) ? null : cursor.getFloat($L)", fieldName, index, index);
            case "double":
                return new Statement("entity.$L = cursor.getDouble($L)", fieldName, index);
            case "java.lang.Double":
                return new Statement("entity.$L = cursor.isNull($L) ? null : cursor.getDouble($L)", fieldName, index, index);
            case "byte[]":
                return new Statement("entity.$L = cursor.getBlob($L)", fieldName, index);
            case "java.lang.String":
                return new Statement("entity.$L = cursor.getString($L)", fieldName, index);
        }
        registryGenerator.codeGenError(field, "Unsupported type");
        throw new IllegalArgumentException("Unsupported type: " + typeName);
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
        throw new IllegalArgumentException("Unsupported type: " + typeName);
    }

    private void verifyIdStatement() {
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
        return implement(PROTECTED, String.class, "getTableName")
                .addStatement("return $S", tableName)
                .build();
    }

    private MethodSpec genIdProperty() {
        return implement(PROTECTED, Property.class, "getIdProperty")
                .addStatement("return id_")
                .build();
    }

    private MethodSpec genProperties() {
        return implement(PROTECTED, listOfPropertiesType, "getProperties")
                .addStatement("return fields_")
                .build();
    }

    private boolean isNullable(VariableElement field) {
        if (field.asType().getKind().isPrimitive()) {
            return false;
        }
        for (AnnotationMirror annotation : field.getAnnotationMirrors()) {
            String typeName = ClassName.get(annotation.getAnnotationType()).toString();
            if (NOT_NULL_ANNOTATIONS.contains(typeName)) {
                return false;
            }
        }
        return true;
    }

    private MethodSpec.Builder implement(Modifier modifier, String name) {
        return implement(modifier, TypeName.VOID, name);
    }

    private MethodSpec.Builder implement(Modifier modifier, Class<?> returns, String name) {
        return implement(modifier, TypeName.get(returns), name);
    }

    private MethodSpec.Builder implement(Modifier modifier, TypeName returns, String name) {
        return MethodSpec.methodBuilder(name)
                .addAnnotation(Override.class)
                .addModifiers(modifier)
                .returns(returns);
    }

    private MethodSpec genConstructor() {
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(sqliteDatabase(), "db")
                .addStatement("super(db, $L)", annotation.sinceVersion())
                .build();
    }

}
