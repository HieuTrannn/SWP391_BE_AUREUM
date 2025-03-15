package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
