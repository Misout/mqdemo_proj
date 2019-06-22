package com.misout.mq.simple.broker;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 消息队列消息中心Broker，负责接收消息和暂存消息
 * @author Misout
 * @date 2019-06-20 20:45:41
 */
public class Broker {

    private static final int MAX_SIZE = 3;

    public static final String CONSUME = "CONSUME";

    /** Broker暂存消息容器队列 */
    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(MAX_SIZE);

    public static void produce(String msg) {
        if(queue.offer(msg)) {
            System.out.println("成功向消息中心投递消息：" + msg + "，当前暂存消息数：" + queue.size());
        } else {
            System.out.println("消息处理中心暂存的消息已达最大容量，不能继续放入消息");
        }
        System.out.println("=================================");
    }

    public static String consume() {
        String msg = queue.poll();
        if(msg != null) {
            System.out.println("已经消费消息：" + msg + "，当前暂存消息数：" + queue.size());
        } else {
            System.out.println("消息处理中心内没有可消费的消息");
        }
        System.out.println("=================================");
        return msg;
    }
}
