package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
