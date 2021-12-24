# [184. 部门工资最高的员工](https://leetcode-cn.com/problems/department-highest-salary)

[English Version](/solution/0100-0199/0184.Department%20Highest%20Salary/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>Employee</code> 表包含所有员工信息，每个员工有其对应的&nbsp;Id, salary 和 department Id。</p>

<pre>+----+-------+--------+--------------+
| Id | Name  | Salary | DepartmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 70000  | 1            |
| 2 &nbsp;| Jim &nbsp; | 90000 &nbsp;| 1 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| 3  | Henry | 80000  | 2            |
| 4  | Sam   | 60000  | 2            |
| 5  | Max   | 90000  | 1            |
+----+-------+--------+--------------+</pre>

<p><code>Department</code>&nbsp;表包含公司所有部门的信息。</p>

<pre>+----+----------+
| Id | Name     |
+----+----------+
| 1  | IT       |
| 2  | Sales    |
+----+----------+</pre>

<p>编写一个 SQL 查询，找出每个部门工资最高的员工。对于上述表，您的 SQL 查询应返回以下行（行的顺序无关紧要）。</p>

<pre>+------------+----------+--------+
| Department | Employee | Salary |
+------------+----------+--------+
| IT         | Max      | 90000  |
| IT &nbsp; &nbsp; &nbsp; &nbsp; | Jim &nbsp; &nbsp; &nbsp;| 90000 &nbsp;|
| Sales      | Henry    | 80000  |
+------------+----------+--------+</pre>

<p><strong>解释：</strong></p>

<p>Max 和 Jim 在 IT 部门的工资都是最高的，Henry 在销售部的工资最高。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
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

<!-- tabs:end -->
