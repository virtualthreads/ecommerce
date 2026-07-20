package com.aeropelican.productservice.dto;

import lombok.Data;

@Data
public class CreateProductRequest {

    private String name;

    private String description;

    private Double price;

    private Integer quantity;
}