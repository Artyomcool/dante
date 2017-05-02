package com.github.artyomcool.dante;

import com.github.artyomcool.dante.core.query.BaseRowReader;
import com.github.artyomcool.dante.core.query.Row;
import com.github.artyomcool.dante.core.query.RowReader;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import static com.github.artyomcool.dante.Methods.implement;
import static com.github.artyomcool.dante.RegistryGenerator.getPackage;
import static com.github.artyomcool.dante.TypeNames.parametrize;
import static com.github.artyomcool.dante.TypeNames.tryWrap;

public class RowReaderGenerator implements Generator {

    private final RegistryGenerator registryGenerator;
    private final EntityContext context;

    public RowReaderGenerator(RegistryGenerator registryGenerator, EntityContext context) {
        this.registryGenerator = registryGenerator;
        this.context = context;
    }

    @Override
    public GenerationResult generate() {
        TypeSpec.Builder builder = TypeSpec.classBuilder(context.getClassName() + "_RowReader_")
                .addOriginatingElement(context.getElement())
                .addModifiers(Modifier.PUBLIC)
                .superclass(parametrize(BaseRowReader.class, context.getTypeName()))
                .addMethod(genReadEntity());

        TypeSpec typeSpec = builder.build();
        String packageName = getPackage(context.getElement());

        return new GenerationResult(packageName, typeSpec);
    }

    private MethodSpec genReadEntity() {
        MethodSpec.Builder builder = implement(Modifier.PUBLIC, context.getTypeName(), "readEntity")
                .addParameter(Row.class, "cursor")
                .addStatement("$T e = new $T()", context.getTypeName(), context.getTypeName());

        int index = 0;
        for (VariableElement field : context.getFields()) {
            Statement statement = readProperty(field, index++);
            builder.addStatement(statement.getCode(), statement.getArgs());
        }
        
        return builder
                .addStatement("return e")
                .build();
    }

    private Statement readProperty(VariableElement field, int index) {
        TypeMirror typeMirror = field.asType();
        TypeName originalType = ClassName.get(typeMirror);
        if (originalType.toString().equals("byte[]")) {
            return statementFor(field, index, "Blob");
        }
        boolean isPrimitive = originalType.isPrimitive();
        TypeName type = isPrimitive ? tryWrap(originalType) : originalType;

        String typeName = type.toString();

        if (!typeName.startsWith("java.lang.")) {
            registryGenerator.codeGenError(field, "Unsupported type " + originalType);
        }

        int lastDotIndex = typeName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            typeName = typeName.substring(lastDotIndex + 1);
        }
        if (!isPrimitive && !typeName.equals("String")) {
            typeName = "Nullable" + typeName;
        }
        return statementFor(field, index, typeName);
    }

    private Statement statementFor(VariableElement field, int index, String type) {
        return new Statement("e.$L = get$L(cursor, $L)", field.getSimpleName(), type, index);
    }

}
