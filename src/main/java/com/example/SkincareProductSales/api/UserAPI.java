package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Account;
import com.example.SkincareProductSales.entity.Skin;
import com.example.SkincareProductSales.entity.request.UserRequest;
import com.example.SkincareProductSales.entity.request.UserSkinRequest;
import com.example.SkincareProductSales.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
@SecurityRequirement(name = "api")

public class UserAPI {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity getAllUsers() {
        List<Account> accounts = userService.getAllUsers();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("{id}")
    public ResponseEntity getUserById(@PathVariable long id) {
        Account account = userService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    @PutMapping("{id}")
    public ResponseEntity updateUser(@PathVariable long id, @RequestBody UserRequest userRequest) {
        Account updateAccount = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(updateAccount);
    }

    @PutMapping("{id}/isActive")
    public ResponseEntity toggleUserIsActive(@PathVariable long id) {
        Account updateAccount = userService.toggleUserIsActive(id);
        return ResponseEntity.ok(updateAccount);
    }

    @PutMapping("{id}/skinType")
    public ResponseEntity updateUserSkinType(@PathVariable long id, @RequestBody UserSkinRequest userSkinRequest) {
        Account account = userService.updateUserSkin(id, userSkinRequest    );
        return ResponseEntity.ok(account);
    }
}
