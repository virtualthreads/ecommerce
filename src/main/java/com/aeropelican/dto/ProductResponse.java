package com.aeropelican.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    private Integer productId;
    private String productName;
    private Integer category_id;
    private String description;
    private String brand;
    private Boolean is_active = true;
    private Timestamp created_at;
    private Timestamp updated_at;
}