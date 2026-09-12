---
comments: true
difficulty: 中等
tags:
    - 数据库
---

<!-- problem:start -->

# [2238. 司机成为乘客的次数 🔒](https://leetcode.cn/problems/number-of-times-a-driver-was-a-passenger)

[English Version](/solution/2200-2299/2238.Number%20of%20Times%20a%20Driver%20Was%20a%20Passenger/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Rides</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| ride_id      | int  |
| driver_id    | int  |
| passenger_id | int  |
+--------------+------+
ride_id 是该表的主键（具有唯一值的列）。
该表的每一行都包含驾驶员的 ID 和在 ride_id 中乘车的乘客的 ID。
注意 driver_id != passenger_id。</pre>

<p>&nbsp;</p>

<p>编写解决方案，获取每个司机的 ID 和他们作为乘客的次数。</p>

<p data-group="1-1">以&nbsp;<strong>任意顺序&nbsp;</strong>返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Rides 表:
+---------+-----------+--------------+
| ride_id | driver_id | passenger_id |
+---------+-----------+--------------+
| 1       | 7         | 1            |
| 2       | 7         | 2            |
| 3       | 11        | 1            |
| 4       | 11        | 7            |
| 5       | 11        | 7            |
| 6       | 11        | 3            |
+---------+-----------+--------------+
<strong>输出:</strong> 
+-----------+-----+
| driver_id | cnt |
+-----------+-----+
| 7         | 2   |
| 11        | 0   |
+-----------+-----+
<strong>解释:</strong> 
在所有给定的行程中有两名司机: 7 和 11.
ID = 7 的司机曾两次成为乘客。
ID = 11 的司机从来不是乘客。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 对每位出现过的司机，统计他作为乘客出现的次数；从未当过乘客则为 $0$。直接在 $\textit{Rides}$ 上按乘客分组会丢掉只当司机、从未坐车的人。
>
> 先取出所有不同的 $\textit{driver\_id}$，再左连接 $\textit{Rides}$，连接条件是「该司机等于某行的乘客」。分组计数 $passenger\_id$ 即可，左连接保证零次乘客仍保留。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH T AS (SELECT DISTINCT driver_id FROM Rides)
SELECT t.driver_id, COUNT(passenger_id) AS cnt
FROM
    T AS t
    LEFT JOIN Rides AS r ON t.driver_id = r.passenger_id
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
