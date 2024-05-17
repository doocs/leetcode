---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1972.First%20and%20Last%20Call%20On%20the%20Same%20Day/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1972. 同一天的第一个电话和最后一个电话 🔒](https://leetcode.cn/problems/first-and-last-call-on-the-same-day)

[English Version](/solution/1900-1999/1972.First%20and%20Last%20Call%20On%20the%20Same%20Day/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Calls</code></p>

<pre>
+--------------+----------+
| Column Name  | Type     |
+--------------+----------+
| caller_id    | int      |
| recipient_id | int      |
| call_time    | datetime |
+--------------+----------+
(caller_id, recipient_id, call_time) 是这个表的主键。
每一行所含的时间信息都是关于caller_id 和recipient_id的。
</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询来找出那些ID们在<strong>任意一天</strong>的第一个电话和最后一个电话都是和同一个人的。这些电话不论是拨打者还是接收者都会被记录。</p>

<p>结果请放在一个<strong>任意次序</strong>约束的表中。</p>

<p>查询结果格式如下所示：</p>

<pre>
<strong>输入：</strong>
Calls table:
+-----------+--------------+---------------------+
| caller_id | recipient_id | call_time           |
+-----------+--------------+---------------------+
| 8         | 4            | 2021-08-24 17:46:07 |
| 4         | 8            | 2021-08-24 19:57:13 |
| 5         | 1            | 2021-08-11 05:28:44 |
| 8         | 3            | 2021-08-17 04:04:15 |
| 11        | 3            | 2021-08-17 13:07:00 |
| 8         | 11           | 2021-08-17 22:22:22 |
+-----------+--------------+---------------------+
<strong>输出：</strong>
+---------+
| user_id |
+---------+
| 1       |
| 4       |
| 5       |
| 8       |
+---------+
<strong>解释：</strong>
在 2021-08-24，这天的第一个电话和最后一个电话都是在user 8和user 4之间。user8应该被包含在答案中。
同样的，user 4在2 021-08-24 的第一个电话和最后一个电话都是和user 8的。user 4也应该被包含在答案中。
在 2021-08-11，user 1和5有一个电话。这个电话是他们彼此当天的唯一一个电话。因此这个电话是他们当天的第一个电话也是最后一个电话，他们都应该被包含在答案中。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
with s as (
    select
        *
    from
        Calls
    union
    all
    select
        recipient_id,
        caller_id,
        call_time
    from
        Calls
),
t as (
    select
        caller_id user_id,
        FIRST_VALUE(recipient_id) over(
            partition by DATE_FORMAT(call_time, '%Y-%m-%d'),
            caller_id
            order by
                call_time asc
        ) first,
        FIRST_VALUE(recipient_id) over(
            partition by DATE_FORMAT(call_time, '%Y-%m-%d'),
            caller_id
            order by
                call_time desc
        ) last
    from
        s
)
select
    distinct user_id
from
    t
where
    first = last
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
