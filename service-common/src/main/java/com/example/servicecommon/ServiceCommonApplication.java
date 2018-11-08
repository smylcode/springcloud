package com.example.servicecommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
/*@MapperScan(value = "com.example.servicecommon.dao")*/
public class ServiceCommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCommonApplication.class, args);
	}
}
