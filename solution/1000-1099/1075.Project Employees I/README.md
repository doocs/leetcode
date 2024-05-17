---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1075.Project%20Employees%20I/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1075. 项目员工 I](https://leetcode.cn/problems/project-employees-i)

[English Version](/solution/1000-1099/1075.Project%20Employees%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>项目表&nbsp;<code>Project</code>：&nbsp;</p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| project_id  | int     |
| employee_id | int     |
+-------------+---------+
主键为 (project_id, employee_id)。
employee_id 是员工表 <code>Employee 表的外键。</code>
</pre>

<p>员工表&nbsp;<code>Employee</code>：</p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| employee_id      | int     |
| name             | varchar |
| experience_years | int     |
+------------------+---------+
主键是 employee_id。
</pre>

<p>&nbsp;</p>

<p>请写一个 SQL&nbsp;语句，查询每一个项目中员工的&nbsp;<strong>平均&nbsp;</strong>工作年限，<strong>精确到小数点后两位</strong>。</p>

<p>查询结果的格式如下：</p>

<pre>
Project 表：
+-------------+-------------+
| project_id  | employee_id |
+-------------+-------------+
| 1           | 1           |
| 1           | 2           |
| 1           | 3           |
| 2           | 1           |
| 2           | 4           |
+-------------+-------------+

Employee 表：
+-------------+--------+------------------+
| employee_id | name   | experience_years |
+-------------+--------+------------------+
| 1           | Khaled | 3                |
| 2           | Ali    | 2                |
| 3           | John   | 1                |
| 4           | Doe    | 2                |
+-------------+--------+------------------+

Result 表：
+-------------+---------------+
| project_id  | average_years |
+-------------+---------------+
| 1           | 2.00          |
| 2           | 2.50          |
+-------------+---------------+
第一个项目中，员工的平均工作年限是 (3 + 2 + 1) / 3 = 2.00；第二个项目中，员工的平均工作年限是 (3 + 2) / 2 = 2.50
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：内连接 + `GROUP BY` 分组

我们可以通过内连接将两张表连接起来，然后通过 `GROUP BY` 分组，最后使用 `AVG` 函数求工作年限的平均值。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement
SELECT project_id, ROUND(AVG(experience_years), 2) AS average_years
FROM
    Project
    JOIN Employee USING (employee_id)
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
