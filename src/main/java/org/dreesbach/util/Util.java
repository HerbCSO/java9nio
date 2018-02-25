package org.dreesbach.util;

import java.nio.ByteBuffer;

public class Util {
    public static int transmogrify(int data) {
        return Character.isLetter(data) ? data ^ ' ' : data;
    }

    public static void transmogrify(ByteBuffer buf) {
        // Before read, buf will have:
        // pos=0, limit=80, capacity=80
        // After read, it looks like this:
        // "hello\n" pos=6, limit=80, capacity=80
        // Need to reset pos to 0 for reading
        // buf.flip() is equivalent to running:
        // buf.limit(buf.position());
        // buf.position(0);
        buf.flip();
        // "hello\n" pos=0, limit=6, capacity=80
        for (int i = 0; i < buf.limit(); i++) {
            buf.put(i, (byte) transmogrify(buf.get(i))); // doesn't work with Unicode?
        }
    }
}
