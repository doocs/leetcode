---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3268.Find%20Overlapping%20Shifts%20II/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3268. Find Overlapping Shifts II 🔒](https://leetcode.cn/problems/find-overlapping-shifts-ii)

[English Version](/solution/3200-3299/3268.Find%20Overlapping%20Shifts%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Table: <code>EmployeeShifts</code></p>

<pre>
+------------------+----------+
| Column Name      | Type     |
+------------------+----------+
| employee_id      | int      |
| start_time       | datetime |
| end_time         | datetime |
+------------------+----------+
(employee_id, start_time) is the unique key for this table.
This table contains information about the shifts worked by employees, including the start time, and end time.
</pre>

<p>Write a solution to analyze overlapping shifts for each employee. Two shifts are considered overlapping if they occur on the <strong>same date</strong> and one shift&#39;s <code>end_time</code> is <strong>later than</strong> another shift&#39;s <code>start_time</code>.</p>

<p>For <strong>each employee</strong>, calculate the following:</p>

<ol>
	<li>The <strong>maximum</strong> number of shifts that <strong>overlap</strong> at any <strong>given time</strong>.</li>
	<li>The <strong>total duration</strong> of all overlaps in minutes.</li>
</ol>

<p><em>Return the result table ordered by</em> <code>employee_id</code> <em>in <strong>ascending</strong> order</em>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p><code>EmployeeShifts</code> table:</p>

<pre class="example-io">
+-------------+---------------------+---------------------+
| employee_id | start_time          | end_time            |
+-------------+---------------------+---------------------+
| 1           | 2023-10-01 09:00:00 | 2023-10-01 17:00:00 |
| 1           | 2023-10-01 15:00:00 | 2023-10-01 23:00:00 |
| 1           | 2023-10-01 16:00:00 | 2023-10-02 00:00:00 |
| 2           | 2023-10-01 09:00:00 | 2023-10-01 17:00:00 |
| 2           | 2023-10-01 11:00:00 | 2023-10-01 19:00:00 |
| 3           | 2023-10-01 09:00:00 | 2023-10-01 17:00:00 |
+-------------+---------------------+---------------------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+-------------+---------------------------+------------------------+
| employee_id | max_overlapping_shifts    | total_overlap_duration |
+-------------+---------------------------+------------------------+
| 1           | 3                         | 600                    |
| 2           | 2                         | 360                    |
| 3           | 1                         | 0                      |
+-------------+---------------------------+------------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Employee 1 has 3 shifts:
	<ul>
		<li>2023-10-01 09:00:00 to 2023-10-01 17:00:00</li>
		<li>2023-10-01 15:00:00 to 2023-10-01 23:00:00</li>
		<li>2023-10-01 16:00:00 to 2023-10-02 00:00:00</li>
	</ul>
	The maximum number of overlapping shifts is 3 (from 16:00 to 17:00). The total overlap duration is: - 2 hours (15:00-17:00) between 1st and 2nd shifts - 1 hour (16:00-17:00) between 1st and 3rd shifts - 7 hours (16:00-23:00) between 2nd and 3rd shifts Total: 10 hours = 600 minutes</li>
	<li>Employee 2 has 2 shifts:
	<ul>
		<li>2023-10-01 09:00:00 to 2023-10-01 17:00:00</li>
		<li>2023-10-01 11:00:00 to 2023-10-01 19:00:00</li>
	</ul>
	The maximum number of overlapping shifts is 2. The total overlap duration is 6 hours (11:00-17:00) = 360 minutes.</li>
	<li>Employee 3 has only 1 shift, so there are no overlaps.</li>
</ul>

<p>The output table contains the employee_id, the maximum number of simultaneous overlaps, and the total overlap duration in minutes for each employee, ordered by employee_id in ascending order.</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：合并 + 连接

我们可以将所有 `employee_id` 的 `start_time` 和 `end_time` 合并到一起，记录在 `T` 表中，然后使用 `LEAD` 函数计算出每个 `employee_id` 的下一个时间段，记录在 `P` 表中。

接着，我们可以通过 `P` 表和 `EmployeeShifts` 表进行连接，计算出每个 `employee_id` 的 `concurrent_count`，即同时存在的时间段数量，记录在 `S` 表中。

最后，我们可以通过 `EmployeeShifts` 表和自身进行连接，计算出每个 `employee_id` 的 `total_overlap_duration`，即总的重叠时间，记录在 `U` 表中。

最终，我们可以通过 `S` 表和 `U` 表进行连接，计算出每个 `employee_id` 的 `max_overlapping_shifts` 和 `total_overlap_duration`。

相似题目：

-   [3156. 员工任务持续时间和并发任务 🔒](https://github.com/doocs/leetcode/blob/main/solution/3100-3199/3156.Employee%20Task%20Duration%20and%20Concurrent%20Tasks/README.md)
-   [3262. 查找重叠的班次 🔒](https://github.com/doocs/leetcode/blob/main/solution/3200-3299/3262.Find%20Overlapping%20Shifts/README.md)

<!-- tabs:start -->

#### MySQL

```sql
WITH
    T AS (
        SELECT DISTINCT employee_id, start_time AS st
        FROM EmployeeShifts
        UNION DISTINCT
        SELECT DISTINCT employee_id, end_time AS st
        FROM EmployeeShifts
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
            INNER JOIN EmployeeShifts USING (employee_id)
        WHERE P.st >= EmployeeShifts.start_time AND P.ed <= EmployeeShifts.end_time
        GROUP BY 1, 2, 3
    ),
    U AS (
        SELECT
            t1.employee_id,
            SUM(
                TIMESTAMPDIFF(MINUTE, t2.start_time, LEAST(t1.end_time, t2.end_time))
            ) total_overlap_duration
        FROM
            EmployeeShifts t1
            JOIN EmployeeShifts t2
                ON t1.employee_id = t2.employee_id
                AND t1.start_time < t2.start_time
                AND t1.end_time > t2.start_time
        GROUP BY 1
    )
SELECT
    employee_id,
    MAX(concurrent_count) max_overlapping_shifts,
    IFNULL(AVG(total_overlap_duration), 0) total_overlap_duration
FROM
    S
    LEFT JOIN U USING (employee_id)
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
