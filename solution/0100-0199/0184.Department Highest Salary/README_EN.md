---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0184.Department%20Highest%20Salary/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [184. Department Highest Salary](https://leetcode.com/problems/department-highest-salary)

[中文文档](/solution/0100-0199/0184.Department%20Highest%20Salary/README.md)

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
departmentId is a foreign key (reference columns) of the ID from the <code>Department </code>table.
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
id is the primary key (column with unique values) for this table. It is guaranteed that department name is not <code>NULL.</code>
Each row of this table indicates the ID of a department and its name.
</pre>

<p>&nbsp;</p>

<p>Write a solution to find employees who have the highest salary in each of the departments.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Employee table:
+----+-------+--------+--------------+
| id | name  | salary | departmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 70000  | 1            |
| 2  | Jim   | 90000  | 1            |
| 3  | Henry | 80000  | 2            |
| 4  | Sam   | 60000  | 2            |
| 5  | Max   | 90000  | 1            |
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
| IT         | Jim      | 90000  |
| Sales      | Henry    | 80000  |
| IT         | Max      | 90000  |
+------------+----------+--------+
<strong>Explanation:</strong> Max and Jim both have the highest salary in the IT department and Henry has the highest salary in the Sales department.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Equi-Join + Subquery

We can use an equi-join to join the `Employee` table and the `Department` table based on `Employee.departmentId = Department.id`, and then use a subquery to find the highest salary for each department. Finally, we can use a `WHERE` clause to filter out the employees with the highest salary in each department.

<!-- tabs:start -->

#### MySQL

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

### Pandas

```python
import pandas as pd


def department_highest_salary(
    employee: pd.DataFrame, department: pd.DataFrame
) -> pd.DataFrame:
    # Merge the two tables on departmentId and department id
    merged = employee.merge(department, left_on='departmentId', right_on='id')

    # Find the maximum salary for each department
    max_salaries = merged.groupby('departmentId')['salary'].transform('max')

    # Filter employees who have the highest salary in their department
    top_earners = merged[merged['salary'] == max_salaries]

    # Select required columns and rename them
    result = top_earners[['name_y', 'name_x', 'salary']].copy()
    result.columns = ['Department', 'Employee', 'Salary']

    return result
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Equi-Join + Window Function

We can use an equi-join to join the `Employee` table and the `Department` table based on `Employee.departmentId = Department.id`, and then use the window function `rank()`, which assigns a rank to each employee in each department based on their salary. Finally, we can select the rows with a rank of $1$ for each department.

<!-- tabs:start -->

#### MySQL

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

<!-- solution:end -->

<!-- problem:end -->
