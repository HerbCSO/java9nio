package org.dreesbach;

import java.net.Socket;

public abstract class SocketHandlerDecorator implements SocketHandler {
    protected SocketHandler socketHandler;

    public SocketHandlerDecorator(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    public void handle(Socket socket) {
        socketHandler.handle(socket);
    }
}
