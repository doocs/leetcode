# [176. Second Highest Salary](https://leetcode.com/problems/second-highest-salary)

[中文文档](/solution/0100-0199/0176.Second%20Highest%20Salary/README.md)

## Description

<p>Table: <code>Employee</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| salary      | int  |
+-------------+------+
id is the primary key column for this table.
Each row of this table contains information about the salary of an employee.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the second highest salary from the <code>Employee</code> table. If there is no second highest salary, the query should report <code>null</code>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
<strong>Output:</strong> 
+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
+----+--------+
<strong>Output:</strong> 
+---------------------+
| SecondHighestSalary |
+---------------------+
| null                |
+---------------------+
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

Solution 1: Use Sub Query and LIMIT.

```sql
# Write your MySQL query statement below
SELECT
(
    SELECT DISTINCT Salary
    FROM Employee
    ORDER BY Salary DESC
    LIMIT 1 OFFSET 1
) AS SecondHighestSalary;
```

Solution 2: Use `MAX()` function.

```sql
# Write your MySQL query statement below
SELECT MAX(Salary) AS SecondHighestSalary
FROM Employee
WHERE Salary < (
    SELECT MAX(Salary)
    FROM Employee
);
```

<!-- tabs:end -->
