package org.dreesbach.handler.classic;

import org.dreesbach.handler.Handler;

public class ThreadedHandler<S> extends UncheckedIOExceptionConverterHandler<S> {

    public ThreadedHandler(Handler<S> other) {
        super(other);
    }

    @Override
    public void handle(S s) {
        new Thread(() -> super.handle(s)).start();
    }
}
