import errno
import json
import os
import re
import time
import requests
import yaml
from typing import Optional, Tuple, List
from urllib.parse import quote, unquote

# Windows maps a brief sharing violation (Defender / indexer / editor) to EINVAL.
_TRANSIENT_WRITE_ERRNOS = {errno.EINVAL, errno.EACCES, errno.EPERM, errno.EBUSY}
# ACCESS_DENIED (5), SHARING_VIOLATION (32), LOCK_VIOLATION (33), USER_MAPPED_FILE (1224)
_TRANSIENT_WINERRORS = {5, 32, 33, 1224}


class _IndentDumper(yaml.SafeDumper):
    def increase_indent(self, flow=False, indentless=False):
        return super().increase_indent(flow, False)


def dump_yaml_metadata(metadata: dict) -> str:
    yaml_metadata = yaml.dump(
        metadata,
        Dumper=_IndentDumper,
        default_flow_style=False,
        allow_unicode=True,
        indent=4,
    )
    return f"---\n{yaml_metadata}---"


def write_text(path: str, content: str, retries: int = 6) -> bool:
    """Write UTF-8 text if changed, retrying Windows file locks. Returns True if written."""
    normalized = content.replace("\r\n", "\n")
    if os.path.exists(path):
        try:
            with open(path, "r", encoding="utf-8") as f:
                if f.read() == normalized:
                    return False
        except OSError:
            pass
    last_err = None
    for attempt in range(retries):
        try:
            with open(path, "w", encoding="utf-8", newline="\n") as f:
                f.write(normalized)
            return True
        except OSError as e:
            last_err = e
            transient = (
                e.errno in _TRANSIENT_WRITE_ERRNOS
                or getattr(e, "winerror", None) in _TRANSIENT_WINERRORS
            )
            if not transient or attempt == retries - 1:
                raise
            time.sleep(0.05 * (attempt + 1))
    raise last_err


def load_template(template_name: str) -> str:
    with open("./template.md", "r", encoding="utf-8") as f:
        content = f.read()
        start_text = f"<!-- 这里是{template_name}开始位置 -->"
        end_text = f"<!-- 这里是{template_name}结束位置 -->"
        content = re.search(f"{start_text}(.*?){end_text}", content, re.S).group(1)
        return content.strip()


def load_ratings():
    res = {}
    if os.path.exists("rating.json"):
        with open("rating.json", "r", encoding="utf-8") as f:
            ratings = json.loads(f.read())
            for item in ratings:
                res[str(item["ID"])] = item

    url = "https://zerotrac.github.io/leetcode_problem_rating/data.json"
    try:
        resp = requests.get(url)
        if resp.status_code == 200:
            ratings = resp.json()
            for item in ratings:
                res[str(item["ID"])] = item
    except Exception as e:
        print(f"Failed to fetch ratings: {e}")
    return res


rating_dict = load_ratings()


readme_cn = load_template("readme_template")
readme_en = load_template("readme_template_en")
problem_readme_cn = load_template("problem_readme_template")
problem_readme_en = load_template("problem_readme_template_en")
sql_readme_cn = load_template("sql_problem_readme_template")
sql_readme_en = load_template("sql_problem_readme_template_en")
bash_readme_cn = load_template("bash_problem_readme_template")
bash_readme_en = load_template("bash_problem_readme_template_en")
ts_readme_cn = load_template("ts_problem_readme_template")
ts_readme_en = load_template("ts_problem_readme_template_en")
contest_readme_cn = load_template("contest_readme_template")
contest_readme_en = load_template("contest_readme_template_en")
category_readme_cn = load_template("category_readme_template")
category_readme_en = load_template("category_readme_template_en")

category_dict = {"Database": "数据库"}

_CJK_RE = re.compile(r"[\u4e00-\u9fff]")

