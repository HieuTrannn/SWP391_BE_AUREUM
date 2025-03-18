package com.example.SkincareProductSales.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    int rating;

    public String comment;

    public String image;

    public LocalDate commentAt = LocalDate.now();

    @JsonIgnore
    public boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    public Account account;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    public Product product;

    public Rating() {
    }

    public Rating(long id, int rating, String comment, String image, LocalDate commentAt, boolean isDeleted, Account account, Product product) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.image = image;
        this.commentAt = commentAt;
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

    public LocalDate getCommentAt() {
        return commentAt;
    }

    public void setCommentAt(LocalDate commentAt) {
        this.commentAt = commentAt;
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
