package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.CreateProductRequest;
import com.aeropelican.productservice.dto.UpdateProduct;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
        private final ProductRepository productRepository;
        public Product getProduct(Integer productId) {
            return productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
        }
        public List<Product> listProducts() {
        List<Product> results = productRepository.findAll();
        return results;
        }
        public Product createProduct(CreateProductRequest request) {
            Product product = new Product();
            product.setProductName(request.getProductName());
            product.setCategory_id(request.getCategory_id());
            product.setDescription(request.getDescription());
            product.setBrand(request.getBrand());
            Timestamp now = new Timestamp(System.currentTimeMillis());
            product.setCreated_at(now);
            product.setUpdated_at(now);

            return productRepository.save(product);
        }

        public Product updateProduct(Integer productId, UpdateProduct request) {

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            product.setProductName(request.getProductName());
            product.setCategory_id(request.getCategory_id());
            product.setDescription(request.getDescription());
            product.setBrand(request.getBrand());

            return productRepository.save(product);
        }

        public Product deleteProduct(Integer productId) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            productRepository.delete(product);

            return product;
        }
    }

