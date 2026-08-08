package com.example.user.service;

import com.example.user.service.entities.Rating;
import com.example.user.service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@Service
@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

    @Autowired
    RatingService ratingService;

    @Test
    void createRating(){
        Rating rating = Rating.builder()
                .rating(2)
                .userId("")
                .hotelId("")
                .feedback("This is created using feign client").build();
        Rating createdRating = ratingService.createRating(rating);
        System.out.println("New Rating CREATED");
    }
}
