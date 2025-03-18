package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Question;
import com.example.SkincareProductSales.entity.Skin;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findQuestionById(long id);

    List<Question> findQuestionsByIsDeletedFalse();

    List<Question> findQuestionsByIsDeletedTrue();
}
