# [185. 部门工资前三高的所有员工](https://leetcode-cn.com/problems/department-top-three-salaries)

[English Version](/solution/0100-0199/0185.Department%20Top%20Three%20Salaries/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>Employee</code> 表包含所有员工信息，每个员工有其对应的工号&nbsp;<code>Id</code>，姓名 <code>Name</code>，工资 <code>Salary</code> 和部门编号 <code>DepartmentId</code> 。</p>

<pre>+----+-------+--------+--------------+
| Id | Name  | Salary | DepartmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 85000  | 1            |
| 2  | Henry | 80000  | 2            |
| 3  | Sam   | 60000  | 2            |
| 4  | Max   | 90000  | 1            |
| 5  | Janet | 69000  | 1            |
| 6  | Randy | 85000  | 1            |
| 7  | Will  | 70000  | 1            |
+----+-------+--------+--------------+</pre>

<p><code>Department</code> 表包含公司所有部门的信息。</p>

<pre>+----+----------+
| Id | Name     |
+----+----------+
| 1  | IT       |
| 2  | Sales    |
+----+----------+</pre>

<p>编写一个&nbsp;SQL 查询，找出每个部门获得前三高工资的所有员工。例如，根据上述给定的表，查询结果应返回：</p>

<pre>+------------+----------+--------+
| Department | Employee | Salary |
+------------+----------+--------+
| IT         | Max      | 90000  |
| IT         | Randy    | 85000  |
| IT         | Joe      | 85000  |
| IT         | Will     | 70000  |
| Sales      | Henry    | 80000  |
| Sales      | Sam      | 60000  |
+------------+----------+--------+</pre>

<p><strong>解释：</strong></p>

<p>IT 部门中，Max 获得了最高的工资，Randy 和 Joe 都拿到了第二高的工资，Will 的工资排第三。销售部门（Sales）只有两名员工，Henry 的工资最高，Sam 的工资排第二。</p>

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
	AND  (SELECT
            COUNT(DISTINCT e2.Salary)
        FROM
            Employee e2
        WHERE
            e2.Salary > Employee.Salary
                AND Employee.DepartmentId = e2.DepartmentId
    ) < 3
```

<!-- tabs:end -->
