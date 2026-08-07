package com.example.rating;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@SpringBootApplication
public class RatingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingServiceApplication.class, args);
	}


    // ✅ Yeh Bean direct MongoDatabaseFactory ko 'microservices' DB connect karwayega
    @Bean
    public MongoDatabaseFactory mongoDbFactory() {
        return new SimpleMongoClientDatabaseFactory("mongodb://localhost:27017/microservices");
    }

    @Bean
    public CommandLineRunner printDbName(MongoTemplate mongoTemplate) {
        return args -> {
            System.out.println("==========================================");
            System.out.println("CONNECTED MONGODB DATABASE: " + mongoTemplate.getDb().getName());
            System.out.println("==========================================");
        };
    }
}
