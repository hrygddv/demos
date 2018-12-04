package com.hryg.demos.kafkademo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author hryg
 * Created on 2018-12-04
 */
@Service
public class IndicatorService {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    public IndicatorService(KafkaTemplate<Integer, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "#{kafkaTopicName}", groupId = "#{topicGroupId}")
    public void processMessage(ConsumerRecord<Integer, String> record) {
        LOG.info("kafka processMessage start...");
        LOG.info("processMessage, topic = {}, msg = {}", record.topic(), record.value());

        // do something ...

        LOG.info("kafka processMessage end");
    }

    public void sendMessage(String topic, String data) {
        LOG.info("kafka sendMessage start");
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, data);
        future.addCallback(result -> LOG.info("kafka sendMessage success topic = {}, data = {}", topic, data),
                ex -> LOG.error("kafka sendMessage error, ex = {}, topic = {}, data = {}", ex, topic, data));
        LOG.info("kafka sendMessage end");
    }
}
