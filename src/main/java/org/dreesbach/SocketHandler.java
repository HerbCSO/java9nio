package org.dreesbach;

import java.net.Socket;

public interface SocketHandler {
    void handle(Socket socket);
}
