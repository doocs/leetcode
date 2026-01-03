---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3793.Find%20Users%20with%20High%20Token%20Usage/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3793. Find Users with High Token Usage](https://leetcode.com/problems/find-users-with-high-token-usage)

[中文文档](/solution/3700-3799/3793.Find%20Users%20with%20High%20Token%20Usage/README.md)

## Description

<!-- description:start -->

<p>Table: <code>prompts</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| prompt      | varchar |
| tokens      | int     |
+-------------+---------+
(user_id, prompt) is the primary key (unique value) for this table.
Each row represents a prompt submitted by a user to an AI system along with the number of tokens consumed.
</pre>

<p>Write a solution to analyze <strong>AI prompt usage patterns</strong> based on the following requirements:</p>

<ul>
	<li>For each user, calculate the <strong>total number of prompts</strong> they have submitted.</li>
	<li>For each user, calculate the <strong>average tokens used per prompt </strong>(Rounded to <code>2</code> decimal places).</li>
	<li>Only include users who have submitted <strong>at least </strong><code>3</code><strong> prompts</strong>.</li>
	<li>Only include users who have submitted <strong>at least one prompt</strong> with <code>tokens</code> <strong>greater than</strong> their own average token usage.</li>
</ul>

<p>Return <em>the result table ordered by <strong>average tokens</strong> in <strong>descending</strong> order, and then by <code>user_id</code> in <strong>ascending</strong> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>prompts table:</p>

<pre class="example-io">
+---------+--------------------------+--------+
| user_id | prompt                   | tokens |
+---------+--------------------------+--------+
| 1       | Write a blog outline     | 120    |
| 1       | Generate SQL query       | 80     |
| 1       | Summarize an article     | 200    |
| 2       | Create resume bullet     | 60     |
| 2       | Improve LinkedIn bio     | 70     |
| 3       | Explain neural networks  | 300    |
| 3       | Generate interview Q&amp;A   | 250    |
| 3       | Write cover letter       | 180    |
| 3       | Optimize Python code     | 220    |
+---------+--------------------------+--------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+---------+---------------+------------+
| user_id | prompt_count  | avg_tokens |
+---------+---------------+------------+
| 3       | 4             | 237.5      |
| 1       | 3             | 133.33     |
+---------+---------------+------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>User 1</strong>:

    <ul>
    	<li>Total prompts = 3</li>
    	<li>Average tokens = (120 + 80 + 200) / 3 = 133.33</li>
    	<li>Has a prompt with 200 tokens, which is greater than the average</li>
    	<li>Included in the result</li>
    </ul>
    </li>
    <li><strong>User 2</strong>:
    <ul>
    	<li>Total prompts = 2 (less than the required minimum)</li>
    	<li>Excluded from the result</li>
    </ul>
    </li>
    <li><strong>User 3</strong>:
    <ul>
    	<li>Total prompts = 4</li>
    	<li>Average tokens = (300 + 250 + 180 + 220) / 4 = 237.5</li>
    	<li>Has prompts with 300 and 250 tokens, both greater than the average</li>
    	<li>Included in the result</li>
    </ul>
    </li>

</ul>

<p>The Results table is ordered by avg_tokens in descending order, then by user_id in ascending order</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Group By Statistics

We first group the prompts by `user_id` and calculate for each user the total number of prompts `prompt_count`, the average tokens `avg_tokens`, and the maximum tokens `max_tokens`. Then we filter users who meet the criteria, i.e., those who have submitted at least 3 prompts and have at least one prompt with tokens greater than their average token usage. Finally, we sort the results by `avg_tokens` in descending order and by `user_id` in ascending order.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    user_id,
    COUNT(1) AS prompt_count,
    ROUND(AVG(tokens), 2) AS avg_tokens
FROM prompts
GROUP BY user_id
HAVING prompt_count >= 3 AND MAX(tokens) > avg_tokens
ORDER BY avg_tokens DESC, user_id;
```

#### Pandas

```python
import pandas as pd


def find_users_with_high_tokens(prompts: pd.DataFrame) -> pd.DataFrame:
    df = prompts.groupby("user_id", as_index=False).agg(
        prompt_count=("user_id", "size"),
        avg_tokens=("tokens", "mean"),
        max_tokens=("tokens", "max"),
    )

    df["avg_tokens"] = df["avg_tokens"].round(2)

    df = df[(df["prompt_count"] >= 3) & (df["max_tokens"] > df["avg_tokens"])]

    df = (
        df.sort_values(["avg_tokens", "user_id"], ascending=[False, True])
        .loc[:, ["user_id", "prompt_count", "avg_tokens"]]
        .reset_index(drop=True)
    )

    return df
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
