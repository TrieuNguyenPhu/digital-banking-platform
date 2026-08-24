package io.github.trieunguyenphu.digitalbanking.customer.api;

import io.github.trieunguyenphu.digitalbanking.customer.application.CustomerApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerApplicationService customerApplicationService;

    public CustomerController(CustomerApplicationService customerApplicationService) {
        this.customerApplicationService = customerApplicationService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest request) {
        var customer = customerApplicationService.createCustomer(request.fullName(), request.email());
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerResponse.from(customer));
    }
}
