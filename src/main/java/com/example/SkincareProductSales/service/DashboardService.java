package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.OrderDetail;
import com.example.SkincareProductSales.entity.response.StatisticsResponse;
import com.example.SkincareProductSales.enums.OrderStatus;
import com.example.SkincareProductSales.enums.RoleEnum;
import com.example.SkincareProductSales.repository.OrderDetailRepository;
import com.example.SkincareProductSales.repository.OrderRepository;
import com.example.SkincareProductSales.repository.ProductRepository;
import com.example.SkincareProductSales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashboardService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository accountRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    UserRepository userRepository;
    public StatisticsResponse getDashboardStatistics() {
        long totalProducts = productRepository.count();
        long totalOrders = orderRepository.count();
        long totalCustomers = accountRepository.count();
        return new StatisticsResponse(totalProducts, totalOrders, totalCustomers);
    }

    public Map<String, Object> getDashboardStates() {
        Map<String, Object> states = new HashMap<>();
        // đếm số lượng sản phẩm trong hệ thôngs
        long totalProducts = productRepository.count();
        states.put("totalProducts", totalProducts);

        // số lượng customer
        long customerCount = userRepository.countByRole(RoleEnum.CUSTOMER);
        states.put("customerCount", customerCount);

        // Top 10 sản phẩm bán chạy nhất ở aureum
        List<Object[]> topProducts = productRepository.findTop10BestSellingProductsByIsDeletedFalse();
        List<Map<String, Object>> topProductsList = new ArrayList<>();

        for(Object[] productData: topProducts) {
            Map<String, Object> productInfo = new HashMap<>();
            productInfo.put("productName", productData[0]);
            productInfo.put("totalSold", productData[1]);
            topProductsList.add(productInfo);
        }

        states.put("topProducts", topProductsList);

        return states;
    }

    // Phương thức lấy top 5 sản phẩm bán chạy nhất
    public List<Object[]> getTop5BestSellingProducts() {
        return orderDetailRepository.findTop5BestSellingProducts();
    }

    public Object getRevenueByPeriod(Date date, Integer month, Integer year, String period) {
        // Nếu không có tham số year, sử dụng năm hiện tại
        if (year == null) {
            year = Calendar.getInstance().get(Calendar.YEAR);  // Lấy năm hiện tại
        }

        List<OrderStatus> statuses = Arrays.asList(OrderStatus.PAID, OrderStatus.COMPLETED);  // Lấy cả PAID và COMPLETED

        // Kiểm tra period và xử lý theo từng trường hợp
        if ("day".equals(period)) {
            return orderRepository.getRevenueByDay(date, statuses) != null
                    ? orderRepository.getRevenueByDay(date, statuses)
                    : 0;
        } else if ("month".equals(period)) {
            // Kiểm tra month và year để tránh null
            if (month != null && year != null) {
                return orderRepository.getRevenueByMonth(month, year, statuses) != null
                        ? orderRepository.getRevenueByMonth(month, year, statuses)
                        : 0;
            } else {
                throw new IllegalArgumentException("Month and year must be provided when period is 'month'");
            }
        } else if ("year".equals(period)) {
            // Trả về doanh thu theo từng tháng trong năm
            if (year != null) {
                List<Object[]> results = orderRepository.getRevenueByYear(year, statuses);
                if (results.isEmpty()) {
                    return 0;
                }
                return results;
            } else {
                throw new IllegalArgumentException("Year must be provided when period is 'year'");
            }
        }
        return 0;  // Mặc định trả về 0 nếu không match được với period
    }

}
