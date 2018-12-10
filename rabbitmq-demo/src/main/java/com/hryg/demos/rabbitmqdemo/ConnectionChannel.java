package com.hryg.demos.rabbitmqdemo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 抽象 rabbitmq连接通道 类
 *
 * @author hryg
 * Created on 2018/11/20
 */
public abstract class ConnectionChannel {
    protected static final String HOST = "127.0.0.1";
    protected static final String USER = "hrygddv";
    protected static final String PASSWORD = "123456";

    // 消息服务请求 URL
    public static final String CMS_HOST = "http://" + HOST + ":8080";
    // routing key
    public static final String ROUTING_KEY = "YOU.SELF.KEY";

    // 连接
    protected Connection connection;
    // 连接通道
    protected Channel channel;
    // 连接通道路由地址
    protected String routingKey;

    // 交换机名称
    protected final static String EXCHANGE_NAME = "cms";

    // 构造方法
    public ConnectionChannel(String routingKey) throws IOException, TimeoutException {
        this.routingKey = routingKey;

        // 创建一个连接工厂 connection factory
        ConnectionFactory factory = new ConnectionFactory();

        // 设置rabbitmq-server服务IP地址、用户名、密码、端口
        factory.setHost(HOST);
        factory.setPort(5672);
        factory.setUsername(USER);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost("/");

        // 创建一个连接
        connection = factory.newConnection();

        // 声明消息通道
        channel = connection.createChannel();

        /*
         * 声明转发器 - 定义一个交换机
         * 参数1：交换机名称
         * 参数2：交换机类型
         * 参数3：交换机持久性，如果为true则服务器重启时不会丢失
         * 参数4：交换机在不被使用时是否删除 参数5：交换机的其他属性
         */
        channel.exchangeDeclare(EXCHANGE_NAME, "topic", false, false, null);
    }

    /**
     * 关闭channel和connection，非必须，因为隐含是自动调用的。
     *
     * @throws IOException
     * @throws TimeoutException
     */
    public void close() throws IOException, TimeoutException {
        this.channel.close();
        this.connection.close();
    }
}
