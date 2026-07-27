package com.aeropelican.productservice.dto.request;

import lombok.Data;

@Data
public class CreateCategoryRequest {

    private String categoryName;

    private String description;

    private Boolean isActive;

}