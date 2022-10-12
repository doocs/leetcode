# [1303. Find the Team Size](https://leetcode.com/problems/find-the-team-size)

[中文文档](/solution/1300-1399/1303.Find%20the%20Team%20Size/README.md)

## Description

<p>Table: <code>Employee</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| employee_id   | int     |
| team_id       | int     |
+---------------+---------+
employee_id is the primary key for this table.
Each row of this table contains the ID of each employee and their respective team.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find the team size of each of the employees.</p>

<p>Return result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Employee Table:
+-------------+------------+
| employee_id | team_id    |
+-------------+------------+
|     1       |     8      |
|     2       |     8      |
|     3       |     8      |
|     4       |     7      |
|     5       |     9      |
|     6       |     9      |
+-------------+------------+
<strong>Output:</strong> 
+-------------+------------+
| employee_id | team_size  |
+-------------+------------+
|     1       |     3      |
|     2       |     3      |
|     3       |     3      |
|     4       |     1      |
|     5       |     2      |
|     6       |     2      |
+-------------+------------+
<strong>Explanation:</strong> 
Employees with Id 1,2,3 are part of a team with team_id = 8.
Employee with Id 4 is part of a team with team_id = 7.
Employees with Id 5,6 are part of a team with team_id = 9.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

Solution 1:

```sql
# Write your MySQL query statement below
SELECT
    e.employee_id, t.team_size
FROM
    Employee e
LEFT JOIN
    (SELECT
        team_id, count(1) as team_size
    FROM
        Employee
    GROUP BY
        team_id
    ) t
ON
    e.team_id = t.team_id;
```

Solution 2:

```sql
# Write your MySQL query statement below
SELECT
    e1.employee_id, count(*) as team_size
FROM
    Employee e1
LEFT JOIN
    Employee e2
ON
    e1.team_id = e2.team_id
GROUP BY
    e1.employee_id;
```

<!-- tabs:end -->
