package com.github.artyomcool.dante.async;

public interface UiTaskCallback<T> {

    void onSuccess(T result);

    void onError(Throwable t);

}
