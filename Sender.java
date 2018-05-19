package cn.jzj.rabbitMQdemo;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {

  /**
   * @param args
   */
  private final static String QUEUE_NAME = "hello";

  public static void main(String[] args) throws java.io.IOException, TimeoutException {
    // 创建链接工厂
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    // 创建链接
    Connection connection = factory.newConnection();
    // 创建信息管道
    Channel channel = connection.createChannel();
    // 创建一个名为hello的队列
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    String message = "Hello World!";
    // 在RabbitMQ中,消息是不能直接发送到队列,它需要发送到交换器(exchange)中。
    // 第一参数空表示使用默认exchange,第二参数表示发送到的queue,第三参数是发送的消息是(字节数组)
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    System.out.println(" [x] Sent '" + message + "'");
    try {
		channel.close();
	} catch (TimeoutException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//关闭管道
    connection.close();//关闭连接
  }
}