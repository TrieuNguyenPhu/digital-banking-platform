# Architecture Overview

## Implemented Now

The application is a Spring Boot backend foundation under `io.github.trieunguyenphu.digitalbanking`. Spring MVC, Bean Validation, Actuator, JPA, Flyway, and PostgreSQL configuration are established. Hibernate validates the Flyway-managed schema and does not generate it.

Docker Compose provides PostgreSQL for local development. The Bruno collection includes a local environment and an Actuator health request at `/actuator/health`. The migration directory is established, but no banking schema or business capability exists yet.

## Near-Term Direction

Grow the system one business capability at a time. Keep business rules explicit and testable, validate trust boundaries, and give correctness, transactional integrity, security, and concurrency appropriate attention for financial behavior.

Prefer a modular monolith and vertical feature organization while the product and domain are still taking shape. Introduce infrastructure only when a concrete feature requires it and record consequential choices as architecture decisions.

## Future Ideas, Not Commitments

The project may later require authentication, accounts, transfers, database persistence, API versioning, event publication, caching, containers, or distributed components. None of those ideas is an architectural commitment merely because it appears in project ambitions or local experiments. Adopt each only through an explicit requirement and, when consequential, an ADR.
