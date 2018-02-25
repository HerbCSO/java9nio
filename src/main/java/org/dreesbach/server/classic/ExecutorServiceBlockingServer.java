package org.dreesbach.server.classic;

import org.dreesbach.handler.classic.ExecutorServiceHandler;
import org.dreesbach.handler.Handler;
import org.dreesbach.handler.classic.PrintingHandler;
import org.dreesbach.handler.classic.TransmogrifyHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class ExecutorServiceBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        Handler<Socket> handler = new ExecutorServiceHandler<>(new PrintingHandler<>(new TransmogrifyHandler()),
                Executors.newFixedThreadPool(10)
        );
        while (true) {
            Socket socket = ss.accept(); // never null
            handler.handle(socket);
        }
    }
}
