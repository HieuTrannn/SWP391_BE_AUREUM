package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Order;
import com.example.SkincareProductSales.entity.request.OrderRequest;
import com.example.SkincareProductSales.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")

public class OrderAPI {
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity create(@RequestBody OrderRequest orderRequest){
        Order order = orderService.create(orderRequest);
        return ResponseEntity.ok(order);

    }
}
