---
name: git-workflow
description: Use for branch selection, staging, commits, pull requests, rebasing, merging, or other Git workflow work in this repository.
---

# Git Workflow

Follow lightweight GitHub Flow:

`Issue/Task -> focused branch -> logical commits -> PR -> review/test -> squash merge -> delete branch`

## Before Work

- Inspect the current branch, `git status`, staged and unstaged diffs, and recent history.
- Preserve unrelated changes. Never reset, clean, stash, overwrite, or amend someone else's work.
- If Git operations are restricted, do all permitted work and report the exact commands the developer should run.

## Branches

- Keep `main` stable and buildable. Create a short-lived branch when permitted.
- Use one branch for one clear objective, named with an appropriate prefix: `feat/`, `fix/`, `refactor/`, `test/`, `docs/`, `chore/`, or `ci/`.
- Prefer a vertical feature slice over branches split only by controller, service, or repository layers.
- Rebase a private feature branch when it makes integration clearer. Never rewrite shared history.
- If a rebased private branch must be pushed, use `--force-with-lease`; never use `--force`.

## Commits

- Use Conventional Commits: `<type>(<optional-scope>): <imperative description>`.
- Make one commit per logical change; do not manufacture file-by-file commits for one coherent change.
- Stage intentionally. Review the staged diff and status before committing.
- Never commit secrets, credentials, private keys, access tokens, passwords, or `.env` files.

## Pull Requests

- Keep the PR focused and reviewable. Review the full diff and run relevant verification first.
- Use a concise Conventional Commit-style title.
- Structure the description with:
  - **Summary**: purpose and outcome.
  - **Changes**: important implementation details.
  - **Testing**: commands actually run and their results.
  - **Notes**: risks, limitations, rollout, or follow-up when relevant.
- Reference issues with `Closes #<issue>` when the PR resolves one.
- Prefer Squash and Merge, then delete the merged branch when practical.
- Do not claim a commit, push, PR, merge, or test succeeded unless it was actually executed successfully.
