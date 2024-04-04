package com.knightscom.knightscom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class KnightscomApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnightscomApplication.class, args);
	}

}
