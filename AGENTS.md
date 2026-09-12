# AGENTS.md

This file provides guidance to AI coding assistants when working with code in this repository.

## Repository Overview

This is [doocs/leetcode](https://github.com/doocs/leetcode) — a large collection of LeetCode, Coding Interviews, and other algorithm problem solutions, each implemented in multiple programming languages (Python, Java, C++, Go, TypeScript, Rust, C#, PHP, JavaScript, Kotlin, Swift, Scala, Ruby, Nim, Shell, SQL).

## Directory Structure

- **`solution/`** — Main LeetCode solutions, organized by problem number ranges (e.g., `0000-0099/`, `0100-0199/`). Each problem has its own directory (e.g., `0000-0099/0001.Two Sum/`) containing:
    - `README.md` / `README_EN.md` — Chinese/English problem descriptions
    - `Solution.{py,java,cpp,go,ts,cs,rs,...}` — Solution files in each language
    - Follow standard LeetCode class-based structure: `class Solution` with the method

- **`lcof/`** — Coding Interviews (2nd Edition); problem directories use Chinese titles
- **`lcof2/`** — Coding Interviews (Special Edition)
- **`lcci/`** — Cracking the Coding Interview (6th Edition) — `01.01.Is Unique` format
- **`lcp/`** — LeetCode Contest Problems
- **`lcs/`** — LeetCode Contest (separate series)
- **`basic/`** — Basic algorithm implementations (sorting algorithms like BubbleSort, QuickSort, etc.)

## Branch model

- **`main`** — problem sources, lint tooling, and deploy workflows.
- **`docs`** — MkDocs site engine only (`build_site.py`, `mkdocs.yml`, `hooks/`, `overrides/`). Problem pages are generated at deploy time from `main`.

Deploy checks out both branches, overlays a whitelist from `docs` onto `main`, runs `python3 build_site.py`, then builds zh/en in parallel. Content pushes on `main` (problem trees, `worker.js`, and `wrangler.jsonc`) go through `deploy-request.yml` (about 90s quiet period, then `gh workflow run deploy.yml`). Pushes to `docs` use `.github/workflows/trigger-deploy.yml` on the `docs` branch the same way. A started `deploy.yml` run is not cancelled.

Dependabot updates npm, GitHub Actions, and pip on `main`, and pip on `docs`.

## Development Workflow

### Adding a New Solution

1. Create a new problem directory under the appropriate parent (e.g., `solution/0000-0099/0042.My Problem/`)
2. Add `Solution.py`, `Solution.java`, `Solution.cpp`, `Solution.go`, `Solution.ts`, `Solution.rs`, `Solution.cs`, etc.
3. Add `README.md` and `README_EN.md` from `solution/template.md` (problem statement, methods, complexity, code tabs)
4. After every method heading, write a Thinking block before the algorithm write-up (see [Thinking section](#thinking-section-how-we-arrive-at-the-answer))
5. All language solutions must implement the same algorithm logic
6. Open the PR with `.github/pull_request_template.md` and complete the checklist (see also `CONTRIBUTING.md`)
7. The problem-sync spider only inserts an empty Thinking stub from `solution/template.md`. Fill the walkthrough before opening the PR. Pages with no method heading are unsolved stubs and do not need a Thinking block.

### Code Formatting

All code must be formatted before committing. The lint-staged hooks run automatically on pre-commit:

```bash
# JavaScript/TypeScript/PHP/SQL/Markdown
pnpm exec prettier --write "**/*.{js,ts,php,sql,md}"

# Python
node scripts/run-py.js -m black -S <file>

# C/C++/Java (LLVM 23 via pip clang-format)
node scripts/run-py.js run_format.py --clang-format <file>

# Go (adds/removes the `package main` header used by Solution.go files)
node scripts/run-py.js run_format.py --gofmt <file>

# Rust
rustfmt --edition 2021 <file>
```

Or run the full formatting script:

```bash
node scripts/run-py.js run_format.py
```

### Installation

```bash
pnpm install              # Node/pnpm devDependencies (uses the committed lockfile)
pnpm run setup:python     # Optional: black and the problem-sync spider
```

### CI/CD

GitHub Actions automatically run:

- **clang-format** lint on changed C/C++/Java files (LLVM 23, same pip package as local)
- **Black** lint on changed Python files
- **gofmt** lint on changed Go files
- **rustfmt** lint on changed Rust files
- **Prettier** on JS/TS/PHP/SQL/Markdown files (auto-format same-repo PRs to `main`; `--check` on all PRs)
- **thinking-check** on changed `README.md` / `README_EN.md`: each method heading must have a non-empty Thinking block
- **Deploy** as described under Branch model. Same-repo Prettier uses `pull_request_target` and skips forks so it never installs untrusted `package.json`.

## Solution Patterns

- Python solutions use `List` from typing (imported implicitly by LeetCode environment)
- Go solutions include `package main` header (added/removed by formatting script)
- PHP solutions include `<?php` header (added/removed by formatting script)
- SQL solutions uppercase built-in function names (handled by `run_format.py`)
- C# solutions contributed by [@kfstorm](https://github.com/kfstorm); match existing brace style and do not add CSharpier

## Key Conventions

- Problem directories follow naming convention: `{NUMBER}.{Problem Name with Spaces}`
- Each solution file is named `Solution.{ext}` (capital S)
- README files use special HTML comment markers for templating (e.g., `<!-- problem:start -->`, `<!-- solution:start -->`, `<!-- thinking:start -->`)
- Each method heading is followed by a Thinking block; see below
- Solutions should match the problem's required class/method signature from LeetCode
- New and updated solution PRs use `.github/pull_request_template.md`

## Thinking section (how we arrive at the answer)

Required for every new or updated method in `README.md` / `README_EN.md`. Teach the path to the solution, not only the finished algorithm.

- Insert a `**Thinking**` blockquote (use the matching Chinese label from `solution/template.md` in `README.md`) immediately after the method heading, wrapped in `<!-- thinking:start -->` / `<!-- thinking:end -->`, before the existing algorithm write-up. Do not add a page-level or `####` heading — the quote box is what separates path-to-answer from the formal write-up.
- Keep the original algorithm steps, complexity, and code tabs unchanged. Do not add a page-level Thinking heading.
- Walk in four beats: naive first idea and whether it fits the constraints; the bottleneck; the key observation that reshapes the problem; why this method's data structure and operation order follow from that observation.
- Write against the code that is actually in the repo. Do not describe a different algorithm. Do not restate the step-by-step that already follows.
- For later methods, only explain what the previous method still lacks (space, constants, implementation). Typical length is 3–6 sentences; hard problems may be longer. Match the existing voice (first-person plural in Chinese READMEs, LaTeX variables).
- The Chinese walkthrough must read as the same formal written prose as the algorithm section that follows. Vary sentence openings and use complete paragraphs. Do not use template slogans.
