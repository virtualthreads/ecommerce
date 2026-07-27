package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.CreateProductVariantRequest;
import com.aeropelican.productservice.dto.request.UpdateProductVariantRequest;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.entity.ProductVariant;
import com.aeropelican.productservice.repository.ProductRepository;
import com.aeropelican.productservice.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;

    // ============================
    // GET ALL VARIANTS
    // ============================

    public List<ProductVariant> getAllVariants() {
        return productVariantRepository.findAll();
    }

    // ============================
    // GET VARIANT BY ID
    // ============================

    public ProductVariant getVariant(Long variantId) {
        return productVariantRepository
                .findById(variantId)
                .orElse(null);
    }

    // ============================
    // CREATE VARIANT
    // ============================

    public ProductVariant createVariant(CreateProductVariantRequest request) {

        Product product = productRepository
                .findById(request.getProductId())
                .orElse(null);

        if (product == null) {
            return null;
        }

        ProductVariant variant = new ProductVariant();

        variant.setProduct(product);
        variant.setSku(request.getSku());
        variant.setColor(request.getColor());
        variant.setStorageCapacity(request.getStorageCapacity());
        variant.setPrice(request.getPrice());
        variant.setIsActive(request.getIsActive());

        return productVariantRepository.save(variant);
    }

    // ============================
    // UPDATE VARIANT
    // ============================

    public ProductVariant updateVariant(
            Long variantId,
            UpdateProductVariantRequest request) {

        ProductVariant variant = productVariantRepository
                .findById(variantId)
                .orElse(null);

        if (variant == null) {
            return null;
        }

        Product product = productRepository
                .findById(request.getProductId())
                .orElse(null);

        if (product == null) {
            return null;
        }

        variant.setProduct(product);
        variant.setSku(request.getSku());
        variant.setColor(request.getColor());
        variant.setStorageCapacity(request.getStorageCapacity());
        variant.setPrice(request.getPrice());
        variant.setIsActive(request.getIsActive());

        return productVariantRepository.save(variant);
    }

    // ============================
    // DELETE VARIANT
    // ============================

    public boolean deleteVariant(Long variantId) {

        if (!productVariantRepository.existsById(variantId)) {
            return false;
        }

        productVariantRepository.deleteById(variantId);

        return true;
    }

}