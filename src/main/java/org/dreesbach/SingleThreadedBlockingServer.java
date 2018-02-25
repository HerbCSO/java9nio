package org.dreesbach;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadedBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        while (true) {
            Socket socket = ss.accept(); // never null
            new PrintingSocketHandler(new DefaultSocketHandler()).handle(socket);
        }
    }
}
