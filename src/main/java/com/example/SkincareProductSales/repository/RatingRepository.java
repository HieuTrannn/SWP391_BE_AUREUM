package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    public Rating findRatingById(long id);

    List<Rating> findRatingByIsDeletedFalse();

    List<Rating> findRatingByIsDeletedTrue();
}
