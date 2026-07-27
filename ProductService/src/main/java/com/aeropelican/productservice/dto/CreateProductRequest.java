package com.aeropelican.productservice.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateProductRequest {
    private Integer productId;
    private String productName;
    private String category;
    private Double price;
    private Integer quantity;
}
