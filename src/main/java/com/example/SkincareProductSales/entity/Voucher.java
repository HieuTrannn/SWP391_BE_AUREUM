package com.example.SkincareProductSales.entity;

import com.example.SkincareProductSales.enums.DiscountTypeEnum;
import com.example.SkincareProductSales.enums.VoucherStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(unique = true)
    @NotBlank(message = "code can not be blank")
    public String code;

    public float discountPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public DiscountTypeEnum discountTypeEnum; // FIXED, PERCENT

    public float minOrderValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public VoucherStatusEnum voucherStatusEnum; // ACTIVE, EXPIRED, USED

    public LocalDate expiryDate;  // Ngày hết hạn

    public LocalDate createdAt = LocalDate.now();  // Ngày tạo voucher (mặc định là ngày hiện tại)

    @OneToMany(mappedBy = "voucher")
    @JsonIgnore
    public List<Order> orders = new ArrayList<>();

    @JsonIgnore
    public boolean isDeleted = false;

    public Voucher() {
    }

    public Voucher(long id, String code, float discountPrice, DiscountTypeEnum discountTypeEnum, float minOrderValue, VoucherStatusEnum voucherStatusEnum, LocalDate expiryDate, LocalDate createdAt, boolean isDeleted) {
        this.id = id;
        this.code = code;
        this.discountPrice = discountPrice;
        this.discountTypeEnum = discountTypeEnum;
        this.minOrderValue = minOrderValue;
        this.voucherStatusEnum = voucherStatusEnum;
        this.expiryDate = expiryDate;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(float discountValue) {
        this.discountPrice = discountValue;
    }

    public float getMinOrderValue() {
        return minOrderValue;
    }

    public void setMinOrderValue(float minOrderValue) {
        this.minOrderValue = minOrderValue;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public DiscountTypeEnum getDiscountTypeEnum() {
        return discountTypeEnum;
    }

    public void setDiscountTypeEnum(DiscountTypeEnum discountTypeEnum) {
        this.discountTypeEnum = discountTypeEnum;
    }

    public VoucherStatusEnum getVoucherStatusEnum() {
        return voucherStatusEnum;
    }

    public void setVoucherStatusEnum(VoucherStatusEnum statusEnum) {
        this.voucherStatusEnum = statusEnum;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

