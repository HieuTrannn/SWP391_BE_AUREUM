package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    public Feedback findFeedbackById(long id);

    List<Feedback> findFeedbackByIsDeletedFalse();

    List<Feedback> findFeedbackByIsDeletedTrue();
}
