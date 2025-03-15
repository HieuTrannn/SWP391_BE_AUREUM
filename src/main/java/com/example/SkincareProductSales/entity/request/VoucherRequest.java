package com.example.SkincareProductSales.entity.request;

import com.example.SkincareProductSales.enums.DiscountTypeEnum;
import com.example.SkincareProductSales.enums.VoucherStatusEnum;

import java.time.LocalDate;

public class VoucherRequest {
    public String code;
    public float discountPrice;  // Có thể là số tiền hoặc phần trăm
    public DiscountTypeEnum discountTypeEnum;  // "fixed" hoặc "percent"
    public float minOrderValue;
    public VoucherStatusEnum voucherStatusEnum;
    public LocalDate expiryDate;

    public VoucherRequest() {
    }

    public VoucherRequest(String code, float discountPrice, DiscountTypeEnum discountTypeEnum, float minOrderValue, VoucherStatusEnum voucherStatusEnum, LocalDate expiryDate) {
        this.code = code;
        this.discountPrice = discountPrice;
        this.discountTypeEnum = discountTypeEnum;
        this.minOrderValue = minOrderValue;
        this.voucherStatusEnum = voucherStatusEnum;
        this.expiryDate = expiryDate;
    }

    public VoucherStatusEnum getVoucherStatusEnum() {
        return voucherStatusEnum;
    }

    public void setVoucherStatusEnum(VoucherStatusEnum voucherStatusEnum) {
        this.voucherStatusEnum = voucherStatusEnum;
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

    public void setDiscountPrice(float discountPrice) {
        this.discountPrice = discountPrice;
    }

    public DiscountTypeEnum getDiscountTypeEnum() {
        return discountTypeEnum;
    }

    public void setDiscountTypeEnum(DiscountTypeEnum discountTypeEnum) {
        this.discountTypeEnum = discountTypeEnum;
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


}
