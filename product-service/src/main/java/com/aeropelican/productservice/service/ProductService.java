package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.CreateProductRequest;
import com.aeropelican.productservice.dto.request.UpdateProductRequest;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.repository.CategoryRepository;
import com.aeropelican.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Create Product
    public ApiResponse<ProductResponse> saveProduct(CreateProductRequest request) {

        // Check for duplicate product name
        if (productRepository.existsByProductName(request.getProductName())) {
            throw new DataIntegrityViolationException("Product name already exists");
        }

        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setBrand(request.getBrand());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category with ID " + request.getCategoryId() + " not found"));
            product.setCategory(category);
        }

        Product savedProduct = productRepository.save(product);
        ProductResponse response = mapToProductResponse(savedProduct);

        return ApiResponse.success("Product created successfully", response);
    }

    // Get All Products
    public ApiResponse<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productRepository.findAll()
                .stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());

        return ApiResponse.success("Products retrieved successfully", products);
    }

    // Get Product By ID
    public ApiResponse<List<ProductResponse>> getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found"));

        ProductResponse response = mapToProductResponse(product);
        List<ProductResponse> productList = List.of(response);

        return ApiResponse.success("Products retrieved successfully", productList);
    }

    // Search Products By Name
    public ApiResponse<List<ProductResponse>> searchProductsByName(String name) {
        List<ProductResponse> products = productRepository.findByProductNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());

        return ApiResponse.success("Products retrieved successfully", products);
    }

    // Update Product
    public ApiResponse<ProductResponse> updateProduct(Long id, UpdateProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found"));

        // Check duplicate name on update if name is changing
        if (!product.getProductName().equalsIgnoreCase(request.getProductName())
                && productRepository.existsByProductName(request.getProductName())) {
            throw new DataIntegrityViolationException("Product name already exists");
        }

        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setBrand(request.getBrand());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category with ID " + request.getCategoryId() + " not found"));
            product.setCategory(category);
        }

        Product updatedProduct = productRepository.save(product);
        ProductResponse response = mapToProductResponse(updatedProduct);

        return ApiResponse.success("Product updated successfully", response);
    }

    // Delete Product
    public ApiResponse<String> deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product with ID " + id + " not found");
        }

        productRepository.deleteById(id);

        return ApiResponse.success("Product deleted successfully", null);
    }

    // Mapper Helper
    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .category(product.getCategory())
                .isActive(true)
                .build();
    }
}