# leetcode.cn may put Chinese in topicTags.name for CN-only / CN-origin tags.
TAG_CN_TO_EN = {
    "数据库": "Database",
    "多线程": "Concurrency",
    "树形 DP": "Tree DP",
    "最大公约数": "Greatest Common Divisor",
    "欧几里得算法": "Euclidean Algorithm",
    "有向无环图": "Directed Acyclic Graph",
    "背包问题": "Knapsack",
    "Dijkstra 算法": "Dijkstra",
    "括号序列": "Parentheses",
    "质因数分解": "Prime Factorization",
    "最长上升子序列": "Longest Increasing Subsequence",
    "零和博弈": "Zero-Sum Game",
    "扩展 KMP": "Extended KMP",
    "筛法": "Sieve",
    "最近公共祖先": "Lowest Common Ancestor",
    "KMP 算法": "KMP",
    "素性测试": "Primality Test",
    "二分图": "Bipartite Graph",
    "费马小定理": "Fermat's Little Theorem",
    "0-1 背包": "0-1 Knapsack",
    "多边形": "Polygon",
    "快速排序": "Quick Sort",
    "双向搜索": "Bidirectional Search",
    "素数筛法": "Sieve of Eratosthenes",
    "最小公倍数": "Least Common Multiple",
    "网络流": "Network Flow",
    "Boyer–Moore 算法": "Boyer–Moore",
    "Floyd 判圈算法": "Floyd Cycle Detection",
    "树堆": "Treap",
    "最长公共子序列": "Longest Common Subsequence",
    "图的匹配": "Graph Matching",
    "抽屉原理": "Pigeonhole Principle",
    "完全背包": "Unbounded Knapsack",
    "无偏博弈": "Impartial Game",
    "A* 搜索": "A* Search",
    "图的着色": "Graph Coloring",
    "Kosaraju 算法": "Kosaraju",
    "Tarjan 强连通分量算法": "Tarjan",
    "SSP 算法": "SSP",
    "冒泡排序": "Bubble Sort",
    "摩尔投票算法": "Boyer-Moore Voting",
    "Nim 游戏": "Nim Game",
    "AC 自动机": "Aho-Corasick",
    "中途相遇": "Meet in the Middle",
    "匈牙利算法": "Hungarian Algorithm",
    "最小费用流": "Min-Cost Flow",
    "Manacher 算法": "Manacher",
    "X 算法": "Algorithm X",
    "线性代数": "Linear Algebra",
    "欧拉路径": "Eulerian Path",
    "容斥原理": "Inclusion-Exclusion",
    "Kruskal 算法": "Kruskal",
    "Prim 算法": "Prim",
    "Boruvka 算法": "Borůvka",
    "Edmonds–Karp 算法": "Edmonds–Karp",
    "Dinic 算法": "Dinic",
    "MPM 算法": "MPM",
    "Push-Relabel 算法": "Push-Relabel",
    "区间最值查询": "Range Query",
    "半欧拉图": "Semi-Eulerian Graph",
    "扩展欧几里得算法": "Extended Euclidean Algorithm",
    "裴蜀定理": "Bézout's Identity",
    "Floyd 算法": "Floyd–Warshall",
    "Bellman–Ford 算法": "Bellman–Ford",
    "平面最近点对": "Closest Pair of Points",
    "三分查找": "Ternary Search",
    "哈密顿通路": "Hamiltonian Path",
    "最大流": "Max Flow",
    "最大匹配": "Maximum Matching",
    "完美匹配": "Perfect Matching",
    "混合背包": "Mixed Knapsack",
    "多重背包": "Bounded Knapsack",
    "锦标赛排序": "Tournament Sort",
    "牛顿迭代法": "Newton's Method",
    "欧拉函数": "Euler's Totient Function",
    "欧拉定理": "Euler's Theorem",
    "凸包": "Convex Hull",
    "欧拉图": "Eulerian Graph",
    "Tim 排序": "Timsort",
    "最小表示法": "Smallest Representation",
    "平面图": "Planar Graph",
    "三角剖分": "Triangulation",
    "可持久化数据结构": "Persistent Data Structure",
    "Lyndon 分解": "Lyndon Factorization",
    "桥": "Bridge",
    "最小割": "Min Cut",
    "割点": "Articulation Point",
    "最小圆覆盖": "Smallest Enclosing Circle",
    "k 短路": "k-Shortest Paths",
}


def contains_cjk(text: str) -> bool:
    return bool(text and _CJK_RE.search(text))


