package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.response.ProductVariantResponse;
import com.aeropelican.productservice.entity.ProductVariant;
import org.springframework.stereotype.Component;

@Component
public class ProductVariantMapper {

    public ProductVariantResponse toResponse(ProductVariant variant) {

        if (variant == null) {
            return null;
        }

        Integer productId = null;

        if (variant.getProduct() != null) {
            productId = variant.getProduct().getProductId();
        }

        return new ProductVariantResponse(
                variant.getVariantId(),
                productId,
                variant.getSku(),
                variant.getColor(),
                variant.getStorageCapacity(),
                variant.getPrice(),
                variant.getIsActive(),
                variant.getCreateAt(),
                variant.getUpdatedAt()
        );
    }
}
