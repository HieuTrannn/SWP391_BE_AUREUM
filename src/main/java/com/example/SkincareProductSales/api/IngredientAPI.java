package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Ingredient;
import com.example.SkincareProductSales.entity.request.IngredientRequest;
import com.example.SkincareProductSales.repository.IngredientRepository;
import com.example.SkincareProductSales.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredient")
@CrossOrigin("*")
public class IngredientAPI {

    @Autowired
    IngredientService ingredientService;

    @PostMapping
    public ResponseEntity createIngredient(@Valid @RequestBody IngredientRequest ingredientRequest) {
        Ingredient newIngredient = ingredientService.createIngredient(ingredientRequest);
        return ResponseEntity.ok(newIngredient);
    }

    @GetMapping
    public ResponseEntity getAllIngredients() {
        List<Ingredient> allIngredients = ingredientService.getAllIngredients();
        return ResponseEntity.ok(allIngredients);
    }

    @GetMapping("/getAllIngredientIsDeleted")
    public ResponseEntity getAllIngredientIsDeleted() {
        List<Ingredient> allIngredients = ingredientService.getAllIngredientIsDeleted();
        return ResponseEntity.ok(allIngredients);
    }

    @GetMapping("{id}")
    public ResponseEntity getIngredientById(@PathVariable long id) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        return ResponseEntity.ok(ingredient);
    }

    @PutMapping("{id}")
    public ResponseEntity updateIngredient(@PathVariable long id, @Valid @RequestBody IngredientRequest ingredientRequest) {
        Ingredient ingredient = ingredientService.updateIngredient(id, ingredientRequest);
        return ResponseEntity.ok(ingredient);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteIngredient(@PathVariable long id) {
        Ingredient ingredient = ingredientService.deleteIngredient(id);
        return ResponseEntity.ok(ingredient);
    }
}
