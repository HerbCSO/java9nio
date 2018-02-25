package org.dreesbach;

import java.net.Socket;

public class PrintingSocketHandler extends SocketHandlerDecorator {
    public PrintingSocketHandler(SocketHandler socketHandler) {
        super(socketHandler);
    }

    @Override
    public void handle(Socket socket) {
        System.out.println(String.format("Connected to socket [%s]", socket));
        socketHandler.handle(socket);
        System.out.println(String.format("Disconnected from socket [%s]", socket));
    }
}
