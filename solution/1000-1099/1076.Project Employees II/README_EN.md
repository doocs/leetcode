---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1076.Project%20Employees%20II/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1076. Project Employees II 🔒](https://leetcode.com/problems/project-employees-ii)

[中文文档](/solution/1000-1099/1076.Project%20Employees%20II/README.md)

## Description

<p>Table: <code>Project</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| project_id  | int     |
| employee_id | int     |
+-------------+---------+
(project_id, employee_id) is the primary key (combination of columns with unique values) of this table.
employee_id is a foreign key (reference column) to <code>Employee</code> table.
Each row of this table indicates that the employee with employee_id is working on the project with project_id.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Employee</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| employee_id      | int     |
| name             | varchar |
| experience_years | int     |
+------------------+---------+
employee_id is the primary key (column with unique values) of this table.
Each row of this table contains information about one employee.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report all the <strong>projects</strong> that have the most employees.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Project table:
+-------------+-------------+
| project_id  | employee_id |
+-------------+-------------+
| 1           | 1           |
| 1           | 2           |
| 1           | 3           |
| 2           | 1           |
| 2           | 4           |
+-------------+-------------+
Employee table:
+-------------+--------+------------------+
| employee_id | name   | experience_years |
+-------------+--------+------------------+
| 1           | Khaled | 3                |
| 2           | Ali    | 2                |
| 3           | John   | 1                |
| 4           | Doe    | 2                |
+-------------+--------+------------------+
<strong>Output:</strong> 
+-------------+
| project_id  |
+-------------+
| 1           |
+-------------+
<strong>Explanation:</strong> The first project has 3 employees while the second one has 2.
</pre>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT project_id
FROM Project
GROUP BY 1
HAVING
    COUNT(1) >= all(
        SELECT COUNT(1)
        FROM Project
        GROUP BY project_id
    );
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            project_id,
            RANK() OVER (ORDER BY COUNT(employee_id) DESC) AS rk
        FROM Project
        GROUP BY 1
    )
SELECT project_id
FROM T
WHERE rk = 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
