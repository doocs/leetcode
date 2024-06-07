---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2853.Highest%20Salaries%20Difference/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2853. 最高薪水差异 🔒](https://leetcode.cn/problems/highest-salaries-difference)

[English Version](/solution/2800-2899/2853.Highest%20Salaries%20Difference/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code><font face="monospace">Salaries</font></code></p>

<pre>
+-------------+---------+ 
| Column Name | Type    | 
+-------------+---------+ 
| emp_name    | varchar | 
| department  | varchar | 
| salary      | int     |
+-------------+---------+
该表的主键（具有唯一值的列的组合）是 (emp_name, department)。 
该表的每一行包含 emp_name、department 和 salary。工程部门和市场部门至少会有一条记录。
</pre>

<p>编写一个解决方案，计算&nbsp;<strong>市场部门&nbsp;</strong>和&nbsp;<strong>工程部门&nbsp;</strong>中&nbsp;<strong>最高&nbsp;</strong>工资之间的差异。输出工资的绝对差异。</p>

<p>返回结果表。</p>

<p>返回结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<b>输入：</b>
Salaries table:
+----------+-------------+--------+
| emp_name | department  | salary |
+----------+-------------+--------+
| Kathy    | Engineering | 50000  |
| Roy      | Marketing   | 30000  |
| Charles  | Engineering | 45000  |
| Jack     | Engineering | 85000  | 
| Benjamin | Marketing   | 34000  |
| Anthony  | Marketing   | 42000  |
| Edward   | Engineering | 102000 |
| Terry    | Engineering | 44000  |
| Evelyn   | Marketing   | 53000  |
| Arthur   | Engineering | 32000  |
+----------+-------------+--------+
<b>输出：</b>
+-------------------+
| salary_difference | 
+-------------------+
| 49000             | 
+-------------------+
<b>解释：</b>
- 工程部门和市场部门的最高工资分别为 102,000 和 53,000，因此绝对差异为 49,000。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：GROUP BY 分组

我们可以先分别计算出每个部门的最高工资，然后再计算两个最高工资的差值。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT MAX(s) - MIN(s) AS salary_difference
FROM
    (
        SELECT MAX(salary) AS s
        FROM Salaries
        GROUP BY department
    ) AS t;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
