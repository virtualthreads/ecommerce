package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.CreateProductRequest;
import com.aeropelican.productservice.dto.request.UpdateProductRequest;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.exceptions.ProductNotFoundException;
import com.aeropelican.productservice.mapper.PageResponseMapper;
import com.aeropelican.productservice.mapper.ProductMapper;
import com.aeropelican.productservice.repository.CategoryRepository;
import com.aeropelican.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ApiResponse<ProductResponse> saveProduct(CreateProductRequest request) {

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
        ProductResponse response = ProductMapper.toProductResponse(savedProduct);

        return ApiResponse.success("Product created successfully", response);
    }

    public ApiResponse<PageResponse<ProductResponse>> getAllProducts(int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductResponse> mappedList = productPage.getContent()
                .stream()
                .map(ProductMapper::toProductResponse)
                .collect(Collectors.toList());

        PageResponse<ProductResponse> pageResponse = PageResponseMapper.toPageResponse(productPage, mappedList);

        return ApiResponse.success("Products retrieved successfully", pageResponse);
    }

    public ApiResponse<List<ProductResponse>> getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));

        ProductResponse response = ProductMapper.toProductResponse(product);
        List<ProductResponse> productList = List.of(response);

        return ApiResponse.success("Products retrieved successfully", productList);
    }

    public ApiResponse<List<ProductResponse>> searchProductsByName(String name) {
        List<ProductResponse> products = productRepository.findByProductNameContainingIgnoreCase(name)
                .stream()
                .map(ProductMapper::toProductResponse)
                .collect(Collectors.toList());

        return ApiResponse.success("Products retrieved successfully", products);
    }

    public ApiResponse<ProductResponse> updateProduct(Long id, UpdateProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));

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
        ProductResponse response = ProductMapper.toProductResponse(updatedProduct);

        return ApiResponse.success("Product updated successfully", response);
    }

    public ApiResponse<String> deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }

        productRepository.deleteById(id);

        return ApiResponse.success("Product deleted successfully", null);
    }
}