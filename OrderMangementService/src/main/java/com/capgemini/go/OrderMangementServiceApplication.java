package com.capgemini.go;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderMangementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderMangementServiceApplication.class, args);
	}
	

}
