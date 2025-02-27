package com.example.SkincareProductSales.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @NotBlank(message = "Name cannot be blank!")
    public String name;

    @JsonIgnore
    public boolean isDeleted = false;

    @OneToMany(mappedBy = "brand")
    @JsonIgnore
    public List<Product> products = new ArrayList<>();

    public Brand() {
    }

    public Brand(long id, String name, boolean isDeleted, List<Product> products) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
        this.products = products;
    }

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
}
