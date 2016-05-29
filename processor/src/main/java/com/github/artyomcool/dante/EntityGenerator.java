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

import com.github.artyomcool.dante.annotation.Id;
import com.github.artyomcool.dante.core.dao.AbstractDao;
import com.github.artyomcool.dante.core.property.BoxingTypeProperty;
import com.github.artyomcool.dante.core.property.IdProperty;
import com.github.artyomcool.dante.core.property.Property;
import com.squareup.javapoet.*;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

import static com.github.artyomcool.dante.RegistryGenerator.*;
import static javax.lang.model.element.Modifier.PROTECTED;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.util.ElementFilter.fieldsIn;

public class EntityGenerator {

    private final RegistryGenerator registryGenerator;
    private final Element entity;

    private final ParameterizedTypeName parameterizedAbstractDaoType;
    private final ParameterizedTypeName propertyType;
    private final ParameterizedTypeName idPropertyType;
    private final ParameterizedTypeName listOfPropertiesType;
    private final TypeName entityTypeName;
    private final List<VariableElement> fields;
    private final VariableElement idField;
    private final String entityClassName;
    private final String tableName;

    public EntityGenerator(RegistryGenerator registryGenerator, Element entity) {
        this.registryGenerator = registryGenerator;
        this.entity = entity;

        entityClassName = entity.getSimpleName().toString();
        tableName = registryGenerator.toTableName(entityClassName);

        entityTypeName = TypeName.get(entity.asType());

        parameterizedAbstractDaoType = ParameterizedTypeName.get(
                ClassName.get(AbstractDao.class),
                entityTypeName
        );

        propertyType = ParameterizedTypeName.get(
                ClassName.get(Property.class),
                entityTypeName
        );
        idPropertyType = ParameterizedTypeName.get(
                ClassName.get(IdProperty.class),
                entityTypeName
        );
        listOfPropertiesType = ParameterizedTypeName.get(
                ClassName.get(List.class),
                propertyType
        );

        fields = fieldsIn(entity.getEnclosedElements()).stream()
                .filter(e -> !e.getModifiers().contains(Modifier.TRANSIENT))
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

    public GeneratedEntity generate() throws IOException {

        TypeSpec.Builder daoClass = TypeSpec.classBuilder(entityClassName + "_Dao_")
                .addOriginatingElement(entity)
                .addModifiers(Modifier.PUBLIC)
                .superclass(parameterizedAbstractDaoType)
                .addField(genFields())
                .addField(genIdField())
                .addMethod(genConstructor())
                .addMethod(genCreateEntity())
                .addMethod(genProperties())
                .addMethod(genIdProperty())
                .addMethod(genTableName(tableName));

        TypeSpec typeSpec = daoClass.build();
        JavaFile file = JavaFile.builder(getPackage(entity), typeSpec)
                .indent("    ")
                .build();

        file.writeTo(registryGenerator.getProcessingEnv().getFiler());

        return new GeneratedEntity(this, typeSpec);
    }

    private FieldSpec genIdField() {
        return FieldSpec.builder(
                idPropertyType,
                "id_",
                Modifier.PRIVATE,
                Modifier.FINAL
        )
                .initializer("$L", getIdType())
                .build();
    }

    private TypeSpec getIdType() {
        Id idAnnotation = idField.getAnnotation(Id.class);
        return TypeSpec.anonymousClassBuilder("fields_.get($L), $L", fields.indexOf(idField), idAnnotation.preventAutoincrement())
                .superclass(idPropertyType)
                .addMethod(getIdAfterInsert())
                .addMethod(MethodSpec.methodBuilder("isNull")
                                .returns(Boolean.TYPE)
                                .addModifiers(Modifier.PUBLIC)
                                .addParameter(entityTypeName, "entity")
                                .addCode(getIdIsNull(idField, idAnnotation))
                                .addAnnotation(Override.class)
                                .build()
                )
                .build();
    }

    private MethodSpec getIdAfterInsert() {
        return MethodSpec.methodBuilder("afterInsert")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(entityTypeName, "entity")
                .addParameter(Long.TYPE, "value")
                .addCode(getAfterIdStatement())
                .build();
    }

    private CodeBlock getAfterIdStatement() {
        Id annotation = idField.getAnnotation(Id.class);

        TypeName typeName = TypeName.get(idField.asType());
        if (isString(typeName) || isByteArray(typeName)) {
            if (!annotation.iWillSetIdByMySelf()) {
                registryGenerator.codeGenError(idField, "You must generate string and blob ids by yourself because sqlite can't generate them for you, so you should set iWillSetIdByMySelf to true in @Id annotation if you are ok with that");
            }
            return noCode();
        }

        if (typeName.isPrimitive()) {
            if (annotation.iWillSetIdByMySelf() && annotation.treatZeroAsNull()) {
                registryGenerator.codeGenError(idField, "Both iWillSetIdByMySelf and treatZeroAsNull can not be true: it just makes no sense");
                return noCode();
            }
            if (annotation.iWillSetIdByMySelf()) {
                return noCode();
            }
            if (!annotation.treatZeroAsNull()) {
                registryGenerator.codeGenError(idField, "You must generate ids by yourself or use a wrapper class because sqlite will treat non null (including zero) values as ids when you will attempt to insert an entity. You should set iWillSetIdByMySelf to true in @Id annotation if you are ok with that. Alternatively you can set treatZeroAsNull to true");
            }
            return getSimpleAfterIdStatement();
        }

        if (annotation.iWillSetIdByMySelf()) {
            registryGenerator.codeGenError(idField, "iWillSetIdByMySelf makes no sense for wrapper classes");
            return noCode();
        }

        if (annotation.treatZeroAsNull()) {
            registryGenerator.codeGenError(idField, "treatZeroAsNull makes no sense for wrapper classes");
            return noCode();
        }

        return getSimpleAfterIdStatement();
    }

    private CodeBlock getSimpleAfterIdStatement() {
        TypeName typeName = TypeName.get(idField.asType());
        if (!typeName.isPrimitive()) {
            typeName = typeName.unbox();
        }
        if (TypeName.LONG == typeName) {
            return statement("entity.$L = value", idField.getSimpleName());
        } else if (TypeName.BOOLEAN == typeName) {
            return statement("entity.$L = value != 0", idField.getSimpleName());
        } else {
            return statement("entity.$L = ($T) value", idField.getSimpleName(), typeName);
        }
    }
    private CodeBlock getIdIsNull(Element idField, Id idAnnotation) {
        TypeName fieldType = TypeName.get(idField.asType());
        if (fieldType.isPrimitive()) {
            return idAnnotation.treatZeroAsNull()
                    ? statement("return entity.$L == 0", idField.getSimpleName())
                    : statement("return false");
        }
        return statement("return entity.$L == null", idField.getSimpleName());
    }

    private FieldSpec genFields() {

        CodeBlock.Builder code = CodeBlock.builder()
                .add("$T.asList(", Arrays.class);
        code.indent();

        Spliterator<VariableElement> spliterator = fields.spliterator();

        spliterator.tryAdvance(field -> buildProperty(code, field));
        spliterator.forEachRemaining(field -> {
            code.add(",\n");
            buildProperty(code, field);
        });

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

    private void buildProperty(CodeBlock.Builder builder, VariableElement field) {
        builder.add("\n");

        TypeName fieldTypeName = TypeName.get(field.asType());
        if (isString(fieldTypeName)) {
            builder.add("$L", getStringProperty(field));
            return;
        }

        if (fieldTypeName.isPrimitive()) {
            builder.add("$L", primitiveProperty(field));
            return;
        }

        if (isPrimitiveWrapper(fieldTypeName)) {
            builder.add("$L", getBoxingProperty(field));
            return;
        }

        builder.add("new UnknownFieldType($S, $S)", fieldTypeName, field.getSimpleName());
    }

    private MethodSpec genTableName(String tableName) {
        return implement(PROTECTED, String.class, "getTableName")
                .addStatement("return $S", tableName)
                .build();
    }

    private MethodSpec genIdProperty() {
        return implement(PROTECTED, idPropertyType, "getIdProperty")
                .addStatement("return id_")
                .build();
    }

    private MethodSpec genProperties() {
        return implement(PROTECTED, listOfPropertiesType, "getProperties")
                .addStatement("return fields_")
                .build();
    }

    private MethodSpec genCreateEntity() {
        return implement(PROTECTED, entityTypeName, "createEntity")
                .addStatement("return new $T()", entityTypeName)
                .build();
    }

    private TypeSpec getStringProperty(VariableElement field) {
        return property(field, TypeName.get(field.asType()), ", false");
    }

    private TypeSpec primitiveProperty(VariableElement field) {
        TypeName typeName = TypeName.get(field.asType());
        if (!typeName.isPrimitive()) {
            typeName = typeName.unbox();
        }
        return property(field, typeName, "");
    }

    private TypeSpec property(VariableElement field, TypeName typeName, String extraParams) {
        String name = getSimpleName(typeName);
        ClassName rawType = ClassName.get(
                "com.github.artyomcool.dante.core.property",
                capitalize(name) + "Property"
        );
        ParameterizedTypeName superclass = ParameterizedTypeName.get(
                rawType,
                TypeName.get(entity.asType())
        );
        String columnName = registryGenerator.toTableName(field.getSimpleName().toString());
        return TypeSpec.anonymousClassBuilder("$S" + extraParams, columnName)
                .superclass(superclass)
                .addMethod(
                        propertySet(field, typeName)
                ).addMethod(
                        propertyGet(field, typeName)
                )
                .build();
    }

    private String getSimpleName(TypeName typeName) {
        String name = typeName.toString();
        int lastDot = name.lastIndexOf('.');
        if (lastDot == -1) {
            return name;
        }
        return name.substring(lastDot + 1);
    }

    private TypeSpec getBoxingProperty(VariableElement field) {
        TypeSpec primitiveDelegate = primitiveProperty(field);

        return TypeSpec.anonymousClassBuilder("$L", primitiveDelegate)
                .superclass(ParameterizedTypeName.get(ClassName.get(BoxingTypeProperty.class), entityTypeName))
                .addMethod(MethodSpec.methodBuilder("isNull")
                                .returns(Boolean.TYPE)
                                .addModifiers(Modifier.PUBLIC)
                                .addParameter(entityTypeName, "entity")
                                .addStatement("return entity.$L == null", field.getSimpleName())
                                .addAnnotation(Override.class)
                                .build()
                )
                .build();
    }

    private MethodSpec propertySet(VariableElement field, TypeName typeName) {
        return implement(PUBLIC, "set")
                .addParameter(typeName, "value")
                .addParameter(entityTypeName, "entity")
                .addStatement("entity.$L = value", field.getSimpleName())
                .build();
    }

    private MethodSpec propertyGet(VariableElement field, TypeName typeName) {
        return implement(PUBLIC, typeName, "get")
                .addParameter(entityTypeName, "entity")
                .addStatement("return entity.$L", field.getSimpleName())
                .build();
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
                .addStatement("super(db)")
                .build();
    }

}
