package com.aeropelican.mapper;


import com.aeropelican.dto.CategoryResponse;
import com.aeropelican.entity.Category;

public class CategoryMapper {
    public static CategoryResponse toResponse(Category category) {

        return CategoryResponse.builder()
                .category_id(category.getCategory_id())
                .category_name(category.getCategory_name())
                .description(category.getDescription())
                .parent_category_id(category.getParent_category_id())
                .is_active(category.is_active())
                .created_at(category.getCreated_at())
                .updated_at(category.getUpdated_at())
                .build();
    }

}