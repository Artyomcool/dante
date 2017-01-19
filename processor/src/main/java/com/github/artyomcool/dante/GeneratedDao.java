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

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;
import java.util.NoSuchElementException;

import static com.github.artyomcool.dante.RegistryGenerator.getPackage;

public class GeneratedDao {

    private final DaoGenerator generator;
    private final TypeSpec dao;
    private final int maxVersion;

    public GeneratedDao(DaoGenerator generator, TypeSpec dao, int maxVersion) {
        this.generator = generator;
        this.dao = dao;
        this.maxVersion = maxVersion;
    }

    public Element getEntity() {
        return generator.getEntity();
    }

    public ClassName getDao() {
        return ClassName.get(getPackage(getEntity()), dao.name);
    }

    public String getQualifiedName() {
        return getEntity().asType().toString();
    }

    public String getTableName() {
        return generator.getTableName();
    }

    public String getColumnName(String fieldName) throws NoSuchElementException {
        VariableElement variableElement = generator.getFields().stream()
                .filter(e -> e.getSimpleName().toString().equals(fieldName))
                .findFirst()
                .get();
        return generator.getRegistryGenerator().toTableName(variableElement.getSimpleName().toString());
    }

    public int getMaxVersion() {
        return maxVersion;
    }

}
