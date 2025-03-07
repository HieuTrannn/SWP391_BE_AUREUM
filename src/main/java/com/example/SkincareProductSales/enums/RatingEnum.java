package com.example.SkincareProductSales.enums;

public enum RatingEnum {
    VERY_BAD(1), BAD(2), AVERAGE(3), GOOD(4), EXCELLENT(5);

    private final int value;

    RatingEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RatingEnum fromValue(int value) {
        for (RatingEnum ratingEnum : values()) {
            if (ratingEnum.getValue() == value) {
                return ratingEnum;
            }
        }
        throw new IllegalArgumentException("Invalid rating value: " + value);
    }
}
