package com.example.SkincareProductSales.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Min(value = 0, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    public int rating;

    @NotBlank(message = "Comment cannot be blank!")
    public String comment;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date CommentAt;

    public String image;

    @JsonIgnore
    public boolean isDeleted = false;

    public Feedback() {
    }

    public Feedback(long id, int rating, String comment, Date commentAt, String image, boolean isDeleted) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        CommentAt = commentAt;
        this.image = image;
        this.isDeleted = isDeleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getCommentAt() {
        return CommentAt;
    }

    public void setCommentAt(Date commentAt) {
        CommentAt = commentAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
