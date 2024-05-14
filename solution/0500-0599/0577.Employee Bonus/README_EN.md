# [577. Employee Bonus](https://leetcode.com/problems/employee-bonus)

[中文文档](/solution/0500-0599/0577.Employee%20Bonus/README.md)

<!-- tags:Database -->

<!-- difficulty:Easy -->

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
empId is the column with unique values for this table.
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
empId is the column of unique values for this table.
empId is a foreign key (reference column) to empId from the Employee table.
Each row of this table contains the id of an employee and their respective bonus.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report the name and bonus amount of each employee with a bonus <strong>less than</strong> <code>1000</code>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

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

### Solution 1: Left Join

We can use a left join to join the `Employee` table and the `Bonus` table on `empId`, and then filter out the employees whose bonus is less than $1000$. Note that the employees with `NULL` bonus values after the join should also be filtered out, so we need to use the `IFNULL` function to convert `NULL` values to $0$.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT name, bonus
FROM
    Employee
    LEFT JOIN Bonus USING (empId)
WHERE IFNULL(bonus, 0) < 1000;
```

<!-- tabs:end -->

<!-- end -->
