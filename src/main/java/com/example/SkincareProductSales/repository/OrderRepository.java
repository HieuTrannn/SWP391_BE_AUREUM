package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByAccountId(Long accountId);

    Optional<Order> findOrderById(Long id);
}
