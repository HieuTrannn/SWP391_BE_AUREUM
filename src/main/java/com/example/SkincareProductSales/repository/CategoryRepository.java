package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Category findCategoryById(long id);

    List<Category> findCategoryByIsDeletedFalse();

    List<Category> findCategoryByIsDeletedTrue();

    Category getProductById(long id);
}
