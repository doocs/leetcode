#!/usr/bin/env python3
"""Normalize math notation in solution prose.

Scheme, applied only inside <!-- solution:start --> regions and never inside
fenced code:

- Formulas stay in ``$...$`` or ``$$...$$``.
- Single-letter symbols stay plain math (``$n$``, ``$O(n\\log n)$``).
- Two lowercase letters stay plain (``$dp$``, ``$O(mn)$``, ``$ab$``).
- Longer code identifiers use ``\\textit`` (``$\\textit{nums}[i]$``).
  A digit, underscore, or capital also forces ``\\textit`` (``$\\textit{s1}$``),
  except a run of single-letter factors (``$C \\times n$``) and a multi-word
  identifier phrase (``$\\textit{NOT IN}$``).
- Natural-language words inside a formula stay ``\\text``.
- ``\\log`` / ``\\ln`` are operator commands. ``\\min`` / ``\\max`` / ``\\gcd``
  are operators when they are called or subscripted; a bare name is an identifier.
- ``\\text`` / ``\\mathrm`` / ``\\operatorname`` are not used for code names.
- Inline code and fenced blocks stay code. This script does not rewrite them.

Spans that look like prose accidentally wrapped in ``$...$`` are left alone.
"""

from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path

ROOTS = ("solution", "lcof", "lcof2", "lcci", "lcp", "lcs", "basic")

REGION = re.compile(
    r"(<!--\s*solution:start\s*-->)(.*?)(<!--\s*solution:end\s*-->)",
    re.S,
)
FENCE = re.compile(r"```.*?```", re.S)
PURE_IDENT = re.compile(r"[A-Za-z][A-Za-z0-9]*(?:\\_[A-Za-z0-9]+)*\Z")
IDENT_RE = re.compile(r"[A-Za-z][A-Za-z0-9]*(?:\\_[A-Za-z0-9]+)*")
CJK_RE = re.compile(r"[\u4e00-\u9fff]")
WORD_RE = re.compile(r"[A-Za-z]{2,}")

WRAP_CMDS = {"text", "mathrm", "textrm", "mathbf", "mathit", "operatorname"}
ALWAYS_OPS = {"log": "\\log", "ln": "\\ln"}
CALL_OPS = {"min": "\\min", "max": "\\max", "gcd": "\\gcd"}
# Glued single-letter factors, not identifiers: O(mnk), O(nkm^2).
GLUED_PRODUCTS = {"mnk", "nkm", "nmk", "mkn", "kmn", "knm"}
# English that shows up when a sentence was accidentally closed by a later $.
STOP = {
    "a",
    "an",
    "the",
    "of",
    "to",
    "for",
    "from",
    "by",
    "in",
    "on",
    "at",
    "as",
    "is",
    "be",
    "are",
    "was",
    "were",
    "and",
    "or",
    "if",
    "else",
    "not",
    "with",
    "without",
    "than",
    "then",
    "so",
    "we",
    "it",
    "its",
    "this",
    "that",
    "these",
    "those",
    "into",
    "over",
    "under",
    "per",
    "via",
    "when",
    "where",
    "which",
    "while",
    "each",
    "both",
    "any",
    "all",
    "some",
    "such",
    "only",
    "also",
    "more",
    "most",
    "less",
}
LOG_WORD = {"login", "logout", "logs", "logarithmic", "polylog"}


class MathAbort(Exception):
    pass


def split_log_ident(ident: str) -> str | None:
    """Turn ``nlogn`` into ``n \\log n``. Leave real words alone."""
    if ident in LOG_WORD or "log" not in ident:
        return None
    if not re.fullmatch(r"[a-z]+", ident):
        return None
    parts: list[str] = []
    i = 0
    while i < len(ident):
        if ident.startswith("log", i):
            parts.append("\\log")
            i += 3
        else:
            parts.append(ident[i])
            i += 1
    if not any(part == "\\log" for part in parts):
        return None
    # ``login`` is log + i + n; a product of single letters is the complexity form.
    if any(len(part) != 1 and part != "\\log" for part in parts):
        return None
    if len(parts) == 1:
        return None
    return " ".join(parts)


def grouped_ident_phrase(inner: str) -> str | None:
    """Keep ``NOT IN`` and ``GROUP BY product\\_id`` as one italic phrase.

    Adjacent ``\\textit`` words that were already separate stay separate.
    This only preserves a phrase that arrived inside one wrapper.
    """
    text = inner.replace("\\ ", " ").strip()
    parts = re.split(r"\s+", text)
    if len(parts) < 2:
        return None
    if not all(PURE_IDENT.fullmatch(part) for part in parts):
        return None
    return " ".join(parts)


