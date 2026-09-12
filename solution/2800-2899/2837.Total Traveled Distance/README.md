---
comments: true
difficulty: 简单
tags:
    - 数据库
---

<!-- problem:start -->

# [2837. 总旅行距离 🔒](https://leetcode.cn/problems/total-traveled-distance)

[English Version](/solution/2800-2899/2837.Total%20Traveled%20Distance/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code><font face="monospace">Users</font></code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| name        | varchar |
+-------------+---------+
user_id 是此表中具有唯一值的列。 
此表的每一行包含用户 id 和姓名。
</pre>

<p>表：<code>Rides</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| ride_id      | int  |
| user_id      | int  | 
| distance     | int  |
+--------------+------+
ride_id 是此表中具有唯一值的列。 
此表中的每一行包含乘车 id、用户 id 和旅行距离。
</pre>

<p>编写一个解决方案，计算每个用户的旅行距离&nbsp;<code>distance</code> 。如果有用户没有任何旅行记录，那么他们的 <code>distance</code>&nbsp;应被视为 <code>0</code> 。输出 <code>user_id</code>,&nbsp;<code>name</code>&nbsp;和总旅行距离&nbsp;<code>traveled distance</code> 。</p>

<p>按 <strong>升序排序</strong> 的 <code>user_id</code> 返回结果表。</p>

<p>结果格式如下示例。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>
Users table:
+---------+---------+
| user_id | name    |
+---------+---------+
| 17      | Addison |
| 14      | Ethan   |
| 4       | Michael |
| 2       | Avery   |
| 10      | Eleanor |
+---------+---------+
Rides table:
+---------+---------+----------+
| ride_id | user_id | distance |
+---------+---------+----------+
| 72      | 17      | 160      |
| 42      | 14      | 161      |
| 45      | 4       | 59       |
| 32      | 2       | 197      |
| 15      | 4       | 357      |
| 56      | 2       | 196      |
| 10      | 14      | 25       |
+---------+---------+----------+
<b>输出：</b>
+---------+---------+-------------------+
| user_id | name    | traveled distance |
+---------+---------+-------------------+
| 2       | Avery   | 393               |
| 4       | Michael | 416               |
| 10      | Eleanor | 0                 |
| 14      | Ethan   | 186               |
| 17      | Addison | 160               |
+---------+---------+-------------------+
<b>解释：</b>
-  User id 为 2 的用户完成了两次旅行，分别为 197 和 196，总旅行距离为 393。
-  User id 为 4 的用户完成了两次旅行，分别为 59 和 357，总旅行距离为 416。
-  User id 为 14 的用户完成了两次旅行，分别为 161 和 25，总旅行距离为 186。
-  User id 为 16 的用户只完成了一次旅行，距离为 160。
-  User id 为 10 的用户没有完成任何旅行，因此总旅行距离为 0。
按升序排序的 <code>user_id</code> 返回结果表</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：左连接 + 分组求和

<!-- thinking:start -->

> **思考**
>
> 需要每位用户的骑行总里程，无骑行者应记 $0$。左连接保留全部用户，再按 `user_id` 对 `distance` 求和，并用 `IFNULL` 把空和换成 $0$。

<!-- thinking:end -->

我们可以使用左连接将两张表连接起来，然后使用分组求和的方式计算每个用户的总距离。注意，如果用户没有完成任何骑行，那么他的距离应该被视为 $0$。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT user_id, name, IFNULL(SUM(distance), 0) AS 'traveled distance'
FROM
    Users
    LEFT JOIN Rides USING (user_id)
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
