package com.example.user.service.services.impl;

import com.example.user.service.entities.Rating;
import com.example.user.service.entities.User;
import com.example.user.service.exceptions.ResourceNotFoundException;
import com.example.user.service.repositories.UserRepository;
import com.example.user.service.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate; // To make it autowired we need its bean also.

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            Rating[] ratingsOfUser = restTemplate.getForObject(
                    "http://localhost:8083/ratings/users/" + user.getUserId(),
                    Rating[].class
            );

            List<Rating> ratings = (ratingsOfUser != null)
                    ? Arrays.asList(ratingsOfUser)
                    : Collections.emptyList();

            user.setRatings(ratings);
        }


        return users;
    }

    @Override
    public User getUser(String userId) {
        // Get user from database.
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + userId));

        // Right now we don't have any rating for this user as rating is not in userDB it is part of RatingDB.
        // Fetch rating of the above user from RATING-SERVICE
        // We need a client in userService who can call http server with the help of http API
        // We have different options to do above thing like REST Template or Feign Client etc.
        // http://localhost:8083/ratings/users/15a01006-a4f9-463d-9e12-6a1a573af8c9

        // For now using REST TEMPLATE : Fetch ratings from RATING-SERVICE
        Rating[] ratingsOfUser = restTemplate.getForObject(
                "http://localhost:8083/ratings/users/" + user.getUserId(),
                Rating[].class
        );

       // Safe check: If null, assign an empty list instead of throwing NullPointerException
        List<Rating> ratings = (ratingsOfUser != null)
                ? Arrays.asList(ratingsOfUser)
                : Collections.emptyList();

        log.info("FETCHED RATING ARRAY: {}", ratings);

        user.setRatings(ratings);

        return user;
    }
}
