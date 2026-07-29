package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.CreateProductRequest;
import com.aeropelican.productservice.dto.request.UpdateProductRequest;
import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.exception.BadRequestException;
import com.aeropelican.productservice.exception.DuplicateResourceException;
import com.aeropelican.productservice.exception.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.ProductMapper;
import com.aeropelican.productservice.repository.CategoryRepository;
import com.aeropelican.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // ============================
    // GET ALL PRODUCTS
    // ============================

    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    // ============================
    // GET PRODUCT BY ID
    // ============================

    public ProductResponse getProduct(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id : " + productId));

        return ProductMapper.toResponse(product);
    }

    // ============================
    // CREATE PRODUCT
    // ============================

    public ProductResponse createProduct(CreateProductRequest request) {

        if (request.getProductName() == null ||
                request.getProductName().trim().isEmpty()) {

            throw new BadRequestException("Product name is required.");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id : " + request.getCategoryId()));

        boolean exists = productRepository.findAll()
                .stream()
                .anyMatch(product ->
                        product.getProductName().equalsIgnoreCase(
                                request.getProductName().trim())
                                &&
                                product.getCategory().getCategoryId()
                                        .equals(category.getCategoryId()));

        if (exists) {
            throw new DuplicateResourceException(
                    "Product already exists in this category.");
        }

        Product product = new Product();

        product.setCategory(category);
        product.setProductName(request.getProductName().trim());
        product.setDescription(request.getDescription());
        product.setBrand(request.getBrand().trim());

        product.setIsActive(
                request.getIsActive() == null
                        ? true
                        : request.getIsActive());

        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        product = productRepository.save(product);

        return ProductMapper.toResponse(product);
    }

    // ============================
    // UPDATE PRODUCT
    // ============================

    public ProductResponse updateProduct(
            Long productId,
            UpdateProductRequest request) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id : " + productId));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id : " + request.getCategoryId()));

        if (request.getProductName() == null ||
                request.getProductName().trim().isEmpty()) {

            throw new BadRequestException("Product name is required.");
        }

        product.setCategory(category);
        product.setProductName(request.getProductName().trim());
        product.setDescription(request.getDescription());
        product.setBrand(request.getBrand().trim());
        product.setIsActive(request.getIsActive());
        product.setUpdatedAt(LocalDateTime.now());

        product = productRepository.save(product);

        return ProductMapper.toResponse(product);
    }

    // ============================
    // DELETE PRODUCT
    // ============================

    public boolean deleteProduct(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id : " + productId));

        productRepository.delete(product);

        return true;
    }

}