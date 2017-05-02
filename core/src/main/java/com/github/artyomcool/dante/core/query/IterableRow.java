package com.github.artyomcool.dante.core.query;

public interface IterableRow extends Row {
    boolean next();
    int getCount();
    void close();
}
