# [185. Department Top Three Salaries](https://leetcode.com/problems/department-top-three-salaries)

[中文文档](/solution/0100-0199/0185.Department%20Top%20Three%20Salaries/README.md)

## Description

<p>Table: <code>Employee</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| id           | int     |
| name         | varchar |
| salary       | int     |
| departmentId | int     |
+--------------+---------+
id is the primary key column for this table.
departmentId is a foreign key of the ID from the <code>Department </code>table.
Each row of this table indicates the ID, name, and salary of an employee. It also contains the ID of their department.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Department</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
id is the primary key column for this table.
Each row of this table indicates the ID of a department and its name.
</pre>

<p>&nbsp;</p>

<p>A company&#39;s executives are interested in seeing who earns the most money in each of the company&#39;s departments. A <strong>high earner</strong> in a department is an employee who has a salary in the <strong>top three unique</strong> salaries for that department.</p>

<p>Write an SQL query to find the employees who are <strong>high earners</strong> in each of the departments.</p>

<p>Return the result table <strong>in any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Employee table:
+----+-------+--------+--------------+
| id | name  | salary | departmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 85000  | 1            |
| 2  | Henry | 80000  | 2            |
| 3  | Sam   | 60000  | 2            |
| 4  | Max   | 90000  | 1            |
| 5  | Janet | 69000  | 1            |
| 6  | Randy | 85000  | 1            |
| 7  | Will  | 70000  | 1            |
+----+-------+--------+--------------+
Department table:
+----+-------+
| id | name  |
+----+-------+
| 1  | IT    |
| 2  | Sales |
+----+-------+
<strong>Output:</strong> 
+------------+----------+--------+
| Department | Employee | Salary |
+------------+----------+--------+
| IT         | Max      | 90000  |
| IT         | Joe      | 85000  |
| IT         | Randy    | 85000  |
| IT         | Will     | 70000  |
| Sales      | Henry    | 80000  |
| Sales      | Sam      | 60000  |
+------------+----------+--------+
<strong>Explanation:</strong> 
In the IT department:
- Max earns the highest unique salary
- Both Randy and Joe earn the second-highest unique salary
- Will earns the third-highest unique salary

In the Sales department:
- Henry earns the highest salary
- Sam earns the second-highest salary
- There is no third-highest salary as there are only two employees
</pre>

## Solutions

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
