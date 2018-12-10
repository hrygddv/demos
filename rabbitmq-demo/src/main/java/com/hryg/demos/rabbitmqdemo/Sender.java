package com.hryg.demos.rabbitmqdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 *
 * @author hryg
 * Created on 2018/11/20
 */
public class Sender extends ConnectionChannel {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    // 持久化队列名称
    private String queueName;

    public Sender(String routingKey) throws IOException, TimeoutException {
        super(routingKey);
        this.queueName = "queue_topic";
    }

    public Sender(String routingKey, String queueName) throws IOException, TimeoutException {
        super(routingKey);
        this.queueName = queueName;
    }

    public void sendMessage(byte[] bodys) throws IOException {

        // 声明一个持久化队列
        channel.queueDeclare(queueName, true, false, false, null);

        // 设置通道预取计数
        channel.basicQos(1);

        // 将消息队列绑定到 exchange
        channel.queueBind(queueName, EXCHANGE_NAME, routingKey);

        /*
         * 发送消息到队列中
         * 参数1：交换机exchange名字，若为空则使用默认的exchange[]
         * 参数2：routing key - 路由地址
         * 参数3：其他的属性
         * 参数4：消息体
         * RabbitMQ默认有一个exchange，叫default exchange，它用一个空字符串表示，它是direct exchange类型，
         * 任何发往这个exchange的消息都会被路由到routing key的名字对应的队列上，如果没有对应的队列，则消息会被丢弃
         */
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, bodys);
        LOG.info("PDM消息发送成功 -- [ " + EXCHANGE_NAME + " ] - " + routingKey);
    }
}
