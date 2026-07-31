package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.CreateCategoryRequest;
import com.aeropelican.productservice.dto.request.UpdateCategoryRequest;
import com.aeropelican.productservice.dto.response.CategoryResponse;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.exception.BadRequestException;
import com.aeropelican.productservice.exception.DuplicateResourceException;
import com.aeropelican.productservice.exception.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.CategoryMapper;
import com.aeropelican.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // ============================
    // GET ALL CATEGORIES
    // ============================

    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }

    // ============================
    // GET CATEGORY BY ID
    // ============================

    public CategoryResponse getCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id : " + categoryId));

        return CategoryMapper.toResponse(category);
    }

    // ============================
    // CREATE CATEGORY
    // ============================

    public CategoryResponse createCategory(CreateCategoryRequest request) {

        if (request.getCategoryName() == null ||
                request.getCategoryName().trim().isEmpty()) {

            throw new BadRequestException("Category name is required.");
        }

        boolean exists = categoryRepository.findAll()
                .stream()
                .anyMatch(c ->
                        c.getCategoryName().equalsIgnoreCase(
                                request.getCategoryName().trim()));

        if (exists) {
            throw new DuplicateResourceException(
                    "Category already exists.");
        }

        Category category = new Category();

        category.setCategoryName(request.getCategoryName().trim());
        category.setDescription(request.getDescription());
        category.setIsActive(
                request.getIsActive() == null
                        ? true
                        : request.getIsActive());

        category.setCreatedAt(LocalDateTime.now());

        category = categoryRepository.save(category);

        return CategoryMapper.toResponse(category);
    }

    // ============================
    // UPDATE CATEGORY
    // ============================

    public CategoryResponse updateCategory(
            Long categoryId,
            UpdateCategoryRequest request) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id : " + categoryId));

        if (request.getCategoryName() == null ||
                request.getCategoryName().trim().isEmpty()) {

            throw new BadRequestException("Category name is required.");
        }

        category.setCategoryName(request.getCategoryName().trim());
        category.setDescription(request.getDescription());
        category.setIsActive(request.getIsActive());

        category = categoryRepository.save(category);

        return CategoryMapper.toResponse(category);
    }

    // ============================
    // DELETE CATEGORY
    // ============================

    public boolean deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id : " + categoryId));

        categoryRepository.delete(category);

        return true;
    }

}