package com.misout.mq.simple.client;

import java.io.IOException;

/**
 * @author Misout
 * @date 2019-06-21 20:37:20
 */
public class ProduceClient {

    public static void main(String[] args) throws IOException {
        MqClient mqClient = new MqClient();
        mqClient.produce("hello world");
    }
}
