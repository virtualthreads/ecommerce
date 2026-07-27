package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.CreateCategoryRequest;
import com.aeropelican.productservice.dto.UpdateCategory;
import com.aeropelican.productservice.dto.response.APIResponse;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public  class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/{category_id}")
    public  ResponseEntity<APIResponse<Category>> getCategory(@PathVariable Integer category_id){
        Category category = categoryService.getCategory(category_id);
        APIResponse<Category> apiResponse = APIResponse.<Category>builder()
                .data(category)
                .message("Category details fetched successfully")
                .success(category == null? false:true)
                .timestamp(LocalDateTime.now())
                .build();
        return  ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<APIResponse<Category>> createCategory(
            @RequestBody CreateCategoryRequest request) {
        Category category = categoryService.createCategory(request);
        APIResponse<Category> apiResponse = APIResponse.<Category>builder()
                .data(category)
                .message("Category created successfully")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
    @PutMapping("/{category_id}")
    public ResponseEntity<APIResponse<Category>> updateCategory(
            @PathVariable Integer category_id,
            @RequestBody UpdateCategory request) {

        Category category = categoryService.updateCategory(category_id, request);

        APIResponse<Category> apiResponse = APIResponse.<Category>builder()
                .data(category)
                .message("Category updated successfully")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{category_id}")
    public ResponseEntity<APIResponse<Category>> deleteCategory(
            @PathVariable Integer category_id) {

        Category category = categoryService.deleteCategory(category_id);

        APIResponse<Category> apiResponse = APIResponse.<Category>builder()
                .data(category)
                .message("Category deleted successfully")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}