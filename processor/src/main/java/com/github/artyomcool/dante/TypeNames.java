package com.github.artyomcool.dante;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import java.util.List;
import java.util.Map;

public class TypeNames {

    public static final TypeName SQLITE_DATABASE_CLASS = ClassName.get("android.database.sqlite", "SQLiteDatabase");
    public static final TypeName SQLITE_STATEMENT_CLASS = ClassName.get("android.database.sqlite", "SQLiteStatement");

    public static TypeName listOf(Class<?> clazz) {
        return ParameterizedTypeName.get(List.class, clazz);
    }

    public static TypeName listOf(TypeName clazz) {
        return parametrize(List.class, clazz);
    }

    public static TypeName mapOf(TypeName key, TypeName value) {
        return parametrize(Map.class, key, value);
    }

    public static TypeName parametrize(Class<?> clazz, TypeName... typeNames) {
        return ParameterizedTypeName.get(ClassName.get(clazz), typeNames);
    }

    public static TypeName parametrize(TypeElement clazz, TypeName... typeNames) {
        return ParameterizedTypeName.get(ClassName.get(clazz), typeNames);
    }

    public static TypeName parametrize(TypeMirror clazz, TypeName... typeNames) {
        TypeElement typeElement = (TypeElement) ((DeclaredType) clazz).asElement();
        return ParameterizedTypeName.get(ClassName.get(typeElement), typeNames);
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

    public static TypeName tryWrap(TypeName typeName) {
        if (typeName.isPrimitive()) {
            return typeName.box();
        }
        return typeName;
    }

}
