package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.CreateProductRequest;
import com.aeropelican.productservice.dto.request.UpdateProductRequest;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.repository.CategoryRepository;
import com.aeropelican.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // Get All Products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get Product By Id
    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    // Create Product
    public Product createProduct(CreateProductRequest request) {

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElse(null);

        if (category == null) {
            return null;
        }

        Product product = new Product();

        product.setCategory(category);
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setBrand(request.getBrand());
        product.setIsActive(request.getIsActive());

        return productRepository.save(product);
    }

    // Update Product
    public Product updateProduct(Long productId, UpdateProductRequest request) {

        Product product = productRepository
                .findById(productId)
                .orElse(null);

        if (product == null) {
            return null;
        }

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElse(null);

        if (category == null) {
            return null;
        }

        product.setCategory(category);
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setBrand(request.getBrand());
        product.setIsActive(request.getIsActive());

        return productRepository.save(product);
    }

    // Delete Product
    public boolean deleteProduct(Long productId) {

        if (!productRepository.existsById(productId)) {
            return false;
        }

        productRepository.deleteById(productId);

        return true;
    }

}