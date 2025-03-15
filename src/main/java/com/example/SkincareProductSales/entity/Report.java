package com.example.SkincareProductSales.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String reason;

    public String description;

    public String image;

//    @ManyToOne
//    @JoinColumn(name = "account_id")
//    public Account account;

//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    public Order order;

    public Report() {
    }

    public Report(long id, String reason, String description, String image) {
        this.id = id;
        this.reason = reason;
        this.description = description;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
