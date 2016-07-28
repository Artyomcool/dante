package com.github.artyomcool.dante.async;

import android.os.Handler;
import android.os.Looper;

import javax.annotation.Nullable;

public abstract class UiDaoTask<T> implements DaoTask<T> {

    private static final Handler handler = new Handler(Looper.getMainLooper());

    private @Nullable UiTaskCallback<? super T> callback;

    public UiDaoTask(@Nullable UiTaskCallback<? super T> callback) {
        this.callback = callback;
    }

    public void clearCallback() {
        setCallback(null);
    }

    public void setCallback(@Nullable UiTaskCallback<? super T> callback) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new RuntimeException("Can be invoked in the UI thread only");
        }
        this.callback = callback;
    }

    @Override
    public void onSuccess(final T result) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        });
    }

    @Override
    public void onError(final Throwable t) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onError(t);
                }
            }
        });
    }
}
