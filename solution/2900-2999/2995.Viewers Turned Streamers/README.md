---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2995.Viewers%20Turned%20Streamers/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2995. 观众变主播 🔒](https://leetcode.cn/problems/viewers-turned-streamers)

[English Version](/solution/2900-2999/2995.Viewers%20Turned%20Streamers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：&nbsp;<code>Sessions</code></p>

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
session_id 是这张表具有唯一值的列。
session_type 是一个 ENUM (枚举) 类型，包含(Viewer, Streamer)两个类别。
这张表包含 user id, session start, session end, session id 和 session type。
</pre>

<p>编写一个解决方案，找到 <strong>首次会话</strong> 为 <strong>观众身份</strong> 的用户，其 <strong>主播会话</strong> 数量。</p>

<p>按照会话数量和 <code>user_id</code> <strong>降序</strong> 排序返回结果表。</p>

<p>结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>
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
<b>输出：</b>
+---------+----------------+
| user_id | sessions_count | 
+---------+----------------+
| 101     | 2              | 
+---------+----------------+
<b>解释</b>
- user_id 101，在 2023-11-06 13:53:42 以观众身份开始了他们的初始会话，随后进行了两次主播会话，所以计数为 2。
- user_id 102，尽管有两个会话，但初始会话是作为主播，因此将排除此用户。
- user_id 103 只参与了一次会话，即作为主播，因此不会考虑在内。
- User_id 104 以观众身份开始了他们的第一次会话，但没有后续会话，因此不会包括在最终计数中。
输出表按照会话数量和 user_id 降序排序。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：窗口函数 + 等值连接

我们可以用窗口函数 `RANK()` 按照 `user_id` 维度，对每个会话进行排名，记录在表 `T` 中，然后再将 `T` 与 `Sessions` 表按照 `user_id` 进行等值连接，并且筛选出 `T` 中排名为 1 的记录，并且 `session_type` 为 `Viewer`，`Sessions` 表中 `session_type` 为 `Streamer` 的记录，最后按照 `user_id` 进行分组求和即可。

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

<!-- solution:end -->

<!-- problem:end -->
