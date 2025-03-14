package com.example.SkincareProductSales.entity.response;

import com.example.SkincareProductSales.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class AuthenticationResponse {
    public long id;
    public String fullName;
    public String email;
    public String phone;

    @Enumerated(value = EnumType.STRING)
    public RoleEnum roleEnum;

    public String token;

    @JsonIgnore
    public boolean isActive;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(long id, String fullName, String email, String phone, RoleEnum roleEnum, String token, boolean isActive) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.roleEnum = roleEnum;
        this.token = token;
        this.isActive = isActive;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
