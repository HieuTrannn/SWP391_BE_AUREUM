package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Account;
import com.example.SkincareProductSales.entity.request.AccountRequest;
import com.example.SkincareProductSales.entity.request.AuthenticationRequest;
import com.example.SkincareProductSales.entity.response.AuthenticationResponse;
import com.example.SkincareProductSales.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class AuthenticationAPI {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity register(@Valid @RequestBody AccountRequest accountRequest){
        Account newAccount = authenticationService.register(accountRequest);
        return ResponseEntity.ok(newAccount);
    }

    @PostMapping("login")
    public ResponseEntity login (@RequestBody AuthenticationRequest authenticationRequest){
        AuthenticationResponse authenticationResponse= authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse);
    }
}
