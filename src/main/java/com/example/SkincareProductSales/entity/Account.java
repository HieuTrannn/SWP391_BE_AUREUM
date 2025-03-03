package com.example.SkincareProductSales.entity;

import com.example.SkincareProductSales.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity

public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // geneta tự động id
    public long id;

    @NotBlank(message = "Full name cannot be blank")
    public String fullName;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email not match Struct")
    @Column(unique = true)
    public String email;

//    @NotBlank(message = "Gender cannot be blank")
    public String gender;

    @Past(message = "Date of birth must be a past date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public LocalDate dateOfBirth;

//    @NotBlank(message = "Address cannot be blank!")
    public String address;

    @Size(min = 6, message = "Password must be exceed 6 characters")
    public String password;

    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "Phone not match struct")
    @Column(unique = true)
    public String phone;

    @Enumerated(value = EnumType.STRING)
    public RoleEnum roleEnum;

    @JsonIgnore
    public boolean isActive = true;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@class")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = SimpleGrantedAuthority.class, name = "SimpleGrantedAuthority")
    })

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.roleEnum.toString()));
        return authorities;
    }

    public Account(long id, String fullName, String email, String gender, LocalDate dateOfBirth, String address, String password, String phone, RoleEnum roleEnum, boolean isActive) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.password = password;
        this.phone = phone;
        this.roleEnum = roleEnum;
        this.isActive = isActive;
    }

    public Account() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
