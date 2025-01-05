---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0185.Department%20Top%20Three%20Salaries/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [185. Department Top Three Salaries](https://leetcode.com/problems/department-top-three-salaries)

[中文文档](/solution/0100-0199/0185.Department%20Top%20Three%20Salaries/README.md)

## Description

<!-- description:start -->

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
id is the primary key (column with unique values) for this table.
departmentId is a foreign key (reference column) of the ID from the <code>Department </code>table.
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
id is the primary key (column with unique values) for this table.
Each row of this table indicates the ID of a department and its name.
</pre>

<p>&nbsp;</p>

<p>A company&#39;s executives are interested in seeing who earns the most money in each of the company&#39;s departments. A <strong>high earner</strong> in a department is an employee who has a salary in the <strong>top three unique</strong> salaries for that department.</p>

<p>Write a solution to find the employees who are <strong>high earners</strong> in each of the departments.</p>

<p>Return the result table <strong>in any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

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

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>There are no employees with the <strong>exact</strong> same name, salary <em>and</em> department.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def top_three_salaries(
    employee: pd.DataFrame, department: pd.DataFrame
) -> pd.DataFrame:
    salary_cutoff = (
        employee.drop_duplicates(["salary", "departmentId"])
        .groupby("departmentId")["salary"]
        .nlargest(3)
        .groupby("departmentId")
        .min()
    )
    employee["Department"] = department.set_index("id")["name"][
        employee["departmentId"]
    ].values
    employee["cutoff"] = salary_cutoff[employee["departmentId"]].values
    return employee[employee["salary"] >= employee["cutoff"]].rename(
        columns={"name": "Employee", "salary": "Salary"}
    )[["Department", "Employee", "Salary"]]
```

#### MySQL

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
    AND (
        SELECT
            COUNT(DISTINCT e2.Salary)
        FROM Employee AS e2
        WHERE e2.Salary > Employee.Salary AND Employee.DepartmentId = e2.DepartmentId
    ) < 3;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            DENSE_RANK() OVER (
                PARTITION BY departmentId
                ORDER BY salary DESC
            ) AS rk
        FROM Employee
    )
SELECT d.name AS Department, t.name AS Employee, salary AS Salary
FROM
    T AS t
    JOIN Department AS d ON t.departmentId = d.id
WHERE rk <= 3;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
