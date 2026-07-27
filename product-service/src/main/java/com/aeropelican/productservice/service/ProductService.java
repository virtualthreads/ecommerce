package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.ClearProductRequest;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Integer productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.orElse(null);
    }

    public Product updateProduct(Integer productId, Integer quantity) {
        // Prevents NoSuchElementException if the ID doesn't exist
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        product.setQuantity(quantity);
        return productRepository.save(product);
    }

    public Product createProduct(ClearProductRequest request) {
        System.out.println("Attempting to create a record in the product table");

        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        Product createdProduct = productRepository.save(product);
        System.out.println("Created a product with product ID: " + createdProduct.getProductId());
        return createdProduct;
    }

    public boolean deleteProduct(Integer productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
            return true;
        }
        return false;
    }
}