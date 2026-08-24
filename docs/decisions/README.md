# Architecture Decision Records

An Architecture Decision Record (ADR) captures a consequential decision, the context that required it, and its tradeoffs. Create one when a choice will materially shape architecture, operations, security, data, or future change and reviewers need a durable explanation.

Name records sequentially, for example:

```text
ADR-001-short-decision-title.md
```

Use these sections:

- **Status**: proposed, accepted, superseded, or rejected.
- **Context**: the problem, constraints, and relevant alternatives.
- **Decision**: the choice actually made.
- **Consequences**: benefits, costs, risks, and follow-up implications.

Do not create placeholder ADRs. Decisions about PostgreSQL, Flyway, money representation, authentication, event infrastructure, or API versioning belong here only when the project actually makes them.
