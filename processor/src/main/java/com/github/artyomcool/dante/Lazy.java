package com.github.artyomcool.dante;

public abstract class Lazy<E> {

    private E e;
    private boolean calculated;

    public E get() {
        if (!calculated) {
            e = calculate();
            calculated = true;
        }
        return e;
    }

    protected abstract E calculate();

}
