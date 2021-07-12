package com.payments.PaymentsAPI.service.impl;

import com.google.gson.Gson;
import com.payments.PaymentsAPI.entity.Payment;
import com.payments.PaymentsAPI.service.ProducerService;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public final class ProducerServiceImpl implements ProducerService {
    
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC = "kafkaTopic";
    private Gson gson = new Gson();

    public ProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(Payment message) {
        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(TOPIC, gson.toJson(message));
    }
}