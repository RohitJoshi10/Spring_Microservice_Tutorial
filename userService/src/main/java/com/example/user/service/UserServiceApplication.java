package com.example.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient: In modern Spring Cloud versions, you no longer need any annotation on your main class to enable Eureka client functionality.
// If you prefer to explicitly declare service discovery in your code, replace @EnableEurekaClient with the generic @EnableDiscoveryClient annotation.
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
