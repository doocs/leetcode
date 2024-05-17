---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2854.Rolling%20Average%20Steps/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [2854. Rolling Average Steps 🔒](https://leetcode.com/problems/rolling-average-steps)

[中文文档](/solution/2800-2899/2854.Rolling%20Average%20Steps/README.md)

## Description

<!-- description:start -->

<p>Table: <code><font face="monospace">Steps</font></code></p>

<pre>
+-------------+------+ 
| Column Name | Type | 
+-------------+------+ 
| user_id     | int  | 
| steps_count | int  |
| steps_date  | date |
+-------------+------+
(user_id, steps_date) is the primary key for this table.
Each row of this table contains user_id, steps_count, and steps_date.
</pre>

<p>Write a solution to calculate <code>3-day</code> <strong>rolling averages</strong> of steps for each user.</p>

<p>We calculate the <code>n-day</code> <strong>rolling average</strong> this way:</p>

<ul>
	<li>For each day, we calculate the average of <code>n</code> consecutive days of step counts ending on that day if available, otherwise, <code>n-day</code> rolling average is not defined for it.</li>
</ul>

<p>Output the <code>user_id</code>, <code>steps_date</code>, and rolling average. Round the rolling average to <strong>two decimal places</strong>.</p>

<p>Return<em> the result table ordered by </em><code>user_id</code><em>, </em><code>steps_date</code><em> in <strong>ascending</strong> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Steps table:
+---------+-------------+------------+
| user_id | steps_count | steps_date |
+---------+-------------+------------+
| 1       | 687         | 2021-09-02 |
| 1       | 395         | 2021-09-04 |
| 1       | 499         | 2021-09-05 |
| 1       | 712         | 2021-09-06 |
| 1       | 576         | 2021-09-07 |
| 2       | 153         | 2021-09-06 |
| 2       | 171         | 2021-09-07 |
| 2       | 530         | 2021-09-08 |
| 3       | 945         | 2021-09-04 |
| 3       | 120         | 2021-09-07 |
| 3       | 557         | 2021-09-08 |
| 3       | 840         | 2021-09-09 |
| 3       | 627         | 2021-09-10 |
| 5       | 382         | 2021-09-05 |
| 6       | 480         | 2021-09-01 |
| 6       | 191         | 2021-09-02 |
| 6       | 303         | 2021-09-05 |
+---------+-------------+------------+
<strong>Output:</strong> 
+---------+------------+-----------------+
| user_id | steps_date | rolling_average | 
+---------+------------+-----------------+
| 1       | 2021-09-06 | 535.33          | 
| 1       | 2021-09-07 | 595.67          | 
| 2       | 2021-09-08 | 284.67          |
| 3       | 2021-09-09 | 505.67          |
| 3       | 2021-09-10 | 674.67          |    
+---------+------------+-----------------+
<strong>Explanation:</strong> 
- For user id 1, the step counts for the three consecutive days up to 2021-09-06 are available. Consequently, the rolling average for this particular date is computed as (395 + 499 + 712) / 3 = 535.33.
- For user id 1, the step counts for the three consecutive days up to 2021-09-07 are available. Consequently, the rolling average for this particular date is computed as (499 + 712 + 576) / 3 = 595.67.
- For user id 2, the step counts for the three consecutive days up to 2021-09-08 are available. Consequently, the rolling average for this particular date is computed as (153 + 171 + 530) / 3 = 284.67.
- For user id 3, the step counts for the three consecutive days up to 2021-09-09 are available. Consequently, the rolling average for this particular date is computed as (120 + 557 + 840) / 3 = 505.67.
- For user id 3, the step counts for the three consecutive days up to 2021-09-10 are available. Consequently, the rolling average for this particular date is computed as (557 + 840 + 627) / 3 = 674.67.
- For user id 4 and 5, the calculation of the rolling average is not viable as there is insufficient data for the consecutive three days. Output table ordered by user_id and steps_date in ascending order.</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Window Functions

We can use the window function `LAG() OVER()` to calculate the difference in days between the current date and the date before the last date for each user. If the difference is $2$, it means that there are continuous data for $3$ days between these two dates. We can use the window function `AVG() OVER()` to calculate the average of these $3$ data.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            user_id,
            steps_date,
            ROUND(
                AVG(steps_count) OVER (
                    PARTITION BY user_id
                    ORDER BY steps_date
                    ROWS 2 PRECEDING
                ),
                2
            ) AS rolling_average,
            DATEDIFF(
                steps_date,
                LAG(steps_date, 2) OVER (
                    PARTITION BY user_id
                    ORDER BY steps_date
                )
            ) = 2 AS st
        FROM Steps
    )
SELECT
    user_id,
    steps_date,
    rolling_average
FROM T
WHERE st = 1
ORDER BY 1, 2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
