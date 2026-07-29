package com.aeropelican.productservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Integer productId;
    private String productName;
    private String description;
    private String brand;
    private Boolean isActive;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}