package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Account;
import com.example.SkincareProductSales.entity.Order;
import com.example.SkincareProductSales.entity.request.UserRequest;
import com.example.SkincareProductSales.enums.OrderStatusEnum;
import com.example.SkincareProductSales.enums.RoleEnum;
import com.example.SkincareProductSales.enums.SkinTypeEnum;
import com.example.SkincareProductSales.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PatchMapping("/updateSkinType/{id}")
    public ResponseEntity updateSkinType(@RequestParam SkinTypeEnum skinTypeEnum, @PathVariable Long id){
        Account account = userService.updateSkinType(skinTypeEnum,id);
        return ResponseEntity.ok(account);
    }
}
