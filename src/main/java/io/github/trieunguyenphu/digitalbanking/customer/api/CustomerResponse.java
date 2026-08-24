package io.github.trieunguyenphu.digitalbanking.customer.api;

import io.github.trieunguyenphu.digitalbanking.customer.domain.Customer;

import java.time.Instant;
import java.util.UUID;

public record CustomerResponse(UUID id, String fullName, String email, Instant createdAt) {

    static CustomerResponse from(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getCreatedAt()
        );
    }
}
