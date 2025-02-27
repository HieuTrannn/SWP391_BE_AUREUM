package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Brand;
import com.example.SkincareProductSales.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    public Brand findBrandById(long id);

    List<Brand> findBrandByIsDeletedFalse();

    List<Brand> findBrandByIsDeletedTrue();
}
