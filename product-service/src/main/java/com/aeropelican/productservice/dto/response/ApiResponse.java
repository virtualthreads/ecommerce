package com.aeropelican.productservice.dto.response;

import com.aeropelican.productservice.entity.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private ApiError error;
    private LocalDateTime timestamp;
    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }
    public static <T> ApiResponse<T> success(T data) {
        return success(data, "Request processed successfully");
    }
    public static <T> ApiResponse<T> failure(String message, ApiError error) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .error(error)
                .build();
    }
}

