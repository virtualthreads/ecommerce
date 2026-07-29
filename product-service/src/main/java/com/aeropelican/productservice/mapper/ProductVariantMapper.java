package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.response.ProductVariantResponse;
import com.aeropelican.productservice.entity.ProductVariant;

public class ProductVariantMapper {

    private ProductVariantMapper() {
    }

    public static ProductVariantResponse toResponse(ProductVariant variant) {

        if (variant == null) {
            return null;
        }

        return ProductVariantResponse.builder()
                .variantId(variant.getVariantId())
                .productId(variant.getProduct().getProductId())
                .productName(variant.getProduct().getProductName())
                .sku(variant.getSku())
                .color(variant.getColor())
                .storageCapacity(variant.getStorageCapacity())
                .price(variant.getPrice())
                .isActive(variant.getIsActive())
                .build();
    }
}