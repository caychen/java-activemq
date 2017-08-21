package org.com.cay.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

public class Producer {

	private final static String QUEUE_NAME = "queue";
	private final static String BROKER_URL = "tcp://localhost:61616";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// 创建ConnectionFactory连接工厂
		ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);

		// 创建Connection
		Connection connection = factory.createConnection();

		// 启动连接
		connection.start();

		// 创建Session会话
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// 创建队列模式的消息发送目的地
		Destination destination = session.createQueue(QUEUE_NAME);

		// 创建生产者
		MessageProducer producer = session.createProducer(destination);

		// 发送消息
		for (int i = 0; i < 100; ++i) {
			TextMessage textMessage = new ActiveMQTextMessage();
			textMessage.setText(i + "");
			producer.send(textMessage);
			System.out.println("队列模式发送：" + i);
		}

		// 结束
		connection.close();
		System.out.println("发送成功...");
	}

}
