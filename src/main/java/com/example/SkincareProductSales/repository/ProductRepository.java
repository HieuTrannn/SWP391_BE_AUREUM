package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Product;
import com.example.SkincareProductSales.enums.RoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.management.relation.Role;
import java.util.List;
import java.util.Objects;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(long id);

    Page<Product> findProductsByIsDeletedFalse(Pageable pageable);

    List<Product> findProductsByIsDeletedFalse();

    List<Product> findProductsByIsDeletedTrue();

    @Query("SELECT p from Product p WHERE p.category.id = :id")
    List<Product> findProductsByCategory(@Param("id") Long id);

    // Top 10 sản phẩm bán chạy nhất (name, quantity)
    @Query("select p.name, sum(od.quantity) as totalSold from OrderDetail od join od.product p " +
            "group by p.id " +
            "order by totalSold desc")
    List<Object[]> findTop10BestSellingProductsByIsDeletedFalse();
}
