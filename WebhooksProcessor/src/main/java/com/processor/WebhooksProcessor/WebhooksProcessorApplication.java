package com.processor.WebhooksProcessor;

import com.processor.WebhooksProcessor.service.ConsumerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.processor.WebhooksProcessor")
@SpringBootApplication
public class WebhooksProcessorApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(WebhooksProcessorApplication.class, args);
		context.getBean(ConsumerService.class).consume();
	}
	
}
