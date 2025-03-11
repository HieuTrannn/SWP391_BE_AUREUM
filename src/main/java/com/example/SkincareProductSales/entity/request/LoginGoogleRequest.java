package com.example.SkincareProductSales.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public class LoginGoogleRequest {
    private String token;


    public LoginGoogleRequest() {
    }


    public String getToken() {
        return token;
    }

    public LoginGoogleRequest(String token) {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
