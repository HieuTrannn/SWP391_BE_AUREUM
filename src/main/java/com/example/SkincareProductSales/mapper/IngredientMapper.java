package com.example.SkincareProductSales.mapper;

import com.example.SkincareProductSales.entity.Ingredient;
import com.example.SkincareProductSales.entity.request.IngredientRequest;
import org.modelmapper.PropertyMap;

public class IngredientMapper extends PropertyMap<IngredientRequest, Ingredient> {

    @Override
    protected void configure() {
        map().setId(0);
    }
}
