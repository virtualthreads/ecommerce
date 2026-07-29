package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.response.CategoryResponse;
import com.aeropelican.productservice.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {

        if (category == null) {
            return null;
        }

        Long parentCategoryId = null;

        if (category.getParentCategory() != null) {
            parentCategoryId = category.getParentCategory().getCategoryId();
        }

        return new CategoryResponse(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getDescription(),
                parentCategoryId,
                category.getIsActive(),
                category.getCreateAt(),
                category.getUpdatedAt()
        );
    }
}