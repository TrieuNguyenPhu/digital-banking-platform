package io.github.trieunguyenphu.digitalbanking.customer.application;

public class CustomerAlreadyExistsException extends RuntimeException {

    public CustomerAlreadyExistsException() {
        super("A customer with this email already exists");
    }
}
