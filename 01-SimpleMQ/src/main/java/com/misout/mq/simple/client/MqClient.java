package com.misout.mq.simple.client;

import com.misout.mq.simple.broker.Broker;
import com.misout.mq.simple.broker.BrokerServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Misout
 * @date 2019-06-21 20:30:29
 */
public class MqClient {

    public void produce(String msg) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.SERVER_PORT);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println(msg);
        out.flush();
        out.close();
    }

    public String consume() throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.SERVER_PORT);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println(Broker.CONSUME);
        out.flush();

        String msg = in.readLine();
        out.close();
        in.close();
        return msg;
    }
}
