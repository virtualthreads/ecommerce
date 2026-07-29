package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductVariantRequest {

    @NotNull(message = "Product Id is required.")
    private Long productId;

    @NotBlank(message = "SKU is required.")
    private String sku;

    @NotBlank(message = "Color is required.")
    private String color;

    @NotBlank(message = "Storage Capacity is required.")
    private String storageCapacity;

    @NotNull(message = "Price is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero.")
    private BigDecimal price;

    private Boolean isActive;
}