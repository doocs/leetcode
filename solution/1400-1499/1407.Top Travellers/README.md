# [1407. 排名靠前的旅行者](https://leetcode.cn/problems/top-travellers)

[English Version](/solution/1400-1499/1407.Top%20Travellers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Users</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| name          | varchar |
+---------------+---------+
id 是该表单主键。
name 是用户名字。</pre>

<p> </p>

<p>表：<code>Rides</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| user_id       | int     |
| distance      | int     |
+---------------+---------+
id 是该表单主键。
user_id 是本次行程的用户的 id, 而该用户此次行程距离为 distance 。
</pre>

<p> </p>

<p>写一段 SQL , 报告每个用户的旅行距离。</p>

<p>返回的结果表单，以 <code>travelled_distance</code> <strong>降序排列</strong> ，如果有两个或者更多的用户旅行了相同的距离, 那么再以 <code>name</code> <strong>升序排列</strong> 。</p>

<p>查询结果格式如下例所示。</p>

<pre>
Users 表：
+------+-----------+
| id   | name      |
+------+-----------+
| 1    | Alice     |
| 2    | Bob       |
| 3    | Alex      |
| 4    | Donald    |
| 7    | Lee       |
| 13   | Jonathan  |
| 19   | Elvis     |
+------+-----------+

Rides 表：
+------+----------+----------+
| id   | user_id  | distance |
+------+----------+----------+
| 1    | 1        | 120      |
| 2    | 2        | 317      |
| 3    | 3        | 222      |
| 4    | 7        | 100      |
| 5    | 13       | 312      |
| 6    | 19       | 50       |
| 7    | 7        | 120      |
| 8    | 19       | 400      |
| 9    | 7        | 230      |
+------+----------+----------+

Result 表：
+----------+--------------------+
| name     | travelled_distance |
+----------+--------------------+
| Elvis    | 450                |
| Lee      | 450                |
| Bob      | 317                |
| Jonathan | 312                |
| Alex     | 222                |
| Alice    | 120                |
| Donald   | 0                  |
+----------+--------------------+
Elvis 和 Lee 旅行了 450 英里，Elvis 是排名靠前的旅行者，因为他的名字在字母表上的排序比 Lee 更小。
Bob, Jonathan, Alex 和 Alice 只有一次行程，我们只按此次行程的全部距离对他们排序。
Donald 没有任何行程, 他的旅行距离为 0。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT name,
    COALESCE(SUM(distance), 0) AS travelled_distance
FROM Users AS u
    LEFT JOIN Rides AS r ON u.id = r.user_id
GROUP BY
    name
ORDER BY
    travelled_distance DESC,
    name;
```

<!-- tabs:end -->
