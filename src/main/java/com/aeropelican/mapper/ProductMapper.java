package com.aeropelican.mapper;


import com.aeropelican.dto.ProductResponse;
import com.aeropelican.entity.Product;

public class ProductMapper {
    public static ProductResponse toResponse(Product product) {

        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .brand(product.getBrand())
                .description(product.getDescription())
                .category_id(product.getCategory_id())
                .is_active(product.getIs_active())
                .created_at(product.getCreated_at())
                .updated_at(product.getUpdated_at())
                .build();
    }

}