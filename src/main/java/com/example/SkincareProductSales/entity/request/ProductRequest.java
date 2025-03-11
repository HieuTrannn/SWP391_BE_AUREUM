package com.example.SkincareProductSales.entity.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;


public class ProductRequest {

    @NotBlank(message = "Product name cannot be blank")
    public String name;

    @NotBlank(message = "Product description cannot be blank")
    public String description;

    @Min(value = 0, message = "Product quantity must be greater than 0")
    public int quantity;

    @Min(value = 0, message = "Product price must be greater than 0")
    public float price;

    @NotBlank(message = "Product image cannot be blank")
    public String image;

    @Pattern(regexp = "PD\\d{5}", message = "Code must be PDxxx!")
    @Column(unique = true) // từ giờ cột này sẽ không được trùng nhau
    public String code;

    @NotNull
    public long categoryId;

    @NotNull
    public long brandId;

    @NotNull
    public List<Long> ingredientId;

    @NotNull
    public long skinId;

    public ProductRequest() {
    }

    public ProductRequest(String name, String description, int quantity, float price, String image, String code, long categoryId, long brandId, List<Long> ingredientId, long skinId) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.code = code;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.ingredientId = ingredientId;
        this.skinId = skinId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public List<Long> getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(List<Long> ingredientId) {
        this.ingredientId = ingredientId;
    }

    public long getSkinId() {
        return skinId;
    }

    public void setSkinId(long skinId) {
        this.skinId = skinId;
    }
}