def localize_tag_en(tag: str):
    """Map a CN-origin tag to English, or None if it should be dropped."""
    if not tag:
        return None
    if not contains_cjk(tag):
        return tag
    return TAG_CN_TO_EN.get(tag)


def normalize_tags_en(tags: List[str]) -> List[str]:
    """Drop or translate CJK entries so English tag lists stay English."""
    out, seen = [], set()
    for tag in tags or []:
        en = localize_tag_en(tag)
        if en and en not in seen:
            seen.add(en)
            out.append(en)
    return out


def format_tag_column(tags: List[str]) -> str:
    col = ",".join(f"`{tag}`" for tag in tags or [])
    return "" if (col == "None" or not col) else col


def apply_stored_tag_locale(item: dict) -> bool:
    """Keep tags_en / the EN table cell in English. Return True if changed."""
    tags_en = normalize_tags_en(item.get("tags_en") or [])
    changed = tags_en != (item.get("tags_en") or [])
    if changed:
        item["tags_en"] = tags_en
    row = item.get("md_table_row_en")
    if row and len(row) > 2:
        col3 = format_tag_column(tags_en)
        if row[2] != col3:
            row[2] = col3
            changed = True
    return changed


def split_topic_tags(topic_tags: List[dict]) -> Tuple[List[str], List[str]]:
    """Split leetcode.cn topicTags into English / Chinese lists."""
    tags_en, tags_cn = [], []
    seen_en, seen_cn = set(), set()
    for tag in topic_tags or []:
        name = (tag.get("name") or "").strip()
        translated = (tag.get("translatedName") or "").strip()

        cn = ""
        if contains_cjk(translated):
            cn = translated
        elif contains_cjk(name):
            cn = name
        elif translated:
            cn = translated
        if cn and cn not in seen_cn:
            seen_cn.add(cn)
            tags_cn.append(cn)

        en = ""
        if name and not contains_cjk(name):
            en = name
        elif name in TAG_CN_TO_EN:
            en = TAG_CN_TO_EN[name]
        elif translated and not contains_cjk(translated):
            en = translated
        if en and en not in seen_en:
            seen_en.add(en)
            tags_en.append(en)
    return tags_en, tags_cn


def load_cookies() -> Tuple[str, str]:
    cookie_cn, cookie_en = "", ""
    env_file = "./.env"
    if not os.path.exists(env_file):
        return cookie_cn, cookie_en
    with open(env_file, "r") as f:
        lines = f.readlines()
        for line in lines:
            if line.startswith("COOKIE_CN"):
                parts = line.split("=")[1:]
                cookie_cn = "=".join(parts).strip().strip('"')
                continue

            if line.startswith("COOKIE_EN"):
                parts = line.split("=")[1:]
                cookie_en = "=".join(parts).strip().strip('"')
                continue
    return cookie_cn, cookie_en


def load_refresh_config() -> bool:
    env_file = "./.env"
    if not os.path.exists(env_file):
        return False
    with open(env_file, "r") as f:
        lines = f.readlines()
        for line in lines:
            if line.startswith("REFRESH"):
                parts = line.split("=")[1:]
                return "=".join(parts).strip().strip('"') == "True"
    return False


def load_result() -> List[dict]:
    result_file = "./result.json"
    if not os.path.exists(result_file):
        return []
    with open(result_file, "r", encoding="utf-8") as f:
        res = f.read()
        items = json.loads(res)
        for item in items:
            apply_stored_tag_locale(item)
        return items


def load_contest_result() -> List[dict]:
    contest_result_file = "./contest.json"
    if not os.path.exists(contest_result_file):
        return []
    with open(contest_result_file, "r", encoding="utf-8") as f:
        res = f.read()
        if res:
            return json.loads(res)
        return []


def save_result(data: List[dict]):
    write_text("./result.json", json.dumps(data))


def save_contest_result(data: List[dict]):
    write_text("./contest.json", json.dumps(data))


def select_templates(category):
    if category == "Shell":
        return [bash_readme_cn, bash_readme_en]
    if category == "Database":
        return [sql_readme_cn, sql_readme_en]
    if category == "JavaScript" or category == "TypeScript":
        return [ts_readme_cn, ts_readme_en]
    return [problem_readme_cn, problem_readme_en]


