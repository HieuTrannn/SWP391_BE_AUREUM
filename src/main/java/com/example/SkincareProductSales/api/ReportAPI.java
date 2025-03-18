package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Rating;
import com.example.SkincareProductSales.entity.Report;
import com.example.SkincareProductSales.entity.request.RatingRequest;
import com.example.SkincareProductSales.entity.request.ReportRequest;
import com.example.SkincareProductSales.service.ReportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
@SecurityRequirement(name = "api")
@CrossOrigin("*")
public class ReportAPI {
    @Autowired
    ReportService reportService;

    @PostMapping
    public ResponseEntity createReport(@Valid @RequestBody ReportRequest reportRequest) {
        Report report = reportService.createReport(reportRequest);
        return ResponseEntity.ok(report);
    }

    @GetMapping
    public ResponseEntity getAllReport () {
        List<Report> reports = reportService.getAllReport();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("{id}")
    public ResponseEntity getReportById(@PathVariable long id) {
        Report report = reportService.getReportById(id);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/getAllReportIsDeleted")
    public ResponseEntity getAllReportIsDeleted() {
        List<Report> reports = reportService.getAllReportIsDeleted();
        return ResponseEntity.ok(reports);
    }

    @PutMapping("{id}")
    public ResponseEntity updateReport (@PathVariable long id, @Valid @RequestBody ReportRequest reportRequest) {
        Report updateReport = reportService.updateReport(id, reportRequest);
        return ResponseEntity.ok(updateReport);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteReport(@PathVariable long id) {
        Report deleteReport = reportService.deleteReport(id);
        return ResponseEntity.ok(deleteReport);
    }
}
