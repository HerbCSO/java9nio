package org.dreesbach.server;

import org.dreesbach.handler.PrintingHandler;
import org.dreesbach.handler.TransmogrifyHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadedBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        PrintingHandler<Socket> handler =
                new PrintingHandler<>(
                        new TransmogrifyHandler()
                );
        while (true) {
            Socket socket = ss.accept(); // never null
            handler.handle(socket);
        }
    }
}