_IMAGE_SRC_RE = re.compile('src="(.*?)"')
_PRE_OR_CODE_RE = re.compile(r"(<(?:pre|code)\b[^>]*>.*?</(?:pre|code)>)", re.S | re.I)


def _normalize_description_for_compare(text: str) -> str:
    """Normalize formatting-insensitive whitespace while preserving `<pre>`/`<code>` blocks."""
    parts = _PRE_OR_CODE_RE.split(text)
    for i in range(0, len(parts), 2):
        parts[i] = re.sub(r"\s+", " ", parts[i])
    for i in range(1, len(parts), 2):
        lines = [
            re.sub(r"// +", "// ", re.sub(r" +(?=//)", " ", line.strip()))
            for line in parts[i].splitlines()
            if line.strip()
        ]
        parts[i] = "\n".join(lines)
    return "".join(parts)


def _extract_md_table_rows(text: str) -> List[List[str]]:
    rows = []
    in_table = False
    for line in text.splitlines():
        if line.startswith("| ---"):
            in_table = True
            continue
        if in_table:
            if not line.startswith("|"):
                break
            rows.append([c.strip() for c in line.strip().strip("|").split("|")])
    return rows


def _replace_cdn_images(text: str, relative_path_cn: str) -> str:
    prefix = "https://fastly.jsdelivr.net/gh/doocs/leetcode@main" + str(
        relative_path_cn
    ).replace("README.md", "images/")
    for url in _IMAGE_SRC_RE.findall(text) or []:
        image_name = (
            os.path.basename(url).replace(".PNG", ".png").replace(".JPG", ".jpg")
        )
        text = text.replace(url, prefix + image_name)
    return text


def _preserve_formatted_description(orig_text: str, raw_desc: str) -> str:
    start_tag = "<!-- description:start -->"
    end_tag = "<!-- description:end -->"
    if start_tag in orig_text and end_tag in orig_text:
        orig_desc = orig_text[
            orig_text.index(start_tag) + len(start_tag) : orig_text.index(end_tag)
        ].strip()
        if _normalize_description_for_compare(
            orig_desc
        ) == _normalize_description_for_compare(raw_desc):
            return orig_desc
    return raw_desc


def _build_metadata_section(
    tags: List[str], category: str, difficulty_str: str, rating, source: str
) -> str:
    cat = category.title() if category and category[0].islower() else category
    metadata = {
        "tags": tags or [cat],
        "difficulty": difficulty_str,
        "rating": rating,
        "comments": True,
        "source": source,
    }
    if (not tags and not cat) or metadata["tags"] == ["Algorithms"]:
        metadata.pop("tags")
    if not rating:
        metadata.pop("rating")
    if not source:
        metadata.pop("source")
    return dump_yaml_metadata(metadata)


