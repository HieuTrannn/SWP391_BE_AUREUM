package com.example.SkincareProductSales.entity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsResponse {
        private long totalProducts;
        private long totalOrders;
        private long totalCustomers;

    }
