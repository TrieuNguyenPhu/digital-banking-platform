---
name: testing
description: Use when adding, changing, reviewing, or running automated tests for application behavior in this Gradle repository.
---

# Testing

- Test observable behavior rather than implementation details.
- Use focused unit tests for domain and application logic. Use integration tests when framework wiring, persistence, HTTP, or another integration boundary is part of the behavior under test.
- Cover happy paths and relevant validation failures, domain failures, and security-sensitive banking cases.
- Keep the test stack proportional to the change; do not add testing frameworks without a demonstrated need.
- Do not weaken valid assertions, hide failures, or disable tests merely to make the build green. Fix the underlying problem.
- Run tests through the Gradle wrapper:
  - Unix-like systems: `./gradlew test`
  - Windows: `.\gradlew.bat test`
- Distinguish verification actually executed from commands only recommended. Never report a pass without a successful run.
