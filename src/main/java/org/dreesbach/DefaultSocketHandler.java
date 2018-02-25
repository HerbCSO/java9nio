package org.dreesbach;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.Socket;

public class DefaultSocketHandler implements SocketHandler {
    public DefaultSocketHandler() { }

    @Override
    public void handle(Socket socket) {
        try (
                socket; // simply declare resource for auto-close (Java 9)
                InputStream in = socket.getInputStream(); OutputStream out = socket.getOutputStream()
        ) {
            int data;
            while ((data = in.read()) != -1) {
                out.write(transmogrify(data));
            }
        }
        catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int transmogrify(int data) {
        return Character.isLetter(data) ? data ^ ' ' : data;
    }
}
