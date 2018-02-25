package org.dreesbach.handler;

import java.io.IOException;

public class PrintingHandler<S> extends DecoratedHandler<S> {
    public PrintingHandler(Handler<S> other) {
        super(other);
    }

    @Override
    public void handle(S s) throws IOException {
        System.out.println(String.format("Connected to [%s]", s));
        try {
            super.handle(s);
        }
        finally {
            System.out.println(String.format("Disconnected from [%s]", s));
        }
    }
}
