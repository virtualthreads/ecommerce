package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.response.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageResponseMapper {

    public static <T> PageResponse<T> toPageResponse(Page<T> page) {
        return PageResponse.<T>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getNumberOfElements()) // Shows current page element count (e.g., 5 or 0)
                .totalPages(page.getTotalPages())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .build();
    }

    public static <T, R> PageResponse<R> toPageResponse(Page<T> page, List<R> mappedContent) {
        return PageResponse.<R>builder()
                .content(mappedContent)
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getNumberOfElements()) // Shows current page element count (e.g., 5 or 0)
                .totalPages(page.getTotalPages())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .build();
    }
}