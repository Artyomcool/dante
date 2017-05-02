package com.github.artyomcool.dante.core;

import com.github.artyomcool.dante.core.cashe.Cache;
import com.github.artyomcool.dante.core.query.CachedRowReader;
import com.github.artyomcool.dante.core.query.RowReader;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityInfo<E> {

    private final Class<E> entityClass;

    private final RowReader<E> reader;

    @Nullable
    private final Cache<E> cache;

    @Nullable
    private final CachedRowReader<E> cachedRowReader;

    private final List<Property> properties;

    private final Property idProperty;

    private final int idIndex;

    EntityInfo(Builder<E> builder) {
        entityClass = builder.entityClass;
        reader = builder.rowReader;
        cache = builder.cache;
        properties = Collections.unmodifiableList(builder.properties);
        idProperty = builder.idProperty;

        idIndex = properties.indexOf(idProperty);

        cachedRowReader = cache == null ? null : new CachedRowReader<>(reader, cache, idIndex);
    }

    public Class<E> getEntityClass() {
        return entityClass;
    }

    public RowReader<E> getReader() {
        return reader;
    }

    @Nullable
    public Cache<E> getCache() {
        return cache;
    }

    @Nullable
    public CachedRowReader<E> getCachedRowReader() {
        return cachedRowReader;
    }

    public RowReader<E> getCachedOrSimpleRowReader() {
        return cachedRowReader == null ? reader : cachedRowReader;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public Property getIdProperty() {
        return idProperty;
    }

    public int getIdIndex() {
        return idIndex;
    }

    public static class Builder<E> {

        Class<E> entityClass;
        RowReader<E> rowReader;
        Cache<E> cache;
        List<Property> properties = new ArrayList<>();
        Property idProperty;

        public Builder(Class<E> entityClass) {
            this.entityClass = entityClass;
        }

        public Builder<E> rowReader(RowReader<E> reader) {
            this.rowReader = reader;
            return this;
        }

        public Builder<E> cache(Cache<E> cache) {
            this.cache = cache;
            return this;
        }

        public Builder<E> property(Property property) {
            this.properties.add(property);
            return this;
        }

        public Builder<E> idProperty(Property property) {
            this.idProperty = property;
            this.properties.add(property);
            return this;
        }

        public EntityInfo<E> build() {
            return new EntityInfo<>(this);
        }

    }

}
