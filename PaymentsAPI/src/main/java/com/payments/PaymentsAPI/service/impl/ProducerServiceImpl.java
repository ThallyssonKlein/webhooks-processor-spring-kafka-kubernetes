package com.payments.PaymentsAPI.service.impl;

import com.google.gson.Gson;
import com.payments.PaymentsAPI.entity.Payment;
import com.payments.PaymentsAPI.service.ProducerService;

import org.springframework.stereotype.Service;

@Service
public final class ProducerServiceImpl implements ProducerService {
    
    private Gson gson = new Gson();
    private KafkaProducer<String,String> producer = new KafkaProducer<String,String>(properties());

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:29092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
    
    @Override
    public void sendMessage(Payment message) {
        var key = "TEMPERATURA";
        var value = "34º";
        var record = new ProducerRecord<String, String>("EXEMPLO_TOPICO", key, value);
        Callback callback = (data, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.println("Mensagem enviada com sucesso para: " + data.topic() + " | partition " + data.partition() + "| offset " + data.offset() + "| tempo " + data.timestamp());
        };
        producer.send(record, callback).get();
    }
}