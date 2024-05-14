# [1303. Find the Team Size 🔒](https://leetcode.com/problems/find-the-team-size)

[中文文档](/solution/1300-1399/1303.Find%20the%20Team%20Size/README.md)

<!-- tags:Database -->

<!-- difficulty:Easy -->

## Description

<p>Table: <code>Employee</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| employee_id   | int     |
| team_id       | int     |
+---------------+---------+
employee_id is the primary key (column with unique values) for this table.
Each row of this table contains the ID of each employee and their respective team.
</pre>

<p>&nbsp;</p>

<p>Write a solution to find the team size of each of the employees.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

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

### Solution 1: Group By + Equi-Join

We can first count the number of people in each team and record it in the `T` table. Then, we can use an equi-join to join the `Employee` table and the `T` table based on `team_id`, and obtain the total number of people in each team.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT team_id, COUNT(1) AS team_size
        FROM Employee
        GROUP BY 1
    )
SELECT employee_id, team_size
FROM
    Employee
    JOIN T USING (team_id);
```

<!-- tabs:end -->

### Solution 2: Left Join

We can also use a left join to join the `Employee` table with itself based on `team_id`, and then group by `employee_id` to count the total number of people in each team that the employee belongs to.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT e1.employee_id, COUNT(1) AS team_size
FROM
    Employee AS e1
    LEFT JOIN Employee AS e2 USING (team_id)
GROUP BY 1;
```

<!-- tabs:end -->

<!-- end -->
