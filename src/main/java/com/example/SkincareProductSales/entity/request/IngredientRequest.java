package com.example.SkincareProductSales.entity.request;

import com.example.SkincareProductSales.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class IngredientRequest {

    @NotBlank(message = "Name cannot be blank!")
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IngredientRequest() {
    }

    public IngredientRequest(String name) {
        this.name = name;
    }
}