# Lowercase chunks that are already products by juxtaposition: O(mn), O(nk).
LOWER_DIM = {
    "m",
    "n",
    "k",
    "mn",
    "nm",
    "nk",
    "kn",
    "mk",
    "km",
    "mnk",
    "nkm",
    "nmk",
    "mkn",
    "kmn",
    "knm",
}
# Two capitals inside big-O are separate sizes. Roman numerals such as CM=900
# are not inside O(·), so they stay one identifier.
UPPER_FACTORS = {"CM", "MU", "VE", "XY"}
FORMULA_TEX = re.compile(
    r"\\(?:le|ge|leq|geq|lt|gt|neq|times|cdot|frac|sqrt)(?![A-Za-z])"
)


def split_letter_product(ident: str) -> str | None:
    """Split a token that is only single-letter factors, not one name.

    ``Cn`` and ``nC`` alternate case. ``Cnm`` and ``mnK`` keep a lowercase
    product such as ``nm`` together and separate the capital with ``\\times``.
    ``XYn`` splits the leading capitals. Names such as ``Fib`` and ``mID``
    do not parse and stay identifiers.
    """
    if len(ident) < 2 or not ident.isalpha():
        return None
    if ident.islower() or ident.isupper():
        return None
    if all(prev.isupper() != curr.isupper() for prev, curr in zip(ident, ident[1:])):
        return r" \times ".join(ident)
    parts: list[str] = []
    i = 0
    n = len(ident)
    while i < n:
        if ident[i].isupper():
            j = i + 1
            while j < n and ident[j].isupper():
                j += 1
            run = ident[i:j]
            if len(run) >= 2:
                if i != 0 or not re.fullmatch(r"[mnk]+", ident[j:]):
                    return None
                parts.extend(run)
            else:
                parts.append(run)
            i = j
            continue
        j = i + 1
        while j < n and ident[j].islower():
            j += 1
        chunk = ident[i:j]
        if chunk not in LOWER_DIM:
            return None
        parts.append(chunk)
        i = j
    if len(parts) < 2:
        return None
    return r" \times ".join(parts)


def radix_suffix(token: str) -> str | None:
    if re.fullmatch(r"[xX][0-9A-Fa-f]+", token):
        return token
    if re.fullmatch(r"[bB][01]+", token):
        return token
    return None


def pop_lone_zero(out: list[str]) -> bool:
    if not out or not out[-1].endswith("0"):
        return False
    tail = out[-1]
    if len(tail) > 1 and tail[-2].isdigit():
        return False
    if len(tail) == 1 and len(out) > 1 and out[-2][-1].isdigit():
        return False
    if len(tail) == 1:
        out.pop()
    else:
        out[-1] = tail[:-1]
    return True


def should_textit(ident: str) -> bool:
    """Multi-letter code names are italic words.

    Two lowercase letters stay plain. ``mn`` and ``ab`` are products as well as
    short names, and plain math already renders both. A digit, underscore, or
    capital letter marks a real token (``s1``, ``is\\_end``, ``COUNT``).
    """
    if ident in GLUED_PRODUCTS or split_letter_product(ident):
        return False
    letters = ident.replace("\\_", "")
    # Complexity O(·) and other single-letter symbols stay plain, any case.
    if "\\_" not in ident and len(letters) == 1 and letters.isalpha():
        return False
    if "\\_" in ident or any(ch.isdigit() for ch in ident):
        return True
    if any(ch.isupper() for ch in letters):
        return True
    return len(letters) >= 3


def style_ident(ident: str, nxt: str) -> str:
    product = split_letter_product(ident)
    if product:
        return product
    split = split_log_ident(ident)
    if split:
        return split
    if ident in ALWAYS_OPS:
        return ALWAYS_OPS[ident]
    if ident in CALL_OPS and nxt in {"(", "_"}:
        return CALL_OPS[ident]
    if not should_textit(ident):
        return ident
    return "\\textit{" + ident + "}"


