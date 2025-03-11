package com.example.SkincareProductSales.entity.response;

import com.example.SkincareProductSales.enums.RoleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class UserResponse {
    public long id;
    public String fullName;

    public String email;

    @Enumerated(EnumType.STRING)
    public RoleEnum roleEnum;

    public UserResponse() {
    }

    public UserResponse(long id, String fullName, String email, RoleEnum roleEnum) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.roleEnum = roleEnum;
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

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }
}











