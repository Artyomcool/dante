package com.github.artyomcool.dante;

import java.util.Spliterator;
import java.util.function.Consumer;

public class StreamUtils {

    public static <E> void join(Iterable<E> iterable, Consumer<E> action, Consumer<E> between) {
        Spliterator<E> spliterator = iterable.spliterator();

        spliterator.tryAdvance(action);
        spliterator.forEachRemaining(between.andThen(action));
    }

}
