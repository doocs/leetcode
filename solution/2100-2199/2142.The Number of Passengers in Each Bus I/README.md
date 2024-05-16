---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2142.The%20Number%20of%20Passengers%20in%20Each%20Bus%20I/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2142. 每辆车的乘客人数 I 🔒](https://leetcode.cn/problems/the-number-of-passengers-in-each-bus-i)

[English Version](/solution/2100-2199/2142.The%20Number%20of%20Passengers%20in%20Each%20Bus%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Buses</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| bus_id       | int  |
| arrival_time | int  |
+--------------+------+
bus_id 是该表的主键。
该表的每一行都包含关于 LeetCode 站点的公交车到达时间的信息。
不会出现两辆公交车会同时到达。</pre>

<p>&nbsp;</p>

<p>表: <code>Passengers</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| passenger_id | int  |
| arrival_time | int  |
+--------------+------+
passenger_id 是该表的主键。
该表的每一行都包含乘客到达 LeetCode 站的时间信息。
</pre>

<p>&nbsp;</p>

<p>公交车和乘客到达 LeetCode 站。如果一辆公交车在时间 <code>t<sub>bus</sub></code> 到站，乘客在时间 <code>t<sub>passenger</sub></code> 到站，其中 <code>t<sub>passenger</sub> &lt;= t<sub>bus</sub></code>，该乘客之前没有赶上任何公交车，则该乘客将搭乘该公交车。</p>

<p>编写一个 SQL 来查询使用每辆公交车的用户数量。</p>

<p>返回按 <code>bus_id</code> <strong>升序排序&nbsp;</strong>的结果表。</p>

<p>查询结果格式如下所示。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Buses 表:
+--------+--------------+
| bus_id | arrival_time |
+--------+--------------+
| 1      | 2            |
| 2      | 4            |
| 3      | 7            |
+--------+--------------+
Passengers 表:
+--------------+--------------+
| passenger_id | arrival_time |
+--------------+--------------+
| 11           | 1            |
| 12           | 5            |
| 13           | 6            |
| 14           | 7            |
+--------------+--------------+
<strong>输出:</strong> 
+--------+----------------+
| bus_id | passengers_cnt |
+--------+----------------+
| 1      | 1              |
| 2      | 0              |
| 3      | 3              |
+--------+----------------+
<strong>解释:</strong> 
- 11 号乘客在时间 1 到达。
- 1 号公交车到达时间为 2，搭载 11 号乘客。

- 2 号公交车车在时间 4 到达，没有乘客。

- 12 号乘客在时间 5 到达。
- 13 号乘客在时间 6 到达。
- 14 号乘客在时间 7 到达。
- 3 号车在时间 7 到达，搭载 12、13、14 号乘客。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    bus_id,
    COUNT(passenger_id) - LAG(COUNT(passenger_id), 1, 0) OVER (
        ORDER BY b.arrival_time
    ) AS passengers_cnt
FROM
    Buses AS b
    LEFT JOIN Passengers AS p ON p.arrival_time <= b.arrival_time
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
