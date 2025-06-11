package com.uber.msl.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CommonMslApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonMslApplication.class, args);
	}

}
