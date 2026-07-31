package com.aeropelican.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponse {
    private Integer category_id;
    private String category_name;
    private String description;
    private Integer parent_category_id;
    private Boolean is_active;
    private Timestamp created_at;
    private Timestamp updated_at;
}