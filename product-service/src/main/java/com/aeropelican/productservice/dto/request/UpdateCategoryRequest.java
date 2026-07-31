package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCategoryRequest {

    @NotBlank(message = "Category name is required.")
    private String categoryName;

    @NotBlank(message = "Description is required.")
    private String description;

    private Boolean isActive;
}