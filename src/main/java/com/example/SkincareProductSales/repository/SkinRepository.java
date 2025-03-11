package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Skin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkinRepository extends JpaRepository<Skin,Long> {

    Skin findSkinById(long id);
}
