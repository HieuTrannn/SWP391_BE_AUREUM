package com.example.SkincareProductSales.entity.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class UserRequest {

    @NotBlank(message = "Full name cannot be blank")
    public String fullName;

    public String gender;

    @Past(message = "Date of birth must be a past date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public LocalDate dateOfBirth;

    public String address;

    public String phone;

    public UserRequest() {
    }

    public UserRequest(String fullName, String gender, LocalDate dateOfBirth, String address, String phone) {
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