def is_kept_prose(inner: str) -> bool:
    if CJK_RE.search(inner) or re.search(r"['\"]", inner):
        return True
    stripped = inner.strip()
    if stripped in {"-", "−", "+", "/", "*", ":", ",", ".", "…"}:
        return True
    if " " in stripped and re.fullmatch(r"[0-9 ]+", stripped):
        return True
    if " " in inner and re.search(r"[a-z]", inner):
        if re.search(
            r"\b(if|else|and|or|not|the|of|to|for)\b", inner
        ) and not re.search(r"[\[\]{}]", inner):
            return True
        if re.search(r"[\[\]()=|\\_^]", inner):
            return False
        return True
    return False


def read_group(src: str, i: int, open_ch: str, close_ch: str) -> tuple[str, int]:
    if i >= len(src) or src[i] != open_ch:
        raise MathAbort
    depth = 1
    i += 1
    start = i
    while i < len(src):
        if src[i] == "\\":
            i += 2 if i + 1 < len(src) else 1
            continue
        if src[i] == open_ch:
            depth += 1
        elif src[i] == close_ch:
            depth -= 1
            if depth == 0:
                return src[start:i], i + 1
        i += 1
    raise MathAbort


def next_sig(src: str, i: int) -> str:
    while i < len(src) and src[i] in " \t":
        i += 1
    return src[i] if i < len(src) else ""


def transform_wrapped(name: str, inner: str) -> str:
    if is_kept_prose(inner):
        return "\\text{" + inner + "}"
    stripped = inner.strip()
    if re.fullmatch(r"\d+", stripped):
        return stripped
    if re.fullmatch(r"0[xX][0-9A-Fa-f]+|0[bB][01]+", stripped):
        return "\\texttt{" + stripped + "}"
    phrase = grouped_ident_phrase(inner)
    if phrase:
        return "\\textit{" + phrase + "}"
    if name == "text" and stripped == inner and is_kept_prose(inner):
        return "\\text{" + inner + "}"
    return normalize_math_expr(inner)


def normalize_math_expr(src: str) -> str:
    try:
        return _normalize_math_expr(src)
    except MathAbort:
        return src


def _normalize_math_expr(src: str) -> str:
    out: list[str] = []
    i = 0
    n = len(src)
    while i < n:
        if src[i] == "\\":
            if i + 1 < n and src[i + 1].isalpha():
                j = i + 1
                while j < n and src[j].isalpha():
                    j += 1
                name = src[i + 1 : j]
                if j < n and src[j] == "*":
                    j += 1
                if name in {"begin", "end"} and j < n and src[j] == "{":
                    inner, j = read_group(src, j, "{", "}")
                    out.append("\\" + name + "{" + _env_name(inner) + "}")
                    i = j
                    continue
                if name in {"texttt", "textitt"} and j < n and src[j] == "{":
                    inner, j = read_group(src, j, "{", "}")
                    if name == "textitt":
                        inner = _unwrap_simple_textit(inner)
                    if re.fullmatch(r"0[bB][01]+", inner):
                        extra, j = absorb_bit_groups(src, j)
                        inner += extra
                    out.append("\\texttt{" + inner + "}")
                    i = j
                    continue
                if name in {"textit", "texttt"}:
                    if j < n and src[j] == "{":
                        inner, j = read_group(src, j, "{", "}")
                        phrase = (
                            grouped_ident_phrase(inner) if name == "textit" else None
                        )
                        if phrase:
                            out.append("\\textit{" + phrase + "}")
                            i = j
                            continue
                        suffix = radix_suffix(inner) if name == "textit" else None
                        if suffix and pop_lone_zero(out):
                            extra = ""
                            if suffix[0] in "bB":
                                extra, j = absorb_bit_groups(src, j)
                            out.append("\\texttt{0" + suffix + extra + "}")
                            i = j
                            continue
                        if name == "texttt" or _keep_textit(inner):
                            if name == "textit":
                                out.append("\\textit{" + inner + "}")
                            else:
                                out.append("\\texttt{" + inner + "}")
                        else:
                            out.append(_normalize_math_expr(inner))
                        i = j
                        continue
                    out.append(src[i:j])
                    i = j
                    continue
                if name in WRAP_CMDS:
                    if j < n and src[j] == "{":
                        inner, j = read_group(src, j, "{", "}")
                        out.append(transform_wrapped(name, inner))
                        i = j
                        continue
                    out.append(src[i:j])
                    i = j
                    continue
                spaced = src[i:j]
                nxt = src[j] if j < n else ""
                if name == "times" and nxt.isalpha():
                    spaced += " "
                out.append(spaced)
                i = j
                continue
            if i + 1 < n:
                out.append(src[i : i + 2])
                i += 2
                continue
            out.append("\\")
            i += 1
            continue
        matched = IDENT_RE.match(src, i)
        if matched:
            ident = matched.group(0)
            suffix = radix_suffix(ident)
            if suffix and pop_lone_zero(out):
                end = matched.end()
                extra = ""
                if suffix[0] in "bB":
                    extra, end = absorb_bit_groups(src, end)
                out.append("\\texttt{0" + suffix + extra + "}")
                i = end
                continue
            nxt = next_sig(src, matched.end())
            out.append(style_ident(ident, nxt))
            i = matched.end()
            continue
        out.append(src[i])
        i += 1
    return rewrite_o_pairs(join_upper_phrases(separate_times("".join(out))))


