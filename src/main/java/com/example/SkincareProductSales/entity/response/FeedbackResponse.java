package com.example.SkincareProductSales.entity.response;

import com.example.SkincareProductSales.enums.RatingEnum;
import com.example.SkincareProductSales.enums.RoleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class FeedbackResponse {
    public long id;

    @Enumerated(EnumType.STRING)
    public RatingEnum ratingEnum;

    public String comment;

    public String image;

    public UserResponse userResponse;

    public ProductResponse productResponse;

    public FeedbackResponse() {
    }

    public FeedbackResponse(long id, RatingEnum ratingEnum, String comment, String image, UserResponse userResponse, ProductResponse productResponse) {
        this.id = id;
        this.ratingEnum = ratingEnum;
        this.comment = comment;
        this.image = image;
        this.userResponse = userResponse;
        this.productResponse = productResponse;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RatingEnum getRatingEnum() {
        return ratingEnum;
    }

    public void setRatingEnum(RatingEnum ratingEnum) {
        this.ratingEnum = ratingEnum;
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

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public ProductResponse getProductResponse() {
        return productResponse;
    }

    public void setProductResponse(ProductResponse productResponse) {
        this.productResponse = productResponse;
    }
}
