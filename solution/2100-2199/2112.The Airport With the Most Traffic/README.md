# [2112. 最繁忙的机场](https://leetcode.cn/problems/the-airport-with-the-most-traffic)

[English Version](/solution/2100-2199/2112.The%20Airport%20With%20the%20Most%20Traffic/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Flights</code></p>

<pre>
+-------------------+------+
| Column Name       | Type |
+-------------------+------+
| departure_airport | int  |
| arrival_airport   | int  |
| flights_count     | int  |
+-------------------+------+
(departure_airport, arrival_airport) 是该表的主键列。
该表的每一行都表示从 departure_airport 出发并到达 arrival_airport 的 flights_count 航班。
</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 来查询&nbsp;<strong>流量最大&nbsp;</strong>的机场的 ID。客流量最大的机场是指从该机场起飞或抵达该机场的航班总数最多的机场。如果有多个机场流量最大，请全部查询出来。</p>

<p data-group="1-1">以&nbsp;<strong>任意顺序&nbsp;</strong>返回结果表。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Flights 表:
+-------------------+-----------------+---------------+
| departure_airport | arrival_airport | flights_count |
+-------------------+-----------------+---------------+
| 1                 | 2               | 4             |
| 2                 | 1               | 5             |
| 2                 | 4               | 5             |
+-------------------+-----------------+---------------+
<strong>输出:</strong> 
+------------+
| airport_id |
+------------+
| 2          |
+------------+
<strong>解释:</strong> 
1 号机场有 9 个航班 (4 个出发, 5 个到达).
2 号机场有 14 个航班 (10 个出发, 4 个到达).
4 号机场有 5 个航班 (5 个到达).
客流量最大的机场是 2 号机场。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> 
Flights 表:
+-------------------+-----------------+---------------+
| departure_airport | arrival_airport | flights_count |
+-------------------+-----------------+---------------+
| 1                 | 2               | 4             |
| 2                 | 1               | 5             |
| 3                 | 4               | 5             |
| 4                 | 3               | 4             |
| 5                 | 6               | 7             |
+-------------------+-----------------+---------------+
<strong>输出:</strong> 
+------------+
| airport_id |
+------------+
| 1          |
| 2          |
| 3          |
| 4          |
+------------+
<strong>解释:</strong> 
1 号机场有 9 个航班 (4 个出发, 5 个到达).
2 号机场有 9 个航班 (5 个出发, 4 个到达).
3 号机场有 9 个航班 (5 个出发, 4 个到达).
4 号机场有 9 个航班 (5 个出发, 4 个到达).
5 号机场有 7 个航班 (7 个出发).
6 号机场有 7 个航班 (7 个到达).
流量最大的机场是机场 1、2、3 和 4。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
