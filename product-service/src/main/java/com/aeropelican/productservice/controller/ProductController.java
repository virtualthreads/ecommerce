package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.CreateProductRequest;
import com.aeropelican.productservice.dto.request.UpdateProductRequest;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ============================
    // GET ALL PRODUCTS
    // ============================
    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {

        List<Product> products = productService.getAllProducts();

        ApiResponse<List<Product>> response = ApiResponse.<List<Product>>builder()
                .success(true)
                .message("Products fetched successfully")
                .data(products)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    // ============================
    // GET PRODUCT BY ID
    // ============================
    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<Product>> getProduct(
            @PathVariable Long productId) {

        Product product = productService.getProduct(productId);

        ApiResponse<Product> response = ApiResponse.<Product>builder()
                .success(product != null)
                .message(product != null
                        ? "Product fetched successfully"
                        : "Product not found")
                .data(product)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    // ============================
    // CREATE PRODUCT
    // ============================
    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(
            @RequestBody CreateProductRequest request) {

        Product product = productService.createProduct(request);

        ApiResponse<Product> response = ApiResponse.<Product>builder()
                .success(product != null)
                .message(product != null
                        ? "Product created successfully"
                        : "Category not found")
                .data(product)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    // ============================
    // UPDATE PRODUCT
    // ============================
    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(
            @PathVariable Long productId,
            @RequestBody UpdateProductRequest request) {

        Product product = productService.updateProduct(productId, request);

        ApiResponse<Product> response = ApiResponse.<Product>builder()
                .success(product != null)
                .message(product != null
                        ? "Product updated successfully"
                        : "Product or Category not found")
                .data(product)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    // ============================
    // DELETE PRODUCT
    // ============================
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(
            @PathVariable Long productId) {

        boolean deleted = productService.deleteProduct(productId);

        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(deleted)
                .message(deleted
                        ? "Product deleted successfully"
                        : "Product not found")
                .data(deleted
                        ? "Product deleted successfully"
                        : "No Product Found")
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

}