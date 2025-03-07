package com.example.SkincareProductSales.entity.response;

import com.example.SkincareProductSales.entity.Brand;
import com.example.SkincareProductSales.entity.Category;

public class ProductResponse {
    public long id;
    public String name;
    public BrandResponse brandResponse;
    public CategoryResponse categoryResponse;
    public String code;

    public ProductResponse() {
    }

    public ProductResponse(long id, String name, BrandResponse brandResponse, CategoryResponse categoryResponse, String code) {
        this.id = id;
        this.name = name;
        this.brandResponse = brandResponse;
        this.categoryResponse = categoryResponse;
        this.code = code;
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

    public BrandResponse getBrandResponse() {
        return brandResponse;
    }

    public void setBrandResponse(BrandResponse brandResponse) {
        this.brandResponse = brandResponse;
    }

    public CategoryResponse getCategoryResponse() {
        return categoryResponse;
    }

    public void setCategoryResponse(CategoryResponse categoryResponse) {
        this.categoryResponse = categoryResponse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
