---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3368.First%20Letter%20Capitalization/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3368. First Letter Capitalization 🔒](https://leetcode.cn/problems/first-letter-capitalization)

[English Version](/solution/3300-3399/3368.First%20Letter%20Capitalization/README_EN.md)

## 题目描述

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

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
