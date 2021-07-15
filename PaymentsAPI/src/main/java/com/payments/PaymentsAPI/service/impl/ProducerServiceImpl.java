package com.payments.PaymentsAPI.service.impl;

import com.google.gson.Gson;
import com.payments.PaymentsAPI.entity.Payment;
import com.payments.PaymentsAPI.service.ProducerService;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Service
public final class ProducerServiceImpl implements ProducerService {
    
    private Gson gson = new Gson();
    private KafkaProducer<String,String> producer = new KafkaProducer<String,String>(properties());
    
    private final String webhooks_hostname = "http://localhost";
    private URL url;

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.102.217.31:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
    
    public void sendRequest(String port, String body){
        Request request = new Request.Builder().method("POST", body)
            .url(webhooks_hostname + ":" + port + "/")
            .build();
        OkHttpClient httpClient = new OkHttpClient();
        try
        {
            Response response = httpClient.newBuilder()
            .readTimeout(1, TimeUnit.SECONDS)
            .build()
            .newCall(request)
            .execute();
            if(response.isSuccessful())
            {
                // notification about succesful request
            }
            else
            {
                // notification about failure request
            }
        }
        catch (IOException e1)
        {
            // notification about other problems
        }
    }
    
    @Override
    public void sendMessage(Payment message) {
        try {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("EXEMPLO_TOPICO", message.getFlag().name(), gson.toJson(message));
            Callback callback = (data, ex) -> {
                if (ex != null) {
                    System.out.println("Erro ao enviar a mensagem");
                    return;
                }
                System.out.println("Mensagem enviada com sucesso para: " + data.topic() + " | partition " + data.partition() + "| offset " + data.offset() + "| tempo " + data.timestamp());
            };
            producer.send(record, callback).get();
        } catch(ExecutionException | InterruptedException e){
            System.out.println("error listening to kafka");
        }
    }
}