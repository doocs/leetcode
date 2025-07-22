---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3368.First%20Letter%20Capitalization/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3368. 首字母大写 🔒](https://leetcode.cn/problems/first-letter-capitalization)

[English Version](/solution/3300-3399/3368.First%20Letter%20Capitalization/README_EN.md)

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

<p>编写一个解决方案来通过应用以下规则来转换&nbsp;<code>content_text</code>&nbsp;列中的文本：</p>

<ul>
	<li>把每个单词的首字母变成大写</li>
	<li>其它字母保持小写</li>
	<li>保留所有现有空格</li>
</ul>

<p><b>注意：</b><code>content_text</code>&nbsp;中没有特殊字符。</p>

<p>返回结果表，同时包含原来的<em>&nbsp;<code>content_text</code>&nbsp;</em>以及将所有单词首字母变成大写的修改后文本。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>user_content 表：</p>

<pre class="example-io">
+------------+-----------------------------------+
| content_id | content_text                      |
+------------+-----------------------------------+
| 1          | hello world of Sql                |
| 2          | the QUICK brown fox               |
| 3          | data science AND machine learning |
| 4          | TOP rated programming BOOKS       |
+------------+-----------------------------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+------------+-----------------------------------+-----------------------------------+
| content_id | original_text                     | converted_text                    |
+------------+-----------------------------------+-----------------------------------+
| 1          | hello world of Sql                | Hello World Of Sql                |
| 2          | the QUICK brown fox               | The Quick Brown Fox               |
| 3          | data science AND machine learning | Data Science And Machine Learning |
| 4          | TOP rated programming BOOKS       | Top Rated Programming Books       |
+------------+-----------------------------------+-----------------------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>对于 content_id = 1:
	<ul>
		<li>每个单词的首字母都已经大写：Hello World Of Sql</li>
	</ul>
	</li>
	<li>对于 content_id = 2:
	<ul>
		<li>原来混合大小写的文本变为首字母大写：The Quick Brown Fox</li>
	</ul>
	</li>
	<li>对于 content_id = 3:
	<ul>
		<li>单词 AND 被转换为 "And"："Data Science And Machine Learning"</li>
	</ul>
	</li>
	<li>对于 content_id = 4:
	<ul>
		<li>正确处理单词 TOP rated：Top Rated</li>
		<li>将 BOOKS 从全大写改为首字母大写：Books</li>
	</ul>
	</li>
</ul>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql
WITH RECURSIVE
    capitalized_words AS (
        SELECT
            content_id,
            content_text,
            SUBSTRING_INDEX(content_text, ' ', 1) AS word,
            SUBSTRING(
                content_text,
                LENGTH(SUBSTRING_INDEX(content_text, ' ', 1)) + 2
            ) AS remaining_text,
            CONCAT(
                UPPER(LEFT(SUBSTRING_INDEX(content_text, ' ', 1), 1)),
                LOWER(SUBSTRING(SUBSTRING_INDEX(content_text, ' ', 1), 2))
            ) AS processed_word
        FROM user_content
        UNION ALL
        SELECT
            c.content_id,
            c.content_text,
            SUBSTRING_INDEX(c.remaining_text, ' ', 1),
            SUBSTRING(c.remaining_text, LENGTH(SUBSTRING_INDEX(c.remaining_text, ' ', 1)) + 2),
            CONCAT(
                c.processed_word,
                ' ',
                CONCAT(
                    UPPER(LEFT(SUBSTRING_INDEX(c.remaining_text, ' ', 1), 1)),
                    LOWER(SUBSTRING(SUBSTRING_INDEX(c.remaining_text, ' ', 1), 2))
                )
            )
        FROM capitalized_words c
        WHERE c.remaining_text != ''
    )
SELECT
    content_id,
    content_text AS original_text,
    MAX(processed_word) AS converted_text
FROM capitalized_words
GROUP BY 1, 2;
```

#### Pandas

```python
import pandas as pd


def process_text(user_content: pd.DataFrame) -> pd.DataFrame:
    user_content["converted_text"] = user_content["content_text"].apply(
        lambda text: " ".join(word.capitalize() for word in text.split(" "))
    )
    return user_content[["content_id", "content_text", "converted_text"]].rename(
        columns={"content_text": "original_text"}
    )
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
