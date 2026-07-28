package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.entity.Product;

public class ProductMapper {

    public static ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                //.productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .isActive(product.getIsActive())
                .createdAt(product.getCreateAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
