package com.example.SkincareProductSales.mapper;

import com.example.SkincareProductSales.entity.Answer;
import com.example.SkincareProductSales.entity.request.AnswerRequest;
import org.modelmapper.PropertyMap;

public class AnswerMapper extends PropertyMap<AnswerRequest, Answer> {

    @Override
    protected void configure() {
        map().setId(0);
    }
}
