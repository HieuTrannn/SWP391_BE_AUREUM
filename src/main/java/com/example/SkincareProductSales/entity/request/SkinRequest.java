package com.example.SkincareProductSales.entity.request;

import jakarta.validation.constraints.NotBlank;

public class SkinRequest {

    @NotBlank(message = "Name can not blank!")
    public String name;

    public SkinRequest() {
    }

    public SkinRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
