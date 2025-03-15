package com.example.SkincareProductSales.entity.request;

import jakarta.validation.constraints.NotBlank;

public class ResetPasswordRequest {
    @NotBlank(message = "Password cannot be blank!")
    String password;

    public ResetPasswordRequest() {
    }

    public ResetPasswordRequest(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
