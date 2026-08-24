# Engineering Guidelines

## Repository Discipline

- Inspect the branch, status, relevant diffs, and existing conventions before changing files.
- Preserve unrelated work; do not reset, clean, stash, overwrite, or amend it.
- Never commit secrets, credentials, `.env` files, private keys, access tokens, or passwords.
- Keep each change focused. Avoid unrelated refactoring, speculative abstractions, and unused dependencies.
- Report only operations and verification that were actually executed successfully.

## Git Fundamentals

- Keep `main` stable, buildable, and deployable. Use a focused short-lived branch for application work when branch operations are permitted.
- Use Conventional Commits and focused pull requests.
- Never force-push `main` or rewrite shared history. If a private branch legitimately requires a rewritten push, use `--force-with-lease`, never `--force`.
- Prefer Squash and Merge.

For branch naming, commits, pull requests, and restricted environments, use `.agents/skills/git-workflow/SKILL.md`.

## Engineering Principles

- Prefer the simplest production-appropriate implementation and implement only current requirements.
- Keep controllers thin when REST APIs are introduced; keep business rules explicit, testable, and outside controllers.
- Use constructor injection.
- Validate external input and do not expose persistence entities directly through APIs.

## Banking Invariants

- Never use `float` or `double` for monetary amounts.
- Preserve transactional integrity for financial operations and handle concurrency when modifying shared financial state.
- Treat authentication, authorization, account ownership, and balance-changing operations as security-sensitive.
- Never trust client-supplied security-sensitive financial state.

## Testing Integrity

- Do not weaken valid assertions or disable tests merely to obtain a green build; fix the underlying problem.
- Never report tests as passing unless they were executed successfully.

## Guidance Routing

- Use `.agents/skills/` for task-specific engineering procedures.
- Use `docs/architecture/` for current structure and architectural direction.
- Use `docs/decisions/` for decisions that have actually been made.
