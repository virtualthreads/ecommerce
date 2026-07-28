package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.CreateCategoryRequest;
import com.aeropelican.productservice.dto.request.UpdateCategoryRequest;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(CreateCategoryRequest request) {

        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {

        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with ID " + id + " not found"));
    }

    public Category updateCategory(Integer id, UpdateCategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found"));

        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        return categoryRepository.save(category);
    }

    public String deleteCategory(Integer id) {

        categoryRepository.deleteById(id);

        return "Category Deleted Successfully";
    }

}