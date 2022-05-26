# [577. Employee Bonus](https://leetcode.com/problems/employee-bonus)

[中文文档](/solution/0500-0599/0577.Employee%20Bonus/README.md)

## Description

<p>Table: <code>Employee</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| empId       | int     |
| name        | varchar |
| supervisor  | int     |
| salary      | int     |
+-------------+---------+
empId is the primary key column for this table.
Each row of this table indicates the name and the ID of an employee in addition to their salary and the id of their manager.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Bonus</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| empId       | int  |
| bonus       | int  |
+-------------+------+
empId is the primary key column for this table.
empId is a foreign key to empId from the Employee table.
Each row of this table contains the id of an employee and their respective bonus.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the name and bonus amount of each employee with a bonus <strong>less than</strong> <code>1000</code>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Employee table:
+-------+--------+------------+--------+
| empId | name   | supervisor | salary |
+-------+--------+------------+--------+
| 3     | Brad   | null       | 4000   |
| 1     | John   | 3          | 1000   |
| 2     | Dan    | 3          | 2000   |
| 4     | Thomas | 3          | 4000   |
+-------+--------+------------+--------+
Bonus table:
+-------+-------+
| empId | bonus |
+-------+-------+
| 2     | 500   |
| 4     | 2000  |
+-------+-------+
<strong>Output:</strong> 
+------+-------+
| name | bonus |
+------+-------+
| Brad | null  |
| John | null  |
| Dan  | 500   |
+------+-------+
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
SELECT
    e.name, b.bonus
FROM
    Employee e
        LEFT JOIN
    Bonus b ON e.empid = b.empid
WHERE
    b.bonus < 1000 OR b.bonus IS NULL;
```

<!-- tabs:end -->
