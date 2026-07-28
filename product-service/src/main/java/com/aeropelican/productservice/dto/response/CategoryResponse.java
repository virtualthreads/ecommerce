package com.aeropelican.productservice.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponse{
        private Integer category_id;
        private String category_name;
        private String description;
        private Integer parent_category_id;
        private Boolean is_active;
        private Timestamp created_at;
        private Timestamp updated_at;
    }


