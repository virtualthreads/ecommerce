package com.aeropelican.productservice.dto.response;

import com.aeropelican.productservice.entity.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    private Long productId;
    private String productName;
    private String description;
    private String brand;
    private Double price;
    private Integer quantity;
    private Boolean isActive;
    private Category category;
}