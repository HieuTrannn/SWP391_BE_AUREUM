package com.example.SkincareProductSales.entity;

import com.example.SkincareProductSales.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public Date createAt;
    public float total;

    public float discountAmount;

    public float finalTotal;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;

    public OrderStatus status = OrderStatus.IN_PROCESS;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<OrderDetail> orderDetails = new ArrayList<>();

//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    @JsonIgnore
//    List<Report> reports = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public float getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(float finalTotal) {
        this.finalTotal = finalTotal;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
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

    public Order() {
    }

    public Order(long id, Date createAt, float total, float discountAmount, float finalTotal, Voucher voucher, Account account, OrderStatus status, List<OrderDetail> orderDetails) {
        this.id = id;
        this.createAt = createAt;
        this.total = total;
        this.discountAmount = discountAmount;
        this.finalTotal = finalTotal;
        this.voucher = voucher;
        this.account = account;
        this.status = status;
        this.orderDetails = orderDetails;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
