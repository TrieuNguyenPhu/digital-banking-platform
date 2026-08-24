---
name: spring-boot-feature
description: Use when implementing a new Spring Boot business capability or REST feature in this repository; do not use for repository-only documentation or tooling work.
---

# Spring Boot Feature

1. Inspect the repository state, relevant architecture documentation, and the closest existing feature before designing the change.
2. Identify the required behavior, trust boundaries, banking invariants, and authorization implications.
3. Implement the smallest coherent vertical slice that satisfies the current requirement. Reuse established repository conventions.
4. Keep controllers responsible for HTTP concerns only. Keep domain and application rules explicit, outside controllers, and testable without HTTP.
5. Validate external input. Keep API models separate from persistence models where persistence exists and the boundary applies.
6. Add focused success and failure tests, including validation, authorization, ownership, and other security-sensitive cases when relevant.
7. Run the narrowest useful tests, then the appropriate broader Gradle verification.
8. Review the final diff for scope, accidental behavior changes, and architecture consistency.

Create only the classes and layers the feature needs. Do not add speculative infrastructure, empty packages, unnecessary interfaces, base classes, generic abstractions, or framework designs for hypothetical future features.
