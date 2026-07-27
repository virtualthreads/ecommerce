package com.aeropelican.productservice.dto;

import lombok.Data;

@Data
public class ClearProductRequest {
    private Integer productId;
    private String productName;
    private String category;
    private Double price;
    private Integer quantity;
}