package org.dreesbach.server.classic;

import org.dreesbach.handler.classic.PrintingHandler;
import org.dreesbach.handler.classic.ThreadedHandler;
import org.dreesbach.handler.classic.TransmogrifyHandler;
import org.dreesbach.handler.classic.UncheckedIOExceptionConverterHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        ThreadedHandler<Socket> handler =
                new ThreadedHandler<>(
                        new UncheckedIOExceptionConverterHandler<>(
                                new PrintingHandler<>(
                                        new TransmogrifyHandler()
                                )
                        )
                );
        while (true) {
            Socket socket = ss.accept(); // never null
            handler.handle(socket);
        }
    }
}
