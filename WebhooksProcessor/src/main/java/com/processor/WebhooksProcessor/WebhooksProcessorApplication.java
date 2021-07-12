package com.processor.WebhooksProcessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.processor.WebhooksProcessor")
@SpringBootApplication
public class WebhooksProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebhooksProcessorApplication.class, args);
	}
	
}
