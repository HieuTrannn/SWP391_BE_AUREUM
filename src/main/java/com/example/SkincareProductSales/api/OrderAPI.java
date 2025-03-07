package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Order;
import com.example.SkincareProductSales.entity.request.OrderRequest;
import com.example.SkincareProductSales.entity.response.OrderResponse;
import com.example.SkincareProductSales.enums.OrderStatusEnum;
import com.example.SkincareProductSales.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
@SecurityRequirement(name = "api")

public class OrderAPI {
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity create(@RequestBody OrderRequest orderRequest) throws Exception{
        String urlPayment = orderService.create(orderRequest);
        return ResponseEntity.ok(urlPayment);
    }

    @PatchMapping("{id}")
    public ResponseEntity updateStatus(@RequestParam OrderStatusEnum statusEnum, @PathVariable Long id){
        Order order = orderService.updateStatus(statusEnum,id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity getAll(){
        List<Order> orders = orderService.getALl();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user")
    public ResponseEntity getOrdersByUser(){
        List<Order> orders = orderService.getOrdersByUser();
        return ResponseEntity.ok(orders);
    }

}
