package com.example.SkincareProductSales.mapper;

import com.example.SkincareProductSales.entity.Order;
import com.example.SkincareProductSales.entity.OrderDetail;
import com.example.SkincareProductSales.entity.Product;
import com.example.SkincareProductSales.entity.request.OrderDetailRequest;
import com.example.SkincareProductSales.entity.request.OrderRequest;
import com.example.SkincareProductSales.repository.ProductRepository;
import org.modelmapper.PropertyMap;

import java.util.ArrayList;
import java.util.Date;

public class OrderMapper extends PropertyMap<OrderRequest, Order> {
    @Override
    protected void configure() {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        map().setId(0);
        map().setCreateAt(new Date());
    }
}
