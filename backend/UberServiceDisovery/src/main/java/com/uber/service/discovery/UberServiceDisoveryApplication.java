package com.uber.service.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class UberServiceDisoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberServiceDisoveryApplication.class, args);
	}

}