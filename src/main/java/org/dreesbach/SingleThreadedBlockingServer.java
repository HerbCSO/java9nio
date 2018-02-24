package org.dreesbach;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadedBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        while (true) {
            Socket s = ss.accept(); // never null
            handle(s);
        }
    }

    private static void handle(Socket socket) throws IOException {
        System.out.println(String.format("Connected to socket [%s]", socket));
        try (
                socket; // simply declare resource for auto-close (Java 9)
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream()
        ) {
            int data;
            while ((data = in.read()) != -1) {
                out.write(transmogrify(data));
            }
        }
        finally {
            System.out.println(String.format("Disconnected from socket [%s]", socket));
        }
    }

    private static int transmogrify(int data) {
        return Character.isLetter(data) ? data ^ ' ' : data;
    }
}
