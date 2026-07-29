package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.CreateProductRequest;
import com.aeropelican.productservice.dto.request.UpdateProductRequest;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@RequestBody CreateProductRequest request) {
        return new ResponseEntity<>(productService.saveProduct(request), HttpStatus.CREATED);
    }

    // URL: GET /api/v1/products/0/10
    @GetMapping("/{page}/{size}")
    public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> getAllProducts(
            @PathVariable int page,
            @PathVariable int size) {
        return ResponseEntity.ok(productService.getAllProducts(page, size, "productId", "ASC"));
    }

    // URL: GET /api/v1/products/0/10/brand
    @GetMapping("/{page}/{size}/{sortBy}")
    public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> getAllProductsWithSort(
            @PathVariable int page,
            @PathVariable int size,
            @PathVariable String sortBy) {
        return ResponseEntity.ok(productService.getAllProducts(page, size, sortBy, "ASC"));
    }

    // URL: GET /api/v1/products/0/10/brand/desc
    @GetMapping("/{page}/{size}/{sortBy}/{sortDirection}")
    public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> getAllProductsWithSortDir(
            @PathVariable int page,
            @PathVariable int size,
            @PathVariable String sortBy,
            @PathVariable String sortDirection) {
        return ResponseEntity.ok(productService.getAllProducts(page, size, sortBy, sortDirection));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> searchProducts(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchProductsByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}