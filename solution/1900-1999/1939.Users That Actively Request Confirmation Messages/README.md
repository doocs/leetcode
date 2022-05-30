# [1939. 主动请求确认消息的用户](https://leetcode.cn/problems/users-that-actively-request-confirmation-messages)

[English Version](/solution/1900-1999/1939.Users%20That%20Actively%20Request%20Confirmation%20Messages/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Signups</code></p>

<pre>+----------------+----------+
| Column Name    | Type     |
+----------------+----------+
| user_id        | int      |
| time_stamp     | datetime |
+----------------+----------+
user_id is the primary key for this table.
每行包含有关 ID 为 user_id 的用户的注册时间的信息。</pre>

<p>&nbsp;</p>

<p>Table: <code>Confirmations</code></p>

<pre>+----------------+----------+
| Column Name    | Type     |
+----------------+----------+
| user_id        | int      |
| time_stamp     | datetime |
| action         | ENUM     |
+----------------+----------+
(user_id, time_stamp) is the primary key for this table.
user_id is a foreign key with a reference to the Signups table.
action is an ENUM of the type ('confirmed', 'timeout')
此表的每一行都表示 ID 为 user_id 的用户在 time_stamp 请求了确认消息，并且该确认消息已被确认（'confirmed'）或已过期（'timeout'）。</pre>

<p>编写 SQL 查询以查找在 24 小时窗口内两次请求确认消息的用户的 ID。 两个正好相隔 24 小时的消息被认为是在窗口内。 该操作不会影响答案，只会影响请求时间。</p>

<p>以任意顺序返回结果表。</p>

<p>查询结果格式如下例：</p>

<pre>Signups table:
+---------+---------------------+
| user_id | time_stamp          |
+---------+---------------------+
| 3       | 2020-03-21 10:16:13 |
| 7       | 2020-01-04 13:57:59 |
| 2       | 2020-07-29 23:09:44 |
| 6       | 2020-12-09 10:39:37 |
+---------+---------------------+

Confirmations table:
+---------+---------------------+-----------+
| user_id | time_stamp          | action    |
+---------+---------------------+-----------+
| 3       | 2021-01-06 03:30:46 | timeout   |
| 3       | 2021-01-06 03:37:45 | timeout   |
| 7       | 2021-06-12 11:57:29 | confirmed |
| 7       | 2021-06-13 11:57:30 | confirmed |
| 2       | 2021-01-22 00:00:00 | confirmed |
| 2       | 2021-01-23 00:00:00 | timeout   |
| 6       | 2021-10-23 14:14:14 | confirmed |
| 6       | 2021-10-24 14:14:13 | timeout   |
+---------+---------------------+-----------+

Result table
+---------+
| user_id |
+---------+
| 2       |
| 3       |
| 6       |
+---------+
</pre>

<p>&nbsp;</p>

<p>用户 2 在彼此恰好 24 小时内请求了两条消息，因此我们将它们包括在内。 用户 3 在 6 分 59 秒内请求了两条消息，因此我们将它们包括在内。 用户 6 在 23 小时 59 分 59 秒内请求了两条消息，因此我们将它们包括在内。 用户 7 在 24 小时 1 秒内请求了两条消息，因此我们将它们从答案中排除。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql
SELECT
    DISTINCT c1.user_id AS user_id
FROM
    Confirmations c1
INNER JOIN Confirmations c2
ON c1.user_id = c2.user_id
WHERE c1.time_stamp < c2.time_stamp
AND TIMESTAMPDIFF(SECOND, c1.time_stamp, c2.time_stamp) <= 24 * 60 * 60;
```

<!-- tabs:end -->
