package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(long id);

    Page<Product> findProductsByIsDeletedFalse(Pageable pageable);

    List<Product> findProductsByIsDeletedFalse();

    List<Product> findProductsByIsDeletedTrue();

    @Query("SELECT p from Product p WHERE p.category.id = :id")
    List<Product> findProductsByCategory(@Param("id") Long id);


}
