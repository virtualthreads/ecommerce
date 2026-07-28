package com.aeropelican.productservice.dto.request;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UpdateProduct {
    private Integer productId;
    private String productName;
    private Integer category_id;
    private String description;
    private String brand;
    private boolean is_active =true;
    private Timestamp created_at;
    private Timestamp updated_at;

}
