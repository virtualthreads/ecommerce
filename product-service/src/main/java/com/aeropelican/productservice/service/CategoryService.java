package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.CreateCategoryRequest;
import com.aeropelican.productservice.dto.request.UpdateCategoryRequest;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.CategoryResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.exceptions.CategoryNotFoundException;
import com.aeropelican.productservice.mapper.CategoryMapper;
import com.aeropelican.productservice.mapper.PageResponseMapper;
import com.aeropelican.productservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ApiResponse<CategoryResponse> saveCategory(CreateCategoryRequest request) {

        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        Category savedCategory = categoryRepository.save(category);
        return ApiResponse.success("Category created successfully", CategoryMapper.toCategoryResponse(savedCategory));
    }

    public ApiResponse<PageResponse<CategoryResponse>> getAllCategories(int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        List<CategoryResponse> mappedList = categoryPage.getContent()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .collect(Collectors.toList());

        PageResponse<CategoryResponse> pageResponse = PageResponseMapper.toPageResponse(categoryPage, mappedList);

        return ApiResponse.success("Categories retrieved successfully", pageResponse);
    }

    public ApiResponse<CategoryResponse> getCategoryById(Integer id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with ID " + id + " not found"));

        return ApiResponse.success("Category retrieved successfully", CategoryMapper.toCategoryResponse(category));
    }

    public ApiResponse<CategoryResponse> updateCategory(Integer id, UpdateCategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category Not Found"));

        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        Category updatedCategory = categoryRepository.save(category);
        return ApiResponse.success("Category updated successfully", CategoryMapper.toCategoryResponse(updatedCategory));
    }

    public ApiResponse<String> deleteCategory(Integer id) {

        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Category with ID " + id + " not found");
        }

        categoryRepository.deleteById(id);

        return ApiResponse.success("Category Deleted Successfully", null);
    }
}