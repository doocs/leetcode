---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0180.Consecutive%20Numbers/README_EN.md
tags:
    - Database
---

# [180. Consecutive Numbers](https://leetcode.com/problems/consecutive-numbers)

[中文文档](/solution/0100-0199/0180.Consecutive%20Numbers/README.md)

## Description

<p>Table: <code>Logs</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| num         | varchar |
+-------------+---------+
In SQL, id is the primary key for this table.
id is an autoincrement column.
</pre>

<p>&nbsp;</p>

<p>Find all numbers that appear at least three times consecutively.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Logs table:
+----+-----+
| id | num |
+----+-----+
| 1  | 1   |
| 2  | 1   |
| 3  | 1   |
| 4  | 2   |
| 5  | 1   |
| 6  | 2   |
| 7  | 2   |
+----+-----+
<strong>Output:</strong> 
+-----------------+
| ConsecutiveNums |
+-----------------+
| 1               |
+-----------------+
<strong>Explanation:</strong> 1 is the only number that appears consecutively for at least three times.
</pre>

## Solutions

### Solution 1: Two Joins

We can use two joins to solve this problem.

First, we perform a self-join with the condition `l1.num = l2.num` and `l1.id = l2.id - 1`, so that we can find all numbers that appear at least twice in a row. Then, we perform another self-join with the condition `l2.num = l3.num` and `l2.id = l3.id - 1`, so that we can find all numbers that appear at least three times in a row. Finally, we only need to select the distinct `l2.num`.

<!-- tabs:start -->

```python
import pandas as pd


def consecutive_numbers(logs: pd.DataFrame) -> pd.DataFrame:
    all_the_same = lambda lst: lst.nunique() == 1
    logs["is_consecutive"] = (
        logs["num"].rolling(window=3, center=True, min_periods=3).apply(all_the_same)
    )
    return (
        logs.query("is_consecutive == 1.0")[["num"]]
        .drop_duplicates()
        .rename(columns={"num": "ConsecutiveNums"})
    )
```

```sql
# Write your MySQL query statement below
SELECT DISTINCT l2.num AS ConsecutiveNums
FROM
    Logs AS l1
    JOIN Logs AS l2 ON l1.id = l2.id - 1 AND l1.num = l2.num
    JOIN Logs AS l3 ON l2.id = l3.id - 1 AND l2.num = l3.num;
```

<!-- tabs:end -->

### Solution 2: Window Function

We can use the window functions `LAG` and `LEAD` to obtain the `num` of the previous row and the next row of the current row, and record them in the fields $a$ and $b$, respectively. Finally, we only need to filter out the rows where $a = num$ and $b = num$, which are the numbers that appear at least three times in a row. Note that we need to use the `DISTINCT` keyword to remove duplicates from the results.

We can also group the numbers by using the `IF` function to determine whether the `num` of the current row is equal to the `num` of the previous row. If they are equal, we set it to $0$, otherwise we set it to $1$. Then, we use the window function `SUM` to calculate the prefix sum, which is the grouping identifier. Finally, we only need to group by the grouping identifier and filter out the numbers with a row count greater than or equal to $3$ in each group. Similarly, we need to use the `DISTINCT` keyword to remove duplicates from the results.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            LAG(num) OVER () AS a,
            LEAD(num) OVER () AS b
        FROM Logs
    )
SELECT DISTINCT num AS ConsecutiveNums
FROM T
WHERE a = num AND b = num;
```

<!-- tabs:end -->

### Solution 3

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            IF(num = (LAG(num) OVER ()), 0, 1) AS st
        FROM Logs
    ),
    S AS (
        SELECT *, SUM(st) OVER (ORDER BY id) AS p
        FROM T
    )
SELECT DISTINCT num AS ConsecutiveNums
FROM S
GROUP BY p
HAVING COUNT(1) >= 3;
```

<!-- tabs:end -->

<!-- end -->
