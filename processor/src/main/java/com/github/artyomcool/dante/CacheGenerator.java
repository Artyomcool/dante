package com.github.artyomcool.dante;

import com.github.artyomcool.dante.core.cashe.LongIdCache;
import com.github.artyomcool.dante.core.cashe.StringIdCache;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;

import static com.github.artyomcool.dante.Methods.implement;
import static com.github.artyomcool.dante.RegistryGenerator.getPackage;
import static com.github.artyomcool.dante.TypeNames.parametrize;
import static com.github.artyomcool.dante.TypeNames.tryUnwrap;

public class CacheGenerator implements Generator {

    private final RegistryGenerator registryGenerator;
    private final EntityContext context;

    public CacheGenerator(RegistryGenerator registryGenerator, EntityContext context) {
        this.registryGenerator = registryGenerator;
        this.context = context;
    }

    @Override
    public GenerationResult generate() {
        Element idField = context.getIdField();

        TypeMirror typeMirror = idField.asType();
        TypeName typeName = tryUnwrap(ClassName.get(typeMirror));
        switch (typeName.toString()) {
            case "boolean":
                return generateId(LongIdCache.class, Long.TYPE, " ? 1L : 0L");
            case "float":
            case "double":
            case "byte[]":
                throw new UnsupportedOperationException("Not implemented yet");
            case "byte":
            case "int":
            case "short":
            case "long":
            case "char":
                return generateId(LongIdCache.class, Long.TYPE, "");
            default:
                registryGenerator.codeGenError(idField, "Unsupported type");
            case "java.lang.String":
                return generateId(StringIdCache.class, String.class, "");
        }

    }

    private GenerationResult generateId(Class<?> cacheClass, Class<?> resultType, String conversion) {
        TypeSpec.Builder builder = TypeSpec.classBuilder(context.getClassName() + "_Cache_")
                .addOriginatingElement(context.getElement())
                .addModifiers(Modifier.PUBLIC)
                .superclass(parametrize(cacheClass, context.getTypeName()))
                .addMethod(genGetId(resultType, conversion));

        TypeSpec typeSpec = builder.build();
        String packageName = getPackage(context.getElement());

        return new GenerationResult(packageName, typeSpec);
    }

    private MethodSpec genGetId(Class<?> resultType, String conversion) {
        return implement(Modifier.PROTECTED, resultType, "getId")
                .addParameter(context.getTypeName(), "e")
                .addStatement("return e.$L" + conversion, context.getIdField().getSimpleName())
                .build();
    }

}
