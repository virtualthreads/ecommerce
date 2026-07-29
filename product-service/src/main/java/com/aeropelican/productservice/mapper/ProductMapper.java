package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.entity.Product;

public class ProductMapper {

    public static ProductResponse toProductResponse(Product product) {
        if (product == null) {
            return null;
        }
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .category(product.getCategory())
                .isActive(true)
                .build();
    }
}