package com.example.SkincareProductSales.entity.request;

public class ReportRequest {
    public long orderId;

    public String reason;

    public String description;

    public String image;

    public ReportRequest() {
    }

    public ReportRequest(long orderId, String reason, String description, String image) {
        this.orderId = orderId;
        this.reason = reason;
        this.description = description;
        this.image = image;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
