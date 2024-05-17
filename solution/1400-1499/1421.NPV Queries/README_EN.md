---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1421.NPV%20Queries/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1421. NPV Queries 🔒](https://leetcode.com/problems/npv-queries)

[中文文档](/solution/1400-1499/1421.NPV%20Queries/README.md)

## Description

<!-- description:start -->

<p>Table: <code>NPV</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| year          | int     |
| npv           | int     |
+---------------+---------+
(id, year) is the primary key (combination of columns with unique values) of this table.
The table has information about the id and the year of each inventory and the corresponding net present value.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Queries</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| year          | int     |
+---------------+---------+
(id, year) is the primary key (combination of columns with unique values) of this table.
The table has information about the id and the year of each inventory query.
</pre>

<p>&nbsp;</p>

<p>Write a solution to find the <code>npv</code> of each query of the <code>Queries</code> table.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
NPV table:
+------+--------+--------+
| id   | year   | npv    |
+------+--------+--------+
| 1    | 2018   | 100    |
| 7    | 2020   | 30     |
| 13   | 2019   | 40     |
| 1    | 2019   | 113    |
| 2    | 2008   | 121    |
| 3    | 2009   | 12     |
| 11   | 2020   | 99     |
| 7    | 2019   | 0      |
+------+--------+--------+
Queries table:
+------+--------+
| id   | year   |
+------+--------+
| 1    | 2019   |
| 2    | 2008   |
| 3    | 2009   |
| 7    | 2018   |
| 7    | 2019   |
| 7    | 2020   |
| 13   | 2019   |
+------+--------+
<strong>Output:</strong> 
+------+--------+--------+
| id   | year   | npv    |
+------+--------+--------+
| 1    | 2019   | 113    |
| 2    | 2008   | 121    |
| 3    | 2009   | 12     |
| 7    | 2018   | 0      |
| 7    | 2019   | 0      |
| 7    | 2020   | 30     |
| 13   | 2019   | 40     |
+------+--------+--------+
<strong>Explanation:</strong> 
The npv value of (7, 2018) is not present in the NPV table, we consider it 0.
The npv values of all other queries can be found in the NPV table.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT q.*, IFNULL(npv, 0) AS npv
FROM
    Queries AS q
    LEFT JOIN NPV AS n USING (id, year);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
