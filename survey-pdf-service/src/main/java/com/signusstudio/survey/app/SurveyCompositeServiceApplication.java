package com.signusstudio.survey.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class PhysicCompositeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhysicCompositeServiceApplication.class, args);
	}

}
