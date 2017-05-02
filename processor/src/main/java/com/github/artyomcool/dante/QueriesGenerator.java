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

import com.github.artyomcool.dante.annotation.DbQueries;
import com.github.artyomcool.dante.annotation.Query;
import com.github.artyomcool.dante.core.Registry;
import com.github.artyomcool.dante.core.query.DbQueriesBase;
import com.github.artyomcool.dante.core.query.EntityIteratorFactory;
import com.github.artyomcool.dante.core.query.QueryImpl;
import com.squareup.javapoet.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import rx.Observable;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.*;
import java.util.concurrent.Callable;

import static com.github.artyomcool.dante.RegistryGenerator.getPackage;
import static com.github.artyomcool.dante.TypeNames.parametrize;
import static javax.lang.model.util.ElementFilter.methodsIn;

public class QueriesGenerator {

    private final RegistryGenerator generator;
    private final Element queries;
    private final Map<String, EntityContext> generatedEntities;

    public QueriesGenerator(RegistryGenerator generator, Element queries, Map<String, EntityContext> entities) {
        this.generator = generator;
        this.queries = queries;
        this.generatedEntities = entities;
    }

    public QueryGenerationResult generate() {
        TypeName queriesTypeName = TypeName.get(queries.asType());
        TypeSpec.Builder spec = TypeSpec.classBuilder(queries.getSimpleName() + "_Impl_")
                .superclass(DbQueriesBase.class)
                .addSuperinterface(queriesTypeName)
                .addOriginatingElement(queries)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(TypeNames.SQLITE_DATABASE_CLASS, "db")
                        .addParameter(Registry.class, "registry")
                        .addStatement("super(db, registry)")
                        .build());

        List<MethodSpec> methods = new ArrayList<>();
        List<FieldSpec> fields = new ArrayList<>();

        methodsIn(queries.getEnclosedElements()).forEach(e -> {
            Query query = e.getAnnotation(Query.class);
            TypeMirror entityType = getEntityType(e.getReturnType());

            TypeMirror rawFactory;
            try {
                query.factory();
                throw new IllegalStateException("MirroredTypeException expected");
            } catch (MirroredTypeException ex) {
                rawFactory = ex.getTypeMirror();
            }
            if (rawFactory.toString().equals(EntityIteratorFactory.class.getName())) {
                try {
                    queries.getAnnotation(DbQueries.class).factory();
                    throw new IllegalStateException("MirroredTypeException expected");
                } catch (MirroredTypeException ex) {
                    rawFactory = ex.getTypeMirror();
                }
            }

            boolean needParametrize;
            if (rawFactory instanceof DeclaredType) {
                List<? extends TypeMirror> typeArguments = ((DeclaredType) rawFactory).getTypeArguments();
                switch (typeArguments.size()) {
                    case 0:
                        needParametrize = false;
                        break;
                    case 1:
                        needParametrize = true;
                        break;
                    default:
                        generator.codeGenError(e, "Wrong type params count: " + rawFactory);
                        needParametrize = false;
                        break;
                }
            } else {
                needParametrize = false;
            }
            TypeName factory = needParametrize ? parametrize(rawFactory, TypeName.get(entityType)) : TypeName.get(rawFactory);

            String where = query.where();

            final List<TextReplacement> replacements = new ArrayList<>();
            final SortedSet<ParamReplacement> paramReplacements = new TreeSet<>();

            SQLiteLexer tokenSource = new SQLiteLexer(new ANTLRInputStream(where));
            TokenStream input = new CommonTokenStream(tokenSource);
            SQLiteParser parser = new SQLiteParser(input);

            ParseTreeWalker.DEFAULT.walk(new SQLiteBaseListener() {

                private String referenceName;

                @Override
                public void enterTable_name(SQLiteParser.Table_nameContext ctx) {
                    referenceName = ctx.getText();
                    EntityContext dao = getEntityContext(referenceName);
                    if (dao == null) {
                        generator.codeGenError(e, "Can't find an dao with name " + referenceName);
                        return;
                    }
                    String tableName = dao.getTableName();
                    replacements.add(new TextReplacement(ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex(), tableName));
                }

                @Override
                public void exitFull_column_name(SQLiteParser.Full_column_nameContext ctx) {
                    referenceName = null;
                }

                @Override
                public void enterColumn_name(SQLiteParser.Column_nameContext ctx) {
                    String referenceName = this.referenceName == null ? getEntityClassName(entityType) : this.referenceName;
                    EntityContext entity = getEntityContext(referenceName);
                    if (entity == null) {
                        generator.codeGenError(e, "Can't find the entity with name " + referenceName);
                        return;
                    }

                    Optional<String> replacement = entity.columnName(ctx.getText());
                    if (replacement.isPresent()) {
                        replacements.add(new TextReplacement(ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex(), "\"" +replacement.get() + "\""));
                    } else {
                        //TODO allow direct references
                        generator.codeGenError(e, "Can't find a column " + ctx.getText() + " in entity with name " + referenceName);
                    }
                }

                @Override
                public void enterBind_parameter(SQLiteParser.Bind_parameterContext ctx) {
                    replacements.add(new TextReplacement(ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex(), "?"));
                    String text = ctx.bind_name().getText();
                    if (text.startsWith("[") && text.endsWith("]")) {
                        text = text.substring(1, text.length() - 1);
                    }
                    paramReplacements.add(new ParamReplacement(ctx.getStart().getStartIndex(), text));
                }
            }, parser.parse());

            StringBuilder whereBuilder = new StringBuilder();
            int start = 0;
            for (TextReplacement replacement : replacements) {
                whereBuilder.append(where.substring(start, replacement.start));
                whereBuilder.append(replacement.replacement);
                start = replacement.stop + 1;
            }
            whereBuilder.append(where.substring(start));

            TypeName fieldType = parametrize(QueryImpl.class, TypeName.get(entityType));
            String methodName = e.getSimpleName().toString();
            FieldSpec field = FieldSpec.builder(fieldType, methodName, Modifier.PRIVATE, Modifier.FINAL)
                    .initializer("create(new $T(getDb(), reader($T.class), query($T.class, $S)))", factory, entityType, entityType, whereBuilder)
                    .build();

            //TODO remove Query annotation
            MethodSpec.Builder statementBuilder = MethodSpec.overriding(e)
                    .addStatement("final Object[] params = new Object[$L]", paramReplacements.size());

            int i = 0;
            for (ParamReplacement replacement : paramReplacements) {
                statementBuilder.addStatement("params[$L] = $L", i++, replacement.paramName);
            }

            MethodSpec methodSpec = statementBuilder
                    .addCode("\n")
                    .addCode(queryReturn(e.getReturnType(), methodName))
                    .build();

            fields.add(field);
            methods.add(methodSpec);
        });

