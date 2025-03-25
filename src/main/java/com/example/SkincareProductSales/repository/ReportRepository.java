package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Rating;
import com.example.SkincareProductSales.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report,Long> {
    Optional<Report> findReportById(long id);

    List<Report> findReportByIsDeletedFalse();

    List<Report> findReportByIsDeletedTrue();
}
