---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0610.Triangle%20Judgement/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [610. Triangle Judgement](https://leetcode.com/problems/triangle-judgement)

[中文文档](/solution/0600-0699/0610.Triangle%20Judgement/README.md)

## Description

<p>Table: <code>Triangle</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| x           | int  |
| y           | int  |
| z           | int  |
+-------------+------+
In SQL, (x, y, z) is the primary key column for this table.
Each row of this table contains the lengths of three line segments.
</pre>

<p>&nbsp;</p>

<p>Report for every three line segments whether they can form a triangle.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Triangle table:
+----+----+----+
| x  | y  | z  |
+----+----+----+
| 13 | 15 | 30 |
| 10 | 20 | 15 |
+----+----+----+
<strong>Output:</strong> 
+----+----+----+----------+
| x  | y  | z  | triangle |
+----+----+----+----------+
| 13 | 15 | 30 | No       |
| 10 | 20 | 15 | Yes      |
+----+----+----+----------+
</pre>

## Solutions

<!-- solution:start -->

### Solution 1: IF Statement + Triangle Inequality

The condition for whether three sides can form a triangle is that the sum of any two sides is greater than the third side. Therefore, we can use an `IF` statement to determine whether this condition is satisfied. If it is satisfied, we return `Yes`, otherwise we return `No`.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    *,
    IF(x + y > z AND x + z > y AND y + z > x, 'Yes', 'No') AS triangle
FROM Triangle;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
