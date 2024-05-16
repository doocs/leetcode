---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1285.Find%20the%20Start%20and%20End%20Number%20of%20Continuous%20Ranges/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1285. Find the Start and End Number of Continuous Ranges 🔒](https://leetcode.com/problems/find-the-start-and-end-number-of-continuous-ranges)

[中文文档](/solution/1200-1299/1285.Find%20the%20Start%20and%20End%20Number%20of%20Continuous%20Ranges/README.md)

## Description

<p>Table: <code>Logs</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| log_id        | int     |
+---------------+---------+
log_id is the column of unique values for this table.
Each row of this table contains the ID in a log Table.
</pre>

<p>&nbsp;</p>

<p>Write a solution to find the start and end number of continuous ranges in the table <code>Logs</code>.</p>

<p>Return the result table ordered by <code>start_id</code>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Logs table:
+------------+
| log_id     |
+------------+
| 1          |
| 2          |
| 3          |
| 7          |
| 8          |
| 10         |
+------------+
<strong>Output:</strong> 
+------------+--------------+
| start_id   | end_id       |
+------------+--------------+
| 1          | 3            |
| 7          | 8            |
| 10         | 10           |
+------------+--------------+
<strong>Explanation:</strong> 
The result table should contain all ranges in table Logs.
From 1 to 3 is contained in the table.
From 4 to 6 is missing in the table
From 7 to 8 is contained in the table.
Number 9 is missing from the table.
Number 10 is contained in the table.
</pre>

## Solutions

<!-- solution:start -->

### Solution 1: Group By + Window Function

We need to find a way to group a continuous sequence of logs into the same group, and then aggregate each group to obtain the start and end logs of each group.

There are two ways to implement grouping:

1. By calculating the difference between each log and the previous log, if the difference is $1$, then the two logs are continuous, and we set $delta$ to $0$, otherwise we set it to $1$. Then we take the prefix sum of $delta$ to obtain the grouping identifier for each row.
2. By calculating the difference between the current log and its row number, we obtain the grouping identifier for each row.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            log_id,
            SUM(delta) OVER (ORDER BY log_id) AS pid
        FROM
            (
                SELECT
                    log_id,
                    IF((log_id - LAG(log_id) OVER (ORDER BY log_id)) = 1, 0, 1) AS delta
                FROM Logs
            ) AS t
    )
SELECT MIN(log_id) AS start_id, MAX(log_id) AS end_id
FROM T
GROUP BY pid;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            log_id,
            log_id - ROW_NUMBER() OVER (ORDER BY log_id) AS pid
        FROM Logs
    )
SELECT MIN(log_id) AS start_id, MAX(log_id) AS end_id
FROM T
GROUP BY pid;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
