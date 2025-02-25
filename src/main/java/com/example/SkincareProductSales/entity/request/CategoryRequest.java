package com.example.SkincareProductSales.entity.request;

import jakarta.validation.constraints.NotBlank;

public class CategoryRequest {

    @NotBlank(message = "Name cannot be blank!")
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryRequest() {
    }

    public CategoryRequest(String name) {
        this.name = name;
    }
}
