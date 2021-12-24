# [184. Department Highest Salary](https://leetcode.com/problems/department-highest-salary)

[中文文档](/solution/0100-0199/0184.Department%20Highest%20Salary/README.md)

## Description

<p>The <code>Employee</code> table holds all employees. Every employee has an Id, a salary, and there is also a column for the department Id.</p>

<pre>

+----+-------+--------+--------------+

| Id | Name  | Salary | DepartmentId |

+----+-------+--------+--------------+

| 1  | Joe   | 70000  | 1            |

| 2 &nbsp;| Jim &nbsp; | 90000 &nbsp;| 1 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|

| 3  | Henry | 80000  | 2            |

| 4  | Sam   | 60000  | 2            |

| 5  | Max   | 90000  | 1            |

+----+-------+--------+--------------+

</pre>

<p>The <code>Department</code> table holds all departments of the company.</p>

<pre>

+----+----------+

| Id | Name     |

+----+----------+

| 1  | IT       |

| 2  | Sales    |

+----+----------+

</pre>

<p>Write a SQL query to find employees who have the highest salary in each of the departments.&nbsp;For the above tables, your SQL query should return the following rows (order of rows does not matter).</p>

<pre>

+------------+----------+--------+

| Department | Employee | Salary |

+------------+----------+--------+

| IT         | Max      | 90000  |

| IT &nbsp; &nbsp; &nbsp; &nbsp; | Jim &nbsp; &nbsp; &nbsp;| 90000 &nbsp;|

| Sales      | Henry    | 80000  |

+------------+----------+--------+

</pre>

<p><strong>Explanation:</strong></p>

<p>Max and Jim both have&nbsp;the highest salary in the IT department and Henry has the highest salary in the Sales department.</p>

## Solutions

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
