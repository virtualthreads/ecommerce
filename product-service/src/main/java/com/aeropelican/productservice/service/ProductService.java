package com.aeropelican.productservice.service;

import com.aeropelican.productservice.Exceptions.ProductNotFound;
import com.aeropelican.productservice.dto.request.CreateProductRequest;
import com.aeropelican.productservice.dto.request.UpdateProduct;
import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
        private final ProductRepository productRepository;
        public Product getProduct(Integer productId) {
            return productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFound("Product not found"));
        }

        public List<ProductResponse> listProducts() {

            List<Product> results = productRepository.findAll();
            List<ProductResponse> response = new ArrayList<>();

            for (Product product : results) {

                ProductResponse productResponse = ProductResponse.builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .description(product.getDescription())
                        .brand(product.getBrand())
                        .is_active(product.getIs_active())
                        .category_id(product.getCategory_id())
                        .created_at(product.getCreated_at())
                        .updated_at(product.getUpdated_at())
                        .build();

                response.add(productResponse);
            }

            return response;
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
                    .orElseThrow(() -> new ProductNotFound("Product not found"));

            product.setProductName(request.getProductName());
            product.setCategory_id(request.getCategory_id());
            product.setDescription(request.getDescription());
            product.setBrand(request.getBrand());
            product.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            return productRepository.save(product);
        }

        public Product deleteProduct(Integer productId) {

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFound("Product not found"));

            productRepository.delete(product);

            return product;
        }
    }
