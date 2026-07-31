package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.entity.Product;

public class ProductMapper {

    private ProductMapper() {
    }

    public static ProductResponse toResponse(Product product){

        if(product == null){
            return null;
        }

        return ProductResponse.builder()
                .productId(product.getProductId())
                .categoryId(product.getCategory().getCategoryId())
                .categoryName(product.getCategory().getCategoryName())
                .productName(product.getProductName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .isActive(product.getIsActive())
                .build();
    }
}