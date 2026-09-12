import html
import re

from mkdocs import plugins

# https://www.mkdocs.org/dev-guide/plugins/#events

code_dict = {
    "py": ("Python3", "python"),
    "java": ("Java", "java"),
    "cpp": ("C++", "cpp"),
    "go": ("Go", "go"),
    "ts": ("TypeScript", "ts"),
    "rs": ("Rust", "rust"),
    "js": ("JavaScript", "js"),
    "cs": ("C#", "cs"),
    "php": ("PHP", "php"),
    "c": ("C", "c"),
    "scala": ("Scala", "scala"),
    "swift": ("Swift", "swift"),
    "rb": ("Ruby", "rb"),
    "kt": ("Kotlin", "kotlin"),
    "dart": ("Dart", "dart"),
    "nim": ("Nim", "nim"),
    "sql": ("MySQL", "sql"),
    "sh": ("Shell", "bash"),
    "cj": ("Cangjie", "cj"),
}

mapping = {lang: name for name, lang in code_dict.values()}


def _badge(title, value):
    return (
        '<span class="lc-badge">'
        f'<span class="lc-badge__label">{html.escape(str(title))}</span>'
        f'<span class="lc-badge__value">{html.escape(str(value))}</span>'
        "</span>"
    )


def add_difficulty_info(markdown, page):
    difficulty = page.meta.get("difficulty")
    rating = page.meta.get("rating")
    if not difficulty and not rating:
        return markdown
    source = page.meta.get("source")
    is_cn_problem_page = (page.edit_url or "").endswith("README.md")
    badges = []
    if source:
        title = "来源" if is_cn_problem_page else "Source"
        badges.append(_badge(title, source))
    if difficulty:
        title = "难度" if is_cn_problem_page else "Difficulty"
        badges.append(_badge(title, difficulty))
    if rating:
        title = "分数" if is_cn_problem_page else "Rating"
        badges.append(_badge(title, rating))
    badges_html = f'<p class="lc-badges">{"".join(badges)}</p>'
    heading_pattern = re.compile(r"(^# .+?$)", re.MULTILINE)
    match = heading_pattern.search(markdown)
    if match:
        insert_position = match.end()
        markdown = (
            markdown[:insert_position]
            + "\n\n"
            + badges_html
            + "\n\n"
            + markdown[insert_position:]
        )

    return markdown


def modify_code_block(content):
    # 修改代码块
    while True:
        start = "<!-- tabs:start -->"
        end = "<!-- tabs:end -->"
        i = content.find(start)
        j = content.find(end)
        if i == -1 or j == -1:
            break
        j = content.find(end)
        codes = content[i + len(start) : j].strip()
        # 同时提取标题和代码块
        res = re.findall(r"####\s+(.*?)\n\n```(.*?)\n(.*?)\n```", codes, re.S)
        result = []
        if res:
            for title, lang, code in res:
                code = code or ""
                # 需要将 code 缩进 4 个空格
                code = code.replace("\n", "\n    ")
                code_snippet = f'=== "{title}"\n\n    ```{lang} linenums="1"\n    {code}\n    ```\n'
                result.append(code_snippet)
        content = content[:i] + "\n".join(result) + content[j + len(end) :]
    return content


def remove_version_switch(content):
    content = re.sub(r"\[中文文档]\((.*?)\)", "", content)
    content = re.sub(r"\[English Version]\((.*?)\)", "", content)
    return content


_REPO_LC_LINK = re.compile(
    r"\((/solution/\d{4}-\d{4}/(\d+)\.[^)]+/README(?:_EN)?\.md)\)"
)
_IMG_WITHOUT_SRC = re.compile(r"<img(?![^>]*\bsrc=)[^>]*/?>", re.IGNORECASE)


def is_contest_page(page):
    url = page.url or ""
    return url == "contest/" or url.endswith("contest/")


def rewrite_repo_problem_links(content, page):
    url = page.url or ""
    if is_contest_page(page):
        dest = "./lc/{num}.md"
    elif url.startswith("lc/"):
        dest = "{num}.md"
    else:
        dest = "../lc/{num}.md"

    def repl(match):
        return f"({dest.format(num=int(match.group(2)))})"

    return _REPO_LC_LINK.sub(repl, content)


def strip_images_without_src(content):
    return _IMG_WITHOUT_SRC.sub("", content)


@plugins.event_priority(90)
def on_page_markdown(markdown, page, config, files):
    markdown = remove_version_switch(markdown)
    markdown = rewrite_repo_problem_links(markdown, page)
    markdown = strip_images_without_src(markdown)
    markdown = add_difficulty_info(markdown, page)
    markdown = modify_code_block(markdown)
    return markdown
