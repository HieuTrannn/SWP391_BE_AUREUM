package com.example.SkincareProductSales.config;

import com.example.SkincareProductSales.mapper.BrandMapper;
import com.example.SkincareProductSales.mapper.IngredientMapper;
import com.example.SkincareProductSales.mapper.OrderMapper;
import com.example.SkincareProductSales.mapper.ProductMapper;
import com.example.SkincareProductSales.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {


    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new ProductMapper());
        modelMapper.addMappings(new BrandMapper());
        modelMapper.addMappings(new IngredientMapper());
        modelMapper.addMappings(new OrderMapper());
        return modelMapper;
    }

}
