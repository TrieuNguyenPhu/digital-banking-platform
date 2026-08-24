package io.github.trieunguyenphu.digitalbanking.customer.application;

import io.github.trieunguyenphu.digitalbanking.customer.domain.Customer;
import io.github.trieunguyenphu.digitalbanking.customer.persistence.CustomerRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerApplicationServiceTests {

    @Test
    void translatesTheCustomerEmailConstraintViolation() {
        var repository = mock(CustomerRepository.class);
        var exception = integrityViolation("uk_customers_email");
        when(repository.existsByEmail("a@example.com")).thenReturn(false);
        when(repository.saveAndFlush(any(Customer.class))).thenThrow(exception);

        assertThatThrownBy(() -> new CustomerApplicationService(repository).createCustomer("Nguyen Van A", "a@example.com"))
                .isInstanceOf(CustomerAlreadyExistsException.class);
    }

    @Test
    void doesNotTranslateOtherIntegrityViolations() {
        var repository = mock(CustomerRepository.class);
        var exception = integrityViolation("other_constraint");
        when(repository.existsByEmail("a@example.com")).thenReturn(false);
        when(repository.saveAndFlush(any(Customer.class))).thenThrow(exception);

        assertThatThrownBy(() -> new CustomerApplicationService(repository).createCustomer("Nguyen Van A", "a@example.com"))
                .isSameAs(exception);
    }

    private DataIntegrityViolationException integrityViolation(String constraintName) {
        return new DataIntegrityViolationException("constraint violation", new ConstraintViolationException(
                "constraint violation", new SQLException(), "insert into customers", constraintName
        ));
    }
}
