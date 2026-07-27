package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {  }