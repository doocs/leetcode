# AGENTS.md

This file provides guidance to AI coding assistants when working with code in this repository.

## Repository Overview

This is [doocs/leetcode](https://github.com/doocs/leetcode) — a large collection of LeetCode, Coding Interviews, and other algorithm problem solutions, each implemented in multiple programming languages (Python, Java, C++, Go, TypeScript, Rust, C#, PHP, JavaScript, Kotlin, Swift, Scala, Ruby, Nim, Shell, SQL).

## Directory Structure

- **`solution/`** — Main LeetCode solutions, organized by problem number ranges (e.g., `0000-0099/`, `0100-0199/`). Each problem has its own directory (e.g., `0000-0099/0001.Two Sum/`) containing:
    - `README.md` / `README_EN.md` — Chinese/English problem descriptions
    - `Solution.{py,java,cpp,go,ts,cs,rs,...}` — Solution files in each language
    - Follow standard LeetCode class-based structure: `class Solution` with the method

- **`lcof/`** — 剑指 Offer (Coding Interviews, 2nd Edition) — problem directories named with Chinese titles
- **`lcof2/`** — 剑指 Offer 专项突击版 (Coding Interviews, Special Edition)
- **`lcci/`** — 程序员面试金典 (Cracking the Coding Interview, 6th Edition) — `01.01.Is Unique` format
- **`lcp/`** — LeetCode Contest Problems
- **`lcs/`** — LeetCode Contest (separate series)
- **`basic/`** — Basic algorithm implementations (sorting algorithms like BubbleSort, QuickSort, etc.)

## Development Workflow

### Adding a New Solution

1. Create a new problem directory under the appropriate parent (e.g., `solution/0000-0099/0042.My Problem/`)
2. Add `Solution.py`, `Solution.java`, `Solution.cpp`, `Solution.go`, `Solution.ts`, `Solution.rs`, `Solution.cs`, etc.
3. Add `README.md` and `README_EN.md` with problem description and solution explanations
4. Follow the existing templates in `solution/template.md` for README formatting
5. All language solutions must implement the same algorithm logic

### Code Formatting

All code must be formatted before committing. The lint-staged hooks run automatically on pre-commit:

```bash
# JavaScript/TypeScript/PHP/SQL/Markdown
pnpm exec prettier --write "**/*.{js,ts,php,sql,md}"

# Python
node scripts/run-py.js -m black -S <file>

# C/C++/Java
pnpm exec clang-format -i --style=file <file>

# Go (adds/removes the `package main` header used by Solution.go files)
node scripts/run-py.js run_format.py --gofmt <file>

# Rust
rustfmt <file>
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

- **clang-format** lint on changed C/C++/Java files
- **Black** lint on changed Python files
- **gofmt** lint on changed Go files
- **rustfmt** lint on changed Rust files
- **Prettier** on JS/TS/PHP/SQL/Markdown files (auto-format same-repo PRs; `--check` on all PRs)
- **Deploy** workflow for the documentation site

## Solution Patterns

- Python solutions use `List` from typing (imported implicitly by LeetCode environment)
- Go solutions include `package main` header (added/removed by formatting script)
- PHP solutions include `<?php` header (added/removed by formatting script)
- SQL solutions uppercase built-in function names (handled by `run_format.py`)
- C# solutions contributed by [@kfstorm](https://github.com/kfstorm)

## Key Conventions

- Problem directories follow naming convention: `{NUMBER}.{Problem Name with Spaces}`
- Each solution file is named `Solution.{ext}` (capital S)
- README files use special HTML comment markers for templating (e.g., `<!-- problem:start -->`, `<!-- solution:start -->`)
- Solutions should match the problem's required class/method signature from LeetCode
