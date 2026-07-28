package com.aeropelican.productservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductVariantsRequest {
    private Long productId;
    private String variantName;
    private String color;
    private String size;
    private Double price;
}