package com.example.SkincareProductSales.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    public boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "account_id")
    public Account account;

    @ManyToOne
    @JoinColumn(name = "order_id")
    public Order order;

    public Report() {
    }

    public Report(long id, String reason, String description, String image, boolean isDeleted, Account account, Order order) {
        this.id = id;
        this.reason = reason;
        this.description = description;
        this.image = image;
        this.isDeleted = isDeleted;
        this.account = account;
        this.order = order;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
