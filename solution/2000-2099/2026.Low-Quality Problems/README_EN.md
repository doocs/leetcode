# [2026. Low-Quality Problems](https://leetcode.com/problems/low-quality-problems)

[中文文档](/solution/2000-2099/2026.Low-Quality%20Problems/README.md)

## Description

<p>Table: <code>Problems</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| problem_id  | int  |
| likes       | int  |
| dislikes    | int  |
+-------------+------+
problem_id is the primary key column for this table.
Each row of this table indicates the number of likes and dislikes for a LeetCode problem.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the IDs of the <strong>low-quality</strong> problems. A LeetCode problem is <strong>low-quality</strong> if the like percentage of the problem (number of likes divided by the total number of votes) is <strong>strictly less than</strong> <code>60%</code>.</p>

<p>Return the result table ordered by <code>problem_id</code> in ascending order.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Problems table:
+------------+-------+----------+
| problem_id | likes | dislikes |
+------------+-------+----------+
| 6          | 1290  | 425      |
| 11         | 2677  | 8659     |
| 1          | 4446  | 2760     |
| 7          | 8569  | 6086     |
| 13         | 2050  | 4164     |
| 10         | 9002  | 7446     |
+------------+-------+----------+
<strong>Output:</strong> 
+------------+
| problem_id |
+------------+
| 7          |
| 10         |
| 11         |
| 13         |
+------------+
<strong>Explanation:</strong> The like percentages are as follows:
- Problem 1: (4446 / (4446 + 2760)) * 100 = 61.69858%
- Problem 6: (1290 / (1290 + 425)) * 100 = 75.21866%
- Problem 7: (8569 / (8569 + 6086)) * 100 = 58.47151%
- Problem 10: (9002 / (9002 + 7446)) * 100 = 54.73006%
- Problem 11: (2677 / (2677 + 8659)) * 100 = 23.61503%
- Problem 13: (2050 / (2050 + 4164)) * 100 = 32.99002%
Problems 7, 10, 11, and 13 are low-quality problems because their like percentages are less than 60%.</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
