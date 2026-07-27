package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.response.CreateCategoryRequest;
import com.aeropelican.productservice.dto.response.UpdateCategory;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category saveCategory(@RequestBody CreateCategoryRequest request) {
        return categoryService.saveCategory(request);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Integer id,
                                   @RequestBody UpdateCategory request) {

        return categoryService.updateCategory(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        return categoryService.deleteCategory(id);
    }

}