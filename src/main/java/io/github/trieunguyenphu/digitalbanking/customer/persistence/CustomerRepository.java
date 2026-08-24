package io.github.trieunguyenphu.digitalbanking.customer.persistence;

import io.github.trieunguyenphu.digitalbanking.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    boolean existsByEmail(String email);
}
