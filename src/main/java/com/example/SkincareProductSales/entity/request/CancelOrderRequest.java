package com.example.SkincareProductSales.entity.request;

public class CancelOrderRequest {
    public String reason;

    public CancelOrderRequest() {
    }

    public CancelOrderRequest(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
