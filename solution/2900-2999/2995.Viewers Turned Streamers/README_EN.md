# [2995. Viewers Turned Streamers 🔒](https://leetcode.com/problems/viewers-turned-streamers)

[中文文档](/solution/2900-2999/2995.Viewers%20Turned%20Streamers/README.md)

<!-- tags:Database -->

## Description

<p>Table: <code>Sessions</code></p>

<pre>
+---------------+----------+
| Column Name   | Type     |
+---------------+----------+
| user_id       | int      |
| session_start | datetime |
| session_end   | datetime |
| session_id    | int      |
| session_type  | enum     |
+---------------+----------+
session_id is column of unique values for this table.
session_type is an ENUM (category) type of (Viewer, Streamer).
This table contains user id, session start, session end, session id and session type.
</pre>

<p>Write a solution to find the number of <strong>streaming</strong> sessions for users whose <strong>first session </strong>was as a <strong>viewer</strong>.</p>

<p>Return <em>the result table ordered by count of streaming sessions, </em> <code>user_id</code><em> in <strong>descending</strong> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Sessions table:
+---------+---------------------+---------------------+------------+--------------+
| user_id | session_start       | session_end         | session_id | session_type | 
+---------+---------------------+---------------------+------------+--------------+
| 101     | 2023-11-06 13:53:42 | 2023-11-06 14:05:42 | 375        | Viewer       |  
| 101     | 2023-11-22 16:45:21 | 2023-11-22 20:39:21 | 594        | Streamer     |   
| 102     | 2023-11-16 13:23:09 | 2023-11-16 16:10:09 | 777        | Streamer     | 
| 102     | 2023-11-17 13:23:09 | 2023-11-17 16:10:09 | 778        | Streamer     | 
| 101     | 2023-11-20 07:16:06 | 2023-11-20 08:33:06 | 315        | Streamer     | 
| 104     | 2023-11-27 03:10:49 | 2023-11-27 03:30:49 | 797        | Viewer       | 
| 103     | 2023-11-27 03:10:49 | 2023-11-27 03:30:49 | 798        | Streamer     |  
+---------+---------------------+---------------------+------------+--------------+
<strong>Output:</strong> 
+---------+----------------+
| user_id | sessions_count | 
+---------+----------------+
| 101     | 2              | 
+---------+----------------+
<strong>Explanation</strong>
- user_id 101, initiated their initial session as a viewer on 2023-11-06 at 13:53:42, followed by two subsequent sessions as a Streamer, the count will be 2.
- user_id 102, although there are two sessions, the initial session was as a Streamer, so this user will be excluded.
- user_id 103 participated in only one session, which was as a Streamer, hence, it won&#39;t be considered.
- User_id 104 commenced their first session as a viewer but didn&#39;t have any subsequent sessions, therefore, they won&#39;t be included in the final count. 
Output table is ordered by sessions count and user_id in descending order.
</pre>

## Solutions

### Solution 1: Window Function + Equi-Join

We can use the window function `RANK()` to rank each session by `user_id` dimension, and record it in table `T`. Then, we equi-join `T` and the `Sessions` table by `user_id`, and filter out the records in `T` where the rank is 1, and `session_type` is `Viewer`, and `session_type` in the `Sessions` table is `Streamer`. Finally, we group by `user_id` and sum up.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            user_id,
            session_type,
            RANK() OVER (
                PARTITION BY user_id
                ORDER BY session_start
            ) AS rk
        FROM Sessions
    )
SELECT user_id, COUNT(1) AS sessions_count
FROM
    T AS t
    JOIN Sessions AS s USING (user_id)
WHERE rk = 1 AND t.session_type = 'Viewer' AND s.session_type = 'Streamer'
GROUP BY 1
ORDER BY 2 DESC, 1 DESC;
```

<!-- tabs:end -->

<!-- end -->
