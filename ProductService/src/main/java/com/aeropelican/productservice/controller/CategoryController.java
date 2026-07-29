package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.CategoryResponse;
import com.aeropelican.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // GET CATEGORY BY ID
    @GetMapping("/{catId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategory(
            @PathVariable("catId") Long catId) {

        CategoryResponse category = categoryService.getCategory(catId);

        ApiResponse<CategoryResponse> apiResponse =
                ApiResponse.<CategoryResponse>builder()
                        .data(category)
                        .message("Category details fetched successfully")
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(apiResponse);
    }
}