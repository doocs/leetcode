---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1965.Employees%20With%20Missing%20Information/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1965. 丢失信息的雇员](https://leetcode.cn/problems/employees-with-missing-information)

[English Version](/solution/1900-1999/1965.Employees%20With%20Missing%20Information/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Employees</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| employee_id | int     |
| name        | varchar |
+-------------+---------+
employee_id 是该表中具有唯一值的列。
每一行表示雇员的 id 和他的姓名。
</pre>

<p>表: <code>Salaries</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| employee_id | int     |
| salary      | int     |
+-------------+---------+
employee_id 是该表中具有唯一值的列。
每一行表示雇员的 id 和他的薪水。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，找到所有 <strong>丢失信息</strong> 的雇员 id。当满足下面一个条件时，就被认为是雇员的信息丢失：</p>

<ul>
	<li>雇员的 <strong>姓名</strong> 丢失了，或者</li>
	<li>雇员的 <strong>薪水信息</strong> 丢失了</li>
</ul>

<p>返回这些雇员的 id &nbsp;<code>employee_id</code>&nbsp;，&nbsp;<strong>从小到大排序&nbsp;</strong>。</p>

<p>查询结果格式如下面的例子所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Employees table:
+-------------+----------+
| employee_id | name     |
+-------------+----------+
| 2           | Crew     |
| 4           | Haven    |
| 5           | Kristian |
+-------------+----------+
Salaries table:
+-------------+--------+
| employee_id | salary |
+-------------+--------+
| 5           | 76071  |
| 1           | 22517  |
| 4           | 63539  |
+-------------+--------+
<strong>输出：</strong>
+-------------+
| employee_id |
+-------------+
| 1           |
| 2           |
+-------------+
<strong>解释：</strong>
雇员 1，2，4，5 都在这个公司工作。
1 号雇员的姓名丢失了。
2 号雇员的薪水信息丢失了。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：子查询 + 合并

我们可以先从 `Employees` 表中找出所有不在 `Salaries` 表中的 `employee_id`，再从 `Salaries` 表中找出所有不在 `Employees` 表中的 `employee_id`，最后将两个结果合并，然后按照 `employee_id` 排序即可。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT employee_id
FROM Employees
WHERE employee_id NOT IN (SELECT employee_id FROM Salaries)
UNION
SELECT employee_id
FROM Salaries
WHERE employee_id NOT IN (SELECT employee_id FROM Employees)
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
