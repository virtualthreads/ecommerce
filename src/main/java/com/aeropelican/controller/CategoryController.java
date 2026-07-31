package com.aeropelican.controller;

import com.aeropelican.dto.CreateCategoryRequest;
import com.aeropelican.dto.UpdateCategory;
import com.aeropelican.dto.APIResponse;
import com.aeropelican.dto.CategoryResponse;
import com.aeropelican.dto.PageResponse;
import com.aeropelican.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{page}/{size}/{sortBy}/{sortDir}")
    public ResponseEntity<APIResponse<PageResponse<CategoryResponse>>> getAllCategories(
            @PathVariable int page,
            @PathVariable int size,
            @PathVariable String sortBy,
            @PathVariable String sortDir) {

        PageResponse<CategoryResponse> result =
                categoryService.listCategories(page, size, sortBy, sortDir);

        return ResponseEntity.ok(
                APIResponse.<PageResponse<CategoryResponse>>builder()
                        .data(result)
                        .message("Products fetched successfully")
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<APIResponse<Category>> getCategory(@PathVariable Integer category_id) {
        Category category = categoryService.getCategory(category_id);
        APIResponse<Category> apiResponse = APIResponse.<Category>builder()
                .data(category)
                .message("Category details fetched successfully")
                .success(category != null)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
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