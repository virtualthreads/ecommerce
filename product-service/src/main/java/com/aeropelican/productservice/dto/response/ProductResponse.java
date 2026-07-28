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
public class ProductResponse{
    private Integer productId;
    private String productName;
    private Integer category_id;
    private String description;
    private String brand;
    private Boolean is_active = true;
    private Timestamp created_at;
    private Timestamp updated_at;
}
