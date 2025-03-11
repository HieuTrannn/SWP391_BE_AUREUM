package com.example.SkincareProductSales.entity;

import com.example.SkincareProductSales.enums.RatingEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    public RatingEnum ratingEnum;

    @NotBlank(message = "Comment cannot be blank!")
    public String comment;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    public Date CommentAt;

    public String image;

    @JsonIgnore
    public boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public Account account;

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product product;

    public Feedback() {
    }

    public Feedback(long id, RatingEnum ratingEnum, String comment, Date commentAt, String image, boolean isDeleted, Account account, Product product) {
        this.id = id;
        this.ratingEnum = ratingEnum;
        this.comment = comment;
        CommentAt = commentAt;
        this.image = image;
        this.isDeleted = isDeleted;
        this.account = account;
        this.product = product;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
