package com.aeropelican.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{page}/{size}/{sortBy}/{sortDir}")
    public ResponseEntity<APIResponse<PageResponse<ProductResponse>>> getAllProducts(
            @PathVariable int page,
            @PathVariable int size,
            @PathVariable String sortBy,
            @PathVariable String sortDir) {

        PageResponse<ProductResponse> result =
                productService.listProducts(page, size, sortBy, sortDir);

        return ResponseEntity.ok(
                APIResponse.<PageResponse<ProductResponse>>builder()
                        .data(result)
                        .message("Products fetched successfully")
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<APIResponse<Product>> createProduct(
            @RequestBody CreateProductRequest request) {
        Product product = productService.createProduct(request);
        APIResponse<Product> response = APIResponse.<Product>builder()
                .data(product)
                .message("Product created successfully")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<APIResponse<Product>> updateProduct(
            @PathVariable Integer productId,
            @RequestBody UpdateProduct request) {

        Product product = productService.updateProduct(productId, request);

        APIResponse<Product> response = APIResponse.<Product>builder()
                .data(product)
                .message("Product updated successfully")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<APIResponse<Product>> deleteProduct(
            @PathVariable Integer productId) {

        Product product = productService.deleteProduct(productId);

        APIResponse<Product> response = APIResponse.<Product>builder()
                .data(product)
                .message("Product deleted successfully")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}