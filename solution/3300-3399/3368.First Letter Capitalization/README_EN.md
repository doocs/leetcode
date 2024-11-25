---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3368.First%20Letter%20Capitalization/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3368. First Letter Capitalization 🔒](https://leetcode.com/problems/first-letter-capitalization)

[中文文档](/solution/3300-3399/3368.First%20Letter%20Capitalization/README.md)

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
	<li>Convert the first letter of each word to uppercase</li>
	<li>Keep all other letters in lowercase</li>
	<li>Preserve all existing spaces</li>
</ul>

<p><strong>Note</strong>: There will be no special character in <code>content_text</code>.</p>

<p>Return <em>the result table that includes both the original <code>content_text</code> and the modified text where each word starts with a capital letter</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>user_content table:</p>

<pre class="example-io">
+------------+-----------------------------------+
| content_id | content_text                      |
+------------+-----------------------------------+
| 1          | hello world of SQL                |
| 2          | the QUICK brown fox               |
| 3          | data science AND machine learning |
| 4          | TOP rated programming BOOKS       |
+------------+-----------------------------------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+------------+-----------------------------------+-----------------------------------+
| content_id | original_text                     | converted_text                    |
+------------+-----------------------------------+-----------------------------------+
| 1          | hello world of SQL                | Hello World Of SQL                |
| 2          | the QUICK brown fox               | The Quick Brown Fox               |
| 3          | data science AND machine learning | Data Science And Machine Learning |
| 4          | TOP rated programming BOOKS       | Top Rated Programming Books       |
+------------+-----------------------------------+-----------------------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For content_id = 1:
	<ul>
		<li>Each word&#39;s first letter is capitalized: Hello World Of SQL</li>
	</ul>
	</li>
	<li>For content_id = 2:
	<ul>
		<li>Original mixed-case text is transformed to title case: The Quick Brown Fox</li>
	</ul>
	</li>
	<li>For content_id = 3:
	<ul>
		<li>The word AND&nbsp;is converted to &quot;And&quot;: &quot;Data Science And Machine Learning&quot;</li>
	</ul>
	</li>
	<li>For content_id = 4:
	<ul>
		<li>Handles&nbsp;word TOP rated&nbsp;correctly: Top Rated</li>
		<li>Converts BOOKS&nbsp;from all caps to title case: Books</li>
	</ul>
	</li>
</ul>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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
