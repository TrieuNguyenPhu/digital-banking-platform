package io.github.trieunguyenphu.digitalbanking.customer.application;

import io.github.trieunguyenphu.digitalbanking.customer.domain.Customer;
import io.github.trieunguyenphu.digitalbanking.customer.persistence.CustomerRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerApplicationService {

    private final CustomerRepository customerRepository;

    public CustomerApplicationService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer createCustomer(String fullName, String email) {
        if (customerRepository.existsByEmail(email)) {
            throw new CustomerAlreadyExistsException();
        }

        try {
            return customerRepository.saveAndFlush(new Customer(fullName, email));
        } catch (DataIntegrityViolationException exception) {
            if (isDuplicateEmail(exception)) {
                throw new CustomerAlreadyExistsException();
            }
            throw exception;
        }
    }

    private boolean isDuplicateEmail(DataIntegrityViolationException exception) {
        Throwable cause = exception;
        while (cause != null) {
            if (cause instanceof ConstraintViolationException constraintViolation
                    && "uk_customers_email".equals(constraintViolation.getConstraintName())) {
                return true;
            }
            cause = cause.getCause();
        }
        return false;
    }
}
