package com.example.SkincareProductSales.entity.request;


public class LoginGoogleRequest {
    private String token;

    public LoginGoogleRequest() {}

    public LoginGoogleRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
