package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.*;
import com.example.SkincareProductSales.entity.request.ReportRequest;
import com.example.SkincareProductSales.enums.OrderStatus;
import com.example.SkincareProductSales.exception.exceptions.BusinessLogicException;
import com.example.SkincareProductSales.repository.OrderDetailRepository;
import com.example.SkincareProductSales.repository.OrderRepository;
import com.example.SkincareProductSales.repository.ReportRepository;
import com.example.SkincareProductSales.utils.AccountUtils;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    AccountUtils accountUtils;

    public Report createReport(ReportRequest reportRequest) {
        OrderDetail orderDetail = orderDetailRepository.findOrderDetailById(reportRequest.getOrderDetailId())
                .orElseThrow(() -> new BusinessLogicException("Order Detail not found"));

        // check xem order này đã thành công chưa
        // nếu đơn đã thành cong rồi thì mới report
        // chưa thì báo lỗi
        if(!OrderStatus.PAID.equals(orderDetail.getOrder().getStatus())) {
            throw new BusinessLogicException("Order not completed so you cannot create a report");
        }

        Account account = accountUtils.getCurrentAccount();

        // check xem user đã report order chưa
        // nếu rồi thì báo lỗi
        // nếu chưa thì tạo mới report
        if(orderDetail.isReported()) {
            throw new BusinessLogicException("Product already Reported!");
        }
        orderDetail.setReported(true);
        orderDetailRepository.save(orderDetail);

        Report report = new Report();
        report.setAccount(account);
        report.setReason(reportRequest.getReason());
        report.setDescription(reportRequest.getDescription());
        report.setImage(reportRequest.getImage());
        report.setProduct(orderDetail.getProduct());

        return reportRepository.save(report);
    }

    public Report getReportById (long reportId) {
        Report report = reportRepository.findReportById(reportId).orElseThrow();
        if(report == null){
            throw new EntityNotFoundException("Report not found!");
        }
        return report;
    }


    public List<Report> getAllReport () {
        return reportRepository.findReportByIsDeletedFalse();
    }

    public List<Report> getAllReportIsDeleted () {
        return reportRepository.findReportByIsDeletedTrue();
    }

    public Report updateReport(long reportId, ReportRequest reportRequest){
        Report currentReport = getReportById(reportId);

        currentReport.setReason(reportRequest.getReason());
        currentReport.setDescription(reportRequest.getDescription());
        currentReport.setImage(reportRequest.getImage());

        return reportRepository.save(currentReport);
    }

    public Report deleteReport(long reportId) {
        Report currentReport = reportRepository.findReportById(reportId).orElseThrow();

        if(currentReport == null){
            throw new EntityNotFoundException("Report not found");
        }

        currentReport.setDeleted(true);
        return reportRepository.save(currentReport);
    }
}
