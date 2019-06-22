package com.misout.mq.simple.broker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Misout
 * @date 2019-06-20 20:58:50
 */
public class BrokerServer implements Runnable {

    public static final int SERVER_PORT = 9999;

    private final Socket socket;

    public BrokerServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            while(true) {
                String str = in.readLine();
                if(str == null) {
                    continue;
                }

                System.out.println("接收到原始数据：" + str);
                if(Broker.CONSUME.equals(str)) {
                    String msg = Broker.consume();
                    out.println(msg);
                    out.flush();
                } else {
                    Broker.produce(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(SERVER_PORT);
        System.out.println("服务已启动");
        while(true) {
            BrokerServer server = new BrokerServer(socket.accept());
            new Thread(server).start();
        }
    }
}
