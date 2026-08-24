# Architecture Overview

## Implemented Now

The application is a modular Spring Boot backend under `io.github.trieunguyenphu.digitalbanking`. Spring MVC, Bean Validation, Actuator, JPA, Flyway, PostgreSQL configuration, and Docker Compose local PostgreSQL are established. Hibernate validates the Flyway-managed schema and does not generate it.

Customer onboarding is the first business capability. It is implemented as a vertical slice with REST validation, an application transaction boundary, a JPA-backed customer model, and a Flyway-managed PostgreSQL migration. HTTP/persistence integration tests run the production migration against PostgreSQL Testcontainers.

The Bruno collection provides a local environment, an Actuator health request, and a Customer creation request. Customer code is grouped by feature; reusable HTTP error response types live in `shared.api`.

## Current Constraints

Authentication, authorization, accounts, balances, transfers, messaging, caching, and distributed infrastructure are not implemented.
