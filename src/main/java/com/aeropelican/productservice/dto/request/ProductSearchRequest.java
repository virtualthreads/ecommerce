package com.aeropelican.productservice.dto.request;

import java.math.BigDecimal;

public record ProductSearchRequest(
        String keyword,
        String brand,
        String color,
        BigDecimal minPrice,
        BigDecimal maxPrice
) {

}
