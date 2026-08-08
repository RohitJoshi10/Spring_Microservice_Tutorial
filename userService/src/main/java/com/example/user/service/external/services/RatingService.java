package com.example.user.service.external.services;

import com.example.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/ratings/users/{userId}")
    List<Rating> getRatingsByUserId(@PathVariable String userId);

    @PostMapping("/ratings")
    Rating createRating(Rating values);


    // For now we don't have this API
    @PutMapping("ratings/{ratingId}")
    Rating updateRating(@PathVariable String ratingId, Rating rating);

    // For now we don't have this API
    @DeleteMapping("ratings/{ratingId}")
    void deleteRating(@PathVariable String ratingId);
}
