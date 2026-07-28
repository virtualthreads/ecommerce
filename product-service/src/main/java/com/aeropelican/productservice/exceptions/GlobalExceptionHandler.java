package com.aeropelican.productservice.exceptions;

import com.aeropelican.productservice.dto.response.ApiError;
import com.aeropelican.productservice.dto.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ApiError apiError = ApiError.builder()
                .path(request.getRequestURI())
                .status(HttpStatus.NOT_FOUND.value())
                .errorCode("RESOURCE_NOT_FOUND")
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.<Void>builder()
                        .success(false)
                        .message(ex.getMessage())
                        .error(apiError)
                        .build()
                );
    }

//    @ExceptionHandler(DuplicateResourceException.class)
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    @ExceptionHandler(NoHandlerFoundException.class)

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(
            Exception ex, HttpServletRequest request) {

        ApiError error = ApiError.builder()
                .errorCode("INTERNAL_SERVER_ERROR")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(request.getRequestURI())
                .build();
        return build(HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred. Please try again later.", error);
    }

    private ResponseEntity<ApiResponse<Void>> build(HttpStatus status, String message, ApiError error) {
        return ResponseEntity.status(status).body(ApiResponse.failure(message, error));
    }
}
