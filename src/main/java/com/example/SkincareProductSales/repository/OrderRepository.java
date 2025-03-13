package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByAccountId(Long accountId);

    Order findOrderById(Long id);
}
