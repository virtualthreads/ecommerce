package com.aeropelican.productservice.service;

import com.aeropelican.productservice.Exceptions.ProductNotFound;
import com.aeropelican.productservice.dto.request.CreateProductRequest;
import com.aeropelican.productservice.dto.request.UpdateProduct;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.mapper.ProductMapper;
import com.aeropelican.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class ProductService {
        private final ProductRepository productRepository;
        public Product getProduct(Integer productId) {
            return productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFound("Product not found"));
        }

    public PageResponse<ProductResponse> listProducts(int page,
                                                      int size,
                                                      String sortBy,
                                                      String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> results = productRepository.findAll(pageable);

        List<ProductResponse> result = new ArrayList<>(results
                .map(ProductMapper::toResponse)
                .getContent());

        return PageResponse.<ProductResponse>builder()
                .content(result)
                .page(results.getNumber())
                .size(results.getSize())
                .totalElement(results.getTotalElements())
                .totalPage(results.getTotalPages())
                .hasNext(results.hasNext())
                .hasPrevious(results.hasPrevious())
                .build();
    }

         //To create a product
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
        //To update a product
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
        //To delete a product
        public Product deleteProduct(Integer productId) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFound("Product not found"));

            productRepository.delete(product);
            return product;
        }
    }
