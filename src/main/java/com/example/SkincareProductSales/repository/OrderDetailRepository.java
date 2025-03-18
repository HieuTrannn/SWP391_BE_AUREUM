package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    Optional<OrderDetail> findOrderDetailById(long id);
}