def rewrite_o_pairs(src: str) -> str:
    """``O(VE)`` and ``O(CM)`` are products. ``CM=900`` is a numeral."""

    def body(inner: str) -> str:
        def one(match: re.Match[str]) -> str:
            word = match.group(1)
            if word in UPPER_FACTORS:
                return r" \times ".join(word)
            return match.group(0)

        return re.sub(r"\\textit\{([A-Z]{2})\}", one, inner)

    out: list[str] = []
    i = 0
    while True:
        j = src.find("O(", i)
        if j < 0:
            out.append(src[i:])
            break
        depth = 0
        k = j + 1
        while k < len(src):
            if src[k] == "(":
                depth += 1
            elif src[k] == ")":
                depth -= 1
                if depth == 0:
                    break
            k += 1
        if k >= len(src) or src[k] != ")":
            out.append(src[i:])
            break
        out.append(src[i:j])
        out.append("O(" + body(src[j + 2 : k]) + ")")
        i = k + 1
    return "".join(out)


def formula_from_code(code: str) -> str | None:
    """A backtick span that is only a TeX formula belongs in math."""
    if not FORMULA_TEX.search(code):
        return None
    if re.search(r"\\(?:b|B|d|D|w|W|s|S|Z)(?![A-Za-z])", code):
        return None
    return "$" + normalize_math_span(code) + "$"


def separate_times(src: str) -> str:
    """``\\timesv`` is an undefined command; keep the control word intact."""
    return re.sub(r"\\times(?=[A-Za-z])", r"\\times ", src)


def join_upper_phrases(src: str) -> str:
    """``UNION\\ ALL`` is one SQL phrase, not two identifiers."""

    def repl(match: re.Match[str]) -> str:
        words = re.findall(r"\\textit\{([A-Z][A-Z0-9_]*)\}", match.group(0))
        return "\\textit{" + " ".join(words) + "}"

    return re.sub(
        r"\\textit\{[A-Z][A-Z0-9_]*\}(?:(?:\s|\\ )+\\textit\{[A-Z][A-Z0-9_]*\})+",
        repl,
        src,
    )


def absorb_bit_groups(src: str, i: int) -> tuple[str, int]:
    """Pull `` 0010 0001`` into a binary mask that already started at ``0b``."""
    extra: list[str] = []
    while i < len(src) and src[i] == " ":
        k = i + 1
        while k < len(src) and src[k] in "01":
            k += 1
        if k == i + 1:
            break
        extra.append(src[i:k])
        i = k
    return "".join(extra), i


def _unwrap_simple_textit(inner: str) -> str:
    match = re.fullmatch(r"\\textit\{([^{}]+)\}", inner.strip())
    if match:
        return match.group(1)
    return inner


def _env_name(inner: str) -> str:
    """Environment names are not identifiers. Repair a previous bad wrap."""
    match = re.fullmatch(
        r"\\(?:textit|text|mathrm|textrm)\{([^{}]+)\}(\*?)?",
        inner.strip(),
    )
    if match:
        return match.group(1) + (match.group(2) or "")
    return inner


def _keep_textit(inner: str) -> bool:
    if PURE_IDENT.fullmatch(inner):
        return should_textit(inner)
    if inner[:1].isdigit() or CJK_RE.search(inner) or re.search(r"['\"]", inner):
        return True
    if is_kept_prose(inner):
        return True
    return False


