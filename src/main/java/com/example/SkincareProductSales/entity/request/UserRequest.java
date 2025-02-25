package com.example.SkincareProductSales.entity.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class UserRequest {
    @NotBlank(message = "Full name cannot be blank")
    public String fullName;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email not match Struct")
    @Column(unique = true)
    public String email;

    @NotBlank(message = "Gender cannot be blank")
    public String gender;

    @Past(message = "Date of birth must be a past date")
    public LocalDate dateOfBirth;

    @NotBlank(message = "Address cannot be blank!")
    public String address;

    public UserRequest() {
    }

    public UserRequest(String fullName, String email, String gender, LocalDate dateOfBirth, String address) {
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
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
}
