package com.aeropelican.productservice.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private Long productId;

    private Long categoryId;

    private String categoryName;

    private String productName;

    private String description;

    private String brand;

    private Boolean isActive;

}