def _render_problem_readmes(
    item: dict, orig_cn: Optional[str] = None, orig_en: Optional[str] = None
) -> Tuple[str, str]:
    """Render CN and EN problem README contents, preserving existing solutions."""
    front_id = str(item["frontend_question_id"])
    paid_only = " 🔒" if item["paid_only"] else ""
    category = item["category"]
    tpl_cn, tpl_en = select_templates(category)

    rating_item = rating_dict.get(front_id)
    rating = int(rating_item.get("Rating", 0)) if rating_item else ""
    source_cn = (
        f'{rating_item.get("ContestID_zh")} {rating_item.get("ProblemIndex")}'
        if rating_item
        else ""
    )
    source_en = (
        f'{rating_item.get("ContestID_en")} {rating_item.get("ProblemIndex")}'
        if rating_item
        else ""
    )

    cat_cn = category_dict.get(category, category)
    meta_cn = _build_metadata_section(
        item["tags_cn"], cat_cn, item["difficulty_cn"], rating, source_cn
    )
    tags_en = normalize_tags_en(item.get("tags_en") or [])
    item["tags_en"] = tags_en
    meta_en = _build_metadata_section(
        tags_en, category, item["difficulty_en"], rating, source_en
    )

    raw_desc_cn = _replace_cdn_images(
        (item["content_cn"] or "")
        .replace("\r\n", "\n")
        .replace("leetcode-cn.com", "leetcode.cn")
        .strip(),
        item["relative_path_cn"],
    )
    raw_desc_en = _replace_cdn_images(
        (item["content_en"] or "").replace("\r\n", "\n").strip(),
        item["relative_path_cn"],
    )

    if orig_cn is not None:
        orig_cn_norm = _replace_cdn_images(
            orig_cn.replace("leetcode-cn.com", "leetcode.cn"),
            item["relative_path_cn"],
        )
        desc_cn = _preserve_formatted_description(orig_cn_norm, raw_desc_cn)
    else:
        orig_cn_norm = None
        desc_cn = raw_desc_cn

    if orig_en is not None:
        orig_en_norm = _replace_cdn_images(orig_en, item["relative_path_cn"])
        desc_en = _preserve_formatted_description(orig_en_norm, raw_desc_en)
    else:
        orig_en_norm = None
        desc_en = raw_desc_en

    title_cn = re.sub(r" +", " ", item["title_cn"].strip()) + paid_only
    title_en = re.sub(r" +", " ", item["title_en"].strip()) + paid_only

    rendered_cn = tpl_cn.format(
        meta_cn,
        int(front_id),
        title_cn,
        item["url_cn"],
        item["relative_path_en"],
        desc_cn,
    )
    rendered_en = tpl_en.format(
        meta_en,
        int(front_id),
        title_en,
        item["url_en"],
        item["relative_path_cn"],
        desc_en,
    )

    if orig_cn_norm is not None:
        rendered_cn = (
            rendered_cn[: rendered_cn.index("## 解法")]
            + orig_cn_norm[orig_cn_norm.index("## 解法") :]
        )
    if orig_en_norm is not None:
        rendered_en = (
            rendered_en[: rendered_en.index("## Solutions")]
            + orig_en_norm[orig_en_norm.index("## Solutions") :]
        )
    return rendered_cn, rendered_en


def _build_md_table(rows: List[List[str]], header: str) -> Tuple[str, List[List[str]]]:
    items = []
    normalized_rows = []
    for row in sorted(rows, key=lambda x: x[0]):
        normalized_rows.append([str(c).strip() for c in row[:5]])
        items.append(
            f"\n|  {row[0]}  |  {row[1]}  |  {row[2]}  |  {row[3]}  |  {row[4]}  |"
        )
    return header + "".join(items), normalized_rows


def generate_readme(result) -> List[str]:
    written: List[str] = []
    sub_folders = sorted(
        {item["sub_folder"] for item in result if item.get("sub_folder")}
    )
    links = "\n".join(f"- [{sf}](/solution/{sf})" for sf in sub_folders)
    if write_text("./README.md", readme_cn.format(links) + "\n"):
        written.append(os.path.abspath("./README.md"))
    if write_text("./README_EN.md", readme_en.format(links) + "\n"):
        written.append(os.path.abspath("./README_EN.md"))
    return written


def generate_question_readme(result) -> List[str]:
    written: List[str] = []
    for item in result:
        if not item["content_cn"] and not item["content_en"]:
            print(f"Skip {item['frontend_question_id']} {item['title_cn']}")
            continue
        path = (
            f'./{item["sub_folder"]}/{item["frontend_question_id"]}.{item["title_en"]}'
        ).replace(":", " ")
        if os.path.isdir(path):
            continue
        os.makedirs(path)

        cn_content, en_content = _render_problem_readmes(item)
        path_cn = f"{path}/README.md"
        path_en = f"{path}/README_EN.md"
        if write_text(path_cn, cn_content):
            written.append(os.path.abspath(path_cn))
        if write_text(path_en, en_content):
            written.append(os.path.abspath(path_en))
    return written


