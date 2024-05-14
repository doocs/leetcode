---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2153.The%20Number%20of%20Passengers%20in%20Each%20Bus%20II/README.md
tags:
    - 数据库
---

# [2153. 每辆车的乘客人数 II 🔒](https://leetcode.cn/problems/the-number-of-passengers-in-each-bus-ii)

[English Version](/solution/2100-2199/2153.The%20Number%20of%20Passengers%20in%20Each%20Bus%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Buses</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| bus_id       | int  |
| arrival_time | int  |
| capacity     | int  |
+--------------+------+
bus_id 包含唯一的值。
该表的每一行都包含关于公交车到达 LeetCode 站点的时间和它的容量 (空座位的数量) 的信息。
不会出现两辆公交车同时到达，所有公交车的容量都是正整数。
</pre>

<p>&nbsp;</p>

<p>表: <code>Passengers</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| passenger_id | int  |
| arrival_time | int  |
+--------------+------+
passenger_id 包含唯一的值。
该表的每一行都包含乘客到达 LeetCode 站的时间信息。
</pre>

<p>&nbsp;</p>

<p>公交车和乘客到达 LeetCode 站。如果一辆公交车在 <code>t<sub>bus</sub></code> 时间点到达车站，乘客在 <code>t<sub>passenger</sub></code> 到达车站，其中&nbsp;<code>t<sub>passenger</sub> &lt;= t<sub>bus</sub></code>，而该乘客没有赶上任何公交车，则该乘客将搭乘该公交车。此外，每辆公交车都有一个容量。如果在公交车到站的那一刻，等待的乘客超过了它的载客量 <code>capacity</code>，只有&nbsp;<code>capacity</code> 个乘客才会搭乘该公交车。</p>

<p>编写解决方案，报告使用每条总线的用户数量。</p>

<p>返回按 <code>bus_id</code> <strong>升序排序&nbsp;</strong>的结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Buses 表:
+--------+--------------+----------+
| bus_id | arrival_time | capacity |
+--------+--------------+----------+
| 1      | 2            | 1        |
| 2      | 4            | 10       |
| 3      | 7            | 2        |
+--------+--------------+----------+
Passengers 表:
+--------------+--------------+
| passenger_id | arrival_time |
+--------------+--------------+
| 11           | 1            |
| 12           | 1            |
| 13           | 5            |
| 14           | 6            |
| 15           | 7            |
+--------------+--------------+
<strong>输出:</strong> 
+--------+----------------+
| bus_id | passengers_cnt |
+--------+----------------+
| 1      | 1              |
| 2      | 1              |
| 3      | 2              |
+--------+----------------+
<strong>解释:</strong> 
- 11 号乘客在时间 1 到达。
- 12 号乘客在时间 1 到达。
- 1 号公交车到达时间为 2，因为有一个空座位，所以搭载了 11 号乘客。

- 2 号公交车在时间 4 到达，搭载了12 号乘客，因为它有 10 个空座位。

- 13 号乘客在时间 5 到达。
- 14 号乘客在时间 6 到达。
- 15 号乘客在时间 7 到达。
- 3 号公交车在时间 7 到达，车上有两个空座位，搭载了 12 号和 13 号乘客。</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            SUM(cnt) OVER (ORDER BY dt, bus_id) AS cur,
            IF(@t > 0, @t := cnt, @t := @t + cnt) AS cur_sum
        FROM
            (
                SELECT bus_id, arrival_time AS dt, capacity AS cnt FROM Buses
                UNION ALL
                SELECT -1, arrival_time AS dt, -1 FROM Passengers
            ) AS a JOIN (SELECT @t := 0 x) AS b
    )
SELECT
    bus_id,
    IF(cur_sum > 0, cnt - cur_sum, cnt) AS passengers_cnt
FROM T
WHERE bus_id > 0
ORDER BY bus_id;
```

<!-- tabs:end -->

<!-- end -->
