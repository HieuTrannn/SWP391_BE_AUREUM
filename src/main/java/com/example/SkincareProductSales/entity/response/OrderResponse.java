package com.example.SkincareProductSales.entity.response;

import com.example.SkincareProductSales.entity.Account;
import com.example.SkincareProductSales.entity.OrderDetail;
import com.example.SkincareProductSales.enums.OrderStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderResponse {

    public long id;


    public Date createAt;
    public float total;

    Account account;

    public OrderStatus status = OrderStatus.IN_PROCESS;
    List<OrderDetail> orderDetails = new ArrayList<>();

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderResponse(long id, Date createAt, float total, Account account, OrderStatus status, List<OrderDetail> orderDetails) {
        this.id = id;
        this.createAt = createAt;
        this.total = total;
        this.account = account;
        this.status = status;
        this.orderDetails = orderDetails;
    }

    public OrderResponse() {
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
