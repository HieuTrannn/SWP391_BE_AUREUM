package com.example.SkincareProductSales.mapper;

import com.example.SkincareProductSales.entity.Question;
import com.example.SkincareProductSales.entity.request.QuestionRequest;
import org.modelmapper.PropertyMap;

public class QuestionMapper extends PropertyMap<QuestionRequest, Question> {
    
    @Override
    protected void configure() {
        map().setId(0);
    }
}
