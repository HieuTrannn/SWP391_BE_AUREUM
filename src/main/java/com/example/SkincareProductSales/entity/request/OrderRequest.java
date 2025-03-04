package com.example.SkincareProductSales.entity.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
 private List<OrderDetailRequest> details;
}
