package com.example.SkincareProductSales.entity.request;

public class ReportRequest {
    public long orderDetailId;

    public String reason;

    public String description;

    public String image;

    public ReportRequest() {
    }

    public ReportRequest(long orderDetailId, String reason, String description, String image) {
        this.orderDetailId = orderDetailId;
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

    public long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
}
