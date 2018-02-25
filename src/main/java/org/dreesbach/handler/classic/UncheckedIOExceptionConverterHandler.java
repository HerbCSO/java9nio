package org.dreesbach.handler.classic;

import org.dreesbach.handler.DecoratedHandler;
import org.dreesbach.handler.Handler;

import java.io.IOException;
import java.io.UncheckedIOException;

public class UncheckedIOExceptionConverterHandler<S> extends DecoratedHandler<S> {
    public UncheckedIOExceptionConverterHandler(Handler<S> other) {
        super(other);
    }

    public void handle(S s) {
        try {
            super.handle(s);
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
