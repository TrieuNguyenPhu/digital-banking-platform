# Architecture Overview

## Implemented Now

The application is an early Spring Boot backend skeleton under `io.github.trieunguyenphu.digitalbanking`. It currently has a bootstrap application class, application configuration, a context-load test, and a Bruno workspace. No banking business capability has been implemented.

The local checkout also contains in-progress configuration for Spring MVC, validation, Actuator, JPA, Flyway, PostgreSQL, and a local PostgreSQL container. It does not yet contain a domain model, REST controller, persistence entity, repository, or versioned schema migration. Those infrastructure changes predate this engineering harness and should be reviewed as a separate unit of work before being treated as established architecture.

## Near-Term Direction

Grow the system as a well-structured Spring Boot backend, one required capability at a time. Keep business rules explicit and testable, validate trust boundaries, and give correctness, transactional integrity, security, and concurrency appropriate attention for financial behavior.

Prefer a modular monolith and vertical feature organization while the product and domain are still taking shape. Introduce infrastructure only when a concrete feature requires it and record consequential choices as architecture decisions.

## Future Ideas, Not Commitments

The project may later require authentication, accounts, transfers, database persistence, API versioning, event publication, caching, containers, or distributed components. None of those ideas is an architectural commitment merely because it appears in project ambitions or local experiments. Adopt each only through an explicit requirement and, when consequential, an ADR.
