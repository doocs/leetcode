---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1978.Employees%20Whose%20Manager%20Left%20the%20Company/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1978. Employees Whose Manager Left the Company](https://leetcode.com/problems/employees-whose-manager-left-the-company)

[中文文档](/solution/1900-1999/1978.Employees%20Whose%20Manager%20Left%20the%20Company/README.md)

## Description

<p>Table: <code>Employees</code></p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| employee_id | int      |
| name        | varchar  |
| manager_id  | int      |
| salary      | int      |
+-------------+----------+
In SQL, employee_id is the primary key for this table.
This table contains information about the employees, their salary, and the ID of their manager. Some employees do not have a manager (manager_id is null). 
</pre>

<p>&nbsp;</p>

<p>Find the IDs of the employees whose salary is strictly less than <code>$30000</code> and whose manager left the company. When a manager leaves the company, their information is deleted from the <code>Employees</code> table, but the reports still have their <code>manager_id</code> set to the manager that left.</p>

<p>Return the result table ordered by <code>employee_id</code>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input: </strong> 
Employees table:
+-------------+-----------+------------+--------+
| employee_id | name      | manager_id | salary |
+-------------+-----------+------------+--------+
| 3           | Mila      | 9          | 60301  |
| 12          | Antonella | null       | 31000  |
| 13          | Emery     | null       | 67084  |
| 1           | Kalel     | 11         | 21241  |
| 9           | Mikaela   | null       | 50937  |
| 11          | Joziah    | 6          | 28485  |
+-------------+-----------+------------+--------+
<strong>Output:</strong> 
+-------------+
| employee_id |
+-------------+
| 11          |
+-------------+

<strong>Explanation:</strong> 
The employees with a salary less than $30000 are 1 (Kalel) and 11 (Joziah).
Kalel&#39;s manager is employee 11, who is still in the company (Joziah).
Joziah&#39;s manager is employee 6, who left the company because there is no row for employee 6 as it was deleted.
</pre>

## Solutions

<!-- solution:start -->

### Solution 1: Left Join

We can use a left join to connect the employee table with itself, and then filter out the employees whose salary is less than $30000$ and have a superior manager who has left the company.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT e1.employee_id
FROM
    Employees AS e1
    LEFT JOIN Employees AS e2 ON e1.manager_id = e2.employee_id
WHERE e1.salary < 30000 AND e1.manager_id IS NOT NULL AND e2.employee_id IS NULL
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Subquery

We can also use a subquery to first find all the managers who have left the company, and then find the employees whose salary is less than $30000$ and whose superior manager is not in the list of managers who have left the company.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT employee_id
FROM Employees
WHERE salary < 30000 AND manager_id NOT IN (SELECT employee_id FROM Employees)
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
