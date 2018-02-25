package org.dreesbach.handler;

import java.io.IOException;
import java.io.UncheckedIOException;

public class UncheckedIOExceptionConverterHandler<S> implements Handler<S> {
    private final Handler<S> other;

    public UncheckedIOExceptionConverterHandler(Handler<S> other) {
        this.other = other;
    }

    public void handle(S s) {
        try {
            other.handle(s);
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
