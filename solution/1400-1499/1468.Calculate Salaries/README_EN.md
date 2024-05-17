---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1468.Calculate%20Salaries/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1468. Calculate Salaries 🔒](https://leetcode.com/problems/calculate-salaries)

[中文文档](/solution/1400-1499/1468.Calculate%20Salaries/README.md)

## Description

<!-- description:start -->

<p>Table <code>Salaries</code>:</p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| company_id    | int     |
| employee_id   | int     |
| employee_name | varchar |
| salary        | int     |
+---------------+---------+
In SQL,(company_id, employee_id) is the primary key for this table.
This table contains the company id, the id, the name, and the salary for an employee.
</pre>

<p>&nbsp;</p>

<p>Find the salaries of the employees after applying taxes. Round the salary to <strong>the nearest integer</strong>.</p>

<p>The tax rate is calculated for each company based on the following criteria:</p>

<ul>
	<li><code>0%</code> If the max salary of any employee in the company is less than <code>$1000</code>.</li>
	<li><code>24%</code> If the max salary of any employee in the company is in the range <code>[1000, 10000]</code> inclusive.</li>
	<li><code>49%</code> If the max salary of any employee in the company is greater than <code>$10000</code>.</li>
</ul>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Salaries table:
+------------+-------------+---------------+--------+
| company_id | employee_id | employee_name | salary |
+------------+-------------+---------------+--------+
| 1          | 1           | Tony          | 2000   |
| 1          | 2           | Pronub        | 21300  |
| 1          | 3           | Tyrrox        | 10800  |
| 2          | 1           | Pam           | 300    |
| 2          | 7           | Bassem        | 450    |
| 2          | 9           | Hermione      | 700    |
| 3          | 7           | Bocaben       | 100    |
| 3          | 2           | Ognjen        | 2200   |
| 3          | 13          | Nyancat       | 3300   |
| 3          | 15          | Morninngcat   | 7777   |
+------------+-------------+---------------+--------+
<strong>Output:</strong> 
+------------+-------------+---------------+--------+
| company_id | employee_id | employee_name | salary |
+------------+-------------+---------------+--------+
| 1          | 1           | Tony          | 1020   |
| 1          | 2           | Pronub        | 10863  |
| 1          | 3           | Tyrrox        | 5508   |
| 2          | 1           | Pam           | 300    |
| 2          | 7           | Bassem        | 450    |
| 2          | 9           | Hermione      | 700    |
| 3          | 7           | Bocaben       | 76     |
| 3          | 2           | Ognjen        | 1672   |
| 3          | 13          | Nyancat       | 2508   |
| 3          | 15          | Morninngcat   | 5911   |
+------------+-------------+---------------+--------+
<strong>Explanation:</strong> 
For company 1, Max salary is 21300. Employees in company 1 have taxes = 49%
For company 2, Max salary is 700. Employees in company 2 have taxes = 0%
For company 3, Max salary is 7777. Employees in company 3 have taxes = 24%
The salary after taxes = salary - (taxes percentage / 100) * salary
For example, Salary for Morninngcat (3, 15) after taxes = 7777 - 7777 * (24 / 100) = 7777 - 1866.48 = 5910.52, which is rounded to 5911.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    s.company_id,
    employee_id,
    employee_name,
    ROUND(
        CASE
            WHEN top < 1000 THEN salary
            WHEN top >= 1000
            AND top <= 10000 THEN salary * 0.76
            ELSE salary * 0.51
        END
    ) AS salary
FROM
    Salaries AS s
    JOIN (
        SELECT company_id, MAX(salary) AS top
        FROM Salaries
        GROUP BY company_id
    ) AS t
        ON s.company_id = t.company_id;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
