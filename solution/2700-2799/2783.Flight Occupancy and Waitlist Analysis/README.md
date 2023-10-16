# [2783. 航班入座率和等待名单分析](https://leetcode.cn/problems/flight-occupancy-and-waitlist-analysis)

[English Version](/solution/2700-2799/2783.Flight%20Occupancy%20and%20Waitlist%20Analysis/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code><font face="monospace">Flights</font></code>表</p>

<pre>
+-------------+------+
| 列名         | 类型 |
+-------------+------+
| flight_id   | int  |
| capacity    | int  |
+-------------+------+
flight_id 是该表中具有唯一值的列。 
该表的每一行包含航班ID和它的座位容量。
</pre>

<p><code>Passengers</code>表</p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| passenger_id | int  |
| flight_id    | int  |
+--------------+------+
passenger_id 是该表中具有唯一值的列。
该表的每一行包含乘客ID和航班ID。
</pre>

<p>乘客提前预订航班机票。如果乘客预订了某个航班的机票，并且该航班还有空座位，乘客的机票将会得到 <strong>确认</strong> 。然而，如果航班已经满员，乘客将会进入 <strong>等待名单</strong> 。</p>

<p>编写解决方案，报告每个航班已成功预订（获得座位）的乘客数以及处于等待名单上的乘客数。</p>

<p>按照 <code>flight_id</code> <strong>升序排序&nbsp;</strong>返回结果表。</p>

<p>结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>
Flights table:
+-----------+----------+
| flight_id | capacity |
+-----------+----------+
| 1         | 2        |
| 2         | 2        |
| 3         | 1        |
+-----------+----------+
Passengers table:
+--------------+-----------+
| passenger_id | flight_id |
+--------------+-----------+
| 101          | 1         |
| 102          | 1         |
| 103          | 1         |
| 104          | 2         |
| 105          | 2         |
| 106          | 3         |
| 107          | 3         |
+--------------+-----------+
<b>输出：</b>
+-----------+------------+--------------+
| flight_id | booked_cnt | waitlist_cnt |
+-----------+------------+--------------+
| 1         | 2          | 1            |
| 2         | 2          | 0            |
| 3         | 1          | 1            |
+-----------+------------+--------------+
<b>解释：</b>
- 航班 1 的座位容量为 2。由于有 3 名乘客预订了机票，只有 2 名乘客可以获得座位。因此，有 2 名乘客成功预订，并且有 1 名乘客在等待名单上。
- 航班 2 的座位容量为 2。由于有 2 名乘客预订了机票，每个人都可以获得座位。结果是，有 2 名乘客成功预订了座位，且没有乘客在等待名单上。
- 航班 3 的座位容量为 1。由于有 2 名乘客预订了机票，只有 1 名乘客可以获得座位。因此，有 1 名乘客成功预订，并且有 1 名乘客在等待名单上。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：左连接 + 分组**

我们可以使用左连接将 `Flights` 和 `Passengers` 表连接起来，然后按照 `flight_id` 分组，统计每个航班的乘客数量。

对于每个航班，我们可以使用 `count(passenger_id)` 统计乘客数量，取 `capacity` 和 `count(passenger_id)` 的最小值作为已预订的乘客数量，取 `count(passenger_id) - capacity` 和 $0$ 的最大值作为等待名单上的乘客数量。

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql
# Write your MySQL query statement below
SELECT
    flight_id,
    LEAST(COUNT(passenger_id), capacity) AS booked_cnt,
    GREATEST(COUNT(passenger_id) - capacity, 0) AS waitlist_cnt
FROM
    Flights
    LEFT JOIN Passengers USING (flight_id)
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->
