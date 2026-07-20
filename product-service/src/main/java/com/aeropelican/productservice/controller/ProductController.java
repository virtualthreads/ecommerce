package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.ClearProductRequest;
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

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.listProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProduct(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{productId}/quantity/{quantity}")
    public ResponseEntity<Product> updateProductQuantity(
            @PathVariable Integer productId,
            @PathVariable Integer quantity) {

        Product updatedProduct = productService.updateProduct(productId, quantity);
        return ResponseEntity.ok(updatedProduct);
    }
    

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ClearProductRequest createProductRequest) {
        Product product = productService.createProduct(createProductRequest);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        boolean isDeleted = productService.deleteProduct(productId);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // 204 No Content means successful deletion
        }
        return ResponseEntity.notFound().build(); // 404 if the product ID didn't exist
    }
}