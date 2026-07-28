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
public class Product_variantsResponse{
    private  Integer variant_id;
    private Integer product_id;
    private String sku;
    private String color;
    private String storage_capacity;
    private Double price;
    private boolean is_active =true;
    private Timestamp created_at;
    private Timestamp updated_at;
}
