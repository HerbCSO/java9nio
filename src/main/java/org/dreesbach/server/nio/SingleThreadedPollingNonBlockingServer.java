package org.dreesbach.server.nio;

import org.dreesbach.handler.Handler;
import org.dreesbach.handler.nio.TransmogrifyChannelHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;

public class SingleThreadedPollingNonBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8080));
        ssc.configureBlocking(false);
        Handler<SocketChannel> handler = new TransmogrifyChannelHandler();
        Collection<SocketChannel> sockets = new ArrayList<>();
        while (true) {
            SocketChannel socketChannel = ssc.accept(); // almost always null
            if (socketChannel != null) {
                sockets.add(socketChannel);
                System.out.println("Connected to " + socketChannel);
                socketChannel.configureBlocking(false);
            }
            for (SocketChannel socket : sockets) {
                if (socket.isConnected()) {
                    handler.handle(socket);
                }
            }
            sockets.removeIf(socket -> !socket.isConnected());
        }
    }
}
