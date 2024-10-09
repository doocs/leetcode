---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3156.Employee%20Task%20Duration%20and%20Concurrent%20Tasks/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3156. Employee Task Duration and Concurrent Tasks 🔒](https://leetcode.com/problems/employee-task-duration-and-concurrent-tasks)

[中文文档](/solution/3100-3199/3156.Employee%20Task%20Duration%20and%20Concurrent%20Tasks/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Tasks</code></p>

<pre>
+---------------+----------+
| Column Name   | Type     |
+---------------+----------+
| task_id       | int      |
| employee_id   | int      |
| start_time    | datetime |
| end_time      | datetime |
+---------------+----------+
(task_id, employee_id) is the primary key for this table.
Each row in this table contains the task identifier, the employee identifier, and the start and end times of each task.
</pre>

<p>Write a solution to find the <strong>total duration</strong> of tasks for <strong>each</strong> employee and the <strong>maximum number of concurrent tasks</strong> an employee handled at <strong>any point in time</strong>. The total duration should be <strong>rounded down</strong> to the nearest number of <strong>full hours</strong>.</p>

<p>Return <em>the result table ordered by</em>&nbsp;<code>employee_id</code><strong> <em>ascending</em></strong><em> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>Tasks table:</p>

<pre class="example-io">
+---------+-------------+---------------------+---------------------+
| task_id | employee_id | start_time          | end_time            |
+---------+-------------+---------------------+---------------------+
| 1       | 1001        | 2023-05-01 08:00:00 | 2023-05-01 09:00:00 |
| 2       | 1001        | 2023-05-01 08:30:00 | 2023-05-01 10:30:00 |
| 3       | 1001        | 2023-05-01 11:00:00 | 2023-05-01 12:00:00 |
| 7       | 1001        | 2023-05-01 13:00:00 | 2023-05-01 15:30:00 |
| 4       | 1002        | 2023-05-01 09:00:00 | 2023-05-01 10:00:00 |
| 5       | 1002        | 2023-05-01 09:30:00 | 2023-05-01 11:30:00 |
| 6       | 1003        | 2023-05-01 14:00:00 | 2023-05-01 16:00:00 |
+---------+-------------+---------------------+---------------------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+-------------+------------------+----------------------+
| employee_id | total_task_hours | max_concurrent_tasks |
+-------------+------------------+----------------------+
| 1001        | 6                | 2                    |
| 1002        | 2                | 2                    |
| 1003        | 2                | 1                    |
+-------------+------------------+----------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For employee ID 1001:
	<ul>
		<li>Task 1 and Task 2 overlap from 08:30 to 09:00 (30 minutes).</li>
		<li>Task 7 has a duration of 150 minutes (2 hours and 30 minutes).</li>
		<li>Total task time: 60 (Task 1) + 120 (Task 2) + 60 (Task 3) + 150 (Task 7) - 30 (overlap) = 360 minutes = 6 hours.</li>
		<li>Maximum concurrent tasks: 2 (during the overlap period).</li>
	</ul>
	</li>
	<li>For employee ID 1002:
	<ul>
		<li>Task 4 and Task 5 overlap from 09:30 to 10:00 (30 minutes).</li>
		<li>Total task time: 60 (Task 4) + 120 (Task 5) - 30 (overlap) = 150 minutes = 2 hours and 30 minutes.</li>
		<li>Total task hours (rounded down): 2 hours.</li>
		<li>Maximum concurrent tasks: 2 (during the overlap period).</li>
	</ul>
	</li>
	<li>For employee ID 1003:
	<ul>
		<li>No overlapping tasks.</li>
		<li>Total task time: 120 minutes = 2 hours.</li>
		<li>Maximum concurrent tasks: 1.</li>
	</ul>
	</li>
</ul>

<p><b>Note:</b> Output table is ordered by employee_id in ascending order.</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Merge + Join

First, we merge the `start_time` and `end_time` for each `employee_id` into a new table `T`. Then, using the `LEAD` function, we calculate the start time of the next task for each employee. Next, we join table `T` with the `Tasks` table to compute the concurrent task count for each employee. Finally, we group by `employee_id` to calculate the total task duration and the maximum concurrent tasks for each employee.

Similar Problem:

-   [3268. Find Overlapping Shifts II 🔒](https://github.com/doocs/leetcode/blob/main/solution/3200-3299/3268.Find%20Overlapping%20Shifts%20II/README_EN.md)

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT DISTINCT employee_id, start_time AS st
        FROM Tasks
        UNION DISTINCT
        SELECT DISTINCT employee_id, end_time AS st
        FROM Tasks
    ),
    P AS (
        SELECT
            *,
            LEAD(st) OVER (
                PARTITION BY employee_id
                ORDER BY st
            ) AS ed
        FROM T
    ),
    S AS (
        SELECT
            P.*,
            COUNT(1) AS concurrent_count
        FROM
            P
            INNER JOIN Tasks USING (employee_id)
        WHERE P.st >= Tasks.start_time AND P.ed <= Tasks.end_time
        GROUP BY 1, 2, 3
    )
SELECT
    employee_id,
    FLOOR(SUM(TIME_TO_SEC(TIMEDIFF(ed, st)) / 3600)) AS total_task_hours,
    MAX(concurrent_count) AS max_concurrent_tasks
FROM S
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
