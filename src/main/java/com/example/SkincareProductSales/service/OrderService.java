package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.Account;
import com.example.SkincareProductSales.entity.Order;
import com.example.SkincareProductSales.entity.OrderDetail;
import com.example.SkincareProductSales.entity.Product;
import com.example.SkincareProductSales.entity.request.OrderDetailRequest;
import com.example.SkincareProductSales.entity.request.OrderRequest;
import com.example.SkincareProductSales.repository.AuthenticationRepository;
import com.example.SkincareProductSales.repository.OrderRepository;
import com.example.SkincareProductSales.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProductRepository productRepository;

    public Order create(OrderRequest orderRequest){

        List<OrderDetail> orderDetails = new ArrayList<>();
        Order order = modelMapper.map(orderRequest, Order.class);

        for (OrderDetailRequest orderDetailRequest: orderRequest.getDetails()){
            OrderDetail orderDetail = new OrderDetail();
            Product product = productRepository.findProductById(orderDetailRequest.getProductId());
            orderDetail.setProduct(product);
            orderDetail.setQuantity(orderDetailRequest.getQuantity());
            orderDetail.setPrice(product.getPrice());
            orderDetail.setOrder(order);
            orderDetails.add(orderDetail);
        }

        order.setOrderDetails(orderDetails);
//        order.setOrderDetails(orderDetails);
        return orderRepository.save(order);
    }
}
