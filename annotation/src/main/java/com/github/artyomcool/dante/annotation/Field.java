package com.github.artyomcool.dante.annotation;

import java.lang.annotation.Target;

import static com.github.artyomcool.dante.annotation.Field.Sort.ASC;

@Target({})
public @interface Field {

    String name();
    Sort order() default ASC;

    enum Sort {
        ASC, DESC
    }

}
