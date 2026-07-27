package com.aeropelican.productservice.service;

import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category getCategory(Long catId) {
        java.util.Optional<Category> result = categoryRepository.findById(catId);
        return result.get();
    }
}
