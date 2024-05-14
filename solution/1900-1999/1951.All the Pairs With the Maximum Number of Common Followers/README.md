---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1951.All%20the%20Pairs%20With%20the%20Maximum%20Number%20of%20Common%20Followers/README.md
tags:
    - 数据库
---

# [1951. 查询具有最多共同关注者的所有两两结对组 🔒](https://leetcode.cn/problems/all-the-pairs-with-the-maximum-number-of-common-followers)

[English Version](/solution/1900-1999/1951.All%20the%20Pairs%20With%20the%20Maximum%20Number%20of%20Common%20Followers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Relations</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| user_id     | int  |
| follower_id | int  |
+-------------+------+
(user_id, follower_id) 是这个表的主键.
这个表的每一行，表示这个user_id的用户和他的关注者，关注者的id 就是本表的 user_id.
</pre>

<p>&nbsp;</p>

<p>写出一个查询语句，找到具有最多共同关注者的所有两两结对组。换句话说，如果有两个用户的共同关注者是最大的，我们应该返回所有具有此最大值的两两结对组</p>

<p>结果返回表，每一行应该包含<code>user1_id</code>和&nbsp;<code>user2_id，</code>其中<code>user1_id &lt; user2_id</code>.</p>

<p>返回结果&nbsp;<strong>不要求顺序</strong>&nbsp;。</p>

<p>查询结果格式如下例：</p>

<p>&nbsp;</p>

<pre>
Relations 表:
+---------+-------------+
| user_id | follower_id |
+---------+-------------+
| 1       | 3           |
| 2       | 3           |
| 7       | 3           |
| 1       | 4           |
| 2       | 4           |
| 7       | 4           |
| 1       | 5           |
| 2       | 6           |
| 7       | 5           |
+---------+-------------+

Result 表:
+----------+----------+
| user1_id | user2_id |
+----------+----------+
| 1        | 7        |
+----------+----------+

用户1 和用户 2 有2个共同的关注者（3和4）。
用户1 和用户 7 有3个共同的关注者（3，4和5）。
用户2 和用户7 有2个共同的关注者（3和4）。
既然两两结对的所有组队的最大共同关注者的数值是3，所以，我们应该返回所有拥有3个共同关注者的两两组队，这就是仅有的一对(1, 7).
我们返回的是(1, 7).，而不是(7, 1).
注意，我们没有关于用户3，4，5的任何关注者信息，我们认为他们有0个关注者。
</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            r1.user_id AS user1_id,
            r2.user_id AS user2_id,
            RANK() OVER (ORDER BY COUNT(1) DESC) AS rk
        FROM
            Relations AS r1
            JOIN Relations AS r2 ON r1.follower_id = r2.follower_id AND r1.user_id < r2.user_id
        GROUP BY r1.user_id, r2.user_id
    )
SELECT
    user1_id,
    user2_id
FROM t
WHERE rk = 1;
```

<!-- tabs:end -->

<!-- end -->
