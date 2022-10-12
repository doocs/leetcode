# [1873. Calculate Special Bonus](https://leetcode.com/problems/calculate-special-bonus)

[中文文档](/solution/1800-1899/1873.Calculate%20Special%20Bonus/README.md)

## Description

<p>Table: <code>Employees</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| employee_id | int     |
| name        | varchar |
| salary      | int     |
+-------------+---------+
employee_id is the primary key for this table.
Each row of this table indicates the employee ID, employee name, and salary.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to calculate the bonus of each employee. The bonus of an employee is <code>100%</code> of their salary if the ID of the employee is <strong>an odd number</strong> and <strong>the employee name does not start with the character </strong><code>&#39;M&#39;</code>. The bonus of an employee is <code>0</code> otherwise.</p>

<p>Return the result table ordered by <code>employee_id</code>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Employees table:
+-------------+---------+--------+
| employee_id | name    | salary |
+-------------+---------+--------+
| 2           | Meir    | 3000   |
| 3           | Michael | 3800   |
| 7           | Addilyn | 7400   |
| 8           | Juan    | 6100   |
| 9           | Kannon  | 7700   |
+-------------+---------+--------+
<strong>Output:</strong> 
+-------------+-------+
| employee_id | bonus |
+-------------+-------+
| 2           | 0     |
| 3           | 0     |
| 7           | 7400  |
| 8           | 0     |
| 9           | 7700  |
+-------------+-------+
<strong>Explanation:</strong> 
The employees with IDs 2 and 8 get 0 bonus because they have an even employee_id.
The employee with ID 3 gets 0 bonus because their name starts with &#39;M&#39;.
The rest of the employees get a 100% bonus.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
SELECT
    employee_id,
    CASE
        WHEN employee_id % 2 = 0
        OR LEFT(name, 1) = 'M' THEN 0
        ELSE salary
    END AS bonus
FROM
    employees;
```

MySQL

```sql
SELECT
    employee_id,
    IF(
        employee_id % 2 = 0
        OR LEFT(name, 1) = 'M',
        0,
        salary
    ) AS bonus
FROM
    employees;
```

<!-- tabs:end -->
