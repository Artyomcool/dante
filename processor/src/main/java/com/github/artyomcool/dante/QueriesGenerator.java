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

import com.github.artyomcool.dante.annotation.Queries;
import com.github.artyomcool.dante.annotation.Query;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.io.IOException;
import java.util.*;

import static com.github.artyomcool.dante.RegistryGenerator.getPackage;
import static javax.lang.model.util.ElementFilter.methodsIn;

public class QueriesGenerator {

    private final RegistryGenerator generator;
    private final Element queries;
    private final Element entity;
    private final Map<String, GeneratedDao> generatedEntities;

    public QueriesGenerator(RegistryGenerator generator, Element queries, Map<String, GeneratedDao> generatedDao) {
        this.generator = generator;
        this.queries = queries;
        this.generatedEntities = generatedDao;

        try {
            queries.getAnnotation(Queries.class).value();
            throw new IllegalStateException();
        } catch (MirroredTypeException e) {
            entity = generator.getProcessingEnv().getTypeUtils().asElement(e.getTypeMirror());
        }
    }

    public GeneratedDao getDao() {
        return generatedEntities.get(getEntityClassName());
    }

    public Element getQueriesElement() {
        return queries;
    }

    private String getEntityClassName() {
        return entity.asType().toString();
    }

    public GeneratedQuery generate() throws IOException {
        GeneratedDao generatedDao = getDao();
        TypeName dao = generatedDao.getDao();

        boolean isInterface;
        switch (queries.getKind()) {
            case INTERFACE:
                isInterface = true;
                break;
            case CLASS:
                isInterface = false;
                break;
            default:
                throw new IllegalArgumentException("Class or interface expected, found " + queries);
        }
        TypeName queriesTypeName = TypeName.get(queries.asType());
        TypeSpec.Builder spec = TypeSpec.classBuilder(queries.getSimpleName() + "_Impl_")
                .addOriginatingElement(queries)
                .addModifiers(Modifier.PUBLIC)
                .addField(dao, "dao", Modifier.PRIVATE, Modifier.FINAL);

        if (isInterface) {
            spec.addSuperinterface(queriesTypeName);
        } else {
            spec.superclass(queriesTypeName);
        }

        spec.addMethod(MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(dao, "dao")
                .addStatement("this.dao = $L", "dao")
                .build());

        methodsIn(queries.getEnclosedElements()).forEach(e -> {
            Query query = e.getAnnotation(Query.class);

            String where = query.where();

            final List<TextReplacement> replacements = new ArrayList<>();
            final SortedSet<ParamReplacement> paramReplacements = new TreeSet<>();

            SQLiteLexer tokenSource = new SQLiteLexer(new ANTLRInputStream(where));
            TokenStream input = new CommonTokenStream(tokenSource);
            SQLiteParser parser = new SQLiteParser(input);

            ParseTreeWalker.DEFAULT.walk(new SQLiteBaseListener() {

                private String referenceName;

                private GeneratedDao getGeneratedDao(String referenceName) {
                    GeneratedDao dao = generatedEntities.get(referenceName);
                    if (dao == null) {
                        String aPackage = getPackage(queries);
                        if (!aPackage.isEmpty()) {
                            dao = generatedEntities.get(aPackage + "." + referenceName);
                        }
                    }
                    return dao;
                }

                @Override
                public void enterTable_name(SQLiteParser.Table_nameContext ctx) {
                    referenceName = ctx.getText();
                    String currentTableName = this.referenceName;
                    GeneratedDao dao = getGeneratedDao(currentTableName);
                    if (dao == null) {
                        generator.codeGenError(e, "Can't find an dao with name " + currentTableName);
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
                    String referenceName = this.referenceName == null ? getEntityClassName() : this.referenceName;
                    GeneratedDao dao = getGeneratedDao(referenceName);
                    if (dao == null) {
                        generator.codeGenError(e, "Can't find the dao with name " + referenceName);
                        return;
                    }
                    try {
                        String columnName = dao.getColumnName(ctx.getText());
                        replacements.add(new TextReplacement(ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex(), columnName));
                    } catch (NoSuchElementException ex) {
                        generator.codeGenError(e, "Can't find a column " + ctx.getText() + " in dao with name " + referenceName);
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

            StringBuilder builder = new StringBuilder();
            int start = 0;
            for (TextReplacement replacement : replacements) {
                builder.append(where.substring(start, replacement.start));
                builder.append(replacement.replacement);
                start = replacement.stop + 1;
            }
            builder.append(where.substring(start));

            //TODO remove Query annotation
            MethodSpec.Builder statementBuilder = MethodSpec.overriding(e)
                    .addStatement("String where = $S", builder)
                    .addCode("\n")
                    .addStatement("String[] params = new String[$L]", paramReplacements.size());

            int i = 0;
            for (ParamReplacement replacement : paramReplacements) {
                statementBuilder.addStatement("params[$L] = String.valueOf($L)", i++, replacement.paramName);
            }

            String statement;

            Types types = generator.getProcessingEnv().getTypeUtils();
            Elements elements = generator.getProcessingEnv().getElementUtils();
            TypeMirror iterableMirror = elements.getTypeElement(Iterable.class.getName()).asType();
            if (types.isAssignable(types.erasure(e.getReturnType()), iterableMirror)) {
                statement = "return dao.selectList(where, params)";
            } else if (types.isAssignable(entity.asType(), e.getReturnType())) {
                statement = "return dao.selectUnique(where, params)";
            } else {
                generator.codeGenError(e, "Unsupported return type: " + e.getReturnType());
                statement = "ERROR();";
            }
            MethodSpec methodSpec = statementBuilder
                    .addCode("\n")
                    .addStatement(statement)
                    .build();

            spec.addMethod(methodSpec);
        });

        TypeSpec typeSpec = spec.build();
        JavaFile file = JavaFile.builder(getPackage(queries), typeSpec)
                .indent("    ")
                .build();

        file.writeTo(generator.getProcessingEnv().getFiler());

        return new GeneratedQuery(this, typeSpec);
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
}
