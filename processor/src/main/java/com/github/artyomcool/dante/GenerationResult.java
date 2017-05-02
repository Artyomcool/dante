package com.github.artyomcool.dante;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

public class GenerationResult {

    private final TypeName typeName;
    private final String packageName;
    private final TypeSpec typeSpec;
    private final int maxVersion;

    public GenerationResult(String packageName, TypeSpec typeSpec) {
        this(packageName, typeSpec, 0);
    }

    public GenerationResult(String packageName, TypeSpec typeSpec, int maxVersion) {
        this.packageName = packageName;
        this.typeSpec = typeSpec;
        this.typeName = ClassName.get(packageName, typeSpec.name);
        this.maxVersion = maxVersion;
    }

    public TypeName getTypeName() {
        return typeName;
    }

    public String getPackageName() {
        return packageName;
    }

    public TypeSpec getTypeSpec() {
        return typeSpec;
    }

    public int getMaxVersion() {
        return maxVersion;
    }
}
