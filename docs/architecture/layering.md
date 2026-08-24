# Layering

Use lightweight separation of concerns as features gain enough complexity to need it:

```text
Web / API
    |
Application / Use Cases
    |
Domain Rules
    |
Persistence / Infrastructure
```

- Web code translates HTTP requests and responses, validates external input, and delegates work. It should not own business rules.
- Application code coordinates a use case and its transaction boundary when one is needed.
- Domain code expresses business rules independently of HTTP and unnecessary framework details.
- Persistence and infrastructure code integrates with databases and external systems without leaking those details into business rules.

This is a guide, not mandatory ceremony. A feature does not need a class or interface in every conceptual layer. Add separation only where it makes dependencies clearer or behavior easier to test. Prefer vertical feature ownership over a repository-wide collection of technical layers.
