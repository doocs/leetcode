# [3057. Employees Project Allocation](https://leetcode.cn/problems/employees-project-allocation)

[English Version](/solution/3000-3099/3057.Employees%20Project%20Allocation/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Project</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| project_id  | int     |
| employee_id | int     |
| workload    | int     |
+-------------+---------+
employee_id is the primary key (column with unique values) of this table.
employee_id is a foreign key (reference column) to <code>Employee</code> table.
Each row of this table indicates that the employee with employee_id is working on the project with project_id and the workload of the project.
</pre>

<p>Table: <code>Employees</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| employee_id      | int     |
| name             | varchar |
| team             | varchar |
+------------------+---------+
employee_id is the primary key (column with unique values) of this table.
Each row of this table contains information about one employee.
</pre>

<p>Write a solution to find the <strong>employees</strong> who are allocated to projects with a <strong>workload that exceeds the average</strong> workload of all employees for <strong>their respective teams</strong></p>

<p>Return t<em>he result table ordered by</em> <code>employee_id</code>, <code>project_id</code> <em>in <strong>ascending</strong> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Project table:
+-------------+-------------+----------+
| project_id  | employee_id | workload |
+-------------+-------------+----------+
| 1           | 1           |  45      |
| 1           | 2           |  90      | 
| 2           | 3           |  12      |
| 2           | 4           |  68      |
+-------------+-------------+----------+
Employees table:
+-------------+--------+------+
| employee_id | name   | team |
+-------------+--------+------+
| 1           | Khaled | A    |
| 2           | Ali    | B    |
| 3           | John   | B    |
| 4           | Doe    | A    |
+-------------+--------+------+
<strong>Output:</strong> 
+-------------+------------+---------------+------------------+
| employee_id | project_id | employee_name | project_workload |
+-------------+------------+---------------+------------------+  
| 2           | 1          | Ali           | 90               | 
| 4           | 2          | Doe           | 68               | 
+-------------+------------+---------------+------------------+
<strong>Explanation:</strong> 
- Employee with ID 1 has a project workload of 45 and belongs to Team A, where the average workload is 56.50. Since his project workload does not exceed the team&#39;s average workload, he will be excluded.
- Employee with ID 2 has a project workload of 90 and belongs to Team B, where the average workload is 51.00. Since his project workload does exceed the team&#39;s average workload, he will be included.
- Employee with ID 3 has a project workload of 12 and belongs to Team B, where the average workload is 51.00. Since his project workload does not exceed the team&#39;s average workload, he will be excluded.
- Employee with ID 4 has a project workload of 68 and belongs to Team A, where the average workload is 56.50. Since his project workload does exceed the team&#39;s average workload, he will be included.
Result table orderd by employee_id, project_id in ascending order.
</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql

```

<!-- tabs:end -->

<!-- end -->
