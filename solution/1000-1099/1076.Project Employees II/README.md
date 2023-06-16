# [1076. 项目员工 II](https://leetcode.cn/problems/project-employees-ii)

[English Version](/solution/1000-1099/1076.Project%20Employees%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table:&nbsp;<code>Project</code></p>

<pre>+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| project_id  | int     |
| employee_id | int     |
+-------------+---------+
主键为 (project_id, employee_id)。
employee_id 是员工表 <code>Employee 表的外键。</code>
</pre>

<p>Table:&nbsp;<code>Employee</code></p>

<pre>+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| employee_id      | int     |
| name             | varchar |
| experience_years | int     |
+------------------+---------+
主键是 employee_id。</pre>

<p>&nbsp;</p>

<p>编写一个SQL查询，报告所有雇员最多的项目。</p>

<p>查询结果格式如下所示：</p>

<pre>Project table:
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

Result table:
+-------------+
| project_id  |
+-------------+
| 1           |
+-------------+
第一个项目有3名员工，第二个项目有2名员工。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT  project_id
FROM Project p
GROUP BY  project_id
HAVING COUNT(employee_id) >= all(
SELECT  COUNT(employee_id)
FROM Project
GROUP BY  project_id )
```

```sql
# Write your MySQL query statement below
SELECT project_id
FROM
    (
        SELECT
            project_id,
            dense_rank() OVER (ORDER BY COUNT(employee_id) DESC) AS rk
        FROM Project
        GROUP BY project_id
    ) AS t
WHERE rk = 1;
```

<!-- tabs:end -->
