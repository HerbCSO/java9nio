package org.dreesbach.server.nio;

import org.dreesbach.handler.nio.BlockingChannelHandler;
import org.dreesbach.handler.classic.ExecutorServiceHandler;
import org.dreesbach.handler.Handler;
import org.dreesbach.handler.classic.PrintingHandler;
import org.dreesbach.handler.nio.TransmogrifyChannelHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executors;

public class BlockingNIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8080));
        Handler<SocketChannel> handler =
                new ExecutorServiceHandler<>(
                        new PrintingHandler<>(
                                new BlockingChannelHandler(
                                    new TransmogrifyChannelHandler()
                                )
                        ),
                Executors.newFixedThreadPool(10)
        );
        while (true) {
            SocketChannel socketChannel = ssc.accept(); // never null
            handler.handle(socketChannel);
        }
    }
}
