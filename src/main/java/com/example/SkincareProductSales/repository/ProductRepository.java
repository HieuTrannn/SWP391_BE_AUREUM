package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(long id);

    List<Product> findProductsByIsDeletedFalse();
}
