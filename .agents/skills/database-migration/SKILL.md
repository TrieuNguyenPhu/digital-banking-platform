---
name: database-migration
description: Use when a task explicitly changes the database schema, PostgreSQL integration, or Flyway migrations in this repository; do not invoke merely because a feature may need persistence later.
---

# Database Migration

- Inspect the configured database technology, existing migrations, schema conventions, and relevant application code first.
- Use Flyway only once Flyway is established in the project. Determine the next migration version from the repository rather than guessing.
- Never edit an already-applied versioned migration to evolve a schema; add a new migration.
- Keep migrations deterministic, focused, reviewable, and safe for the supported deployment path.
- Express important invariants with appropriate database constraints. Consider uniqueness, nullability, referential integrity, indexes, and backward compatibility where applicable.
- Use `NUMERIC` or `DECIMAL` for monetary values, never floating-point types.
- Add only tables, columns, constraints, and indexes required by the current task.
- Verify migrations with the project's available migration or integration tests and report only commands actually run.

This skill does not authorize adding PostgreSQL, Flyway, JPA, database configuration, or speculative schema. Those changes require an explicit task.
