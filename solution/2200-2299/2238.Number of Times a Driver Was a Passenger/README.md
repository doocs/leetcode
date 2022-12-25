# [2238. 司机成为乘客的次数](https://leetcode.cn/problems/number-of-times-a-driver-was-a-passenger)

[English Version](/solution/2200-2299/2238.Number%20of%20Times%20a%20Driver%20Was%20a%20Passenger/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Rides</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| ride_id      | int  |
| driver_id    | int  |
| passenger_id | int  |
+--------------+------+
ride_id 是该表的主键。
该表的每一行都包含驾驶员的 ID 和在 ride_id 中乘车的乘客的 ID。
注意 driver_id != passenger_id。</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询，获取每个司机的 ID 和他们作为乘客的次数。</p>

<p data-group="1-1">以&nbsp;<strong>任意顺序&nbsp;</strong>返回结果表。</p>

<p>查询结果格式如下所示。</p>

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
