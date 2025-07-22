---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3374.First%20Letter%20Capitalization%20II/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3374. 首字母大写 II](https://leetcode.cn/problems/first-letter-capitalization-ii)

[English Version](/solution/3300-3399/3374.First%20Letter%20Capitalization%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>user_content</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| content_id  | int     |
| content_text| varchar |
+-------------+---------+
content_id 是这张表的唯一主键。
每一行包含一个不同的 ID 以及对应的文本内容。
</pre>

<p>编写一个解决方案来根据下面的规则来转换&nbsp;<code>content_text</code>&nbsp;列中的文本：</p>

<ul>
	<li>将每个单词的 <strong>第一个字母</strong>&nbsp;转换为 <strong>大写</strong>，其余字母 <strong>保持小写</strong>。</li>
	<li>特殊处理包含特殊字符的单词：
	<ul>
		<li>对于用短横&nbsp;<code>-</code>&nbsp;连接的词语，<strong>两个部份</strong>&nbsp;都应该&nbsp;<strong>大写</strong>（<strong>例如</strong>，top-rated&nbsp;→ Top-Rated）</li>
	</ul>
	</li>
	<li>所有其他 <strong>格式</strong> 和 <strong>空格</strong> 应保持 <strong>不变</strong></li>
</ul>

<p>返回结果表同时包含原始的&nbsp;<code>content_text</code> 以及根据上述规则修改后的文本。</p>

<p>结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>user_content 表：</p>

<pre class="example-io">
+------------+---------------------------------+
| content_id | content_text                    |
+------------+---------------------------------+
| 1          | hello world of SQL              |
| 2          | the QUICK-brown fox             |
| 3          | modern-day DATA science         |
| 4          | web-based FRONT-end development |
+------------+---------------------------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+------------+---------------------------------+---------------------------------+
| content_id | original_text                   | converted_text                  |
+------------+---------------------------------+---------------------------------+
| 1          | hello world of SQL              | Hello World Of Sql              |
| 2          | the QUICK-brown fox             | The Quick-Brown Fox             |
| 3          | modern-day DATA science         | Modern-Day Data Science         |
| 4          | web-based FRONT-end development | Web-Based Front-End Development |
+------------+---------------------------------+---------------------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>对于 content_id = 1：
	<ul>
		<li>每个单词的首字母都是大写的："Hello World Of Sql"</li>
	</ul>
	</li>
	<li>对于 content_id = 2：
	<ul>
		<li>包含的连字符词 "QUICK-brown" 变为 "Quick-Brown"</li>
		<li>其它单词遵循普通的首字母大写规则</li>
	</ul>
	</li>
	<li>对于 content_id = 3：
	<ul>
		<li>连字符词 "modern-day" 变为 "Modern-Day"</li>
		<li>"DATA" 转换为 "Data"</li>
	</ul>
	</li>
	<li>对于 content_id = 4：
	<ul>
		<li>包含两个连字符词："web-based" → "Web-Based"</li>
		<li>以及 "FRONT-end" → "Front-End"</li>
	</ul>
	</li>
</ul>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Pandas

```python
import pandas as pd


def capitalize_content(user_content: pd.DataFrame) -> pd.DataFrame:
    def convert_text(text: str) -> str:
        return " ".join(
            (
                "-".join([part.capitalize() for part in word.split("-")])
                if "-" in word
                else word.capitalize()
            )
            for word in text.split(" ")
        )

    user_content["converted_text"] = user_content["content_text"].apply(convert_text)
    return user_content.rename(columns={"content_text": "original_text"})[
        ["content_id", "original_text", "converted_text"]
    ]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
