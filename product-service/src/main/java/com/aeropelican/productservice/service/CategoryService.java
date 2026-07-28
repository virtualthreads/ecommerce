package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.CreateCategoryRequest;
import com.aeropelican.productservice.dto.request.UpdateCategory;
import com.aeropelican.productservice.dto.response.CategoryResponse;
import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

   /* public List<Category> listCategory() {
        List<Category> results = categoryRepository.findAll();
        return results;
    }

    public Category getCategory(Integer category_id) {
        Optional<Category> category = categoryRepository.findById(category_id);
        if (category.isPresent()) {
            return category.get();
        } else {
            return null;
        }
    }*/
    public Category getCategory(Integer category_id) {
        return categoryRepository.findById(category_id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
    public List<CategoryResponse> listCategories() {

        List<Category> results = categoryRepository.findAll();
        List<CategoryResponse> response = new ArrayList<>();

        for (Category category : results) {

            CategoryResponse categoryResponse = CategoryResponse.builder()
                    .category_id(category.getCategory_id())
                    .category_name(category.getCategory_name())
                    .description(category.getDescription())
                    .parent_category_id(category.getParent_category_id())
                    .is_active(category.is_active())
                    .created_at(category.getCreated_at())
                    .updated_at(category.getUpdated_at())
                    .build();

            response.add(categoryResponse);
        }

        return response;
    }
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
