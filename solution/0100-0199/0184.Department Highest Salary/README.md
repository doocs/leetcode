---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0184.Department%20Highest%20Salary/README.md
tags:
    - 数据库
---

# [184. 部门工资最高的员工](https://leetcode.cn/problems/department-highest-salary)

[English Version](/solution/0100-0199/0184.Department%20Highest%20Salary/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：&nbsp;<code>Employee</code></p>

<pre>
+--------------+---------+
| 列名          | 类型    |
+--------------+---------+
| id           | int     |
| name         | varchar |
| salary       | int     |
| departmentId | int     |
+--------------+---------+
在 SQL 中，id是此表的主键。
departmentId 是 Department 表中 id 的外键（在 Pandas 中称为 join key）。
此表的每一行都表示员工的 id、姓名和工资。它还包含他们所在部门的 id。
</pre>

<p>&nbsp;</p>

<p>表：&nbsp;<code>Department</code></p>

<pre>
+-------------+---------+
| 列名         | 类型    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
在 SQL 中，id 是此表的主键列。
此表的每一行都表示一个部门的 id 及其名称。
</pre>

<p>&nbsp;</p>

<p>查找出每个部门中薪资最高的员工。<br />
按 <strong>任意顺序</strong> 返回结果表。<br />
查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<b>输入：</b>
Employee 表:
+----+-------+--------+--------------+
| id | name  | salary | departmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 70000  | 1            |
| 2  | Jim   | 90000  | 1            |
| 3  | Henry | 80000  | 2            |
| 4  | Sam   | 60000  | 2            |
| 5  | Max   | 90000  | 1            |
+----+-------+--------+--------------+
Department 表:
+----+-------+
| id | name  |
+----+-------+
| 1  | IT    |
| 2  | Sales |
+----+-------+
<b>输出：</b>
+------------+----------+--------+
| Department | Employee | Salary |
+------------+----------+--------+
| IT         | Jim      | 90000  |
| Sales      | Henry    | 80000  |
| IT         | Max      | 90000  |
+------------+----------+--------+
<strong>解释：</strong>Max 和 Jim 在 IT 部门的工资都是最高的，Henry 在销售部的工资最高。</pre>

## 解法

### 方法一：等值连接 + 子查询

我们可以使用等值连接，将 `Employee` 表和 `Department` 表连接起来，连接条件为 `Employee.departmentId = Department.id`，然后使用子查询来找到每个部门的最高工资，最后使用 `WHERE` 子句来筛选出每个部门中薪资最高的员工。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT d.name AS department, e.name AS employee, salary
FROM
    Employee AS e
    JOIN Department AS d ON e.departmentId = d.id
WHERE
    (d.id, salary) IN (
        SELECT departmentId, MAX(salary)
        FROM Employee
        GROUP BY 1
    );
```

<!-- tabs:end -->

### 方法二：等值连接 + 窗口函数

我们可以使用等值连接，将 `Employee` 表和 `Department` 表连接起来，连接条件为 `Employee.departmentId = Department.id`，然后使用窗口函数 `rank()`，它可以为每个部门的每个员工分配一个排名，然后我们可以选择排名为 $1$ 的行即可。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            d.name AS department,
            e.name AS employee,
            salary,
            RANK() OVER (
                PARTITION BY d.name
                ORDER BY salary DESC
            ) AS rk
        FROM
            Employee AS e
            JOIN Department AS d ON e.departmentId = d.id
    )
SELECT department, employee, salary
FROM T
WHERE rk = 1;
```

<!-- tabs:end -->

<!-- end -->
