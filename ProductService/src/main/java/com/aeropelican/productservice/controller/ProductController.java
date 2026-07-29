package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // GET ALL PRODUCTS WITH PAGINATION
    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> getAllProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        PageRequest pageable = PageRequest.of(page, size);

        Page<ProductResponse> products =
                productService.listProducts(pageable);

        ApiResponse<Page<ProductResponse>> apiResponse =
                ApiResponse.<Page<ProductResponse>>builder()
                        .data(products)
                        .message("Products fetched successfully")
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(apiResponse);
    }

    // GET PRODUCT BY ID
    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProduct(
            @PathVariable("productId") Integer productId) {

        ProductResponse product = productService.getProduct(productId);

        ApiResponse<ProductResponse> apiResponse =
                ApiResponse.<ProductResponse>builder()
                        .data(product)
                        .message("Product details fetched successfully")
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(apiResponse);
    }
}