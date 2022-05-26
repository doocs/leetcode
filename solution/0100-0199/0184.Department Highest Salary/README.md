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
id是此表的主键列。
departmentId是Department表中ID的外键。
此表的每一行都表示员工的ID、姓名和工资。它还包含他们所在部门的ID。
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
id是此表的主键列。
此表的每一行都表示一个部门的ID及其名称。
</pre>

<p>&nbsp;</p>

<p>编写SQL查询以查找每个部门中薪资最高的员工。<br />
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

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT
	Department.NAME AS Department,
	Employee.NAME AS Employee,
	Salary
FROM
	Employee,
	Department
WHERE
	Employee.DepartmentId = Department.Id
	AND ( Employee.DepartmentId, Salary )
    IN (SELECT DepartmentId, max( Salary )
        FROM Employee
        GROUP BY DepartmentId )
```

```sql
# Write your MySQL query statement below
SELECT
	d.NAME AS Department,
	e1.NAME AS Employee,
	e1.salary AS Salary
FROM
	Employee AS e1
	JOIN Department AS d ON e1.departmentId = d.id
WHERE
	e1.salary = (
	SELECT
		MAX( Salary )
	FROM
		Employee AS e2
	WHERE
		e2.departmentId = d.id
	)
```

<!-- tabs:end -->
