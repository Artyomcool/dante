package com.github.artyomcool.dante.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface CompoundIndex {

    int sinceVersion() default 1;
    String name() default "";
    Field[] fields();
    boolean unique() default false;

}
