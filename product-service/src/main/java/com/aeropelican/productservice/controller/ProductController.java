package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.CreateProductRequest;
import com.aeropelican.productservice.dto.UpdateProduct;
import com.aeropelican.productservice.dto.response.APIResponse;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @GetMapping
    public List<Product> getAllProducts() {
        List<Product> result = productService.listProducts();
        return result;
    }
    @GetMapping("/{productId}")
    public ResponseEntity<APIResponse<Product>> getProduct(
            @PathVariable Integer productId) {
        Product product = productService.getProduct(productId);
        APIResponse<Product> response = APIResponse.<Product>builder()
                .data(product)
                .message("Product fetched successfully")
                .success(product != null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
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

        Product product = productService.updateProduct(productId,request);

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
