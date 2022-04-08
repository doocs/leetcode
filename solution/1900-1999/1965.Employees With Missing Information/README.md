# [1965. Employees With Missing Information](https://leetcode-cn.com/problems/employees-with-missing-information)

[English Version](/solution/1900-1999/1965.Employees%20With%20Missing%20Information/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Employees</code></p>

<pre>+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| employee_id | int     |
| name        | varchar |
+-------------+---------+
employee_id 是这个表的主键。
每一行表示雇员的id 和他的姓名。
</pre>

<p>表: <code>Salaries</code></p>

<pre>+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| employee_id | int     |
| salary      | int     |
+-------------+---------+
employee_id is 这个表的主键。
每一行表示雇员的id 和他的薪水。
</pre>

<p>写出一个查询语句，找到所有丢失信息的雇员id。当满足下面一个条件时，就被认为是雇员的信息丢失：</p>

<ul>
	<li>雇员的姓名丢失了，或者</li>
	<li>雇员的薪水信息丢失了，或者</li>
</ul>

<p>返回这些雇员的id，从小到大排序。</p>

<p>查询结果格式如下面的例子所示：</p>

<pre>Employees table:
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

Result table:
+-------------+
| employee_id |
+-------------+
| 1           |
| 2           |
+-------------+
雇员1，2，4，5 都工作在这个公司。

1号雇员的姓名丢失了。
2号雇员的薪水信息丢失了。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql
SELECT employee_id
FROM Employees AS e
WHERE e.employee_id NOT IN (
        SELECT employee_id
        FROM Salaries
    )
UNION
SELECT employee_id
FROM Salaries AS s
WHERE s.employee_id NOT IN (
        SELECT employee_id
        FROM Employees
    )
ORDER BY employee_id;
```

<!-- tabs:end -->
