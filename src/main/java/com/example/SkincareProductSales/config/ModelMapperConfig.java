package com.example.SkincareProductSales.config;

import com.example.SkincareProductSales.mapper.ProductMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new ProductMapper());
        return modelMapper;
    }
}
