---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3236.CEO%20Subordinate%20Hierarchy/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3236. CEO Subordinate Hierarchy 🔒](https://leetcode.com/problems/ceo-subordinate-hierarchy)

[中文文档](/solution/3200-3299/3236.CEO%20Subordinate%20Hierarchy/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Employees</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| employee_id   | int     |
| employee_name | varchar |
| manager_id    | int     |
| salary        | int     |
+---------------+---------+
employee_id is the unique identifier for this table.
manager_id is the employee_id of the employee&#39;s manager. The CEO has a NULL manager_id.
</pre>

<p>Write a solution to find subordinates of the CEO (both <strong>direct</strong> and <strong>indirect</strong>), along with their <strong>level in the hierarchy</strong> and their <strong>salary difference</strong> from the CEO.</p>

<p>The result should have the following columns:</p>

<p>The query result format is in the following example.</p>

<ul>
	<li><code>subordinate_id</code>: The employee_id of the subordinate</li>
	<li><code>subordinate_name</code>: The name of the subordinate</li>
	<li><code>hierarchy_level</code>: The level of the subordinate in the hierarchy (<code>1</code> for <strong>direct</strong> reports, <code>2</code> for <strong>their direct</strong> reports, and <strong>so on</strong>)</li>
	<li><code>salary_difference</code>: The difference between the subordinate&#39;s salary and the CEO&#39;s salary</li>
</ul>

<p>Return <em>the result table ordered by</em> <code>hierarchy_level</code> <em><strong>ascending</strong></em>, <em>and then by</em> <code>subordinate_id</code> <em><strong>ascending</strong></em>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p><code>Employees</code> table:</p>

<pre class="example-io">
+-------------+----------------+------------+---------+
| employee_id | employee_name  | manager_id | salary  |
+-------------+----------------+------------+---------+
| 1           | Alice          | NULL       | 150000  |
| 2           | Bob            | 1          | 120000  |
| 3           | Charlie        | 1          | 110000  |
| 4           | David          | 2          | 105000  |
| 5           | Eve            | 2          | 100000  |
| 6           | Frank          | 3          | 95000   |
| 7           | Grace          | 3          | 98000   |
| 8           | Helen          | 5          | 90000   |
+-------------+----------------+------------+---------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+----------------+------------------+------------------+-------------------+
| subordinate_id | subordinate_name | hierarchy_level  | salary_difference |
+----------------+------------------+------------------+-------------------+
| 2              | Bob              | 1                | -30000            |
| 3              | Charlie          | 1                | -40000            |
| 4              | David            | 2                | -45000            |
| 5              | Eve              | 2                | -50000            |
| 6              | Frank            | 2                | -55000            |
| 7              | Grace            | 2                | -52000            |
| 8              | Helen            | 3                | -60000            |
+----------------+------------------+------------------+-------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Bob and Charlie are direct subordinates of Alice (CEO) and thus have a hierarchy_level of 1.</li>
	<li>David and Eve report to Bob, while Frank and Grace report to Charlie, making them second-level subordinates (hierarchy_level 2).</li>
	<li>Helen reports to Eve, making Helen a third-level subordinate (hierarchy_level 3).</li>
	<li>Salary differences are calculated relative to Alice&#39;s salary of 150000.</li>
	<li>The result is ordered by hierarchy_level ascending, and then by subordinate_id ascending.</li>
</ul>

<p><strong>Note:</strong> The output is ordered first by hierarchy_level in ascending order, then by subordinate_id in ascending order.</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Recursive CTE + Join

First, we use a recursive CTE to calculate the hierarchy level of each employee, where the CEO's level is $0$. We save `employee_id`, `employee_name`, `hierarchy_level`, `manager_id`, and `salary` into a temporary table `T`.

Then, we query the CEO's salary and save it into a temporary table `P`.

Finally, we join tables `T` and `P` to calculate the salary difference for each subordinate, and sort by `hierarchy_level` and `subordinate_id`.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH RECURSIVE
    T AS (
        SELECT
            employee_id,
            employee_name,
            0 AS hierarchy_level,
            manager_id,
            salary
        FROM Employees
        WHERE manager_id IS NULL
        UNION ALL
        SELECT
            e.employee_id,
            e.employee_name,
            hierarchy_level + 1 AS hierarchy_level,
            e.manager_id,
            e.salary
        FROM
            T t
            JOIN Employees e ON t.employee_id = e.manager_id
    ),
    P AS (
        SELECT salary
        FROM Employees
        WHERE manager_id IS NULL
    )
SELECT
    employee_id subordinate_id,
    employee_name subordinate_name,
    hierarchy_level,
    t.salary - p.salary salary_difference
FROM
    T t
    JOIN P p
WHERE hierarchy_level != 0
ORDER BY 3, 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
