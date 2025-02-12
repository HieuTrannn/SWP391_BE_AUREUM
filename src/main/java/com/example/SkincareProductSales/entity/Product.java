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
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
