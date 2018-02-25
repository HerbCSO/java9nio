package org.dreesbach;

import java.io.IOException;
import java.net.Socket;

public class NastyChump {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket[] socket = new Socket[3000];
        for (int i = 0; i < socket.length; i++) {
            try {
                socket[i] = new Socket("localhost", 8080);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(100_000);
    }
}
