package com.example.SkincareProductSales.entity.response;

import com.example.SkincareProductSales.enums.RoleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    public long id;
    public String fullName;
    public String email;
    public String phone;

    @Enumerated(value = EnumType.STRING)
    public RoleEnum roleEnum;
    public String token;
}
