---
comments: true
difficulty: 困难
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
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| content_id   | int     |
| content_text | varchar |
+--------------+---------+
content_id 是该表的唯一键。
每一行包含一个唯一的 ID 以及对应的文本内容。
</pre>

<p><strong>单词</strong>是指不包含空格的极大非空字符序列。</p>

<p>编写一个解决方案，按照以下规则对 <code>content_text</code> 列中的文本进行转换，并对每个单词应用这些规则：</p>

<ul>
	<li>如果单词以一个<strong>不是</strong>英文字母的字符开头，则保持整个单词<strong>不变</strong>。</li>
	<li>否则，如果单词由两个或更多非空的英文字母部分通过连字符 <code>-</code> 连接而成，则将<strong>每个部分的首字母</strong>转换为大写，并将<strong>每个部分的其余字母</strong>转换为小写。例如，<code>top-rated</code> 转换为 <code>Top-Rated</code>，<code>FR-ONT-end</code> 转换为 <code>Fr-Ont-End</code>。</li>
	<li>否则，将单词的<strong>首字母</strong>转换为大写，并将其余所有<strong>英文字母</strong>转换为小写。所有特殊字符保持不变。</li>
</ul>

<p>所有其他<strong>格式</strong>和<strong>空格</strong>必须保持<strong>不变</strong>。</p>

<p>返回<em>结果表，其中同时包含原始的 <code>content_text</code> 以及按照上述规则转换后的文本</em>。</p>

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
		<li>将每个单词的首字母大写，得到 "Hello World Of Sql"。</li>
	</ul>
	</li>
	<li>对于 content_id = 2：
	<ul>
		<li>带连字符的单词 "QUICK-brown" 转换为 "Quick-Brown"。</li>
		<li>其他单词按照普通的大小写转换规则处理。</li>
	</ul>
	</li>
	<li>对于 content_id = 3：
	<ul>
		<li>带连字符的单词 "modern-day" 转换为 "Modern-Day"。</li>
		<li>"DATA" 转换为 "Data"。</li>
	</ul>
	</li>
	<li>对于 content_id = 4：
	<ul>
		<li>"web-based" 转换为 "Web-Based"。</li>
		<li>"FRONT-end" 转换为 "Front-End"。</li>
	</ul>
	</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>content_text</code> 仅包含英文字母、空格以及字符 <code>\</code>、<code>@</code>、<code>-</code>、<code>/</code>、<code>^</code> 和 <code>,</code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 在 I 的按词首字母大写之外，连字符分隔的每一段也要单独首字母大写。
>
> 先按空格切词；词内若含 `-`，再按 `-` 切段并分别 $\textit{capitalize}$。
>
> 这样 `foo-bar` 变为 `Foo-Bar`，其余规则与 I 相同。

<!-- thinking:end -->

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
