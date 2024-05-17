---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0569.Median%20Employee%20Salary/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [569. Median Employee Salary 🔒](https://leetcode.com/problems/median-employee-salary)

[中文文档](/solution/0500-0599/0569.Median%20Employee%20Salary/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Employee</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| id           | int     |
| company      | varchar |
| salary       | int     |
+--------------+---------+
id is the primary key (column with unique values) for this table.
Each row of this table indicates the company and the salary of one employee.
</pre>

<p>&nbsp;</p>

<p>Write a solution to find the rows that contain the median salary of each company. While calculating the median, when you sort the salaries of the company, break the ties by <code>id</code>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Employee table:
+----+---------+--------+
| id | company | salary |
+----+---------+--------+
| 1  | A       | 2341   |
| 2  | A       | 341    |
| 3  | A       | 15     |
| 4  | A       | 15314  |
| 5  | A       | 451    |
| 6  | A       | 513    |
| 7  | B       | 15     |
| 8  | B       | 13     |
| 9  | B       | 1154   |
| 10 | B       | 1345   |
| 11 | B       | 1221   |
| 12 | B       | 234    |
| 13 | C       | 2345   |
| 14 | C       | 2645   |
| 15 | C       | 2645   |
| 16 | C       | 2652   |
| 17 | C       | 65     |
+----+---------+--------+
<strong>Output:</strong> 
+----+---------+--------+
| id | company | salary |
+----+---------+--------+
| 5  | A       | 451    |
| 6  | A       | 513    |
| 12 | B       | 234    |
| 9  | B       | 1154   |
| 14 | C       | 2645   |
+----+---------+--------+
<strong>Explanation:</strong> 
For company A, the rows sorted are as follows:
+----+---------+--------+
| id | company | salary |
+----+---------+--------+
| 3  | A       | 15     |
| 2  | A       | 341    |
| 5  | A       | 451    | &lt;-- median
| 6  | A       | 513    | &lt;-- median
| 1  | A       | 2341   |
| 4  | A       | 15314  |
+----+---------+--------+
For company B, the rows sorted are as follows:
+----+---------+--------+
| id | company | salary |
+----+---------+--------+
| 8  | B       | 13     |
| 7  | B       | 15     |
| 12 | B       | 234    | &lt;-- median
| 11 | B       | 1221   | &lt;-- median
| 9  | B       | 1154   |
| 10 | B       | 1345   |
+----+---------+--------+
For company C, the rows sorted are as follows:
+----+---------+--------+
| id | company | salary |
+----+---------+--------+
| 17 | C       | 65     |
| 13 | C       | 2345   |
| 14 | C       | 2645   | &lt;-- median
| 15 | C       | 2645   | 
| 16 | C       | 2652   |
+----+---------+--------+
</pre>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve it without using any built-in or window functions?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            *,
            ROW_NUMBER() OVER (
                PARTITION BY company
                ORDER BY salary ASC
            ) AS rk,
            COUNT(id) OVER (PARTITION BY company) AS n
        FROM Employee
    )
SELECT
    id,
    company,
    salary
FROM t
WHERE rk >= n / 2 AND rk <= n / 2 + 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
