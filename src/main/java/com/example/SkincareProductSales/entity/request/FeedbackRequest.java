package com.example.SkincareProductSales.entity.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FeedbackRequest {

    @Min(value = 0, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    public int rating;

    @NotBlank(message = "Comment cannot be blank!")
    public String comment;

    public String image;

    public FeedbackRequest() {
    }

    public FeedbackRequest(int rating, String comment, String image) {
        this.rating = rating;
        this.comment = comment;
        this.image = image;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
