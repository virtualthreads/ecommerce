package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.CreateProductRequest;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.repository.ProductRepository;
import com.aeropelican.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        List<Product> result = productService.listProducts();
        return result;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable(name = "productId") Integer pid) {
        Product product = productService.getProduct(pid);
        if (product == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{productId}/{quantity}")
    public Product updateProduct(@PathVariable("productId") Integer id, @PathVariable("quantity") Integer qty) {
        return productService.updateProduct(id, qty);
    }

    // PUT: Update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        Product updated = productService.updateProduct(id, product);
        return ResponseEntity.ok(updated);
    }

    // DELETE: Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        String response = productService.deleteProduct(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest createProductRequest) {
        System.out.println("Received a post request from client");
        Product product = productService.createProduct(createProductRequest);
        return ResponseEntity.ok(product);
    }
}