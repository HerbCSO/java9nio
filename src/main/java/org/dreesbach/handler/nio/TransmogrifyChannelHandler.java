package org.dreesbach.handler.nio;

import org.dreesbach.handler.Handler;
import org.dreesbach.util.Util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TransmogrifyChannelHandler implements Handler<SocketChannel> {
    @Override
    public void handle(SocketChannel socketChannel) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(80); // expensive to allocate
        int read = socketChannel.read(buf);
        if (read == -1) {
            socketChannel.close();
            return;
        }
        if (read > 0) {
            Util.transmogrify(buf);
            while (buf.hasRemaining()) {
                socketChannel.write(buf);
            }
//            buf.compact(); // use compact() instead of reset() to ensure any leftover bits in the buffer go to the front
        }
    }
}
