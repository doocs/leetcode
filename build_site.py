"""Flatten problem READMEs and generate MkDocs nav grouped by number range."""

import os
from collections import defaultdict
from typing import Dict, List, NamedTuple, Optional, Set, Tuple


class NavItem(NamedTuple):
    sort_key: Tuple[int, ...]
    num: str
    name: str
    dest: str


dirs_mapping = {
    "solution": ("lc", 4),
    "lcof": ("lcof", 3),
    "lcof2": ("lcof2", 3),
    "lcci": ("lcci", 3),
    "lcp": ("lcp", 3),
    "lcs": ("lcs", 3),
}


def get_paths(dirs: str, m: int) -> List[str]:
    paths = []
    for root, _, files in os.walk(dirs):
        for file in files:
            file_name = os.path.join(root, file)
            if file.endswith(".md") and len(file_name.split(os.sep)) == m:
                paths.append(file_name)
    return paths


def parse_sort_key(num: str) -> Tuple[int, ...]:
    parts = []
    for part in num.split("."):
        try:
            parts.append(int(part))
        except ValueError:
            parts.append(0)
    return tuple(parts) if parts else (0,)


def parse_heading(content: str, dir_name: str) -> Tuple[str, str]:
    title = content[content.find("[") + 1 : content.find("]")]
    dot = title.find(".") if dir_name != "lcci" else title.rfind(".")
    num = (
        title[:dot]
        .replace("面试题", "")
        .replace("剑指 Offer II", "")
        .replace("LCP", "")
        .replace("LCS", "")
        .strip(" ")
        .lstrip("0")
    )
    name = (
        title[dot + 1 :]
        .replace("面试题", "")
        .replace("剑指 Offer II", "")
        .replace("LCP", "")
        .replace("LCS", "")
        .strip(" ")
        .lstrip("0")
    )
    if num.endswith("- III"):
        num = num[:-5] + ".3"
    elif num.endswith("- II"):
        num = num[:-4] + ".2"
    elif num.endswith("- I"):
        num = num[:-3] + ".1"
    num = ".".join([x.strip(" ").lstrip("0") for x in num.split(".")])
    return num, name


def range_start(num: str) -> int:
    return parse_sort_key(num)[0] // 100 * 100


def range_label(start: int) -> str:
    return f"{start:04d}–{start + 99:04d}"


def range_slug(start: int) -> str:
    return f"{start:04d}-{start + 99:04d}"


def write_range_indexes(
    items: List[NavItem], docs_root: str, target_dir: str, lang: str
) -> None:
    buckets: Dict[int, List[NavItem]] = defaultdict(list)
    for item in items:
        buckets[range_start(item.num)].append(item)

    out_dir = os.path.join(docs_root, target_dir)
    os.makedirs(out_dir, exist_ok=True)
    heading = "本段题目" if lang == "zh" else "Problems in this range"
    colon = "：" if lang == "zh" else ": "

    for start, group in buckets.items():
        group.sort(key=lambda x: x.sort_key)
        label = range_label(start)
        lines = [
            "---",
            "hide:",
            "  - toc",
            "  - feedback",
            "---",
            "",
            f"# {label}",
            "",
            f"{heading}{colon}{len(group)}",
            "",
        ]
        for item in group:
            lines.append(f"- [{item.num}. {item.name}]({item.num}.md)")
        lines.append("")
        path = os.path.join(out_dir, f"{range_slug(start)}.md")
        with open(path, "w", encoding="utf-8") as f:
            f.write("\n".join(lines))


def format_flat_nav(items: List[NavItem], indent: int = 4) -> str:
    pad = " " * indent
    return "\n".join(f"{pad}- {item.num}. {item.name}: {item.dest}" for item in items)


def format_ranged_nav(items: List[NavItem], index_label: str, indent: int = 4) -> str:
    buckets: Dict[int, List[NavItem]] = defaultdict(list)
    for item in items:
        buckets[range_start(item.num)].append(item)

    lines = []
    pad = " " * indent
    child = " " * (indent + 2)
    for start in sorted(buckets):
        group = sorted(buckets[start], key=lambda x: x.sort_key)
        dest_dir = group[0].dest.rsplit("/", 1)[0]
        lines.append(f"{pad}- {range_label(start)}:")
        lines.append(f"{child}- {index_label}: {dest_dir}/{range_slug(start)}.md")
        for item in group:
            lines.append(f"{child}- {item.num}. {item.name}: {item.dest}")
    return "\n".join(lines)


