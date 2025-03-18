package com.example.SkincareProductSales.entity.response;

import com.example.SkincareProductSales.entity.Skin;
import com.example.SkincareProductSales.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class AuthenticationResponse {
    public long id;
    public String fullName;
    public String email;
    public String phone;

    @Enumerated(value = EnumType.STRING)
    public RoleEnum roleEnum;

    @ManyToOne
    @JoinColumn(name = "skin_id")
    public Skin skin;

    public String token;

    @JsonIgnore
    public boolean isActive;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(long id, String fullName, String email, String phone, RoleEnum roleEnum, Skin skin, String token, boolean isActive) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.roleEnum = roleEnum;
        this.skin = skin;
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

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }
}
