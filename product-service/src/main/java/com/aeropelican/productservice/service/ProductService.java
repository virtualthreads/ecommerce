package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.CreateProductRequest;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // Create Product
    public Product createProduct(CreateProductRequest request) {

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        return productRepository.save(product);
    }

    // Get All Products
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    // Get Product By Id
    public Product getProduct(Integer productId) {
        return productRepository.findById(productId).orElse(null);
    }

    // Update Product Quantity
    public Product updateProduct(Integer productId, Integer quantity) {

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            return null;
        }

        product.setQuantity(quantity);

        return productRepository.save(product);
    }

    // Delete Product
    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }
}