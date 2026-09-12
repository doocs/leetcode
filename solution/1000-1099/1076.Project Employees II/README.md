---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1076.Project%20Employees%20II/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1076. 项目员工II 🔒](https://leetcode.cn/problems/project-employees-ii)

[English Version](/solution/1000-1099/1076.Project%20Employees%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Project</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| project_id  | int     |
| employee_id | int     |
+-------------+---------+
(project_id, employee_id) 是该表的主键(具有唯一值的列的组合)。
employee_id 是该表的外键(reference 列)。
该表的每一行都表明 employee_id 的雇员正在处理 Project 表中 project_id 的项目。
</pre>

<p>表：<code>Employee</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| employee_id      | int     |
| name             | varchar |
| experience_years | int     |
+------------------+---------+
employee_id 是该表的主键(具有唯一值的列)。
该表的每一行都包含一名雇员的信息。</pre>

<p>&nbsp;</p>

<p>编写一个解决方案来报告所有拥有最多员工的 <strong>项目</strong>。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>返回结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Project table:
+-------------+-------------+
| project_id  | employee_id |
+-------------+-------------+
| 1           | 1           |
| 1           | 2           |
| 1           | 3           |
| 2           | 1           |
| 2           | 4           |
+-------------+-------------+
Employee table:
+-------------+--------+------------------+
| employee_id | name   | experience_years |
+-------------+--------+------------------+
| 1           | Khaled | 3                |
| 2           | Ali    | 2                |
| 3           | John   | 1                |
| 4           | Doe    | 2                |
+-------------+--------+------------------+
<strong>输出：</strong>
+-------------+
| project_id  |
+-------------+
| 1           |
+-------------+
<strong>解释：</strong>
第一个项目有3名员工，第二个项目有2名员工。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 员工数最多的项目可能有多个并列。按项目计数后，留下不小于所有组计数的那些 `project_id`。
>
> `HAVING COUNT(1) >= ALL (子查询各组人数)` 选出最大值对应的全部项目。
>
> 子查询只做分组计数，外层再过滤。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT project_id
FROM Project
GROUP BY 1
HAVING
    COUNT(1) >= all(
        SELECT COUNT(1)
        FROM Project
        GROUP BY project_id
    );
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> `ALL` 子查询要再聚合一遍。窗口 `RANK()` 可按人数降序编号，名为 $1$ 的即最大值（含并列）。
>
> CTE 中分组并计算 `rk`，外层取 `rk = 1`。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            project_id,
            RANK() OVER (ORDER BY COUNT(employee_id) DESC) AS rk
        FROM Project
        GROUP BY 1
    )
SELECT project_id
FROM T
WHERE rk = 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