def generate_category_summary(result, category=""):
    """generate category summary files"""
    summary_cn = (
        "- " + category_dict.get(category, category) + "专项练习\n\n"
        if category
        else ""
    )
    summary_en = "- " + category + " Practice\n\n" if category else ""
    category = category.lower() if category else ""
    sub_category = category + "-" if category else ""
    m = {int(item["frontend_question_id"]): item for item in result}
    for file in sorted(os.listdir("./"), key=lambda x: x.lower()):
        if os.path.isdir("./" + file) and file != "__pycache__":
            for sub in sorted(os.listdir("./" + file), key=lambda x: x.lower()):
                sub = sub.replace("`", " ")
                enc = quote(sub)
                if not sub[:4].isdigit():
                    continue
                data = m.get(int(sub[:4]))
                if not data or (category and data["category"].lower() != category):
                    continue
                sub_cn = sub[:5] + data["title_cn"] if data else sub
                summary_cn += (
                    f"  - [{sub_cn}](/{sub_category}solution/{file}/{enc}/README.md)\n"
                )
                summary_en += (
                    f"  - [{sub}](/{sub_category}solution/{file}/{enc}/README_EN.md)\n"
                )

    write_text(f"./{sub_category}summary.md", summary_cn)
    write_text(f"./{sub_category}summary_en.md", summary_en)


def generate_category_readme(result, category="") -> List[str]:
    written: List[str] = []
    filtered = [item for item in result if not category or item["category"] == category]
    table_cn, rows_cn = _build_md_table(
        [item["md_table_row_cn"] for item in filtered],
        "\n|  题号  |  题解  |  标签  |  难度  | 备注 |\n| --- | --- | --- | --- | --- |",
    )
    table_en, rows_en = _build_md_table(
        [item["md_table_row_en"] for item in filtered],
        "\n|  #  |  Solution  |  Tags  |  Difficulty  |  Remark |\n| --- | --- | --- | --- | --- |",
    )

    path_prefix = category.upper() + "_" if category else ""
    for path, rows, content in (
        (
            f"./{path_prefix}README.md",
            rows_cn,
            category_readme_cn.format(
                category_dict.get(category, category), category.upper(), table_cn
            ),
        ),
        (
            f"./{path_prefix}README_EN.md",
            rows_en,
            category_readme_en.format(category, category.upper(), table_en),
        ),
    ):
        need_write = True
        if os.path.exists(path):
            try:
                with open(path, "r", encoding="utf-8") as f:
                    need_write = _extract_md_table_rows(f.read()) != rows
            except OSError:
                pass
        if need_write and write_text(path, content):
            written.append(os.path.abspath(path))
    return written


def refresh(result) -> List[str]:
    """update problems"""
    written: List[str] = []
    for question in result:
        print(question["frontend_question_id"])
        path_cn = unquote(str(question["relative_path_cn"]).replace("/solution", "."))
        path_en = unquote(str(question["relative_path_en"]).replace("/solution", "."))

        try:
            with open(path_cn, "r", encoding="utf-8") as f1:
                orig_cn = f1.read()
        except Exception as e:
            print(f"Failed to open {path_cn}: {e}")
            continue
        try:
            with open(path_en, "r", encoding="utf-8") as f2:
                orig_en = f2.read()
        except Exception as e:
            print(f"Failed to open {path_en}: {e}")
            continue

        cn_content, en_content = _render_problem_readmes(question, orig_cn, orig_en)

        if cn_content != orig_cn:
            try:
                if write_text(path_cn, cn_content):
                    written.append(os.path.abspath(path_cn))
            except OSError as e:
                print(f"Failed to write {path_cn}: {e}")
                continue

        if en_content != orig_en:
            try:
                if write_text(path_en, en_content):
                    written.append(os.path.abspath(path_en))
            except OSError as e:
                print(f"Failed to write {path_en}: {e}")
                continue
    return written


def generate_contest_readme(result: List) -> List[str]:
    written: List[str] = []
    result.sort(key=lambda x: -x[0])
    content_cn = "\n\n".join(c[1] for c in result)
    content_en = "\n\n".join(c[2] for c in result)
    metadata_section = "---\ncomments: true\n---"
    content_cn = contest_readme_cn.format(metadata_section, content_cn)
    if write_text("./CONTEST_README.md", content_cn):
        written.append(os.path.abspath("./CONTEST_README.md"))
    content_en = contest_readme_en.format(metadata_section, content_en)
    if write_text("./CONTEST_README_EN.md", content_en):
        written.append(os.path.abspath("./CONTEST_README_EN.md"))
    return written
