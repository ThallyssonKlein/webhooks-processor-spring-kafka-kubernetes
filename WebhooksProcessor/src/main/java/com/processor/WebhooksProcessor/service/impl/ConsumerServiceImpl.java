package com.processor.WebhooksProcessor.service.impl;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import com.processor.WebhooksProcessor.entity.Payment;
import com.processor.WebhooksProcessor.service.ConsumerService;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Service;

import com.squareup.okhttp.*;

import java.io.IOException;

import com.google.gson.Gson;

@Service
public final class ConsumerServiceImpl implements ConsumerService {

    private OkHttpClient httpClient = new OkHttpClient();
    private final String webhooks_hostname = "http://localhost";
    private Gson gson = new Gson();

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.102.217.31:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        return properties;
    }

    public void sendRequest(String port, String json){
        try {
            RequestBody body = RequestBody.create(
                    MediaType.parse("application/json"), json);

            Request request = new Request.Builder()
                    .url(webhooks_hostname + ":" + port + "/")
                    .post(body)
                    .build();

            httpClient.newCall(request).execute();
        } catch (IOException e){
            System.out.println("Ocorreu um erro enviando uma request a um webhook");
        }
    }
    
    @Override
    public void consume() {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties());
        consumer.subscribe(Collections.singletonList("EXEMPLO_TOPICO"));
    
        while (true) {
            try {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> registro : records) {
                    Payment pay = gson.fromJson(registro.value(), Payment.class);
                    switch(pay.getFlag().name()){
                        case "MASTERCARD":
                            sendRequest("8082", pay.getFlag().name());
                            break;
                        case "VISA":
                            sendRequest("8083", pay.getFlag().name());
                            break;
                        case "AMERICAN_EXPRESS":
                            sendRequest("8084", pay.getFlag().name());
                            break;
                        case "HIPERCARD":
                            sendRequest("8085", pay.getFlag().name());
                            break;
                        case "ELO":
                            sendRequest("8086", pay.getFlag().name());
                            break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro parseando a mensagem!");
            }
        }
    }
}