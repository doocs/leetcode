---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3808.Find%20Emotionally%20Consistent%20Users/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3808. Find Emotionally Consistent Users](https://leetcode.com/problems/find-emotionally-consistent-users)

[中文文档](/solution/3800-3899/3808.Find%20Emotionally%20Consistent%20Users/README.md)

## Description

<!-- description:start -->

<p>Table: <code>reactions</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| user_id      | int     |
| content_id   | int     |
| reaction     | varchar |
+--------------+---------+
(user_id, content_id) is the primary key (unique value) for this table.
Each row represents a reaction given by a user to a piece of content.
</pre>

<p>Write a solution to identify <strong>emotionally consistent users</strong> based on the following requirements:</p>

<ul>
	<li>For each user, count the total number of reactions they have given.</li>
	<li>Only include users who have reacted to <strong>at least </strong><code>5</code><strong> different content items</strong>.</li>
	<li>A user is considered <strong>emotionally consistent</strong> if <strong>at least </strong><code>60%</code> of their reactions are of the <strong>same type</strong>.</li>
</ul>

<p>Return <em>the result table ordered by</em> <code>reaction_ratio</code> <em>in <strong>descending</strong> order and then by</em> <code>user_id</code> <em>in <strong>ascending</strong> order</em>.</p>

<p><strong>Note:</strong></p>

<ul>
	<li><code>reaction_ratio</code>&nbsp;should be rounded to <code>2</code> decimal places</li>
</ul>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>reactions table:</p>

<pre class="example-io">
+---------+------------+----------+
| user_id | content_id | reaction |
+---------+------------+----------+
| 1       | 101        | like     |
| 1       | 102        | like     |
| 1       | 103        | like     |
| 1       | 104        | wow      |
| 1       | 105        | like     |
| 2       | 201        | like     |
| 2       | 202        | wow      |
| 2       | 203        | sad      |
| 2       | 204        | like     |
| 2       | 205        | wow      |
| 3       | 301        | love     |
| 3       | 302        | love     |
| 3       | 303        | love     |
| 3       | 304        | love     |
| 3       | 305        | love     |
+---------+------------+----------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+---------+-------------------+----------------+
| user_id | dominant_reaction | reaction_ratio |
+---------+-------------------+----------------+
| 3       | love              | 1.00           |
| 1       | like              | 0.80           |
+---------+-------------------+----------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>User 1</strong>:

    <ul>
    	<li>Total reactions = 5</li>
    	<li>like&nbsp;appears 4 times</li>
    	<li>reaction_ratio = 4 / 5 = 0.80</li>
    	<li>Meets the 60% consistency requirement</li>
    </ul>
    </li>
    <li><strong>User 2</strong>:
    <ul>
    	<li>Total reactions = 5</li>
    	<li>Most frequent reaction appears only 2 times</li>
    	<li>reaction_ratio = 2 / 5 = 0.40</li>
    	<li>Does not meet the consistency requirement</li>
    </ul>
    </li>
    <li><strong>User 3</strong>:
    <ul>
    	<li>Total reactions = 5</li>
    	<li>&#39;love&#39; appears 5 times</li>
    	<li>reaction_ratio = 5 / 5 = 1.00</li>
    	<li>Meets the consistency requirement</li>
    </ul>
    </li>

</ul>

<p>The Results table is ordered by reaction_ratio in descending order, then by user_id in ascending order.</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Grouping Statistics + Join Query

We first count the number of each reaction for every user and record it in a temporary table $t$. Then, based on the temporary table $t$, we calculate the maximum reaction count and total reaction count for each user, compute the reaction ratio, and filter out the users who meet the conditions, recording them in a temporary table $s$. Finally, we join the temporary tables $s$ and $t$ to find the dominant reaction for each user and sort the results as required.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            user_id,
            reaction,
            COUNT(1) cnt
        FROM reactions
        GROUP BY 1, 2
    ),
    s AS (
        SELECT
            user_id,
            MAX(cnt) mx_cnt,
            ROUND(MAX(cnt) / SUM(cnt), 2) reaction_ratio
        FROM t
        GROUP BY 1
        HAVING reaction_ratio >= 0.60 AND SUM(cnt) >= 5
    )
SELECT user_id, reaction dominant_reaction, reaction_ratio
FROM
    s
    JOIN t USING (user_id)
WHERE cnt = mx_cnt
ORDER BY 3 DESC, 1;
```

#### Pandas

```python
import pandas as pd
from decimal import Decimal, ROUND_HALF_UP


def find_emotionally_consistent_users(reactions: pd.DataFrame) -> pd.DataFrame:
    t = reactions.groupby(["user_id", "reaction"]).size().reset_index(name="cnt")

    s = (
        t.groupby("user_id")
        .agg(mx_cnt=("cnt", "max"), total_cnt=("cnt", "sum"))
        .reset_index()
    )

    s["reaction_ratio"] = (
        s["mx_cnt"]
        .div(s["total_cnt"])
        .apply(
            lambda x: float(
                Decimal(str(x)).quantize(Decimal("0.00"), rounding=ROUND_HALF_UP)
            )
        )
    )

    s = s[(s["reaction_ratio"] >= 0.60) & (s["total_cnt"] >= 5)]

    merged = pd.merge(
        s[["user_id", "mx_cnt", "reaction_ratio"]],
        t,
        left_on=["user_id", "mx_cnt"],
        right_on=["user_id", "cnt"],
    )

    result = (
        merged[["user_id", "reaction", "reaction_ratio"]]
        .rename(columns={"reaction": "dominant_reaction"})
        .sort_values(by=["reaction_ratio", "user_id"], ascending=[False, True])
        .reset_index(drop=True)
    )

    return result
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
