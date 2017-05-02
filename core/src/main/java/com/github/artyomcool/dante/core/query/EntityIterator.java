package com.github.artyomcool.dante.core.query;

import javax.annotation.Nullable;

public interface EntityIterator<E> {

    int getCount();

    @Nullable E next();

    void close();

}