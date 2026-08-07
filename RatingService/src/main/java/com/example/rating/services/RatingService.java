package com.example.rating.services;

import com.example.rating.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RatingService {

    Rating create(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getRatingByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);

}
