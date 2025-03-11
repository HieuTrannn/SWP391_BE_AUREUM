package com.example.SkincareProductSales.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Skin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    public long id;

    private String name;

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

    public Skin(long id, String name, List<Product> products, List<Account> accounts) {
        this.id = id;
        this.name = name;
        this.products = products;
        this.accounts = accounts;
    }
}
