# [570. Managers with at Least 5 Direct Reports](https://leetcode.com/problems/managers-with-at-least-5-direct-reports)

[中文文档](/solution/0500-0599/0570.Managers%20with%20at%20Least%205%20Direct%20Reports/README.md)

## Description

<p>Table: <code>Employee</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
| department  | varchar |
| managerId   | int     |
+-------------+---------+
id is the primary key (column with unique values) for this table.
Each row of this table indicates the name of an employee, their department, and the id of their manager.
If managerId is null, then the employee does not have a manager.
No employee will be the manager of themself.
</pre>

<p>&nbsp;</p>

<p>Write a solution to find managers with at least <strong>five direct reports</strong>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Employee table:
+-----+-------+------------+-----------+
| id  | name  | department | managerId |
+-----+-------+------------+-----------+
| 101 | John  | A          | None      |
| 102 | Dan   | A          | 101       |
| 103 | James | A          | 101       |
| 104 | Amy   | A          | 101       |
| 105 | Anne  | A          | 101       |
| 106 | Ron   | B          | 101       |
+-----+-------+------------+-----------+
<strong>Output:</strong> 
+------+
| name |
+------+
| John |
+------+
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    name
FROM
    Employee AS e1
    JOIN (
        SELECT
            managerId
        FROM Employee
        WHERE managerId IS NOT NULL
        GROUP BY managerId
        HAVING COUNT(1) >= 5
    ) AS e2
        ON e1.id = e2.managerId;
```

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            managerId,
            COUNT(1) OVER (PARTITION BY managerId) AS cnt
        FROM Employee
    )
SELECT DISTINCT name
FROM
    Employee AS e
    JOIN T AS t ON e.id = t.managerId
WHERE cnt >= 5;
```

<!-- tabs:end -->
