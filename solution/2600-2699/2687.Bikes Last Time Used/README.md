---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2687.Bikes%20Last%20Time%20Used/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2687. 自行车的最后使用时间 🔒](https://leetcode.cn/problems/bikes-last-time-used)

[English Version](/solution/2600-2699/2687.Bikes%20Last%20Time%20Used/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Bikes</code>&nbsp;</p>

<pre>
+-------------+----------+ 
| 列名        | 类型     | 
+-------------+----------+ 
| ride_id     | int      | 
| bike_number | int      | 
| start_time  | datetime |
| end_time    | datetime |
+-------------+----------+
ride_id 是该表的主键。
每行包含一个骑行信息，包括 ride_id、自行车编号、骑行的起始和结束时间。
输入保证 start_time 和 end_time 是有效的日期值。
</pre>

<p>编写一个解决方案，找出每辆自行车 <strong>最近一次被使用</strong> 的时间。</p>

<p>返回结果表按 <strong>最近被使用</strong>&nbsp;的自行车进行排序。</p>

<p>返回结果的格式如下所示：</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<strong>输入：
</strong><code>Bikes</code> 表:
+---------+-------------+---------------------+---------------------+ 
| ride_id | bike_number | start_time          | end_time            |  
+---------+-------------+---------------------+---------------------+
| 1       | W00576      | 2012-03-25 11:30:00 | 2012-03-25 12:40:00 |
| 2       | W00300      | 2012-03-25 10:30:00 | 2012-03-25 10:50:00 |
| 3       | W00455      | 2012-03-26 14:30:00 | 2012-03-26 17:40:00 |
| 4       | W00455      | 2012-03-25 12:30:00 | 2012-03-25 13:40:00 |
| 5       | W00576      | 2012-03-25 08:10:00 | 2012-03-25 09:10:00 |
| 6       | W00576      | 2012-03-28 02:30:00 | 2012-03-28 02:50:00 |
+---------+-------------+---------------------+---------------------+ 
<b>输出：</b>
+-------------+---------------------+ 
| bike_number | end_time            |  
+-------------+---------------------+
| W00576      | 2012-03-28 02:50:00 |
| W00455      | 2012-03-26 17:40:00 |
| W00300      | 2012-03-25 10:50:00 |
+-------------+---------------------+ 
<b>解释：</b>
编号为 W00576 的自行车有三次骑行记录，其中最近的骑行记录是 ride_id 为 6，于 2012-03-28 02:50:00结束。
编号为 W00300 的自行车只有一次骑行记录，所以我们直接在输出中包含结束时间。
编号为 W00455 的自行车有两次骑行记录，其中最近的骑行记录是 ride_id 为 3，于 2012-03-26 17:40:00结束。
按照最近使用的自行车顺序返回输出。</pre>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    bike_number,
    MAX(end_time) AS end_time
FROM Bikes
GROUP BY bike_number
ORDER BY end_time DESC;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
