package com.aeropelican.productservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductVariants {
    private Integer variantId;
    private String variantName;
    private String color;
    private String size;
    private Double price;
}