def looks_like_prose(expr: str) -> bool:
    if len(expr) > 800 or "`" in expr or "://" in expr:
        return True
    if re.search(r"\.\s+[A-Za-z]", expr):
        return True
    plain = re.sub(r"\\[a-zA-Z]+", " ", expr)
    words = WORD_RE.findall(plain)
    if not words:
        return False
    stops = sum(1 for word in words if word.lower() in STOP)
    has_formula = bool(re.search(r"[=+\-^_{}\\<>()\[\]]|\\", expr))
    if stops >= 3:
        return True
    if not has_formula and stops >= 2:
        return True
    lower = [word for word in words if word.islower() and len(word) >= 3]
    if not has_formula and len(lower) >= 3:
        return True
    return False


def code_literal(expr: str) -> str | None:
    """A whole span that is only a hex or binary mask belongs in inline code."""
    stripped = expr.strip()
    stripped = re.sub(r"\\(?:textit|texttt|text)\{([^{}]*)\}", r"\1", stripped)
    stripped = re.sub(r"\s+", " ", stripped).strip()
    if re.fullmatch(r"0[xX][0-9A-Fa-f]+", stripped):
        return stripped
    if re.fullmatch(r"0[bB][01]+(?: [01]+)*", stripped):
        return stripped
    return None


def render_math(inner: str, display: bool) -> str:
    literal = None if display else code_literal(inner)
    if literal is not None:
        return "`" + literal + "`"
    body = normalize_math_span(inner)
    if display:
        return "$$" + body + "$$"
    return "$" + body + "$"


def normalize_math_span(expr: str) -> str:
    if looks_like_prose(expr):
        return expr
    return normalize_math_expr(expr)


def normalize_prose(src: str) -> str:
    out: list[str] = []
    i = 0
    n = len(src)
    while i < n:
        if src[i] == "`":
            nl = src.find("\n", i + 1)
            end = src.find("`", i + 1)
            if end == -1 or (nl != -1 and nl < end):
                out.append(src[i])
                i += 1
                continue
            code = src[i + 1 : end]
            formula = formula_from_code(code)
            out.append(formula if formula is not None else src[i : end + 1])
            i = end + 1
            continue
        if src[i] == "\\" and i + 1 < n:
            out.append(src[i : i + 2])
            i += 2
            continue
        if src[i] == "$":
            if i + 1 < n and src[i + 1] == "$":
                j = i + 2
                closed = None
                while j < n:
                    if src[j] == "\\":
                        j += 2
                        continue
                    if src[j] == "$" and j + 1 < n and src[j + 1] == "$":
                        closed = j
                        break
                    j += 1
                if closed is None:
                    out.append("$$")
                    i += 2
                    continue
                inner = src[i + 2 : closed]
                out.append(render_math(inner, True))
                i = closed + 2
                continue
            j = i + 1
            closed = None
            while j < n:
                if src[j] == "\\":
                    j += 2
                    continue
                if src[j] == "\n":
                    break
                if src[j] == "$":
                    closed = j
                    break
                j += 1
            if closed is None:
                out.append("$")
                i += 1
                continue
            inner = src[i + 1 : closed]
            out.append(render_math(inner, False))
            i = closed + 1
            continue
        out.append(src[i])
        i += 1
    return "".join(out)


def normalize_region(region: str) -> str:
    parts: list[str] = []
    last = 0
    for match in FENCE.finditer(region):
        parts.append(normalize_prose(region[last : match.start()]))
        parts.append(match.group(0))
        last = match.end()
    parts.append(normalize_prose(region[last:]))
    return "".join(parts)


def process_text(text: str) -> str:
    def repl(match: re.Match[str]) -> str:
        return match.group(1) + normalize_region(match.group(2)) + match.group(3)

    return REGION.sub(repl, text)


def iter_readmes(paths: list[Path]) -> list[Path]:
    if paths:
        return [path for path in paths if path.suffix == ".md" and path.is_file()]
    found: list[Path] = []
    for root in ROOTS:
        base = Path(root)
        if base.exists():
            found.extend(base.rglob("README.md"))
            found.extend(base.rglob("README_EN.md"))
    return found


