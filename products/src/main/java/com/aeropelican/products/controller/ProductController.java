package com.aeropelican.products.controller;

import com.aeropelican.productservice.entity.Product;
import com.aeropelican.products.dto.CreateProductRequest;
import com.aeropelican.products.service.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PutMapping("/{productId}/{quantity}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Integer productId,
            @PathVariable Integer quantity) {

        Product updatedProduct = productService.updateProduct(productId, quantity);

        if (updatedProduct == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedProduct);
    }
}