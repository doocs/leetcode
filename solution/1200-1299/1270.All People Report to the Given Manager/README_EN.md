---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1270.All%20People%20Report%20to%20the%20Given%20Manager/README_EN.md
tags:
    - Database
---

# [1270. All People Report to the Given Manager 🔒](https://leetcode.com/problems/all-people-report-to-the-given-manager)

[中文文档](/solution/1200-1299/1270.All%20People%20Report%20to%20the%20Given%20Manager/README.md)

## Description

<p>Table: <code>Employees</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| employee_id   | int     |
| employee_name | varchar |
| manager_id    | int     |
+---------------+---------+
employee_id is the column of unique values for this table.
Each row of this table indicates that the employee with ID employee_id and name employee_name reports his work to his/her direct manager with manager_id
The head of the company is the employee with employee_id = 1.
</pre>

<p>&nbsp;</p>

<p>Write a solution to find <code>employee_id</code> of all employees that directly or indirectly report their work to the head of the company.</p>

<p>The indirect relation between managers <strong>will not exceed three managers</strong> as the company is small.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Employees table:
+-------------+---------------+------------+
| employee_id | employee_name | manager_id |
+-------------+---------------+------------+
| 1           | Boss          | 1          |
| 3           | Alice         | 3          |
| 2           | Bob           | 1          |
| 4           | Daniel        | 2          |
| 7           | Luis          | 4          |
| 8           | Jhon          | 3          |
| 9           | Angela        | 8          |
| 77          | Robert        | 1          |
+-------------+---------------+------------+
<strong>Output:</strong> 
+-------------+
| employee_id |
+-------------+
| 2           |
| 77          |
| 4           |
| 7           |
+-------------+
<strong>Explanation:</strong> 
The head of the company is the employee with employee_id 1.
The employees with employee_id 2 and 77 report their work directly to the head of the company.
The employee with employee_id 4 reports their work indirectly to the head of the company 4 --&gt; 2 --&gt; 1. 
The employee with employee_id 7 reports their work indirectly to the head of the company 7 --&gt; 4 --&gt; 2 --&gt; 1.
The employees with employee_id 3, 8, and 9 do not report their work to the head of the company directly or indirectly. 
</pre>

## Solutions

### Solution 1: Two Joins

We can use two joins to find all employees who report directly or indirectly to the company CEO.

Specifically, we first use a join to find the `manager_id` of the superior manager for each `manager_id`, and then use another join to find the `manager_id` of the higher-level manager. Finally, if the `manager_id` of the higher-level manager is $1$ and the `employee_id` of the employee is not $1$, it means that the employee reports directly or indirectly to the company CEO.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT e1.employee_id
FROM
    Employees AS e1
    JOIN Employees AS e2 ON e1.manager_id = e2.employee_id
    JOIN Employees AS e3 ON e2.manager_id = e3.employee_id
WHERE e1.employee_id != 1 AND e3.manager_id = 1;
```

<!-- tabs:end -->

<!-- end -->
