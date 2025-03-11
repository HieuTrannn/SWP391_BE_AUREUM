package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Account;
import com.example.SkincareProductSales.entity.request.AccountRequest;
import com.example.SkincareProductSales.entity.request.AuthenticationRequest;
import com.example.SkincareProductSales.entity.request.LoginGoogleRequest;
import com.example.SkincareProductSales.entity.response.AuthenticationResponse;
import com.example.SkincareProductSales.entity.response.UserResponse;
import com.example.SkincareProductSales.service.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
@SecurityRequirement(name = "api")
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

    @PostMapping("/loginGoogle")
    public ResponseEntity loginWithGoogle(@RequestBody LoginGoogleRequest loginGoogleRequest) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticateWithGoogle(loginGoogleRequest);
        return ResponseEntity.ok(authenticationResponse);
    }
}
