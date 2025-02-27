package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.Ingredient;
import com.example.SkincareProductSales.entity.request.IngredientRequest;
import com.example.SkincareProductSales.repository.IngredientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    ModelMapper modelMapper;

    public Ingredient createIngredient(IngredientRequest ingredientRequest) {
        Ingredient ingredient = modelMapper.map(ingredientRequest, Ingredient.class);
        return ingredientRepository.save(ingredient);
    }

    public Ingredient getIngredientById(long ingredientId) {
        Ingredient currentIngredient = ingredientRepository.findIngredientById(ingredientId);
        if (currentIngredient == null) {
            throw new EntityNotFoundException("Ingredient not found");
        }
        return currentIngredient;
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findIngredientByIsDeletedFalse();
    }

    public List<Ingredient> getAllIngredientIsDeleted() {
        return ingredientRepository.findIngredientByIsDeletedTrue();
    }

    public Ingredient updateIngredient(long ingredientId, IngredientRequest ingredientRequest) {
        Ingredient currentIngredient = getIngredientById(ingredientId);

        currentIngredient.setName(ingredientRequest.getName());
        return ingredientRepository.save(currentIngredient);
    }

    public Ingredient deleteIngredient(long ingredientId) {
        Ingredient currentIngredient = ingredientRepository.findIngredientById(ingredientId);
        if (currentIngredient == null) {
            throw new EntityNotFoundException("Ingredient not found");
        }
        currentIngredient.setDeleted(true);
        return ingredientRepository.save(currentIngredient);
    }
}
