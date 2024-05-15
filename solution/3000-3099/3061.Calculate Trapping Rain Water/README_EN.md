---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3061.Calculate%20Trapping%20Rain%20Water/README_EN.md
tags:
    - Database
---

# [3061. Calculate Trapping Rain Water 🔒](https://leetcode.com/problems/calculate-trapping-rain-water)

[中文文档](/solution/3000-3099/3061.Calculate%20Trapping%20Rain%20Water/README.md)

## Description

<p>Table: <font face="monospace">Heights</font></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| height      | int  |
+-------------+------+
id is the primary key (column with unique values) for this table, and it is guaranteed to be in sequential order.
Each row of this table contains an id and height.
</pre>

<p>Write a solution to calculate the amount of rainwater can be <strong>trapped between the bars</strong> in the landscape, considering that each bar has a <strong>width</strong> of <code>1</code> unit.</p>

<p>Return <em>the result table in </em><strong>any</strong><em> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Heights table:
+-----+--------+
| id  | height |
+-----+--------+
| 1   | 0      |
| 2   | 1      |
| 3   | 0      |
| 4   | 2      |
| 5   | 1      |
| 6   | 0      |
| 7   | 1      |
| 8   | 3      |
| 9   | 2      |
| 10  | 1      |
| 11  | 2      |
| 12  | 1      |
+-----+--------+
<strong>Output:</strong> 
+---------------------+
| total_trapped_water | 
+---------------------+
| 6                   | 
+---------------------+
<strong>Explanation:</strong> 
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3061.Calculate%20Trapping%20Rain%20Water/images/trapping_rain_water.png" style="width:500px; height:200px;" />

The elevation map depicted above (in the black section) is graphically represented with the x-axis denoting the id and the y-axis representing the heights [0,1,0,2,1,0,1,3,2,1,2,1]. In this scenario, 6 units of rainwater are trapped within the blue section.
</pre>

## Solutions

### Solution 1: Window Function + Summation

We use the window function `MAX(height) OVER (ORDER BY id)` to calculate the maximum height for each position and its left side, and use `MAX(height) OVER (ORDER BY id DESC)` to calculate the maximum height for each position and its right side, denoted as `l` and `r` respectively. Then, the amount of water stored at each position is `min(l, r) - height`. Finally, we sum them up.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            MAX(height) OVER (ORDER BY id) AS l,
            MAX(height) OVER (ORDER BY id DESC) AS r
        FROM Heights
    )
SELECT SUM(LEAST(l, r) - height) AS total_trapped_water
FROM T;
```

```python
import pandas as pd


def calculate_trapped_rain_water(heights: pd.DataFrame) -> pd.DataFrame:
    heights["l"] = heights["height"].cummax()
    heights["r"] = heights["height"][::-1].cummax()[::-1]
    heights["trapped_water"] = heights[["l", "r"]].min(axis=1) - heights["height"]
    return pd.DataFrame({"total_trapped_water": [heights["trapped_water"].sum()]})
```

<!-- tabs:end -->

<!-- end -->
