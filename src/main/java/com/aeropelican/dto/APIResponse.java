package com.aeropelican.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class APIResponse<T> {
    private T data;
    private String message;
    private Boolean success;
    private LocalDateTime timestamp;
    private ApiError error;
}