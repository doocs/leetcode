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
}

mapping = {lang: name for name, lang in code_dict.values()}


def add_difficulty_info(markdown, page):
    difficulty = page.meta.get("difficulty")
    rating = page.meta.get("rating")
    if not difficulty and not rating:
        return markdown
    source = page.meta.get("source")
    is_cn_problem_page = (page.edit_url or "").endswith("README.md")
    images = []
    if source:
        title = "来源" if is_cn_problem_page else "Source"
        images.append(
            f'<img src="https://img.shields.io/badge/{title}-{source}-4051B5?style=flat-square">'
        )
    if difficulty:
        title = "难度" if is_cn_problem_page else "Difficulty"
        images.append(
            f'<img src="https://img.shields.io/badge/{title}-{difficulty}-4051B5?style=flat-square">'
        )
    if rating:
        title = "分数" if is_cn_problem_page else "Rating"
        images.append(
            f'<img src="https://img.shields.io/badge/{title}-{rating}-4051B5?style=flat-square">'
        )
    # 将 images 放到 <p> 标签中
    images_html = "<p>" + " ".join(images) + "</p>"
    heading_pattern = re.compile(r"(^# .+?$)", re.MULTILINE)
    match = heading_pattern.search(markdown)
    if match:
        insert_position = match.end()
        markdown = (
            markdown[:insert_position]
            + "\n\n"
            + images_html
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
        res = re.findall(r"```(.*?)\n(.*?)\n```", codes, re.S)
        result = []
        if res:
            for lang, code in res:
                name = mapping.get(lang)
                code = code or ""
                # 需要将 code 缩进 4 个空格
                code = code.replace("\n", "\n    ")
                code_snippet = f'=== "{name}"\n\n    ```{lang} linenums="1"\n    {code}\n    ```\n'
                result.append(code_snippet)
        content = content[:i] + "\n".join(result) + content[j + len(end) :]
    return content

def remove_version_switch(content):
    content = re.sub(r"\[中文文档]\((.*?)\)", "", content)
    content = re.sub(r"\[English Version]\((.*?)\)", "", content)
    return content

def is_contest_page(page):
    a = page.title == 'Contest' and page.url == 'contest/'
    b = page.title == '竞赛' and page.url == 'contest/'
    return a or b


def replace_contest_problem_link(content, page):
    if not is_contest_page(page):
        return content
    res = re.findall(r"\[(.*?)\]\((.*?)\)", content)
    for _, link in res:
        try:
            num = link.split("/")[-2].split(".")[0]
            num = int(num)
            content = content.replace(link, f"./lc/{num}.md")
        except:
            pass
    return content


@plugins.event_priority(90)
def on_page_markdown(markdown, page, config, files):
    markdown = remove_version_switch(markdown)
    markdown = replace_contest_problem_link(markdown, page)
    markdown = add_difficulty_info(markdown, page)
    markdown = modify_code_block(markdown)
    return markdown

