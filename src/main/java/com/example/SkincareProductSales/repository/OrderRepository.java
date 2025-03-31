package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Order;
import com.example.SkincareProductSales.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByAccountId(Long accountId);

    Optional<Order> findOrderById(Long id);

    @Query("SELECT SUM(o.finalTotal) FROM Order o " +
            "WHERE (YEAR(o.createAt) = :year AND (:period = 'year')) " +
            "OR (MONTH(o.createAt) = :month AND YEAR(o.createAt) = :year AND (:period = 'month')) " +
            "OR (o.createAt = :date AND (:period = 'day')) " +
            "AND o.status IN :statuses")
    Float getRevenueByPeriod(Date date, int month, int year, String period, @Param("statuses") List<OrderStatus> statuses);


    @Query("SELECT SUM(o.finalTotal) FROM Order o WHERE o.createAt = :date AND o.status IN :statuses")
    Float getRevenueByDay(Date date, @Param("statuses") List<OrderStatus> statuses);

    // Truy vấn doanh thu theo tháng cho các đơn hàng đã thanh toán (PAID)
    @Query("SELECT SUM(o.finalTotal) FROM Order o WHERE MONTH(o.createAt) = :month AND YEAR(o.createAt) = :year AND o.status IN :statuses")
    Float getRevenueByMonth(int month, int year, @Param("statuses") List<OrderStatus> statuses);

    // Truy vấn doanh thu theo năm cho các đơn hàng đã thanh toán (PAID), trả về doanh thu cho từng tháng trong năm
    @Query("SELECT MONTH(o.createAt), SUM(o.finalTotal) FROM Order o WHERE YEAR(o.createAt) = :year AND o.status IN :statuses GROUP BY MONTH(o.createAt) ORDER BY MONTH(o.createAt)")
    List<Object[]> getRevenueByYear(int year, @Param("statuses") List<OrderStatus> statuses);
}
