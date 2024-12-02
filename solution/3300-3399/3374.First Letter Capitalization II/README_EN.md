---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3374.First%20Letter%20Capitalization%20II/README_EN.md
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
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| content_id  | int     |
| content_text| varchar |
+-------------+---------+
content_id is the unique key for this table.
Each row contains a unique ID and the corresponding text content.
</pre>

<p>Write a solution to transform the text in the <code>content_text</code> column by applying the following rules:</p>

<ul>
	<li>Convert the <strong>first letter</strong> of each word to <strong>uppercase</strong> and the <strong>remaining</strong> letters to <strong>lowercase</strong></li>
	<li>Special handling for words containing special characters:
	<ul>
		<li>For words connected with a hyphen <code>-</code>, <strong>both parts</strong> should be <strong>capitalized</strong> (<strong>e.g.</strong>, top-rated&nbsp;&rarr; Top-Rated)</li>
	</ul>
	</li>
	<li>All other <strong>formatting</strong> and <strong>spacing</strong> should remain <strong>unchanged</strong></li>
</ul>

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
		<li>Each word&#39;s first letter is capitalized: &quot;Hello World Of Sql&quot;</li>
	</ul>
	</li>
	<li>For content_id = 2:
	<ul>
		<li>Contains the hyphenated word &quot;QUICK-brown&quot; which becomes &quot;Quick-Brown&quot;</li>
		<li>Other words follow normal capitalization rules</li>
	</ul>
	</li>
	<li>For content_id = 3:
	<ul>
		<li>Hyphenated word &quot;modern-day&quot; becomes &quot;Modern-Day&quot;</li>
		<li>&quot;DATA&quot; is converted to &quot;Data&quot;</li>
	</ul>
	</li>
	<li>For content_id = 4:
	<ul>
		<li>Contains two hyphenated words: &quot;web-based&quot; &rarr; &quot;Web-Based&quot;</li>
		<li>And &quot;FRONT-end&quot; &rarr; &quot;Front-End&quot;</li>
	</ul>
	</li>
</ul>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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
