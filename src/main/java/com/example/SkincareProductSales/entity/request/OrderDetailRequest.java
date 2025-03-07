package com.example.SkincareProductSales.entity.request;

import lombok.Data;

@Data
public class OrderDetailRequest {
    long productId;
    int quantity;

    public OrderDetailRequest() {
    }

    public OrderDetailRequest(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
