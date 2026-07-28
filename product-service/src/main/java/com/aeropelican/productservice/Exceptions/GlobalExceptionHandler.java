package com.aeropelican.productservice.Exceptions;

import com.aeropelican.productservice.dto.response.APIResponse;
import com.aeropelican.productservice.dto.response.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ProductNotFound.class, CategoryNotFound.class, Product_VariantsNotFound.class})
    public ResponseEntity<APIResponse<Void>> handleResourceNotFoundException(Exception ex, HttpServletRequest request){
        ApiError apiError =ApiError.builder()
                .errorcode("RESOURCE_NOT_FOUND")
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(APIResponse.<Void>builder()
                        .success(false)
                        .error(apiError)
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
                );
    }
    // Generic Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Void>> handleGenericException(
            Exception ex,
            HttpServletRequest request) {

        ApiError apiError = ApiError.builder()
                .errorcode("INTERNAL_SERVER_ERROR")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(APIResponse.<Void>builder()
                        .success(false)
                        .message(ex.getMessage())
                        .error(apiError)
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    // Duplicate Resource
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<APIResponse<Void>> handleDuplicateResource(
            DataIntegrityViolationException ex,
            HttpServletRequest request) {

        ApiError apiError = ApiError.builder()
                .errorcode("DUPLICATE_RESOURCE")
                .status(HttpStatus.CONFLICT.value())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(APIResponse.<Void>builder()
                        .success(false)
                        .message("Resource already exists.")
                        .error(apiError)
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    // Missing Request Parameter
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<APIResponse<Void>> handleMissingParameter(
            MissingServletRequestParameterException ex,
            HttpServletRequest request) {

        ApiError apiError = ApiError.builder()
                .errorcode("MISSING_REQUEST_PARAMETER")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest()
                .body(APIResponse.<Void>builder()
                        .success(false)
                        .message(ex.getParameterName() + " parameter is required.")
                        .error(apiError)
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    // Method Argument Type Mismatch
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<APIResponse<Void>> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request) {

        ApiError apiError = ApiError.builder()
                .errorcode("INVALID_PARAMETER_TYPE")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest()
                .body(APIResponse.<Void>builder()
                        .success(false)
                        .message("Invalid value '" + ex.getValue()
                                + "' for parameter '" + ex.getName() + "'.")
                        .error(apiError)
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    // No Handler Found
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<APIResponse<Void>> handleNoHandlerFound(
            NoHandlerFoundException ex,
            HttpServletRequest request) {

        ApiError apiError = ApiError.builder()
                .errorcode("RESOURCE_NOT_FOUND")
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(APIResponse.<Void>builder()
                        .success(false)
                        .message("Requested endpoint does not exist.")
                        .error(apiError)
                        .timestamp(LocalDateTime.now())
                        .build());
    }


}
