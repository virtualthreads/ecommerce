package com.aeropelican.dto;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class CreateCategoryRequest {
    private Integer category_id;
    private String category_name;
    private String description;
    private Integer parent_category_id;
    private boolean is_active = true;
    private Timestamp created_at;
    private Timestamp updated_at;
}