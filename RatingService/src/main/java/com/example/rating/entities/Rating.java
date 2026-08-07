package com.example.rating.entities;

//import jakarta.persistence.Id;
import org.springframework.data.annotation.Id; // ✅ MongoDB / Spring Data wala @Id
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user_ratings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    private String ratingId; // In mongoDB Id is auto generated
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
}
