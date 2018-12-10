package com.hryg.demos.rabbitmqdemo;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 *
 * @author hryg
 * Created on 2018/11/20
 */
public class Receiver extends ConnectionChannel {
    private String queueName;

    public Receiver(String routingKey) throws IOException, TimeoutException {
        super(routingKey);
        this.queueName = "queue_topic";
    }

    public Receiver(String routingKey, String queueName) throws IOException, TimeoutException {
        super(routingKey);
        this.queueName = queueName;
    }

    public void getMessage() throws IOException {

        // 声明一个临时队列，该队列会在实用完成吼自动销毁 - 非必需
        queueName = channel.queueDeclare().getQueue();

        // 声明要关注的队列 - 非必需
//        channel.queueDeclare(queueName, true, false, false, null);

        channel.basicQos(1);

        channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
    }
}
