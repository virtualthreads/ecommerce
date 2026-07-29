package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateProductRequest {

    @NotNull(message = "Category Id is required.")
    private Long categoryId;

    @NotBlank(message = "Product name is required.")
    private String productName;

    @NotBlank(message = "Description is required.")
    private String description;

    @NotBlank(message = "Brand is required.")
    private String brand;

    private Boolean isActive;
}