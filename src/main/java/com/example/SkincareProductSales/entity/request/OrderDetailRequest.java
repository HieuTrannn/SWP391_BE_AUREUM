package com.example.SkincareProductSales.entity.request;

import lombok.Data;

@Data
public class OrderDetailRequest {
    long productId;
    int quantity;
}
