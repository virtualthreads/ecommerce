package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.response.CategoryResponse;
import com.aeropelican.productservice.entity.Category;

public class CategoryMapper {

    public static CategoryResponse toCategoryResponse(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .build();
    }
}