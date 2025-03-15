package com.example.SkincareProductSales.entity.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RatingRequest {

    public Long orderDetailId;

    @Min(value = 0, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    public int rating;

    public String comment;

    public String image;

    public RatingRequest() {
    }

    public RatingRequest(Long orderDetailId, int rating, String comment, String image) {
        this.orderDetailId = orderDetailId;
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

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
}
