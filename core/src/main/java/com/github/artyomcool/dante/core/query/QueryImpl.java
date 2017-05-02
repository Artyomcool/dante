package com.github.artyomcool.dante.core.query;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueryImpl<E> {

    private final EntityIteratorFactory<E> iteratorFactory;

    public QueryImpl(EntityIteratorFactory<E> iteratorFactory) {
        this.iteratorFactory = iteratorFactory;
    }

    public E queryUnique(@Nullable Object[] args) {
        EntityIterator<E> iterator = iteratorFactory.requery(args);
        try {
            E next = iterator.next();
            if (next == null) {
                return null;
            }
            if (iterator.next() != null) {
                throw new IllegalStateException(iterator.getCount() + " elements matches \"" + iterator+ "\"");
            }
            return next;
        } finally {
            iterator.close();
        }
    }

    public List<E> queryList(@Nullable Object[] args) {
        EntityIterator<E> iterator = iteratorFactory.requery(args);
        try {
            E next = iterator.next();
            if (next == null) {
                return Collections.emptyList();
            }
            List<E> result = new ArrayList<>(iterator.getCount());
            do {
                result.add(next);
                next = iterator.next();
            } while (next != null);

            return result;
        } finally {
            iterator.close();
        }
    }

    public <T> T accept(@Nullable Object[] args, Acceptor<? super E, T> acceptor) {
        EntityIterator<E> iterator = iteratorFactory.requery(args);
        try {
            E next = iterator.next();
            if (next == null) {
                acceptor.acceptStart(0);
                return acceptor.acceptFinish();
            }
            acceptor.acceptStart(iterator.getCount());
            do {
                acceptor.acceptNext(next);
                next = iterator.next();
            } while (next != null);

            return acceptor.acceptFinish();
        } finally {
            iterator.close();
        }
    }

}
