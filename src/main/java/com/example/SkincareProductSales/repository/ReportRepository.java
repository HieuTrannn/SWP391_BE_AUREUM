package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Rating;
import com.example.SkincareProductSales.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Long> {
    Report findReportById(long id);

    List<Report> findReportByIsDeletedFalse();

    List<Report> findReportByIsDeletedTrue();
}
