package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.response.ProductVariantsResponse;
import com.aeropelican.productservice.entity.ProductVariants;

import java.math.BigDecimal;

public class ProductVariantsMapper {

    public static ProductVariantsResponse toProductVariantsResponse(ProductVariants variant) {
        if (variant == null) {
            return null;
        }
        return ProductVariantsResponse.builder()
                .variantId(variant.getVariantId() != null ? variant.getVariantId().longValue() : null)
                .variantName(variant.getVariantName())
                .color(variant.getColor())
                .size(variant.getSize())
                .price(variant.getPrice() != null ? BigDecimal.valueOf(variant.getPrice()) : null)
                .productId(variant.getProduct() != null ? variant.getProduct().getProductId() : null)
                .build();
    }
}