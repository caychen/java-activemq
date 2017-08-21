package org.com.cay.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Subscribe {

	private final static String TOPIC_NAME = "topic";
	private final static String BROKER_URL = "tcp://localhost:61616";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);

		Connection connection = factory.createConnection();

		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// 跟消息生产者发送的目的地一致
		Destination destination = session.createTopic(TOPIC_NAME);

		// 创建消息消费者
		MessageConsumer consumer = session.createConsumer(destination);

		consumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				// TODO Auto-generated method stub
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println("主题模式接收：" + textMessage.getText());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
