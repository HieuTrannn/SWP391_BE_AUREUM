package com.example.SkincareProductSales.mapper;

import com.example.SkincareProductSales.entity.Product;
import com.example.SkincareProductSales.entity.request.ProductRequest;
import org.modelmapper.PropertyMap;

public class ProductMapper extends PropertyMap<ProductRequest, Product> {

    @Override
    protected void configure() {
        map().setId(0);
    }
}
