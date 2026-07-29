package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.CreateProductVariantsRequest;
import com.aeropelican.productservice.dto.request.UpdateProductVariantsRequest;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductVariantsResponse;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.entity.ProductVariants;
import com.aeropelican.productservice.exceptions.ProductNotFoundException;
import com.aeropelican.productservice.exceptions.ProductVariantNotFoundException;
import com.aeropelican.productservice.mapper.PageResponseMapper;
import com.aeropelican.productservice.mapper.ProductVariantsMapper;
import com.aeropelican.productservice.repository.ProductRepository;
import com.aeropelican.productservice.repository.ProductVariantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductVariantsService {

    @Autowired
    private ProductVariantsRepository productVariantsRepository;

    @Autowired
    private ProductRepository productRepository;

    public ApiResponse<ProductVariantsResponse> saveVariant(CreateProductVariantsRequest request) {

        ProductVariants variant = new ProductVariants();

        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
            variant.setProduct(product);
        }

        variant.setVariantName(request.getVariantName());
        variant.setColor(request.getColor());
        variant.setSize(request.getSize());
        variant.setPrice(request.getPrice());

        ProductVariants savedVariant = productVariantsRepository.save(variant);
        return ApiResponse.success("Product Variant created successfully", ProductVariantsMapper.toProductVariantsResponse(savedVariant));
    }

    public ApiResponse<PageResponse<ProductVariantsResponse>> getAllVariants(int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProductVariants> variantPage = productVariantsRepository.findAll(pageable);

        List<ProductVariantsResponse> mappedList = variantPage.getContent()
                .stream()
                .map(ProductVariantsMapper::toProductVariantsResponse)
                .collect(Collectors.toList());

        PageResponse<ProductVariantsResponse> pageResponse = PageResponseMapper.toPageResponse(variantPage, mappedList);

        return ApiResponse.success("Product Variants retrieved successfully", pageResponse);
    }

    public ApiResponse<ProductVariantsResponse> getVariantById(Long id) {

        ProductVariants variant = productVariantsRepository.findById(id)
                .orElseThrow(() -> new ProductVariantNotFoundException("Product Variant with ID " + id + " not found"));

        return ApiResponse.success("Product Variant retrieved successfully", ProductVariantsMapper.toProductVariantsResponse(variant));
    }

    public ApiResponse<ProductVariantsResponse> updateVariant(Long id, UpdateProductVariantsRequest request) {

        ProductVariants variant = productVariantsRepository.findById(id)
                .orElseThrow(() -> new ProductVariantNotFoundException("Product Variant Not Found"));

        if (request.getVariantName() != null) {
            variant.setVariantName(request.getVariantName());
        }
        variant.setColor(request.getColor());
        variant.setSize(request.getSize());
        variant.setPrice(request.getPrice());

        ProductVariants updatedVariant = productVariantsRepository.save(variant);
        return ApiResponse.success("Product Variant updated successfully", ProductVariantsMapper.toProductVariantsResponse(updatedVariant));
    }

    public ApiResponse<String> deleteVariant(Long id) {

        if (!productVariantsRepository.existsById(id)) {
            throw new ProductVariantNotFoundException("Product Variant with ID " + id + " not found");
        }

        productVariantsRepository.deleteById(id);

        return ApiResponse.success("Product Variant Deleted Successfully", null);
    }
}