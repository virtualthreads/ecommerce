package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.CreateCategoryRequest;
import com.aeropelican.productservice.dto.request.UpdateCategoryRequest;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.CategoryResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@RequestBody CreateCategoryRequest request) {
        return new ResponseEntity<>(categoryService.saveCategory(request), HttpStatus.CREATED);
    }

    // URL: GET /api/v1/categories/0/10
    @GetMapping("/{page}/{size}")
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> getAllCategories(
            @PathVariable int page,
            @PathVariable int size) {
        return ResponseEntity.ok(categoryService.getAllCategories(page, size, "categoryId", "ASC"));
    }

    // URL: GET /api/v1/categories/0/10/categoryName
    @GetMapping("/{page}/{size}/{sortBy}")
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> getAllCategoriesWithSort(
            @PathVariable int page,
            @PathVariable int size,
            @PathVariable String sortBy) {
        return ResponseEntity.ok(categoryService.getAllCategories(page, size, sortBy, "ASC"));
    }

    // URL: GET /api/v1/categories/0/10/categoryName/desc
    @GetMapping("/{page}/{size}/{sortBy}/{sortDirection}")
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> getAllCategoriesWithSortDir(
            @PathVariable int page,
            @PathVariable int size,
            @PathVariable String sortBy,
            @PathVariable String sortDirection) {
        return ResponseEntity.ok(categoryService.getAllCategories(page, size, sortBy, sortDirection));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(@PathVariable Integer id, @RequestBody UpdateCategoryRequest request) {
        return ResponseEntity.ok(categoryService.updateCategory(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }
}