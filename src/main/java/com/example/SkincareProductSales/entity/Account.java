package com.example.SkincareProductSales.entity;

import com.example.SkincareProductSales.enums.RoleEnum;
import com.example.SkincareProductSales.enums.SkinTypeEnum;
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
@Column(unique = true)
    public String username;
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

    @Enumerated(value = EnumType.STRING)
    public SkinTypeEnum skinTypeEnum;


    @OneToMany(mappedBy = "account")
            @JsonIgnore
    List<Order> orders = new ArrayList<>();

    @JsonIgnore
    public boolean isActive = true;


    @ManyToOne
    @JoinColumn(name = "skin_id")
    public Skin skin;





    @OneToMany(mappedBy = "account")
    @JsonIgnore
    public List<Feedback> feedbacks = new ArrayList<>();

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@class")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = SimpleGrantedAuthority.class, name = "SimpleGrantedAuthority")
    })

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(this.getRoleEnum() != null){
            authorities.add(new SimpleGrantedAuthority(this.roleEnum.toString()));
        }

        return authorities;
    }

    public SkinTypeEnum getSkinTypeEnum() {
        return skinTypeEnum;
    }

    public void setSkinTypeEnum(SkinTypeEnum skinTypeEnum) {
        this.skinTypeEnum = skinTypeEnum;
    }


    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Account(long id, String username, String fullName, String email, String gender, LocalDate dateOfBirth, String address, String password, String phone, RoleEnum roleEnum, SkinTypeEnum skinTypeEnum, List<Order> orders, boolean isActive, Skin skin, List<Feedback> feedbacks) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.password = password;
        this.phone = phone;
        this.roleEnum = roleEnum;
        this.skinTypeEnum = skinTypeEnum;
        this.orders = orders;
        this.isActive = isActive;
        this.skin = skin;
        this.feedbacks = feedbacks;
    }

    public Account() {
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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


    public void setGender(String gender) {
        this.gender = gender;
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

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
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
