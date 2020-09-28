package com.capgemini.go;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@Configuration
public class OrderMangementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderMangementServiceApplication.class, args);
	}
	
	@Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.capgemini.go"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(myApiInfo());
       
    }

 private ApiInfo myApiInfo() {
        return new ApiInfo(
                "Order Management service API",
                 "Add,update,delete and fetchs  the order details.",
                 "1.0",
                 "Free to Use",
                 new Contact("Shubham ","/api" ,"shubh98bansal@gmail.com"),
                 "API licence",
                 "/api",
                 Collections.emptyList());
    }
 
 @LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
		
	}
	

}
