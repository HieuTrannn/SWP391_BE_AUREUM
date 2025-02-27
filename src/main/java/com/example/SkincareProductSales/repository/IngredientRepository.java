package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Category;
import com.example.SkincareProductSales.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    public Ingredient findIngredientById(long id);

    List<Ingredient> findIngredientByIsDeletedFalse();

    List<Ingredient> findIngredientByIsDeletedTrue();
}
