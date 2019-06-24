package com.misout.mq.jms.producer;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by yangkang5
 * Date: 2019/6/24 20:10
 */
public class QueueProducer {

    /** 默认用户名 */
    public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

    /** 默认密码 */
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    /** 默认连接地址 */
    public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("activemq-queue-test1");

            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage("测试点对点的一条消息");
            producer.send(message);

            session.commit();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
