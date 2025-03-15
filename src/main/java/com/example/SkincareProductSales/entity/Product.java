package com.example.SkincareProductSales.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @NotBlank(message = "Product name cannot be blank")
    public String name;

    @NotBlank(message = "Product description cannot be blank")
    @Column(length = 10000)
    public String description;

    @Min(value = 0, message = "Product quantity must be greater than 0")
    public int quantity;

    @Min(value = 0, message = "Product price must be greater than 0")
    public float price;

    @NotBlank(message = "Product image cannot be blank")
    public String image;

    @Pattern(regexp = "PD\\d{5}", message = "Code must be PDxxxxx!")
    @Column(unique = true) // từ giờ cột này sẽ không được trùng nhau
    public String code;

    @JsonIgnore // Không bắt người dùng nhập thông tin này
    public boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category category;

    @ManyToOne
    @JoinColumn(name = "skin_id")
    public Skin skin;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    public Brand brand;

    @ManyToMany
    @JoinTable(
            name = "product_ingredient",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    public List<Ingredient> ingredient = new ArrayList<>();

    @OneToMany(mappedBy = "product")
            @JsonIgnore
    List<OrderDetail> orderDetails = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    public List<Rating> ratings = new ArrayList<>();

    public Product() {
    }

    public Product(long id, String name, String description, int quantity, float price, String image, String code, boolean isDeleted, Category category, Skin skin, Brand brand, List<Ingredient> ingredient, List<OrderDetail> orderDetails, List<Rating> ratings) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.code = code;
        this.isDeleted = isDeleted;
        this.category = category;
        this.skin = skin;
        this.brand = brand;
        this.ingredient = ingredient;
        this.orderDetails = orderDetails;
        this.ratings = ratings;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<Rating> getFeedbacks() {
        return ratings;
    }

    public void setFeedbacks(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
