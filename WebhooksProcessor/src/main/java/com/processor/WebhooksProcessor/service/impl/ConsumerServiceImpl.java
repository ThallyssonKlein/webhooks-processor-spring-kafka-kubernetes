package com.processor.WebhooksProcessor.service.impl;

import com.processor.WebhooksProcessor.service.ConsumerService;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public final class ConsumerServiceImpl implements ConsumerService {

    @KafkaListener(topics = "kafkaTopic", groupId = "group_id")
    @Override
    public void consume(String message) {
        System.out.println(message);
    }
}