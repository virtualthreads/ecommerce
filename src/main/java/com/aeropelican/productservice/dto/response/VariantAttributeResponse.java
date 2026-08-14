package com.aeropelican.productservice.dto.response;

import lombok.Builder;

@Builder
public record VariantAttributeResponse (
        long attributeId,
        long variantId,
        String attributeName,
        String attributeValue
){
}
