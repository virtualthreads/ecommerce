package com.aeropelican.productservice.service;

import com.aeropelican.productservice.Exceptions.ProductVariantNotFoundException;
import com.aeropelican.productservice.dto.response.ProductVariantResponse;
import com.aeropelican.productservice.entity.ProductVariant;
import com.aeropelican.productservice.mapper.ProductVariantMapper;
import com.aeropelican.productservice.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductVariantMapper productVariantMapper;

    // GET ALL PRODUCT VARIANTS
    public List<ProductVariantResponse> listProductVariants() {

        return productVariantRepository.findAll()
                .stream()
                .map(productVariantMapper::toResponse)
                .toList();
    }

    // GET PRODUCT VARIANT BY ID
    public ProductVariantResponse getProductVariant(Long variantId) {

        ProductVariant variant = productVariantRepository.findById(variantId)
                .orElseThrow(() ->
                        new ProductVariantNotFoundException(variantId));

        return productVariantMapper.toResponse(variant);
    }
}