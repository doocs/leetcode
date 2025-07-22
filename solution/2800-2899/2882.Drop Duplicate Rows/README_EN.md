---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2882.Drop%20Duplicate%20Rows/README_EN.md
tags:
    - Pandas
---

<!-- problem:start -->

# [2882. Drop Duplicate Rows](https://leetcode.com/problems/drop-duplicate-rows)

[中文文档](/solution/2800-2899/2882.Drop%20Duplicate%20Rows/README.md)

## Description

<!-- description:start -->

<pre>
DataFrame customers
+-------------+--------+
| Column Name | Type   |
+-------------+--------+
| customer_id | int    |
| name        | object |
| email       | object |
+-------------+--------+
</pre>

<p>There are some duplicate rows in the DataFrame based on the <code>email</code> column.</p>

<p>Write a solution to remove these duplicate rows and keep only the <strong>first</strong> occurrence.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<pre>
<strong class="example">Example 1:</strong>
<strong>Input:</strong>
+-------------+---------+---------------------+
| customer_id | name    | email               |
+-------------+---------+---------------------+
| 1           | Ella    | emily@example.com   |
| 2           | David   | michael@example.com |
| 3           | Zachary | sarah@example.com   |
| 4           | Alice   | john@example.com    |
| 5           | Finn    | john@example.com    |
| 6           | Violet  | alice@example.com   |
+-------------+---------+---------------------+
<strong>Output: </strong> 
+-------------+---------+---------------------+
| customer_id | name    | email               |
+-------------+---------+---------------------+
| 1           | Ella    | emily@example.com   |
| 2           | David   | michael@example.com |
| 3           | Zachary | sarah@example.com   |
| 4           | Alice   | john@example.com    |
| 6           | Violet  | alice@example.com   |
+-------------+---------+---------------------+
<strong>Explanation:</strong>
Alic (customer_id = 4) and Finn (customer_id = 5) both use john@example.com, so only the first occurrence of this email is retained.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def dropDuplicateEmails(customers: pd.DataFrame) -> pd.DataFrame:
    return customers.drop_duplicates(subset=['email'])
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
