package com.aeropelican.dto;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class CreateProduct_VariantsRequest {
    private Integer variant_id;
    private Integer product_id;
    private String sku;
    private String color;
    private String storage_capacity;
    private Double price;
    private boolean is_active = true;
    private Timestamp created_at;
    private Timestamp updated_at;
}