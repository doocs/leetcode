---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1141.User%20Activity%20for%20the%20Past%2030%20Days%20I/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1141. 查询近30天活跃用户数](https://leetcode.cn/problems/user-activity-for-the-past-30-days-i)

[English Version](/solution/1100-1199/1141.User%20Activity%20for%20the%20Past%2030%20Days%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Activity</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| session_id    | int     |
| activity_date | date    |
| activity_type | enum    |
+---------------+---------+
该表没有包含重复数据。
activity_type 列是 ENUM(category) 类型， 从 ('open_session'， 'end_session'， 'scroll_down'， 'send_message') 取值。
该表记录社交媒体网站的用户活动。
注意，每个会话只属于一个用户。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，统计截至&nbsp;<code>2019-07-27</code>（包含2019-07-27），近<strong>&nbsp;</strong><code>30</code> 天的每日活跃用户数（当天只要有一条活动记录，即为活跃用户）。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果示例如下。</p>

<p><strong>注意</strong>：（<code>'open_session'</code>，<code>'end_session'</code>，<code>'scroll_down'</code>，<code>'send_message'</code>）中的任何活动将被视为用户在某一天活跃的有效活动。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Activity table:
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
| 4       | 3          | 2019-06-25    | open_session  |
| 4       | 3          | 2019-06-25    | end_session   |
+---------+------------+---------------+---------------+
<strong>输出：</strong>
+------------+--------------+ 
| day        | active_users |
+------------+--------------+ 
| 2019-07-20 | 2            |
| 2019-07-21 | 2            |
+------------+--------------+ <strong>
解释：</strong>注意非活跃用户的记录不需要展示。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：GROUP BY + HAVING

我们查询出所有在 `2019-07-27` 且在 $30$ 天内的所有活动记录，然后按照日期分组，统计每天的去重活跃用户数。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT activity_date AS day, COUNT(DISTINCT user_id) AS active_users
FROM Activity
WHERE activity_date <= '2019-07-27' AND DATEDIFF('2019-07-27', activity_date) < 30
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