def self_test() -> None:
    cases = {
        "$nums$": "$\\textit{nums}$",
        "$n$": "$n$",
        "$nums[i] + nums[j]$": "$\\textit{nums}[i] + \\textit{nums}[j]$",
        "$O(n \\log n)$": "$O(n \\log n)$",
        "$O(n log n)$": "$O(n \\log n)$",
        "$O(nlogn)$": "$O(n \\log n)$",
        "$O(mn)$": "$O(mn)$",
        "$O(mnk)$": "$O(mnk)$",
        "$(a+bi)(ac-bd)$": "$(a+bi)(ac-bd)$",
        "$dp$": "$dp$",
        "$\\textit{dp}$": "$dp$",
        "$\\textit{mx}$": "$mx$",
        "$s1$": "$\\textit{s1}$",
        "$\\textit{OR}$": "$\\textit{OR}$",
        "$\\textit{ln}$": "$\\ln$",
        "$\\text{dfs}$": "$\\textit{dfs}$",
        "$\\text{nums[i]}$": "$\\textit{nums}[i]$",
        "$\\text{真实值}$": "$\\text{真实值}$",
        "$\\mathrm{lowbit}$": "$\\textit{lowbit}$",
        "$\\operatorname{dist}$": "$\\textit{dist}$",
        "$\\textit{nums}$": "$\\textit{nums}$",
        "$s_{left}$": "$s_{\\textit{left}}$",
        "$\\max(a, b)$": "$\\max(a, b)$",
        "$max(a, b)$": "$\\max(a, b)$",
        "$max$": "$\\textit{max}$",
        "$dp[i][j]$": "$dp[i][j]$",
        "$is\\_end$": "$\\textit{is\\_end}$",
        "$\\text{is\\_end}$": "$\\textit{is\\_end}$",
        "$\\texttt{\\$}$": "$\\texttt{\\$}$",
        "$\\text{true}$": "$\\textit{true}$",
        "$\\text{'S'}$": "$\\text{'S'}$",
        "$\\text{-}$": "$\\text{-}$",
        "$\\text{0}$": "$0$",
        "$\\text{0x5555}$": "`0x5555`",
        "$0xFFFFFFFF$": "`0xFFFFFFFF`",
        "$0\\textit{xFFFFFFFF}$": "`0xFFFFFFFF`",
        "$0b1111$": "`0b1111`",
        "$0\\textit{b0001} 0010 0001$": "`0b0001 0010 0001`",
        "$n = 0xFF$": "$n = \\texttt{0xFF}$",
        "$O(\\textit{Cn})$": "$O(C \\times n)$",
        "$O(\\textit{nC})$": "$O(n \\times C)$",
        "$O(\\textit{nG}(n))$": "$O(n \\times G(n))$",
        "$\\textit{maxGap}$": "$\\textit{maxGap}$",
        "$|\\mathbf{u}\\times\\mathbf{v}|/2$": "$|u\\times v|/2$",
        "$u\\timesv$": "$u\\times v$",
        "$\\frac{nums}{2}$": "$\\frac{\\textit{nums}}{2}$",
        "$\\begin{bmatrix} a \\\\ b \\end{bmatrix}$": "$\\begin{bmatrix} a \\\\ b \\end{bmatrix}$",
        "$\\begin{\\textit{bmatrix}} a \\end{\\textit{bmatrix}}$": "$\\begin{bmatrix} a \\end{bmatrix}$",
        "$\\begin{cases} 1 \\\\ 0 \\end{cases}$": "$\\begin{cases} 1 \\\\ 0 \\end{cases}$",
        "$\\begin{align*} a \\end{align*}$": "$\\begin{align*} a \\end{align*}$",
        "$\\begin{\\textit{align}*} a \\end{\\textit{align}*}$": "$\\begin{align*} a \\end{align*}$",
        "$\\texttt{arr}$": "$\\texttt{arr}$",
        "$\\textitt{arr}$": "$\\texttt{arr}$",
        "$\\textitt{\\textit{arr}}$": "$\\texttt{arr}$",
        "$f[i][j]$": "$f[i][j]$",
        "`nums` $nums$": "`nums` $\\textit{nums}$",
        "$\\text{Gold Medal}$": "$\\text{Gold Medal}$",
        "$\\textit{nums[i]}$": "$\\textit{nums}[i]$",
        "$\\textit{2k}$": "$\\textit{2k}$",
        "$\\textit{root.val}$": "$\\textit{root}.\\textit{val}$",
        "$\\mathrm{NOT\\ IN}$": "$\\textit{NOT IN}$",
        "$\\textit{NOT IN}$": "$\\textit{NOT IN}$",
        "$\\textit{GROUP BY product\\_id}$": "$\\textit{GROUP BY product\\_id}$",
        "$\\textit{NOT} \\textit{IN}$": "$\\textit{NOT IN}$",
        "$O(\\textit{Kn}^2)$": "$O(K \\times n^2)$",
        "$O(\\textit{kC}_n^k)$": "$O(k \\times C_n^k)$",
        "$O(\\textit{Cnm})$": "$O(C \\times nm)$",
        "$O(\\textit{mnK})$": "$O(mn \\times K)$",
        "$O(\\textit{nkM})$": "$O(nk \\times M)$",
        "$O(\\textit{nmC})$": "$O(nm \\times C)$",
        "$O(\\textit{XYn})$": "$O(X \\times Y \\times n)$",
        "$O(\\textit{XY} n)$": "$O(X \\times Y n)$",
        "$O(n + \\textit{CM})$": "$O(n + C \\times M)$",
        "$O(\\textit{VE})$": "$O(V \\times E)$",
        "$O(n \\log(\\textit{MU}))$": "$O(n \\log(M \\times U))$",
        "$\\textit{CM}=900$": "$\\textit{CM}=900$",
        "$\\textit{Fib}$": "$\\textit{Fib}$",
        "$\\textit{mID}$": "$\\textit{mID}$",
        "$\\textit{OR}$": "$\\textit{OR}$",
        "`guess(x)\\le 0`": "$\\textit{guess}(x)\\le 0$",
        "`s \\ge nums[i]`": "$s \\ge \\textit{nums}[i]$",
        "$UNION\\ ALL$": "$\\textit{UNION ALL}$",
        "$\\textit{NOT}\\ \\textit{IN}$": "$\\textit{NOT IN}$",
        "$\\textit{AND} \\textit{nums}$": "$\\textit{AND} \\textit{nums}$",
        "$mask = 0b0001 0010 0001$": "$\\textit{mask} = \\texttt{0b0001 0010 0001}$",
        "$\\texttt{0b0001} 0010 0001$": "`0b0001 0010 0001`",
        "$\\text{1 if pos \\% 2 == 0 else -1}$": "$\\text{1 if pos \\% 2 == 0 else -1}$",
        "$dfs(pos, mod, limit)$": "$\\textit{dfs}(\\textit{pos}, \\textit{mod}, \\textit{limit})$",
        "find the words equal to $n$": "find the words equal to $n$",
    }
    failed = False
    for src, expected in cases.items():
        got = normalize_prose(src)
        if got != expected:
            print("FAIL", src)
            print(" got ", got)
            print(" want", expected)
            failed = True
        again = normalize_prose(got)
        if again != got:
            print("NOT IDEMPOTENT", src)
            print(" once", got)
            print(" twice", again)
            failed = True
    region = (
        "<!-- solution:start -->\n"
        "Use $nums$ here.\n"
        "```python\n"
        "nums = $nums$\n"
        "```\n"
        "<!-- solution:end -->\n"
    )
    processed = process_text(region)
    if "$\\textit{nums}$" not in processed or "nums = $nums$" not in processed:
        print("FAIL region\n", processed)
        failed = True
    if process_text(processed) != processed:
        print("FAIL region idempotent")
        failed = True
    # Description dollars outside the solution region stay put.
    untouched = "price $\n<!-- solution:start -->\n$n$\n<!-- solution:end -->\n"
    if process_text(untouched) != untouched:
        print("FAIL outside", process_text(untouched))
        failed = True
    if failed:
        raise SystemExit(1)
    print(f"self-test ok ({len(cases)} cases)")


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("files", nargs="*", type=Path)
    parser.add_argument("--write", action="store_true", help="rewrite files in place")
    parser.add_argument(
        "--check", action="store_true", help="exit 1 if a file would change"
    )
    parser.add_argument("--self-test", action="store_true")
    args = parser.parse_args()
    if args.self_test:
        self_test()
        return 0
    if not args.write and not args.check:
        parser.error("pass --write, --check, or --self-test")
    changed: list[Path] = []
    for path in iter_readmes(args.files):
        original = path.read_text(encoding="utf-8")
        updated = process_text(original)
        if updated != original:
            changed.append(path)
            if args.write:
                path.write_text(updated, encoding="utf-8")
    if args.check:
        if changed:
            print("\n".join(str(path) for path in changed))
            print(f"{len(changed)} file(s) differ from the math notation scheme.")
            return 1
        print(f"OK {len(iter_readmes(args.files))} file(s)")
        return 0
    print(f"updated {len(changed)} file(s)")
    return 0


if __name__ == "__main__":
    sys.exit(main())
