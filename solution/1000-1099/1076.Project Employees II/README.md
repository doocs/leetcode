# [1076. 项目员工 II 🔒](https://leetcode.cn/problems/project-employees-ii)

[English Version](/solution/1000-1099/1076.Project%20Employees%20II/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

### 方法一

<!-- tabs:start -->

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

### 方法二

<!-- tabs:start -->

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

<!-- end -->
