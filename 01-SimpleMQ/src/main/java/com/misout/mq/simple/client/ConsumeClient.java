package com.misout.mq.simple.client;

import java.io.IOException;

/**
 * @author Misout
 * @date 2019-06-21 20:39:02
 */
public class ConsumeClient {
    public static void main(String[] args) throws IOException {
        MqClient mqClient = new MqClient();
        String msg = mqClient.consume();
        System.out.println("获取的消息为：" + msg);
    }
}
