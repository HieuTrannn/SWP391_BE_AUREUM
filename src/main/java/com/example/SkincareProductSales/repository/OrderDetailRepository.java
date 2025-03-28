package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.OrderDetail;
import com.example.SkincareProductSales.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    Optional<OrderDetail> findOrderDetailById(long id);

    @Query("SELECT od.product, SUM(od.quantity) AS totalSold " +
            "FROM OrderDetail od " +
            "WHERE od.order.status = 1 " +  // chỉ lấy đơn hàng đã thanh toán
            "GROUP BY od.product " +
            "ORDER BY totalSold DESC")
    List<Object[]> findTop5BestSellingProducts(@Param("status") OrderStatus status);
}
