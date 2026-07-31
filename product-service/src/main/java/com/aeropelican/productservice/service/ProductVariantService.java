package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.CreateProductVariantRequest;
import com.aeropelican.productservice.dto.request.UpdateProductVariantRequest;
import com.aeropelican.productservice.dto.response.ProductVariantResponse;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.entity.ProductVariant;
import com.aeropelican.productservice.exception.BadRequestException;
import com.aeropelican.productservice.exception.DuplicateResourceException;
import com.aeropelican.productservice.exception.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.ProductVariantMapper;
import com.aeropelican.productservice.repository.ProductRepository;
import com.aeropelican.productservice.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;

    // ===========================================
    // GET ALL VARIANTS
    // ===========================================

    public List<ProductVariantResponse> getAllVariants() {

        return productVariantRepository.findAll()
                .stream()
                .map(ProductVariantMapper::toResponse)
                .toList();
    }

    // ===========================================
    // GET VARIANT BY ID
    // ===========================================

    public ProductVariantResponse getVariant(Long variantId) {

        ProductVariant variant = productVariantRepository.findById(variantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Variant not found with id : " + variantId));

        return ProductVariantMapper.toResponse(variant);
    }

    // ===========================================
    // CREATE VARIANT
    // ===========================================

    public ProductVariantResponse createVariant(CreateProductVariantRequest request) {

        if (request.getSku() == null || request.getSku().trim().isEmpty()) {
            throw new BadRequestException("SKU is required.");
        }

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id : "
                                        + request.getProductId()));

        boolean skuExists = productVariantRepository.findAll()
                .stream()
                .anyMatch(v ->
                        v.getSku().equalsIgnoreCase(
                                request.getSku().trim()));

        if (skuExists) {
            throw new DuplicateResourceException(
                    "SKU already exists.");
        }

        ProductVariant variant = new ProductVariant();

        variant.setProduct(product);
        variant.setSku(request.getSku().trim());
        variant.setColor(request.getColor().trim());
        variant.setStorageCapacity(request.getStorageCapacity().trim());
        variant.setPrice(request.getPrice());

        variant.setIsActive(
                request.getIsActive() == null
                        ? true
                        : request.getIsActive());

        variant.setCreatedAt(LocalDateTime.now());
        variant.setUpdatedAt(LocalDateTime.now());

        variant = productVariantRepository.save(variant);

        return ProductVariantMapper.toResponse(variant);
    }

    // ===========================================
    // UPDATE VARIANT
    // ===========================================

    public ProductVariantResponse updateVariant(
            Long variantId,
            UpdateProductVariantRequest request) {

        ProductVariant variant = productVariantRepository.findById(variantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Variant not found with id : "
                                        + variantId));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id : "
                                        + request.getProductId()));

        if (request.getSku() == null ||
                request.getSku().trim().isEmpty()) {

            throw new BadRequestException("SKU is required.");
        }

        variant.setProduct(product);
        variant.setSku(request.getSku().trim());
        variant.setColor(request.getColor().trim());
        variant.setStorageCapacity(request.getStorageCapacity().trim());
        variant.setPrice(request.getPrice());
        variant.setIsActive(request.getIsActive());
        variant.setUpdatedAt(LocalDateTime.now());

        variant = productVariantRepository.save(variant);

        return ProductVariantMapper.toResponse(variant);
    }

    // ===========================================
    // DELETE VARIANT
    // ===========================================

    public boolean deleteVariant(Long variantId) {

        ProductVariant variant = productVariantRepository.findById(variantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Variant not found with id : "
                                        + variantId));

        productVariantRepository.delete(variant);

        return true;
    }

}