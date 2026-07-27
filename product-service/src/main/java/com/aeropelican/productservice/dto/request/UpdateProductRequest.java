package com.aeropelican.productservice.dto.request;

import lombok.Data;

@Data
public class UpdateProductRequest {

    private Long categoryId;

    private String productName;

    private String description;

    private String brand;

    private Boolean isActive;

}