package com.github.artyomcool.dante.core.query;

import javax.annotation.Nullable;

public interface EntityIteratorFactory<E> {

    EntityIterator<E> requery(@Nullable Object[] args);

}
