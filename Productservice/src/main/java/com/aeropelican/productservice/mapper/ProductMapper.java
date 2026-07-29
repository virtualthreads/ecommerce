package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product) {

        if (product == null) {
            return null;
        }

        return new ProductResponse(
                product.getProductId(),
                product.getProductName(),
                product.getDescription(),
                product.getBrand(),
                product.getIsActive(),
                product.getCreateAt(),
                product.getUpdatedAt()
        );
    }
}