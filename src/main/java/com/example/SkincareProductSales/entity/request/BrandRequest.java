package com.example.SkincareProductSales.entity.request;

import jakarta.validation.constraints.NotBlank;

public class BrandRequest {

    @NotBlank(message = "Name cannot be blank!")
    public String name;

    public BrandRequest() {
    }

    public BrandRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
