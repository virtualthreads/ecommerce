package com.aeropelican.productservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductVariantResponse {

    private Long variantId;

    private Long productId;

    private String productName;

    private String sku;

    private String color;

    private String storageCapacity;

    private BigDecimal price;

    private Boolean isActive;

}