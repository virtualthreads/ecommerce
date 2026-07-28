package com.aeropelican.productservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    private String productName;
    private String description;
    private String brand;
    private Double price;
    private Integer quantity;
    private Integer categoryId;
}