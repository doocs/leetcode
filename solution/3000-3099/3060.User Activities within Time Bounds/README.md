# [3060. User Activities within Time Bounds](https://leetcode.cn/problems/user-activities-within-time-bounds)

[English Version](/solution/3000-3099/3060.User%20Activities%20within%20Time%20Bounds/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

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

<p>Write a solution to find the the <strong>users</strong> who have had <strong>at least one</strong> <strong>consecutive session</strong> of the <strong>same</strong> type (either &#39;<strong>Viewer</strong>&#39; or &#39;<strong>Streamer</strong>&#39;) with a <strong>maximum</strong> gap of <code>12</code> hours <strong>between</strong> sessions.</p>

<p>Return <em>the result table ordered by </em><code>user_id</code><em> in <b>ascending</b> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<pre>
<strong>Input:</strong> 
Sessions table:
+---------+---------------------+---------------------+------------+--------------+
| user_id | session_start       | session_end         | session_id | session_type | 
+---------+---------------------+---------------------+------------+--------------+
| 101     | 2023-11-01 08:00:00 | 2023-11-01 09:00:00 | 1          | Viewer       |  
| 101     | 2023-11-01 10:00:00 | 2023-11-01 11:00:00 | 2          | Streamer     |   
| 102     | 2023-11-01 13:00:00 | 2023-11-01 14:00:00 | 3          | Viewer       | 
| 102     | 2023-11-01 15:00:00 | 2023-11-01 16:00:00 | 4          | Viewer       | 
| 101     | 2023-11-02 09:00:00 | 2023-11-02 10:00:00 | 5          | Viewer       | 
| 102     | 2023-11-02 12:00:00 | 2023-11-02 13:00:00 | 6          | Streamer     | 
| 101     | 2023-11-02 13:00:00 | 2023-11-02 14:00:00 | 7          | Streamer     | 
| 102     | 2023-11-02 16:00:00 | 2023-11-02 17:00:00 | 8          | Viewer       | 
| 103     | 2023-11-01 08:00:00 | 2023-11-01 09:00:00 | 9          | Viewer       | 
| 103     | 2023-11-02 20:00:00 | 2023-11-02 23:00:00 | 10         | Viewer       | 
| 103     | 2023-11-03 09:00:00 | 2023-11-03 10:00:00 | 11         | Viewer       | 
+---------+---------------------+---------------------+------------+--------------+
<strong>Output:</strong> 
+---------+
| user_id |
+---------+
| 102     |
| 103     |
+---------+
<strong>Explanation:</strong>
- User ID 101 will not be included in the final output as they do not have any consecutive sessions of the same session type.
- User ID 102 will be included in the final output as they had two viewer sessions with session IDs 3 and 4, respectively, and the time gap between them was less than 12 hours.
- User ID 103 participated in two viewer sessions with a gap of less than 12 hours between them, identified by session IDs 10 and 11. Therefore, user 103 will be included in the final output.
Output table is ordered by user_id in increasing order.
</pre>

## 解法

### 方法一：窗口函数 + 时间函数

我们先使用 `LAG` 窗口函数，找到每个用户相同类型的会话的上一个会话的结束时间，记为 `prev_session_end`。然后我们使用 `TIMESTAMPDIFF` 函数计算当前会话的开始时间与上一个会话的结束时间的时间差，如果时间差小于等于 12 小时，那么这个用户就符合题目要求。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            user_id,
            session_start,
            LAG(session_end) OVER (
                PARTITION BY user_id, session_type
                ORDER BY session_end
            ) AS prev_session_end
        FROM Sessions
    )
SELECT DISTINCT
    user_id
FROM T
WHERE TIMESTAMPDIFF(HOUR, prev_session_end, session_start) <= 12;
```

<!-- tabs:end -->

<!-- end -->
