package io.github.trieunguyenphu.digitalbanking.shared.api;

import io.github.trieunguyenphu.digitalbanking.customer.application.CustomerAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException exception) {
        var errors = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> new ApiFieldError(error.getField(), error.getDefaultMessage()))
                .toList();
        return error(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", "Request validation failed", errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleMalformedRequest(HttpMessageNotReadableException exception) {
        return error(HttpStatus.BAD_REQUEST, "MALFORMED_REQUEST", "Request body is malformed", List.of());
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicateCustomer(CustomerAlreadyExistsException exception) {
        return error(HttpStatus.CONFLICT, "CUSTOMER_EMAIL_ALREADY_EXISTS", exception.getMessage(), List.of());
    }

    private ResponseEntity<ApiErrorResponse> error(
            HttpStatus status,
            String code,
            String message,
            List<ApiFieldError> errors
    ) {
        return ResponseEntity.status(status).body(new ApiErrorResponse(status.value(), code, message, errors));
    }
}
