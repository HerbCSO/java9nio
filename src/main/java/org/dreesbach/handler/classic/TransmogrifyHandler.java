package org.dreesbach.handler.classic;

import org.dreesbach.handler.Handler;
import org.dreesbach.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TransmogrifyHandler implements Handler<Socket> {
    @Override
    public void handle(Socket socket) throws IOException {
        try (
                socket; // simply declare resource for auto-close (Java 9)
                InputStream in = socket.getInputStream(); OutputStream out = socket.getOutputStream()
        ) {
            int data;
            while ((data = in.read()) != -1) {
                if (data == '%') throw new IOException("Oopsie");
                out.write(Util.transmogrify(data));
            }
        }
    }
}
