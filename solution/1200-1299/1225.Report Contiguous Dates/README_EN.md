---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1225.Report%20Contiguous%20Dates/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1225. Report Contiguous Dates 🔒](https://leetcode.com/problems/report-contiguous-dates)

[中文文档](/solution/1200-1299/1225.Report%20Contiguous%20Dates/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Failed</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| fail_date    | date    |
+--------------+---------+
fail_date is the primary key (column with unique values) for this table.
This table contains the days of failed tasks.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Succeeded</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| success_date | date    |
+--------------+---------+
success_date is the primary key (column with unique values) for this table.
This table contains the days of succeeded tasks.
</pre>

<p>&nbsp;</p>

<p>A system is running one task <strong>every day</strong>. Every task is independent of the previous tasks. The tasks can fail or succeed.</p>

<p>Write a solution&nbsp;to report the&nbsp;<code>period_state</code> for each continuous interval of days in the period from <code>2019-01-01</code> to <code>2019-12-31</code>.</p>

<p><code>period_state</code> is <em>&#39;</em><code>failed&#39;</code><em> </em>if tasks in this interval failed or <code>&#39;succeeded&#39;</code> if tasks in this interval succeeded. Interval of days are retrieved as <code>start_date</code> and <code>end_date.</code></p>

<p>Return the result table ordered by <code>start_date</code>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Failed table:
+-------------------+
| fail_date         |
+-------------------+
| 2018-12-28        |
| 2018-12-29        |
| 2019-01-04        |
| 2019-01-05        |
+-------------------+
Succeeded table:
+-------------------+
| success_date      |
+-------------------+
| 2018-12-30        |
| 2018-12-31        |
| 2019-01-01        |
| 2019-01-02        |
| 2019-01-03        |
| 2019-01-06        |
+-------------------+
<strong>Output:</strong> 
+--------------+--------------+--------------+
| period_state | start_date   | end_date     |
+--------------+--------------+--------------+
| succeeded    | 2019-01-01   | 2019-01-03   |
| failed       | 2019-01-04   | 2019-01-05   |
| succeeded    | 2019-01-06   | 2019-01-06   |
+--------------+--------------+--------------+
<strong>Explanation:</strong> 
The report ignored the system state in 2018 as we care about the system in the period 2019-01-01 to 2019-12-31.
From 2019-01-01 to 2019-01-03 all tasks succeeded and the system state was &quot;succeeded&quot;.
From 2019-01-04 to 2019-01-05 all tasks failed and the system state was &quot;failed&quot;.
From 2019-01-06 to 2019-01-06 all tasks succeeded and the system state was &quot;succeeded&quot;.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Union + Window Function + Group By

We can merge the two tables into one table with a field `st` representing the status, where `failed` indicates failure and `succeeded` indicates success. Then, we can use a window function to group the records with the same status into one group, and calculate the difference between each date and its rank within the group as `pt`, which serves as the identifier for the same continuous status. Finally, we can group by `st` and `pt`, and calculate the minimum and maximum dates for each group, and sort by the minimum date.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT fail_date AS dt, 'failed' AS st
        FROM Failed
        WHERE YEAR(fail_date) = 2019
        UNION ALL
        SELECT success_date AS dt, 'succeeded' AS st
        FROM Succeeded
        WHERE YEAR(success_date) = 2019
    )
SELECT
    st AS period_state,
    MIN(dt) AS start_date,
    MAX(dt) AS end_date
FROM
    (
        SELECT
            *,
            SUBDATE(
                dt,
                RANK() OVER (
                    PARTITION BY st
                    ORDER BY dt
                )
            ) AS pt
        FROM T
    ) AS t
GROUP BY 1, pt
ORDER BY 2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
