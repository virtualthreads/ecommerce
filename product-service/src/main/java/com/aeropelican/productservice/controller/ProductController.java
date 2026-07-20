package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.CreateProductRequest;
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

    // Get All Products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.listProducts();
    }

    // Get Product By Id
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {

        Product product = productService.getProduct(productId);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    // Create Product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) {

        Product product = productService.createProduct(request);

        return ResponseEntity.ok(product);
    }

    // Update Product Quantity
    @PutMapping("/{productId}/{quantity}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Integer productId,
            @PathVariable Integer quantity) {

        Product product = productService.updateProduct(productId, quantity);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    // Delete Product
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer productId) {

        productService.deleteProduct(productId);

        return ResponseEntity.ok("Product Deleted Successfully");
    }
}