package com.github.artyomcool.dante;

import com.github.artyomcool.dante.annotation.*;
import com.github.artyomcool.dante.core.Property;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.VariableElement;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.github.artyomcool.dante.TypeNames.isByteArray;
import static com.github.artyomcool.dante.TypeNames.isPrimitiveWrapper;
import static com.github.artyomcool.dante.TypeNames.isString;

public class PropertyGenerationHelper {

    private final static List<String> NOT_NULL_ANNOTATIONS = Arrays.asList(
            "org.jetbrains.annotations.NotNull",
            "javax.annotation.Nonnull",
            "edu.umd.cs.findbugs.annotations.NonNull",
            "android.support.annotation.NonNull"
    );

    private final RegistryGenerator registryGenerator;
    private final EntityContext context;

    public PropertyGenerationHelper(RegistryGenerator registryGenerator, EntityContext context) {
        this.registryGenerator = registryGenerator;
        this.context = context;
    }

    public CodeBlock buildIdProperty() {
        VariableElement idField = context.getIdField();

        return CodeBlock.builder()
                .add("new $T()", Property.Builder.class).indent().indent()
                .add("\n.columnName($S)", columnName(idField))
                .add("\n.columnType($S)", columnType(idField))
                .add("\n.columnExtraDefinition($S)", idExtraDefinition(idField))
                .add("\n.build()").unindent().unindent()
                .build();
    }

    public CodeBlock buildProperty(VariableElement field) {
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
                .add("\n.index($L, $S, $L)", indexSince(field), indexName(field), indexUnique(field))
                .add("\n.build()").unindent().unindent()
                .build();
    }

    private String columnName(VariableElement field) {
        return context.columnName(field);
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
        return "UNKNOWN";
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

    public static boolean indexUnique(VariableElement field) {
        Index index = field.getAnnotation(Index.class);
        return index != null && index.unique();
    }

    public static Optional<String> getSinceDefault(VariableElement field) {
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

    public static boolean isPrimitiveOrWrapper(VariableElement field) {
        TypeName fieldTypeName = TypeName.get(field.asType());
        return fieldTypeName.isPrimitive() || isPrimitiveWrapper(fieldTypeName);
    }

    public static int indexSince(VariableElement field) {
        Index index = field.getAnnotation(Index.class);
        if (index == null) {
            return Property.NO_INDEX;
        }
        return index.sinceVersion();
    }

    public static int sinceVersion(VariableElement field) {
        SinceVersion annotation = field.getAnnotation(SinceVersion.class);
        return annotation == null ? 1 : annotation.value();
    }

}
