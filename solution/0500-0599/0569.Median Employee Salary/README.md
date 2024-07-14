---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0569.Median%20Employee%20Salary/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [569. 员工薪水中位数 🔒](https://leetcode.cn/problems/median-employee-salary)

[English Version](/solution/0500-0599/0569.Median%20Employee%20Salary/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表:&nbsp;<code>Employee</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| id           | int     |
| company      | varchar |
| salary       | int     |
+--------------+---------+
id 是该表的主键列(具有唯一值的列)。
该表的每一行表示公司和一名员工的工资。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，找出每个公司的工资中位数。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Employee 表:
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
<strong>输出:</strong> 
+----+---------+--------+
| id | company | salary |
+----+---------+--------+
| 5  | A       | 451    |
| 6  | A       | 513    |
| 12 | B       | 234    |
| 9  | B       | 1154   |
| 14 | C       | 2645   |
+----+---------+--------+
</pre>

<p>&nbsp;</p>

<p><strong>进阶:&nbsp;</strong>你能在不使用任何内置函数或窗口函数的情况下解决它吗?</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

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
