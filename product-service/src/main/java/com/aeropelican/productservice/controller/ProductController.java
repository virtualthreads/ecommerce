package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.CreateProductRequest;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.repository.ProductRepository;
import com.aeropelican.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts() {
        List<ProductResponse> result = productService.listProducts();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<List<ProductResponse>>builder()
                        .success(true)
                        .message("Products list has been retrieved")
                        .timestamp(LocalDateTime.now())
                        .data(result)
                        .build()
                );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProduct(@PathVariable(name = "productId") Integer pid) {
        ProductResponse product = productService.getProduct(pid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<ProductResponse>builder()
                        .success(true)
                        .message("Product found")
                        .data(product)
                        .timestamp(LocalDateTime.now())
                        .build()
                );
    }
}
