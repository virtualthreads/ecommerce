package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.response.CreateProductRequest;
import com.aeropelican.productservice.dto.response.UpdateProduct;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product saveProduct(@RequestBody CreateProductRequest request) {
        return productService.saveProduct(request);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id,
                                 @RequestBody UpdateProduct request) {

        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }

}