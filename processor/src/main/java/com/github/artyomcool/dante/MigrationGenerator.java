package com.github.artyomcool.dante;

import com.github.artyomcool.dante.annotation.Migration.OnVersion;
import com.github.artyomcool.dante.core.migration.Migration;
import com.github.artyomcool.dante.core.migration.MigrationInfo;
import com.squareup.javapoet.*;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.util.*;
import java.util.stream.Collectors;

import static com.github.artyomcool.dante.RegistryGenerator.getPackage;

public class MigrationGenerator {

    private final TypeElement element;
    private final ClassName type;
    private int version = 1;

    public MigrationGenerator(Element element) {
        this.element = (TypeElement) element;
        this.type = ClassName.get(this.element);
    }

    public GenerationResult generate() {

        ParameterizedTypeName returnType = ParameterizedTypeName.get(List.class, Migration.class);

        CodeBlock.Builder codeBuilder = CodeBlock.builder();
        codeBuilder.addStatement("$T typeSpec = new $T<>()", returnType, ArrayList.class);
        codeBuilder.add("switch(onVersion) {\n");

        getMigrations().forEach(migrations -> {
            codeBuilder.add("case $L:$>\n", migrations.getVersion());
            migrations.methods.forEach(e ->
                    codeBuilder.addStatement("typeSpec.add($L)",
                            TypeSpec.anonymousClassBuilder("")
                                    .superclass(Migration.class)
                                    .addMethod(
                                            MethodSpec.methodBuilder("migrate")
                                                    .addModifiers(Modifier.PUBLIC)
                                                    .addAnnotation(Override.class)
                                                    .addStatement("migration.$L(db)", e.getSimpleName())
                                                    .build()
                                    )
                                    .build()
                    ));
            codeBuilder.add("break;$<\n");

            version = Math.max(version, migrations.getVersion());
        });

        codeBuilder.add("}\n");
        codeBuilder.addStatement("return typeSpec");

        TypeSpec typeSpec = TypeSpec.classBuilder(element.getSimpleName().toString() + "_Info_")
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(MigrationInfo.class)
                .addField(
                        FieldSpec.builder(type, "migration", Modifier.PRIVATE, Modifier.FINAL)
                                .initializer("new $T()", type)
                                .build()
                )
                .addMethod(
                        MethodSpec.methodBuilder("migrations")
                                .addModifiers(Modifier.PUBLIC)
                                .returns(returnType)
                                .addParameter(TypeNames.SQLITE_DATABASE_CLASS, "db", Modifier.FINAL)
                                .addParameter(TypeName.INT, "onVersion")
                                .addAnnotation(Override.class)
                                .addCode(codeBuilder.build())
                                .build()
                )
                .build();

        return new GenerationResult(getPackage(element), typeSpec, version);
    }

    private Collection<VersionMigrations> getMigrations() {
        Map<Integer, VersionMigrations> resultMap = new HashMap<>();
        element.getEnclosedElements().forEach(e -> {
            OnVersion version = e.getAnnotation(OnVersion.class);
            if (version == null) {
                return;
            }
            resultMap.computeIfAbsent(version.value(), VersionMigrations::new).methods.add(e);
        });
        return resultMap
                .values()
                .stream()
                .sorted(Comparator.comparingInt(VersionMigrations::getVersion))
                .collect(Collectors.toList());
    }

    private static class VersionMigrations {

        private final List<Element> methods = new ArrayList<>();
        private final int version;

        private VersionMigrations(int version) {
            this.version = version;
        }

        public int getVersion() {
            return version;
        }
    }

}
