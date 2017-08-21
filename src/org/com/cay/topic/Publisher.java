package org.com.cay.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

public class Publisher {

	private final static String TOPIC_NAME = "topic";
	private final static String BROKER_URL = "tcp://localhost:61616";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);

		Connection connection = factory.createConnection();

		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// 创建主题模式的消息目的地
		Destination destination = session.createTopic(TOPIC_NAME);

		MessageProducer producer = session.createProducer(destination);

		// 发送消息
		for (int i = 0; i < 100; ++i) {
			TextMessage textMessage = new ActiveMQTextMessage();
			textMessage.setText(i + "");
			producer.send(textMessage);
			System.out.println("主题模式发送：" + i);
		}

		// 结束
		connection.close();
		System.out.println("发送成功...");
	}

}
