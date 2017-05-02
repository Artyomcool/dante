package com.github.artyomcool.dante.async;

import com.github.artyomcool.dante.core.dao.DaoMaster;
import com.github.artyomcool.dante.core.dao.DaoRegistry;
import com.github.artyomcool.dante.core.db.DatabaseOpener;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
public class Async {

    private final ExecutorService executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<Runnable>());

    private final AtomicLong number = new AtomicLong(Long.MIN_VALUE);

    private final DaoMaster master;

    //should be accessed only in the executor's thread
    private boolean initialized;

    public Async(DatabaseOpener opener) {
        this(new DaoMaster(opener));
    }
    public Async(DatabaseOpener opener, DaoRegistry registry) {
        this(new DaoMaster(opener, registry));
    }

    private Async(DaoMaster master) {
        this.master = master;
    }

    public <T> void execute(DaoTask<T> task) {
        execute(task, 0);
    }

    public <T> void execute(final DaoTask<T> task, int priority) {
        executor.execute(new ComparableTask(priority, number.getAndIncrement()) {

            @Override
            public void run() {
                T result;
                try {
                    result = task.execute(getMaster());
                } catch (Throwable t) {
                    task.onError(t);
                    return;
                }
                task.onSuccess(result);
            }
        });
    }

    public <T> Future<T> submit(DaoCallable<T> task) {
        return submit(task, 0);
    }

    public <T> Future<T> submit(final DaoCallable<T> task, int priority) {
        final FutureTask<T> delegate = new FutureTask<>(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return task.call(getMaster());
            }
        });

        executor.execute(new ComparableTask(priority, number.getAndIncrement()) {
            @Override
            public void run() {
                delegate.run();
            }
        });

        return delegate;
    }

    //should be accessed only in the executor's thread
    private DaoMaster getMaster() {
        if (!initialized) {
            master.init();
            initialized = true;
        }
        return master;
    }

    private abstract class ComparableTask implements Runnable, Comparable<ComparableTask> {

        private final int priority;
        private final long number;

        protected ComparableTask(int priority, long number) {
            this.priority = priority;
            this.number = number;
        }

        @Override
        public int compareTo(@Nonnull ComparableTask o) {
            //can't use Long.compare and Integer.compare because of old Android API
            int otherPriority = o.priority;
            if (priority < otherPriority) {
                return -1;
            }
            if (priority > otherPriority) {
                return 1;
            }
            long otherNumber = o.number;
            return number < otherNumber ? -1 : number > otherNumber ? 1 : 0;
        }
    }

}
