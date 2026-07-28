package com.aeropelican.productservice.exceptions;

import com.aeropelican.productservice.dto.response.ApiError;
import com.aeropelican.productservice.dto.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Invalid Endpoint Handler (Catches Spring Boot 3+ NoResourceFoundException)
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNoResourceFoundException(
            NoResourceFoundException ex, HttpServletRequest request) {

        ApiError apiError = ApiError.builder()
                .errorCode("ENDPOINT_NOT_FOUND")
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .build();

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .message("No handler found for " + ex.getHttpMethod() + " " + request.getRequestURI())
                .error(apiError)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // 2. Legacy / Configured No Handler Found Exception
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpServletRequest request) {

        ApiError apiError = ApiError.builder()
                .errorCode("ENDPOINT_NOT_FOUND")
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .build();

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .message("No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL())
                .error(apiError)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // 3. Generic Runtime Exception (Handles 404 for Product, Category, and Variant)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntimeException(
            RuntimeException ex, HttpServletRequest request) {

        String path = request.getRequestURI();
        String errorCode = "NOT_FOUND";

        if (path.contains("/products")) {
            errorCode = "PRODUCT_NOT_FOUND";
        } else if (path.contains("/categories")) {
            errorCode = "CATEGORY_NOT_FOUND";
        } else if (path.contains("/variants")) {
            errorCode = "PRODUCT_VARIANT_NOT_FOUND";
        }

        ApiError apiError = ApiError.builder()
                .errorCode(errorCode)
                .status(HttpStatus.NOT_FOUND.value())
                .path(path)
                .build();

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .message(ex.getMessage() != null ? ex.getMessage() : "Resource not found")
                .error(apiError)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // 4. Duplicate Resource (DataIntegrityViolationException)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex, HttpServletRequest request) {

        ApiError apiError = ApiError.builder()
                .errorCode("DUPLICATE_RESOURCE")
                .status(HttpStatus.CONFLICT.value())
                .path(request.getRequestURI())
                .build();

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .message("Resource already exists or violates unique database constraint.")
                .error(apiError)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // 5. Missing Servlet Request Parameter
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<Void>> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpServletRequest request) {

        ApiError apiError = ApiError.builder()
                .errorCode("MISSING_REQUEST_PARAM")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .build();

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .message("Required request parameter '" + ex.getParameterName() + "' is missing")
                .error(apiError)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 6. Method Argument Type Mismatch
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {

        String requiredType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown";
        String message = String.format("Parameter '%s' should be of type '%s'", ex.getName(), requiredType);

        ApiError apiError = ApiError.builder()
                .errorCode("ARGUMENT_TYPE_MISMATCH")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .build();

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .message(message)
                .error(apiError)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 7. ResponseStatusException
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse<Void>> handleResponseStatusException(
            ResponseStatusException ex, HttpServletRequest request) {

        ApiError apiError = ApiError.builder()
                .errorCode(ex.getStatusCode().toString())
                .status(ex.getStatusCode().value())
                .path(request.getRequestURI())
                .build();

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .message(ex.getReason())
                .error(apiError)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    // 8. Generic Fallback Exception Handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(
            Exception ex, HttpServletRequest request) {

        ApiError apiError = ApiError.builder()
                .errorCode("INTERNAL_SERVER_ERROR")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(request.getRequestURI())
                .build();

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .message(ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred")
                .error(apiError)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}