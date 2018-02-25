package org.dreesbach;

import java.net.Socket;

public class MultiThreadedSocketHandler extends SocketHandlerDecorator {
    public MultiThreadedSocketHandler(SocketHandler socketHandler) {
        super(socketHandler);
    }

    @Override
    public void handle(Socket socket) {
        new Thread(() -> {
            socketHandler.handle(socket);
        }).start();
    }
}
