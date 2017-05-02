/*
 * Copyright (c)  2015-2016, Artyom Drozdov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.artyomcool.dante.core.cashe;

import net.jcip.annotations.NotThreadSafe;

import javax.annotation.Nullable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Arrays;

@NotThreadSafe
public class LongWeakValueIdentityHashMap<T> {

    private final ReferenceQueue<T> referenceQueue = new ReferenceQueue<>();

    private WeakEntity[] table;

    private int count;

    public LongWeakValueIdentityHashMap() {
        table = new WeakEntity[16];
    }

    @Nullable
    public T put(long key, T entity) {
        checkReferenceQueue();

        int index = index(key);
        @SuppressWarnings("unchecked")
        WeakEntity<T> e = table[index];
        if (e == null) {
            table[index] = new WeakEntity<>(key, entity, referenceQueue);
        } else {
            WeakEntity prev = null;
            while (e != null) {
                if (e.id == key) {
                    if (e.get() == entity) {
                        return entity;
                    }
                    WeakEntity<T> newEntity = new WeakEntity<>(key, entity, referenceQueue);
                    if (prev == null) {
                        table[index] = newEntity;
                    } else {
                        prev.next = new WeakEntity<>(key, entity, referenceQueue);
                        prev.next.next = e.next;
                    }
                    return e.get();
                }

                prev = e;
                e = e.next;
            }
            prev.next = new WeakEntity<>(key, entity, referenceQueue);
        }

        count++;
        reallocateIfNeed();
        return null;
    }

    @Nullable
    public T get(long key) {
        int index = index(key);
        @SuppressWarnings("unchecked")
        WeakEntity<T> e = table[index];
        while (e != null) {
            if (e.id == key) {
                return e.get();
            }
            e = e.next;
        }
        return null;
    }

    @Nullable
    public T remove(long key) {
        int index = index(key);
        @SuppressWarnings("unchecked")
        WeakEntity<T> e = table[index];
        WeakEntity prev = null;
        while (e != null) {
            if (e.id == key) {
                if (prev == null) {
                    table[index] = null;
                } else {
                    prev.next = e.next;
                }
                count--;
                return e.get();
            }
            prev = e;
            e = e.next;
        }
        return null;
    }

    public void removeAll() {
        Arrays.fill(table, null);
        count = 0;
    }

    private void remove(WeakEntity entity) {
        int index = index(entity.id);
        @SuppressWarnings("unchecked")
        WeakEntity<T> e = table[index];
        WeakEntity prev = null;
        while (e != null) {
            if (e == entity) {
                if (prev == null) {
                    table[index] = null;
                } else {
                    prev.next = e.next;
                }
                count--;
                return;
            }
            prev = e;
            e = e.next;
        }
    }

    private void checkReferenceQueue() {
        Reference<? extends T> cleared;
        while ((cleared = referenceQueue.poll()) != null) {
            WeakEntity weakEntity = (WeakEntity) cleared;
            remove(weakEntity);
        }
    }

    private void reallocateIfNeed() {
        if (count < table.length * 3 / 4) {
            return;
        }
        WeakEntity[] newTable = new WeakEntity[table.length * 2];
        for (WeakEntity aTable : table) {
            WeakEntity entity = aTable;
            while (entity != null) {
                WeakEntity next = entity.next;
                if (!entity.isEnqueued()) {
                    append(entity, newTable);
                }
                entity = next;
            }
        }
        table = newTable;
    }

    private int index(long key) {
        return index(key, table);
    }

    private static int index(long key, WeakEntity[] table) {
        return hashCode(key) % table.length;
    }

    private static void append(WeakEntity entity, WeakEntity[] table) {
        int index = index(entity.id, table);
        entity.next = table[index];
        table[index] = entity;
    }

    private static int hashCode(long key) {
        int r = (int) (key ^ (key >>> 32));
        return r ^ (r >>> 16);
    }

    private static class WeakEntity<T> extends WeakReference<T> {

        final long id;

        WeakEntity<T> next;

        public WeakEntity(long id, T referent, ReferenceQueue<T> referenceQueue) {
            super(referent, referenceQueue);
            this.id = id;
        }
    }

}
