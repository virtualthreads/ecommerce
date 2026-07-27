package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.CreateProductRequest;
import com.aeropelican.productservice.dto.UpdateProductRequest;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ============================
    // GET ALL PRODUCTS
    // ============================
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.listProducts();
    }

    // ============================
    // GET PRODUCT BY ID
    // ============================
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(
            @PathVariable("productId") Integer productId) {

        Product product = productService.getProduct(productId);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    // ============================
    // CREATE PRODUCT
    // ============================
    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestBody CreateProductRequest createProductRequest) {

        System.out.println("Received a POST request from client");

        Product product = productService.createProduct(createProductRequest);

        return ResponseEntity.ok(product);
    }

    // ============================
    // UPDATE PRODUCT
    // ============================
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("productId") Integer productId,
            @RequestBody UpdateProductRequest request) {

        Product product = productService.updateProduct(productId, request);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    // ============================
    // DELETE PRODUCT
    // ============================
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable("productId") Integer productId) {

        boolean deleted = productService.deleteProduct(productId);

        if (deleted) {
            return ResponseEntity.ok("Product Deleted Successfully");
        }

        return ResponseEntity.notFound().build();
    }
}