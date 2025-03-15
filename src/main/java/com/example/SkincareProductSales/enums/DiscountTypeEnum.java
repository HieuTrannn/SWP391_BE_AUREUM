package com.example.SkincareProductSales.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum DiscountTypeEnum {
    FIXED,  // Giảm giá theo số tiền (VD: 50,000 VND)
    PERCENT; // Giảm giá theo phần trăm (VD: 20%)

    @JsonCreator
    public static DiscountTypeEnum fromString(String value) {
        for (DiscountTypeEnum type : DiscountTypeEnum.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid discount type: " + value);
    }
}
