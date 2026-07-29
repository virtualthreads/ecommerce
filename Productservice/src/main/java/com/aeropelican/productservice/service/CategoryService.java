package com.aeropelican.productservice.service;

import com.aeropelican.productservice.Exceptions.CategoryNotFoundException;
import com.aeropelican.productservice.dto.response.CategoryResponse;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.mapper.CategoryMapper;
import com.aeropelican.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    // GET CATEGORY BY ID
    public CategoryResponse getCategory(Long catId) {

        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new CategoryNotFoundException(catId));

        return categoryMapper.toResponse(category);
    }
}