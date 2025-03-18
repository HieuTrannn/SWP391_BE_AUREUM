package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Answer findAnswerById(long id);

    List<Answer> findAnswersByIsDeletedFalse();

    List<Answer> findAnswersByIsDeletedTrue();

}
