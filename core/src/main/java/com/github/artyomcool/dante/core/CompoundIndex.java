package com.github.artyomcool.dante.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompoundIndex {

    private CompoundIndex(Builder builder) {
        sinceVersion = builder.sinceVersion;
        name = builder.name;
        properties = Collections.unmodifiableList(new ArrayList<>(builder.properties));
        unique = builder.unique;
    }

    private final int sinceVersion;
    private final String name;
    private final List<Field> properties;
    private final boolean unique;

    public int getSinceVersion() {
        return sinceVersion;
    }

    public String getName() {
        return name;
    }

    public List<Field> getFields() {
        return properties;
    }

    public boolean isUnique() {
        return unique;
    }

    public static final class Builder {
        private final List<Field> properties = new ArrayList<>();

        private int sinceVersion;
        private String name;
        private boolean unique;

        public Builder() {
        }

        public Builder sinceVersion(int val) {
            sinceVersion = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder property(Property property, boolean desc) {
            properties.add(new Field(property, desc));
            return this;
        }

        public Builder unique(boolean val) {
            unique = val;
            return this;
        }

        public CompoundIndex build() {
            return new CompoundIndex(this);
        }
    }

    public static class Field {
        private final Property property;
        private final boolean desc;

        Field(Property property, boolean desc) {
            this.property = property;
            this.desc = desc;
        }

        public Property getProperty() {
            return property;
        }

        public boolean isDesc() {
            return desc;
        }
    }
}
