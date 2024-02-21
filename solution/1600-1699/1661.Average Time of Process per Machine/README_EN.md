# [1661. Average Time of Process per Machine](https://leetcode.com/problems/average-time-of-process-per-machine)

[中文文档](/solution/1600-1699/1661.Average%20Time%20of%20Process%20per%20Machine/README.md)

<!-- tags:Database -->

## Description

<p>Table: <code>Activity</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| machine_id     | int     |
| process_id     | int     |
| activity_type  | enum    |
| timestamp      | float   |
+----------------+---------+
The table shows the user activities for a factory website.
(machine_id, process_id, activity_type) is the primary key (combination of columns with unique values) of this table.
machine_id is the ID of a machine.
process_id is the ID of a process running on the machine with ID machine_id.
activity_type is an ENUM (category) of type (&#39;start&#39;, &#39;end&#39;).
timestamp is a float representing the current time in seconds.
&#39;start&#39; means the machine starts the process at the given timestamp and &#39;end&#39; means the machine ends the process at the given timestamp.
The &#39;start&#39; timestamp will always be before the &#39;end&#39; timestamp for every (machine_id, process_id) pair.</pre>

<p>&nbsp;</p>

<p>There is a factory website that has several machines each running the <strong>same number of processes</strong>. Write a solution&nbsp;to find the <strong>average time</strong> each machine takes to complete a process.</p>

<p>The time to complete a process is the <code>&#39;end&#39; timestamp</code> minus the <code>&#39;start&#39; timestamp</code>. The average time is calculated by the total time to complete every process on the machine divided by the number of processes that were run.</p>

<p>The resulting table should have the <code>machine_id</code> along with the <strong>average time</strong> as <code>processing_time</code>, which should be <strong>rounded to 3 decimal places</strong>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Activity table:
+------------+------------+---------------+-----------+
| machine_id | process_id | activity_type | timestamp |
+------------+------------+---------------+-----------+
| 0          | 0          | start         | 0.712     |
| 0          | 0          | end           | 1.520     |
| 0          | 1          | start         | 3.140     |
| 0          | 1          | end           | 4.120     |
| 1          | 0          | start         | 0.550     |
| 1          | 0          | end           | 1.550     |
| 1          | 1          | start         | 0.430     |
| 1          | 1          | end           | 1.420     |
| 2          | 0          | start         | 4.100     |
| 2          | 0          | end           | 4.512     |
| 2          | 1          | start         | 2.500     |
| 2          | 1          | end           | 5.000     |
+------------+------------+---------------+-----------+
<strong>Output:</strong> 
+------------+-----------------+
| machine_id | processing_time |
+------------+-----------------+
| 0          | 0.894           |
| 1          | 0.995           |
| 2          | 1.456           |
+------------+-----------------+
<strong>Explanation:</strong> 
There are 3 machines running 2 processes each.
Machine 0&#39;s average time is ((1.520 - 0.712) + (4.120 - 3.140)) / 2 = 0.894
Machine 1&#39;s average time is ((1.550 - 0.550) + (1.420 - 0.430)) / 2 = 0.995
Machine 2&#39;s average time is ((4.512 - 4.100) + (5.000 - 2.500)) / 2 = 1.456
</pre>

## Solutions

### Solution 1: Grouping and Aggregation

We can group by `machine_id` and use the `AVG` function to calculate the average time consumption of all process tasks on each machine. Since each process task on the machine has a pair of start and end timestamps, the time consumption of each process task can be calculated by subtracting the `start` timestamp from the `end` timestamp. Therefore, we can use the `CASE WHEN` or `IF` function to calculate the time consumption of each process task, and then use the `AVG` function to calculate the average time consumption of all process tasks on each machine.

Note that each machine has $2$ process tasks, so we need to multiply the calculated average time consumption by $2$.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    machine_id,
    ROUND(
        AVG(
            CASE
                WHEN activity_type = 'start' THEN -timestamp
                ELSE timestamp
            END
        ) * 2,
        3
    ) AS processing_time
FROM Activity
GROUP BY 1;
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    machine_id,
    ROUND(AVG(IF(activity_type = 'start', -1, 1) * timestamp) * 2, 3) AS processing_time
FROM Activity
GROUP BY 1;
```

<!-- tabs:end -->

<!-- end -->
