package org.com.cay.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {

	private final static String QUEUE_NAME = "queue";
	private final static String BROKER_URL = "tcp://localhost:61616";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);

		Connection connection = factory.createConnection();

		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// 跟消息生产者发送的目的地一致
		Destination destination = session.createQueue(QUEUE_NAME);

		// 创建消息消费者
		MessageConsumer consumer = session.createConsumer(destination);

		while (true) {
			// 设置接收者接收消息的时间
			TextMessage message = (TextMessage) consumer.receive(10000);
			if (null != message) {
				System.out.println("队列模式接收：" + message.getText());
			} else {
				break;
			}
		}

	}

}
