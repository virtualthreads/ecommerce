package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.CreateProductRequest;
import com.aeropelican.productservice.dto.UpdateProductRequest;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // Get All Products
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    // Get Product By Id
    public Product getProduct(Integer productId) {
        return productRepository.findById(productId).orElse(null);
    }

    // Create Product
    public Product createProduct(CreateProductRequest request) {

        Product product = new Product();

        product.setProductName(request.getProductName());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        return productRepository.save(product);
    }

    // Update Product
    public Product updateProduct(Integer productId, UpdateProductRequest request) {

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            return null;
        }

        product.setProductName(request.getProductName());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        return productRepository.save(product);
    }

    // Delete Product
    public boolean deleteProduct(Integer productId) {

        if (!productRepository.existsById(productId)) {
            return false;
        }

        productRepository.deleteById(productId);

        return true;
    }
}