package com.github.artyomcool.dante.core.query;

public interface Acceptor<E, T> {

    void acceptStart(int count);

    void acceptNext(E next);

    T acceptFinish();

}
