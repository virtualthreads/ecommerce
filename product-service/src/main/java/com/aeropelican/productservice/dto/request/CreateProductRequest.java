package com.aeropelican.productservice.dto.request;

import lombok.Data;

@Data
public class CreateProductRequest {

    private Long categoryId;

    private String productName;

    private String description;

    private String brand;

    private Boolean isActive;

}