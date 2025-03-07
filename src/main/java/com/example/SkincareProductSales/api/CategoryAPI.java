package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Category;
import com.example.SkincareProductSales.entity.request.CategoryRequest;
import com.example.SkincareProductSales.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
@SecurityRequirement(name = "api")

public class CategoryAPI {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity createCategory (@Valid @RequestBody CategoryRequest categoryRequest) {
        Category newCategory = categoryService.createCategory(categoryRequest);
        return ResponseEntity.ok(newCategory);
    }

    @GetMapping
    public ResponseEntity GetAllCategory() {
        List<Category> categories = categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/getAllCategoryIsDeleted")
    public ResponseEntity GetAllCategoryIsDeleted(){
        List<Category> categories = categoryService.getAllCategoryIsDeleted();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("{id}")
    public ResponseEntity GetCategoryById(@PathVariable long id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PutMapping("{id}")
    public ResponseEntity updateCategory(@PathVariable long id, @Valid @RequestBody CategoryRequest categoryRequest){
        Category updateCategory = categoryService.updateCategory(id, categoryRequest);
        return ResponseEntity.ok(updateCategory);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCategory(@PathVariable long id) {
        Category deleteCategory = categoryService.deleteCategory(id);
        return ResponseEntity.ok(deleteCategory);

    }
}
