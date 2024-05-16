---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2394.Employees%20With%20Deductions/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [2394. Employees With Deductions 🔒](https://leetcode.com/problems/employees-with-deductions)

[中文文档](/solution/2300-2399/2394.Employees%20With%20Deductions/README.md)

## Description

<p>Table: <code>Employees</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| employee_id  | int  |
| needed_hours | int  |
+--------------+------+
employee_id is column with unique values for this table.
Each row contains the id of an employee and the minimum number of hours needed for them to work to get their salary.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Logs</code></p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| employee_id | int      |
| in_time     | datetime |
| out_time    | datetime |
+-------------+----------+
(employee_id, in_time, out_time) is the primary key (combination of columns with unique values) for this table.
Each row of this table shows the time stamps for an employee. in_time is the time the employee started to work, and out_time is the time the employee ended work.
All the times are in October 2022. out_time can be one day after in_time which means the employee worked after the midnight.
</pre>

<p>&nbsp;</p>

<p>In a company, each employee must work a certain number of hours every month. Employees work in sessions. The number of hours an employee worked can be calculated from the sum of the number of minutes the employee worked in all of their sessions. The number of minutes in each session is rounded up.</p>

<ul>
	<li>For example, if the employee worked for <code>51</code> minutes and <code>2</code> seconds in a session, we consider it <code>52</code> minutes.</li>
</ul>

<p>Write a solution to report the IDs of the employees that will be deducted. In other words, report the IDs of the employees that did not work the needed hours.</p>

<p>Return the result table <strong>in any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Employees table:
+-------------+--------------+
| employee_id | needed_hours |
+-------------+--------------+
| 1           | 20           |
| 2           | 12           |
| 3           | 2            |
+-------------+--------------+
Logs table:
+-------------+---------------------+---------------------+
| employee_id | in_time             | out_time            |
+-------------+---------------------+---------------------+
| 1           | 2022-10-01 09:00:00 | 2022-10-01 17:00:00 |
| 1           | 2022-10-06 09:05:04 | 2022-10-06 17:09:03 |
| 1           | 2022-10-12 23:00:00 | 2022-10-13 03:00:01 |
| 2           | 2022-10-29 12:00:00 | 2022-10-29 23:58:58 |
+-------------+---------------------+---------------------+
<strong>Output:</strong> 
+-------------+
| employee_id |
+-------------+
| 2           |
| 3           |
+-------------+
<strong>Explanation:</strong> 
Employee 1:
 - Worked for three sessions:
    - On 2022-10-01, they worked for 8 hours.
    - On 2022-10-06, they worked for 8 hours and 4 minutes.
    - On 2022-10-12, they worked for 4 hours and 1 minute. Note that they worked through midnight.
 - Employee 1 worked a total of 20 hours and 5 minutes across sessions and will not be deducted.
Employee 2:
 - Worked for one session:
    - On 2022-10-29, they worked for 11 hours and 59 minutes.
 - Employee 2 did not work their hours and will be deducted.
Employee 3:
 - Did not work any session.
 - Employee 3 did not work their hours and will be deducted.
</pre>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            employee_id,
            SUM(ceiling(TIMESTAMPDIFF(second, in_time, out_time) / 60)) / 60 AS tot
        FROM Logs
        GROUP BY employee_id
    )
SELECT employee_id
FROM
    Employees
    LEFT JOIN T USING (employee_id)
WHERE IFNULL(tot, 0) < needed_hours;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
