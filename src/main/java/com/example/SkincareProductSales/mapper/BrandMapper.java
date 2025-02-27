package com.example.SkincareProductSales.mapper;

import com.example.SkincareProductSales.entity.Brand;
import com.example.SkincareProductSales.entity.request.BrandRequest;
import org.modelmapper.PropertyMap;

public class BrandMapper extends PropertyMap<BrandRequest, Brand> {

    @Override
    protected void configure() {
        map().setId(0);
    }
}
