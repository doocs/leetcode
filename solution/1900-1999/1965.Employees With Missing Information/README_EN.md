# [1965. Employees With Missing Information](https://leetcode.com/problems/employees-with-missing-information)

[中文文档](/solution/1900-1999/1965.Employees%20With%20Missing%20Information/README.md)

## Description

<p>Table: <code>Employees</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| employee_id | int     |
| name        | varchar |
+-------------+---------+
employee_id is the primary key for this table.
Each row of this table indicates the name of the employee whose ID is employee_id.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Salaries</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| employee_id | int     |
| salary      | int     |
+-------------+---------+
employee_id is the primary key for this table.
Each row of this table indicates the salary of the employee whose ID is employee_id.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the IDs of all the employees with <strong>missing information</strong>. The information of an employee is missing if:</p>

<ul>
	<li>The employee&#39;s <strong>name</strong> is missing, or</li>
	<li>The employee&#39;s <strong>salary</strong> is missing.</li>
</ul>

<p>Return the result table ordered by <code>employee_id</code> <strong>in ascending order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
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
<strong>Output:</strong> 
+-------------+
| employee_id |
+-------------+
| 1           |
| 2           |
+-------------+
<strong>Explanation:</strong> 
Employees 1, 2, 4, and 5 are working at this company.
The name of employee 1 is missing.
The salary of employee 2 is missing.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

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