def load_only_dirs() -> Optional[Set[str]]:
    path = os.environ.get("PREVIEW_ONLY_DIRS_FILE")
    if not path:
        return None
    only: Set[str] = set()
    with open(path, encoding="utf-8") as f:
        for line in f:
            line = line.strip()
            if line and not line.startswith("#"):
                only.add(os.path.normpath(line))
    return only


def collect_items() -> Tuple[Dict[str, List[NavItem]], Dict[str, List[NavItem]]]:
    nav_cn: Dict[str, List[NavItem]] = defaultdict(list)
    nav_en: Dict[str, List[NavItem]] = defaultdict(list)
    only = load_only_dirs()

    for dir_name, (target_dir, depth) in dirs_mapping.items():
        if not os.path.isdir(dir_name):
            continue
        for path in sorted(get_paths(dir_name, depth)):
            problem_dir = os.path.normpath(os.path.dirname(path))
            if only is not None and problem_dir not in only:
                continue
            with open(path, "r", encoding="utf-8") as f:
                content = f.read()
            num, name = parse_heading(content, dir_name)
            dest = f"{target_dir}/{num}.md"
            item = NavItem(parse_sort_key(num), num, name, dest)
            is_en = "README_EN" in path
            (nav_en if is_en else nav_cn)[dir_name].append(item)
            docs_dir = os.path.join("docs-en" if is_en else "docs", target_dir)
            os.makedirs(docs_dir, exist_ok=True)
            with open(os.path.join(docs_dir, f"{num}.md"), "w", encoding="utf-8") as f:
                f.write(content)

        nav_cn[dir_name].sort(key=lambda x: x.sort_key)
        nav_en[dir_name].sort(key=lambda x: x.sort_key)

    return nav_cn, nav_en


def replace_nav(config: str, nav: str) -> str:
    if "nav:" in config:
        config = config[: config.find("nav:")]
    return config + nav


def main() -> None:
    nav_cn, nav_en = collect_items()

    write_range_indexes(nav_cn["solution"], "docs", "lc", "zh")
    write_range_indexes(nav_en["solution"], "docs-en", "lc", "en")

    def section(title: str, body: str) -> str:
        if not body.strip():
            return ""
        return f"  - {title}:\n{body}\n"

    lc = format_ranged_nav(nav_cn["solution"], "目录")
    lcci = format_flat_nav(nav_cn["lcci"])
    lcof = format_flat_nav(nav_cn["lcof"])
    lcof2 = format_flat_nav(nav_cn["lcof2"])

    nav_sections = (
        "nav:\n  - 首页: index.md\n"
        + section("LeetCode 全解", lc)
        + section("剑指 Offer", lcof)
        + section("剑指 Offer（专项突破）", lcof2)
        + section("程序员面试金典", lcci)
        + "  - 专项训练: tags.md\n  - 竞赛专区: contest.md\n"
    )

    lc_en = format_ranged_nav(nav_en["solution"], "Index")
    lcci_en = format_flat_nav(nav_en["lcci"])

    en_nav_sections = (
        "nav:\n  - Home: index.md\n"
        + section("LeetCode", lc_en)
        + section("Cracking the Coding Interview", lcci_en)
        + "  - Focused Training: tags.md\n  - Contest: contest.md\n"
    )

    with open("mkdocs.yml", "r", encoding="utf-8") as f:
        config = f.read()
    with open("mkdocs-en.yml", "r", encoding="utf-8") as f:
        en_config = f.read()

    with open("mkdocs.yml", "w", encoding="utf-8") as f:
        f.write(replace_nav(config, nav_sections))
    with open("mkdocs-en.yml", "w", encoding="utf-8") as f:
        f.write(replace_nav(en_config, en_nav_sections))


if __name__ == "__main__":
    main()
