package com.github.artyomcool.dante.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface CompoundIndexes {
    CompoundIndex[] value();
}
