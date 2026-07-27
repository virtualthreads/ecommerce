package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.CreateCategoryRequest;
import com.aeropelican.productservice.dto.request.UpdateCategoryRequest;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // Get All Categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Get Category By Id
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    // Create Category
    public Category createCategory(CreateCategoryRequest request) {

        Category category = new Category();

        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());
        category.setIsActive(request.getIsActive());
        category.setCreatedAt(LocalDateTime.now());

        return categoryRepository.save(category);
    }

    // Update Category
    public Category updateCategory(Long categoryId,
                                   UpdateCategoryRequest request) {

        Category category =
                categoryRepository.findById(categoryId).orElse(null);

        if (category == null) {
            return null;
        }

        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());
        category.setIsActive(request.getIsActive());

        return categoryRepository.save(category);
    }

    // Delete Category
    public boolean deleteCategory(Long categoryId) {

        if (!categoryRepository.existsById(categoryId)) {
            return false;
        }

        categoryRepository.deleteById(categoryId);

        return true;
    }

}