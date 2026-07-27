package com.aeropelican.productservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProduct {
    private String productName;
    private String description;
    private String brand;
    private Double price;
    private Integer quantity;
    private Integer categoryId;
}