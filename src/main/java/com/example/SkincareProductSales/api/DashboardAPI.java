package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.OrderDetail;
import com.example.SkincareProductSales.entity.response.StatisticsResponse;
import com.example.SkincareProductSales.service.DashboardService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
@SecurityRequirement(name = "api")
@CrossOrigin("*")
public class DashboardAPI {

    @Autowired
    DashboardService dashboardService;

    @GetMapping("/statistics")
    public StatisticsResponse getDashboardStatistics() {
        return dashboardService.getDashboardStatistics();
    }

    // API lấy Top 5 sản phẩm bán chạy nhất
    @GetMapping("/top-selling-products")
    public List<Object[]> getTop5BestSellingProducts() {
        return dashboardService.getTop5BestSellingProducts();
    }

    @GetMapping("/admin/dashboard/revenue")
    public Object getRevenueByPeriod(
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,  // Ngày (dùng khi period = 'day')
            @RequestParam(required = false) Integer month, // Tháng (dùng khi period = 'month')
            @RequestParam(required = false) Integer year  // Năm (dùng khi period = 'year')
    ) {
        // Nếu có month, lấy doanh thu theo tháng
        if (month != null) {
            return dashboardService.getRevenueByPeriod(null, month, year != null ? year : Calendar.getInstance().get(Calendar.YEAR), "month");
        }
        // Nếu có date, lấy doanh thu theo ngày
        else if (date != null) {
            return dashboardService.getRevenueByPeriod(date, null, year != null ? year : Calendar.getInstance().get(Calendar.YEAR), "day");
        }
        // Nếu có year, lấy doanh thu theo năm
        else if (year != null) {
            return dashboardService.getRevenueByPeriod(null, null, year, "year");
        }

        // Nếu không có tham số nào, trả về doanh thu cho năm hiện tại
        return dashboardService.getRevenueByPeriod(null, null, Calendar.getInstance().get(Calendar.YEAR), "year");
    }
}
