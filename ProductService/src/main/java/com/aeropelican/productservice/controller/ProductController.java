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

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest createProductRequest) {
        System.out.println("Received a post request from client");
        Product product = productService.createProduct(createProductRequest);
        return ResponseEntity.ok(product);
    }
}
