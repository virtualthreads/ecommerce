package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByCategoryNameIgnoreCase(String categoryName);

    List<Category> findByParentCategoryIdIsNullAndIsActiveIsTrue();

    List<Category> findByParentCategoryId(Long parentCategoryId);
}
