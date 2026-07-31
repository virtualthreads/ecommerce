package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.CreateProductRequest;
import com.aeropelican.productservice.dto.request.UpdateProductRequest;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ==========================================
    // GET ALL PRODUCTS
    // ==========================================

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts() {

        return ResponseEntity.ok(
                ApiResponse.<List<ProductResponse>>builder()
                        .success(true)
                        .message("Products fetched successfully.")
                        .data(productService.getAllProducts())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // ==========================================
    // GET PRODUCT BY ID
    // ==========================================

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProduct(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                ApiResponse.<ProductResponse>builder()
                        .success(true)
                        .message("Product fetched successfully.")
                        .data(productService.getProduct(productId))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // ==========================================
    // CREATE PRODUCT
    // ==========================================

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(
            @Valid @RequestBody CreateProductRequest request) {

        return ResponseEntity.ok(
                ApiResponse.<ProductResponse>builder()
                        .success(true)
                        .message("Product created successfully.")
                        .data(productService.createProduct(request))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // ==========================================
    // UPDATE PRODUCT
    // ==========================================

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody UpdateProductRequest request) {

        return ResponseEntity.ok(
                ApiResponse.<ProductResponse>builder()
                        .success(true)
                        .message("Product updated successfully.")
                        .data(productService.updateProduct(productId, request))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // ==========================================
    // DELETE PRODUCT
    // ==========================================

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(
            @PathVariable Long productId) {

        productService.deleteProduct(productId);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Product deleted successfully.")
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}