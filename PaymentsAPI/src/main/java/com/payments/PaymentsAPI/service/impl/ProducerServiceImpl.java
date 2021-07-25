package com.payments.PaymentsAPI.service.impl;

import com.google.gson.Gson;
import com.payments.PaymentsAPI.entity.Payment;
import com.payments.PaymentsAPI.service.ProducerService;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Service
public final class ProducerServiceImpl implements ProducerService {
    
    private KafkaProducer<String,String> producer = new KafkaProducer<String,String>(properties());

    private Gson gson = new Gson();

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.106.240.240:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_DOC, "true");
        return properties;
    }
    
    @Override
    public void sendMessage(Payment message) {
        try {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("consumer-hooks-pagamentos", message.getFlag().name(), gson.toJson(message));
            Callback callback = (data, ex) -> {
                if (ex != null) {
                    System.out.println(ex);
                    return;
                }
                System.out.println("Mensagem enviada com sucesso para: " + data.topic() + " | partition " + data.partition() + "| offset " + data.offset() + "| tempo " + data.timestamp());
            };
            producer.send(record, callback).get();
        } catch(ExecutionException | InterruptedException e){
            System.out.println("error sending to kafka");
        }
    }
}