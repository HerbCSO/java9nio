package org.dreesbach.handler.classic;

import org.dreesbach.handler.DecoratedHandler;
import org.dreesbach.handler.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class ExecutorServiceHandler<S> extends DecoratedHandler<S> {

    private final ExecutorService pool;
    private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    public ExecutorServiceHandler(
            Handler<S> other, ExecutorService pool, Thread.UncaughtExceptionHandler uncaughtExceptionHandler
    ) {
        super(other);
        this.pool = pool;
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    public ExecutorServiceHandler(
            Handler<S> other, ExecutorService pool
    ) {
        this(other, pool, (t, e) -> System.out.println(String.format("Uncaught on thread [%s], exception [%s]", t, e)));
    }

    @Override
    public void handle(S s) {
        pool.submit(new FutureTask<>(() -> {
            super.handle(s);
            return null;
        }) {
            @Override
            protected void setException(Throwable t) {
                uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), t);
            }
        });
    }
}
