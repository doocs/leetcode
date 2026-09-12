---
comments: true
difficulty: 简单
tags:
    - 数据库
---

<!-- problem:start -->

# [1142. 过去30天的用户活动 II 🔒](https://leetcode.cn/problems/user-activity-for-the-past-30-days-ii)

[English Version](/solution/1100-1199/1142.User%20Activity%20for%20the%20Past%2030%20Days%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p><code>Activity</code> 表：</p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| session_id    | int     |
| activity_date | date    |
| activity_type | enum    |
+---------------+---------+
该表没有主键，它可能有重复的行。
activity_type 列是 ENUM 类型，可以取（“ open_session”，“ end_session”，“ scroll_down”，“ send_message”）四种活动类型之一。
该表显示了社交媒体网站的用户活动。
请注意，每个会话只属于一个用户。</pre>

<p>&nbsp;</p>

<p>编写解决方案，统计截至 <code>2019-07-27</code>（含）的 <code>30</code> 天内每个用户的平均会话数，<strong>四舍五入到小数点后两位</strong>。只统计那些会话期间用户至少进行一项活动的有效会话。</p>

<p>结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
Activity 表：
+---------+------------+---------------+---------------+
| user_id | session_id | activity_date | activity_type |
+---------+------------+---------------+---------------+
| 1       | 1          | 2019-07-20    | open_session  |
| 1       | 1          | 2019-07-20    | scroll_down   |
| 1       | 1          | 2019-07-20    | end_session   |
| 2       | 4          | 2019-07-20    | open_session  |
| 2       | 4          | 2019-07-21    | send_message  |
| 2       | 4          | 2019-07-21    | end_session   |
| 3       | 2          | 2019-07-21    | open_session  |
| 3       | 2          | 2019-07-21    | send_message  |
| 3       | 2          | 2019-07-21    | end_session   |
| 3       | 5          | 2019-07-21    | open_session  |
| 3       | 5          | 2019-07-21    | scroll_down   |
| 3       | 5          | 2019-07-21    | end_session   |
| 4       | 3          | 2019-06-25    | open_session  |
| 4       | 3          | 2019-06-25    | end_session   |
+---------+------------+---------------+---------------+
<strong>输出：</strong>
+---------------------------+ 
| average_sessions_per_user |
+---------------------------+ 
| 1.33                      |
+---------------------------+
<strong>解释：</strong>用户 1 和 2 每人在过去 30 天有 1 个会话，而用户 3 有 2 个会话。所以平均是 (1 + 1 + 2) / 3 = 1.33 。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 先按用户统计窗口内不同 `session_id` 个数，再对这些会话数取平均，空表时用 `IFNULL` 得到 $0$。窗口条件与上一题相同。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            COUNT(DISTINCT session_id) AS sessions
        FROM Activity
        WHERE activity_date <= '2019-07-27' AND DATEDIFF('2019-07-27', activity_date) < 30
        GROUP BY user_id
    )
SELECT IFNULL(ROUND(AVG(sessions), 2), 0) AS average_sessions_per_user
FROM T;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 方法一先按用户聚合再平均。若每名用户的会话互不重叠，则 `COUNT(DISTINCT session_id)/COUNT(DISTINCT user_id)` 与「先按人再平均」在本数据下同解，少一层 CTE。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
SELECT
    IFNULL(
        ROUND(COUNT(DISTINCT session_id) / COUNT(DISTINCT user_id), 2),
        0
    ) AS average_sessions_per_user
FROM Activity
WHERE DATEDIFF('2019-07-27', activity_date) < 30;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
