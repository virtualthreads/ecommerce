package com.aeropelican.productservice.dto.response;

import com.aeropelican.productservice.entity.Category;
import lombok.Builder;
import lombok.Data;
import java.security.Timestamp;
import java.time.LocalDateTime;
@Data
@Builder
public class APIResponse<T>{
    private T data;
    private String message;
    private Boolean success;
    private LocalDateTime timestamp;
    private ApiError error;
}