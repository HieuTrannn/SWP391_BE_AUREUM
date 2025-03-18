package com.example.SkincareProductSales.config;

import com.example.SkincareProductSales.mapper.*;
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
        modelMapper.addMappings(new QuestionMapper());
        modelMapper.addMappings(new AnswerMapper());
        return modelMapper;
    }

}
