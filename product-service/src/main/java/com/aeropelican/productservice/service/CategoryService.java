package com.aeropelican.productservice.service;

import com.aeropelican.productservice.Exceptions.ProductNotFound;
import com.aeropelican.productservice.dto.request.CreateCategoryRequest;
import com.aeropelican.productservice.dto.request.UpdateCategory;
import com.aeropelican.productservice.dto.response.CategoryResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.mapper.CategoryMapper;
import com.aeropelican.productservice.mapper.ProductMapper;
import com.aeropelican.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category getCategory(Integer category_id) {
        return categoryRepository.findById(category_id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public PageResponse<CategoryResponse> listCategories(int page,
                                                      int size,
                                                      String sortBy,
                                                      String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Category> results = categoryRepository.findAll(pageable);

        List<CategoryResponse> result = new ArrayList<>(results
                .map(CategoryMapper::toResponse)
                .getContent());

        return PageResponse.<CategoryResponse>builder()
                .content(result)
                .page(results.getNumber())
                .size(results.getSize())
                .totalElement(results.getTotalElements())
                .totalPage(results.getTotalPages())
                .hasNext(results.hasNext())
                .hasPrevious(results.hasPrevious())
                .build();
    }

    /*public List<CategoryResponse> listCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toResponse)
                .collect(Collectors.toList());
    }*/

    //To create category
    public Category createCategory(CreateCategoryRequest request) {
        System.out.println("Attempting to create a record in the Category table");
        Category category = new Category();
        category.setCategory_name(request.getCategory_name());
        category.setDescription(request.getDescription());
        category.setParent_category_id(request.getParent_category_id());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        category.setCreated_at(now);
        category.setUpdated_at(now);
        return categoryRepository.save(category);
    }

    //To update category
    public Category updateCategory(Integer categoryId, UpdateCategory request) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setCategory_name(request.getCategory_name());
        category.setDescription(request.getDescription());
        category.setParent_category_id(request.getParent_category_id());
        return categoryRepository.save(category);
    }

    //To delete category
    public Category deleteCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);

        return category;
    }

}
