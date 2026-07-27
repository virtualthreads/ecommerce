package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.CreateCategoryRequest;
import com.aeropelican.productservice.dto.request.UpdateCategoryRequest;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories() {

        List<Category> categories = categoryService.getAllCategories();

        ApiResponse<List<Category>> response =
                ApiResponse.<List<Category>>builder()
                        .success(true)
                        .message("Categories fetched successfully")
                        .data(categories)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<Category>> getCategory(
            @PathVariable Long categoryId) {

        Category category = categoryService.getCategory(categoryId);

        ApiResponse<Category> response =
                ApiResponse.<Category>builder()
                        .success(category != null)
                        .message(category != null ?
                                "Category fetched successfully"
                                : "Category not found")
                        .data(category)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Category>> createCategory(
            @RequestBody CreateCategoryRequest request) {

        Category category = categoryService.createCategory(request);

        ApiResponse<Category> response =
                ApiResponse.<Category>builder()
                        .success(true)
                        .message("Category created successfully")
                        .data(category)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<Category>> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody UpdateCategoryRequest request) {

        Category category =
                categoryService.updateCategory(categoryId, request);

        ApiResponse<Category> response =
                ApiResponse.<Category>builder()
                        .success(category != null)
                        .message(category != null ?
                                "Category updated successfully"
                                : "Category not found")
                        .data(category)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(
            @PathVariable Long categoryId) {

        boolean deleted = categoryService.deleteCategory(categoryId);

        ApiResponse<String> response =
                ApiResponse.<String>builder()
                        .success(deleted)
                        .message(deleted ?
                                "Category deleted successfully"
                                : "Category not found")
                        .data(deleted ?
                                "Category deleted successfully"
                                : "No Category Found")
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

}