package com.example.SkincareProductSales.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Skin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String name;

    @JsonIgnore
    public boolean isDeleted = false;

    @OneToMany(mappedBy = "skin")
    @JsonIgnore
    public List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "skin")
    @JsonIgnore
    public List<Account> accounts = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Skin() {
    }

    public Skin(long id, String name, boolean isDeleted, List<Product> products, List<Account> accounts) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
        this.products = products;
        this.accounts = accounts;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
