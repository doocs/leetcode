---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2984.Find%20Peak%20Calling%20Hours%20for%20Each%20City/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2984. 找到每座城市的高峰通话小时 🔒](https://leetcode.cn/problems/find-peak-calling-hours-for-each-city)

[English Version](/solution/2900-2999/2984.Find%20Peak%20Calling%20Hours%20for%20Each%20City/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：&nbsp;<code>Calls</code></p>

<pre>
+--------------+----------+
| Column Name  | Type     |
+--------------+----------+
| caller_id    | int      |
| recipient_id | int      |
| call_time    | datetime |
| city         | varchar  |
+--------------+----------+
(caller_id, recipient_id, call_time) 是该表的主键(具有唯一值的列)。
每一行包含 caller id, recipient id, call time,和 city。
</pre>

<p>编写一个查询，找到每个 <code>city</code>&nbsp;的 <strong>高峰</strong> 通话 <b>小时</b>。如果 <strong>多个小时</strong>&nbsp;有 <strong>相同</strong> 数量的通话，则所有这些小时都将被视为该特定城市的 <strong>高峰小时</strong>。</p>

<p>按照 <strong>高峰小时</strong>&nbsp;和<em> </em><code>city</code><em> </em>按 <strong>降序</strong> 排序返回结果表。</p>

<p>结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<b>输入：</b>
Calls table:
+-----------+--------------+---------------------+----------+
| caller_id | recipient_id | call_time           | city     |
+-----------+--------------+---------------------+----------+
| 8         | 4            | 2021-08-24 22:46:07 | Houston  |
| 4         | 8            | 2021-08-24 22:57:13 | Houston  |  
| 5         | 1            | 2021-08-11 21:28:44 | Houston  |  
| 8         | 3            | 2021-08-17 22:04:15 | Houston  |
| 11        | 3            | 2021-08-17 13:07:00 | New York |
| 8         | 11           | 2021-08-17 14:22:22 | New York |
+-----------+--------------+---------------------+----------+
<b>输出：</b>
+----------+-------------------+-----------------+
| city     | peak_calling_hour | number_of_calls |
+----------+-------------------+-----------------+
| Houston  | 22                | 3               |
| New York | 14                | 1               |
| New York | 13                | 1               |
+----------+-------------------+-----------------+
<b>解释：</b>
对于 Houston：
  - 高峰小时是 22:00，总共记录了 3 次通话。 
对于 New York：
  - 13:00 和 14:00 都有相同数量的通话，因此这两个小时都被视为高峰小时。
输出表按照高峰小时和城市按降序排序。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 每个城市要求通话次数最多的小时，并列则都保留。先按城市与 $HOUR(call_time)$ 分组计数，再用窗口 $RANK$ 按次数降序编号，取出 $rk=1$。
>
> 外层按小时、城市降序排列。无需自连接求最大值。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY city
                ORDER BY cnt DESC
            ) AS rk
        FROM
            (
                SELECT
                    city,
                    HOUR(call_time) AS h,
                    COUNT(1) AS cnt
                FROM Calls
                GROUP BY 1, 2
            ) AS t
    )
SELECT city, h AS peak_calling_hour, cnt AS number_of_calls
FROM T
WHERE rk = 1
ORDER BY 2 DESC, 1 DESC;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
