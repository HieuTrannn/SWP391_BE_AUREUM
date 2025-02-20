package com.example.SkincareProductSales.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    public long id;

    @NotBlank(message = "Product name cannot be blank")
    public String name;

    @NotBlank(message = "Product brand cannot be blank")
    public String brand;

    @NotBlank(message = "Product description cannot be blank")
    public String description;

    @Min(value = 0, message = "Product quantity must be greater than 0")
    public int quantity;

    @Min(value = 0, message = "Product price must be greater than 0")
    public float price;

    @NotBlank(message = "Product category cannot be blank")
    public String category;

    @NotBlank(message = "Product image cannot be blank")
    public String image;

    @Pattern(regexp = "PD\\d{3}", message = "Code must be PDxxx!")
    @Column(unique = true) // từ giờ cột này sẽ không được trùng nhau
    public String code;

    @JsonIgnore // Không bắt người dùng nhập thông tin này
    public boolean isDeleted = false;

    public Product(long id, String name, String brand, String description, int quantity, float price, String category, String image, String code, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.image = image;
        this.code = code;
        this.isDeleted = isDeleted;
    }

    public Product() {
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
