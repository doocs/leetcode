---
comments: true
difficulty: Hard
tags:
    - Database
---

<!-- problem:start -->

# [3374. First Letter Capitalization II](https://leetcode.com/problems/first-letter-capitalization-ii)

[中文文档](/solution/3300-3399/3374.First%20Letter%20Capitalization%20II/README.md)

## Description

<!-- description:start -->

<p>Table: <code>user_content</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| content_id   | int     |
| content_text | varchar |
+--------------+---------+
content_id is the unique key for this table.
Each row contains a unique ID and the corresponding text content.
</pre>

<p>A <strong>word</strong> is a maximal non-empty sequence of characters that does not contain a space.</p>

<p>Write a solution to transform the text in the <code>content_text</code> column by applying the following rules to each word:</p>

<ul>
	<li>If the word starts with a character that is <strong>not</strong> an English letter, leave the entire word <strong>unchanged</strong>.</li>
	<li>Otherwise, if the word consists of two or more non-empty parts of English letters connected by hyphens <code>-</code>, convert the <strong>first letter of each part</strong> to uppercase and the <strong>remaining letters of each part</strong> to lowercase. For example, <code>top-rated</code> becomes <code>Top-Rated</code> and <code>FR-ONT-end</code> becomes <code>Fr-Ont-End</code>.</li>
	<li>Otherwise, convert the <strong>first letter</strong> of the word to uppercase and all <strong>remaining English letters</strong> to lowercase. Any special characters remain unchanged.</li>
</ul>

<p>All other <strong>formatting</strong> and <strong>spacing</strong> must remain <strong>unchanged</strong>.</p>

<p>Return <em>the result table that includes both the original <code>content_text</code> and the modified text following the above rules</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>user_content table:</p>

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

<p><strong>Output:</strong></p>

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

<p><strong>Explanation:</strong></p>

<ul>
	<li>For content_id = 1:
	<ul>
		<li>Each word&#39;s first letter is capitalized: &quot;Hello World Of Sql&quot;.</li>
	</ul>
	</li>
	<li>For content_id = 2:
	<ul>
		<li>The hyphenated word &quot;QUICK-brown&quot; becomes &quot;Quick-Brown&quot;.</li>
		<li>Other words follow the normal capitalization rules.</li>
	</ul>
	</li>
	<li>For content_id = 3:
	<ul>
		<li>The hyphenated word &quot;modern-day&quot; becomes &quot;Modern-Day&quot;.</li>
		<li>&quot;DATA&quot; is converted to &quot;Data&quot;.</li>
	</ul>
	</li>
	<li>For content_id = 4:
	<ul>
		<li>&quot;web-based&quot; becomes &quot;Web-Based&quot;.</li>
		<li>&quot;FRONT-end&quot; becomes &quot;Front-End&quot;.</li>
	</ul>
	</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>content_text</code> contains only English letters, spaces, and the characters <code>\</code>, <code>@</code>, <code>-</code>, <code>/</code>, <code>^</code>, and <code>,</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> Besides capitalizing words as in part I, each hyphen-separated piece must be capitalized on its own.
>
> Split on spaces; if a token contains `-`, split again and $\textit{capitalize}$ each piece.
>
> Thus `foo-bar` becomes `Foo-Bar`, and the rest follows part I.

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
