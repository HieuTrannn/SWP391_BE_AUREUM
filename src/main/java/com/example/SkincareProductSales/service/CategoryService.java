package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.Category;
import com.example.SkincareProductSales.entity.request.CategoryRequest;
import com.example.SkincareProductSales.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    public Category createCategory (CategoryRequest categoryRequest) {
        Category category = modelMapper.map(categoryRequest, Category.class);
        return categoryRepository.save(category);
    }

    public Category getCategoryById (long categoryId) {
        Category currentCategory = categoryRepository.findCategoryById(categoryId);
        if (currentCategory == null){
            throw new EntityNotFoundException("Category Not Found!");
        }
        return currentCategory;
    }

    public List<Category> getAllCategory(){
        return categoryRepository.findCategoryByIsDeletedFalse();
    }

    public List<Category> getAllCategoryIsDeleted() {
        return categoryRepository.findCategoryByIsDeletedTrue();
    }

    public Category updateCategory(long categoryId, CategoryRequest categoryRequest) {
        Category currentCategory = getCategoryById(categoryId);

        currentCategory.setName(categoryRequest.getName());
        return categoryRepository.save(currentCategory);
    }

    public Category deleteCategory(long categoryId) {
        Category currentCategory = categoryRepository.findCategoryById(categoryId);
        if (currentCategory == null) {
            throw new EntityNotFoundException("Category Not Found!");
        }
        currentCategory.setDeleted(true);
        return categoryRepository.save(currentCategory);
    }
}
