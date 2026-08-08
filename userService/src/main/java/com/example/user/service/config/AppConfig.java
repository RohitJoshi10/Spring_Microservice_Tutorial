package com.example.user.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    @LoadBalanced // Host and port ko hata k load balancer laga dia
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}


/*
To put it simply: Standard RestTemplate ko Service Names (jaise RATING-SERVICE) samajh nahi aate, use sirf IP ya Domain Names (localhost, 127.0.0.1, google.com) samajh aate hain.

1. Bina @LoadBalanced ke kya ho raha tha?
Jab aapne URL me http://RATING-SERVICE/ratings/... likha aur @LoadBalanced nahi lagaya tha:

Normal RestTemplate ne RATING-SERVICE ko ek real website domain name samjha.

Usne aapke Operating System / DNS se pucha: "Kya RATING-SERVICE naam ki koi website ya IP hai?"

System ne bola: "Aisi koi website nahi hai."

Result: UnknownHostException / Request fail ho gayi.

2. @LoadBalanced lagane se kya Magic hua?
Jab aap @Bean method par @LoadBalanced annotation lagate ho, toh Spring Boot us RestTemplate me ek Smart Interceptor (LoadBalancerInterceptor) attach kar deta hai.

[ Your Code ]
restTemplate.getForObject("http://RATING-SERVICE/ratings/123")
                    │
                    ▼
       [@LoadBalanced Interceptor] ──(Intercepts Request)
                    │
                    ├──1. Eureka se poochta hai: "RATING-SERVICE kahan chal raha hai?"
                    │
                    ├──2. Eureka jawab deta hai: "http://localhost:8083 par"
                    │
                    ▼
       [URL Rewrite on the fly]
       "http://localhost:8083/ratings/123"
                    │
                    ▼
      [ Actual HTTP Call Executed ]
 */