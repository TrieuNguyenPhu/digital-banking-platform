package io.github.trieunguyenphu.digitalbanking.customer.api;

import io.github.trieunguyenphu.digitalbanking.customer.application.CustomerAlreadyExistsException;
import io.github.trieunguyenphu.digitalbanking.shared.api.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicateCustomer(CustomerAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiErrorResponse(
                HttpStatus.CONFLICT.value(),
                "CUSTOMER_EMAIL_ALREADY_EXISTS",
                exception.getMessage(),
                List.of()
        ));
    }
}
