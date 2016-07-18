package com.github.artyomcool.dante.core.dao;

import javax.annotation.Nullable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ObjectWeakValueIdentityHashMap<K, T> {

    private final ReferenceQueue<T> referenceQueue = new ReferenceQueue<>();

    private final Map<K, WeakReference<T>> backingMap = new HashMap<>();

    @Nullable
    public T put(K key, T value) {
        checkReferenceQueue();

        WeakReference<T> old = backingMap.put(key, new WeakEntity<K, T>(key, value, referenceQueue));
        return unwrap(old);
    }

    @Nullable
    public T get(K key) {
        WeakReference<T> old = backingMap.get(key);
        return unwrap(old);
    }

    @Nullable
    public T remove(K key) {
        WeakReference<T> old = backingMap.remove(key);
        return unwrap(old);
    }

    @Nullable
    private T unwrap(@Nullable WeakReference<T> reference) {
        return reference == null ? null : reference.get();
    }

    private void checkReferenceQueue() {
        Reference<? extends T> cleared;
        while ((cleared = referenceQueue.poll()) != null) {
            @SuppressWarnings("unchecked")
            K id = ((WeakEntity<K, T>) cleared).id;
            remove(id);
        }
    }

    private static class WeakEntity<K, T> extends WeakReference<T> {

        final K id;

        public WeakEntity(K id, T referent, ReferenceQueue<T> referenceQueue) {
            super(referent, referenceQueue);
            this.id = id;
        }
    }
}
