package com.github.artyomcool.dante;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Modifier;

public class Methods {

    public static MethodSpec.Builder implement(Modifier modifier, String name) {
        return implement(modifier, TypeName.VOID, name);
    }

    public static MethodSpec.Builder implement(Modifier modifier, Class<?> returns, String name) {
        return implement(modifier, TypeName.get(returns), name);
    }

    public static MethodSpec.Builder implement(Modifier modifier, TypeName returns, String name) {
        return MethodSpec.methodBuilder(name)
                .addAnnotation(Override.class)
                .addModifiers(modifier)
                .returns(returns);
    }
}
