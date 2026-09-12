---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1107.New%20Users%20Daily%20Count/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1107. 每日新用户统计 🔒](https://leetcode.cn/problems/new-users-daily-count)

[English Version](/solution/1100-1199/1107.New%20Users%20Daily%20Count/README_EN.md)

## 题目描述

<!-- description:start -->

<p><code>Traffic</code>&nbsp;表：</p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| activity      | enum    |
| activity_date | date    |
+---------------+---------+
该表可能有重复的行。
activity 列是 ENUM 类型，可能取 ('login', 'logout', 'jobs', 'groups', 'homepage') 几个值之一。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，找出从今天起最多 <code>90</code> 天内，每个日期首次登录的用户数。假设今天是&nbsp;<strong>2019-06-30 </strong>。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Traffic 表：
+---------+----------+---------------+
| user_id | activity | activity_date |
+---------+----------+---------------+
| 1       | login    | 2019-05-01    |
| 1       | homepage | 2019-05-01    |
| 1       | logout   | 2019-05-01    |
| 2       | login    | 2019-06-21    |
| 2       | logout   | 2019-06-21    |
| 3       | login    | 2019-01-01    |
| 3       | jobs     | 2019-01-01    |
| 3       | logout   | 2019-01-01    |
| 4       | login    | 2019-06-21    |
| 4       | groups   | 2019-06-21    |
| 4       | logout   | 2019-06-21    |
| 5       | login    | 2019-03-01    |
| 5       | logout   | 2019-03-01    |
| 5       | login    | 2019-06-21    |
| 5       | logout   | 2019-06-21    |
+---------+----------+---------------+
<strong>输出：</strong>
+------------+-------------+
| login_date | user_count  |
+------------+-------------+
| 2019-05-01 | 1           |
| 2019-06-21 | 2           |
+------------+-------------+
<strong>解释：</strong>
请注意，我们只关心用户数非零的日期.
ID 为 5 的用户第一次登陆于 2019-03-01，因此他不算在 2019-06-21 的的统计内。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 「新用户」指该用户在 `Traffic` 中最早一次 `login`。窗口函数 `MIN(activity_date) OVER (PARTITION BY user_id)` 可在一行内得到每人的首次登录日，再筛出距 `2019-06-30` 不超过 $90$ 天的日期，按日 `COUNT(DISTINCT user_id)`。
>
> 先取首次登录再聚合，避免把同一用户日后的 login 重复计入某日。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            user_id,
            MIN(activity_date) OVER (PARTITION BY user_id) AS login_date
        FROM Traffic
        WHERE activity = 'login'
    )
SELECT login_date, COUNT(DISTINCT user_id) AS user_count
FROM T
WHERE DATEDIFF('2019-06-30', login_date) <= 90
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
