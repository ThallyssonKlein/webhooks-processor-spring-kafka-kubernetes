package com.payments.PaymentsAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.payments.PaymentsAPI")
@EnableSwagger2
@SpringBootApplication
public class PaymentsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsApiApplication.class, args);
	}
	
}
