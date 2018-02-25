package org.dreesbach.server;

import org.dreesbach.handler.ExecutorServiceHandler;
import org.dreesbach.handler.Handler;
import org.dreesbach.handler.PrintingHandler;
import org.dreesbach.handler.TransmogrifyHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class ExecutorServiceBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        Handler<Socket> handler = new ExecutorServiceHandler<>(
                new PrintingHandler<>(
                        new TransmogrifyHandler()
                ), Executors.newCachedThreadPool(), (t, e) -> System.out.println(String.format("Uncaught on thread [%s], "
                + "exception [%s]", t, e))
        );
        while (true) {
            Socket socket = ss.accept(); // never null
            handler.handle(socket);
        }
    }
}
