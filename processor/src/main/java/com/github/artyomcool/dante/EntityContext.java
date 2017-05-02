package com.github.artyomcool.dante;

import com.github.artyomcool.dante.annotation.Entity;
import com.github.artyomcool.dante.annotation.Id;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.artyomcool.dante.TypeNames.parametrize;
import static javax.lang.model.util.ElementFilter.fieldsIn;

public class EntityContext {

    private final RegistryGenerator registryGenerator;
    private final Element element;

    private final Entity annotation;
    private final TypeName typeName;
    private final List<VariableElement> fields;
    private final String className;
    private final String tableName;

    private final Lazy<VariableElement> idField;
    private final Lazy<TypeName> abstractDaoType;

    private final List<GenerationResult> results = new ArrayList<>();

    private final Lazy<GenerationResult> cache;

    private final Lazy<GenerationResult> dao;

    private final Lazy<GenerationResult> rowReader;

    public EntityContext(RegistryGenerator registryGenerator, Element entity) {
        this.registryGenerator = registryGenerator;
        this.element = entity;

        annotation = entity.getAnnotation(Entity.class);

        className = entity.getSimpleName().toString();
        tableName = registryGenerator.toTableName(className);

        typeName = TypeName.get(entity.asType());

        Stream<VariableElement> fieldsStream = fieldsStream((TypeElement) entity);
        fields = fieldsStream
                .filter(e -> !e.getModifiers().contains(Modifier.TRANSIENT) &&
                        !e.getModifiers().contains(Modifier.STATIC))
                .collect(Collectors.toList());

        idField = new Lazy<VariableElement>() {
            @Override
            protected VariableElement calculate() {
                return calcIdField();
            }
        };

        abstractDaoType = new Lazy<TypeName>() {
            @Override
            protected TypeName calculate() {
                return calcAbstractDaoType();
            }
        };

        cache = new Lazy<GenerationResult>() {
            @Override
            protected GenerationResult calculate() {
                return generate(new CacheGenerator(registryGenerator, EntityContext.this));
            }
        };

        dao = new Lazy<GenerationResult>() {
            @Override
            protected GenerationResult calculate() {
                return generate(new DaoGenerator(registryGenerator, EntityContext.this));
            }
        };

        rowReader = new Lazy<GenerationResult>() {
            @Override
            protected GenerationResult calculate() {
                return generate(new RowReaderGenerator(registryGenerator, EntityContext.this));
            }
        };
    }

    private Stream<VariableElement> fieldsStream(TypeElement entity) {
        Types typeUtils = registryGenerator.getProcessingEnv().getTypeUtils();
        Stream<VariableElement> stream = fieldsIn(entity.getEnclosedElements()).stream();
        while (entity.getSuperclass().getKind() != TypeKind.NONE) {
            entity = (TypeElement) typeUtils.asElement(entity.getSuperclass());
            stream = Stream.concat(fieldsIn(entity.getEnclosedElements()).stream(), stream);
        }
        return stream;
    }

    private VariableElement calcIdField() {
        List<VariableElement> elementsWithId = fields.stream()
                .filter(e -> e.getAnnotation(Id.class) != null)
                .collect(Collectors.toList());

        if (elementsWithId.isEmpty()) {
            registryGenerator.codeGenError(element, "No element with @Id annotation");
        } else if (elementsWithId.size() > 1) {
            registryGenerator.codeGenError(element, "Multiple elements with @Id annotation");
        } else {
            return elementsWithId.get(0);
        }

        Elements elements = registryGenerator.getProcessingEnv().getElementUtils();
        TypeElement mockElement = elements.getTypeElement("java.lang.Integer");

        return fieldsStream(mockElement)
                .filter(e -> e.getSimpleName().toString().equals("value"))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Can't create mock value for id in class " + className));
    }

    private TypeName calcAbstractDaoType() {
        TypeElement daoClass = registryGenerator.getAnnotationElement(getAnnotation()::dao);

        int typesCount = daoClass.getTypeParameters().size();
        if (typesCount > 1) {
            registryGenerator.codeGenError(element, "Dao class should have no more then one type parameter: " + daoClass);
            return ClassName.get(daoClass);
        }
        if (typesCount == 0) {
            return ClassName.get(daoClass);
        }

        return parametrize(daoClass, getTypeName());
    }

    public GenerationResult generate(Generator generator) {
        GenerationResult result = generator.generate();
        results.add(result);
        return result;
    }

    public VariableElement getIdField() {
        return idField.get();
    }

    public Element getElement() {
        return element;
    }

    public Entity getAnnotation() {
        return annotation;
    }

    public TypeName getTypeName() {
        return typeName;
    }

    public List<VariableElement> getFields() {
        return fields;
    }

    public String getClassName() {
        return className;
    }

    public String getTableName() {
        return tableName;
    }

    public TypeName getAbstractDaoType() {
        return abstractDaoType.get();
    }

    public String columnName(VariableElement field) {
        return registryGenerator.toTableName(field.getSimpleName().toString());
    }

    public Optional<String> columnName(String fieldName) {
        Optional<VariableElement> variableElement = fields.stream()
                .filter(e -> e.getSimpleName().toString().equals(fieldName))
                .findAny();

        return variableElement.map(this::columnName);
    }

    public GenerationResult accessCache() {
        return cache.get();
    }

    public GenerationResult accessDao() {
        return dao.get();
    }

    public GenerationResult accessRowReader() {
        return rowReader.get();
    }

    public List<GenerationResult> getResults() {
        return results;
    }
}