        fields.forEach(spec::addField);
        methods.forEach(spec::addMethod);

        TypeSpec typeSpec = spec.build();

        String packageName = getPackage(queries);
        GenerationResult generationResult = new GenerationResult(packageName, typeSpec, 0);
        return new QueryGenerationResult(generationResult, queriesTypeName);
    }

    private TypeMirror getEntityType(TypeMirror returnType) {
        if (isObservable(returnType)) {
            return getEntityType(getFirstGenericArg(returnType));
        }
        if (isIterable(returnType)) {
            return getFirstGenericArg(returnType);
        }
        return returnType;
    }

    private TypeMirror getFirstGenericArg(TypeMirror returnType) {
        returnType = ((DeclaredType) returnType).getTypeArguments().get(0);
        return returnType;
    }

    private CodeBlock queryReturn(TypeMirror returnType, String methodName) {
        CodeBlock.Builder builder = CodeBlock.builder();

        if (isObservable(returnType)) {
            TypeMirror wrappedType = getFirstGenericArg(returnType);
            TypeName callableParam = TypeName.get(wrappedType);
            TypeName callableType = parametrize(Callable.class, callableParam);
            TypeSpec callable = TypeSpec.anonymousClassBuilder("")
                    .superclass(callableType)
                    .addMethod(
                            MethodSpec.methodBuilder("call")
                                    .addAnnotation(Override.class)
                                    .addModifiers(Modifier.PUBLIC)
                                    .returns(callableParam)
                                    .addCode(queryReturn(wrappedType, methodName))
                                    .build()
                    )
                    .build();
            builder.addStatement("return $T.fromCallable($L)", Observable.class, callable);
        } else if (isIterable(returnType)) {
            builder.addStatement("return $L.queryList(params)", methodName);
        } else {
            builder.addStatement("return $L.queryUnique(params)", methodName);
        }
        return builder.build();
    }

    private boolean isObservable(TypeMirror returnType) {
        Types types = generator.getProcessingEnv().getTypeUtils();
        Elements elements = generator.getProcessingEnv().getElementUtils();

        TypeElement typeElement = elements.getTypeElement(Observable.class.getName());
        if (typeElement == null) {
            return false;
        }
        TypeMirror observableMirror = types.erasure(typeElement.asType());

        TypeMirror erasure = types.erasure(returnType);
        return erasure.equals(observableMirror);
    }

    private boolean isIterable(TypeMirror returnType) {
        Types types = generator.getProcessingEnv().getTypeUtils();
        Elements elements = generator.getProcessingEnv().getElementUtils();

        TypeMirror iterableMirror = elements.getTypeElement(List.class.getName()).asType();
        return types.isAssignable(iterableMirror, types.erasure(returnType));
    }

    private EntityContext getEntityContext(String referenceName) {
        EntityContext dao = generatedEntities.get(referenceName);
        if (dao == null) {
            String aPackage = getPackage(queries);
            if (!aPackage.isEmpty()) {
                dao = generatedEntities.get(aPackage + "." + referenceName);
            }
        }
        return dao;
    }

    private String getEntityClassName(TypeMirror type) {
        Types typeUtils = generator.getProcessingEnv().getTypeUtils();
        Element element = typeUtils.asElement(type);

        return element.getSimpleName().toString();
    }

    private static class TextReplacement {

        final int start;
        final int stop;
        final String replacement;

        private TextReplacement(int start, int stop, String replacement) {
            this.start = start;
            this.stop = stop;
            this.replacement = replacement;
        }
    }

    private static class ParamReplacement implements Comparable<ParamReplacement> {
        final int start;
        final String paramName;

        private ParamReplacement(int start, String paramName) {
            this.start = start;
            this.paramName = paramName;
        }

        @Override
        public int compareTo(ParamReplacement o) {
            return Integer.compare(start, o.start);
        }
    }

    public static class QueryGenerationResult {

        private final GenerationResult generationResult;
        private final TypeName interfaceName;

        public QueryGenerationResult(GenerationResult generationResult, TypeName interfaceName) {
            this.generationResult = generationResult;
            this.interfaceName = interfaceName;
        }

        public GenerationResult getGenerationResult() {
            return generationResult;
        }

        public TypeName getInterfaceName() {
            return interfaceName;
        }

    }

}
