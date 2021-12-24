# [1076. Project Employees II](https://leetcode.com/problems/project-employees-ii)

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
(project_id, employee_id) is the primary key of this table.
employee_id is a foreign key to <code>Employee</code> table.
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
employee_id is the primary key of this table.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query that reports all the <strong>projects</strong> that have the most employees.</p>

<p>The query result format is in the following example:</p>

<p>&nbsp;</p>

<pre>
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

Result table:
+-------------+
| project_id  |
+-------------+
| 1           |
+-------------+
The first project has 3 employees while the second one has 2.</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
