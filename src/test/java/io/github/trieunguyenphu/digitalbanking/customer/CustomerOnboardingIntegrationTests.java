package io.github.trieunguyenphu.digitalbanking.customer;

import io.github.trieunguyenphu.digitalbanking.customer.domain.Customer;
import io.github.trieunguyenphu.digitalbanking.customer.persistence.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class CustomerOnboardingIntegrationTests {

    @Container
    @ServiceConnection
    static final PostgreSQLContainer postgres = new PostgreSQLContainer(DockerImageName.parse("postgres:17-alpine"));

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void clearCustomers() {
        customerRepository.deleteAll();
    }

    @Test
    void createsAndPersistsCustomer() throws Exception {
        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"fullName":"  Nguyen Van A  ","email":" A@EXAMPLE.COM "}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.fullName").value("Nguyen Van A"))
                .andExpect(jsonPath("$.email").value("a@example.com"))
                .andExpect(jsonPath("$.createdAt").isNotEmpty());

        var customer = customerRepository.findAll().getFirst();
        org.junit.jupiter.api.Assertions.assertAll(
                () -> org.junit.jupiter.api.Assertions.assertEquals("Nguyen Van A", customer.getFullName()),
                () -> org.junit.jupiter.api.Assertions.assertEquals("a@example.com", customer.getEmail())
        );
    }

    @Test
    void rejectsBlankName() throws Exception {
        assertValidationError("{\"fullName\":\"   \",\"email\":\"a@example.com\"}", "fullName");
    }

    @Test
    void rejectsInvalidEmail() throws Exception {
        assertValidationError("{\"fullName\":\"Nguyen Van A\",\"email\":\"not-an-email\"}", "email");
    }

    @Test
    void rejectsBlankEmail() throws Exception {
        assertValidationError("{\"fullName\":\"Nguyen Van A\",\"email\":\"   \"}", "email");
    }

    @Test
    void rejectsDuplicateEmail() throws Exception {
        var request = "{\"fullName\":\"Nguyen Van A\",\"email\":\"a@example.com\"}";
        mockMvc.perform(post("/api/v1/customers").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fullName\":\"Another Customer\",\"email\":\"A@EXAMPLE.COM\"}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.code").value("CUSTOMER_EMAIL_ALREADY_EXISTS"));
    }

    @Test
    void databaseRejectsDuplicateEmail() {
        customerRepository.saveAndFlush(new Customer("Nguyen Van A", "a@example.com"));

        assertThatThrownBy(() -> customerRepository.saveAndFlush(new Customer("Another Customer", "a@example.com")))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    private void assertValidationError(String request, String field) throws Exception {
        mockMvc.perform(post("/api/v1/customers").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"))
                .andExpect(jsonPath("$.errors[*].field", hasItem(field)));
    }
}
