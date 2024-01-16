# [2793. 航班机票状态](https://leetcode.cn/problems/status-of-flight-tickets)

[English Version](/solution/2700-2799/2793.Status%20of%20Flight%20Tickets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：&nbsp;<code><font face="monospace">Flights</font></code></p>

<pre>
+-------------+------+
| 列名        | 类型 |
+-------------+------+
| flight_id   | int  |
| capacity    | int  |
+-------------+------+
flight_id 列包含不同的值。
每行包含航班 id 和座位容量。
</pre>

<p>表：<code>Passengers</code></p>

<pre>
+--------------+----------+
| 列名         | 类型 |
+--------------+----------+
| passenger_id | int      |
| flight_id    | int      |
| booking_time | datetime |
+--------------+----------+
passenger_id 包含不同的值。
booking_time 包含不同的值。
每行包含乘客 id、预订时间和所预订的航班 id。
</pre>

<p>乘客提前预订航班机票。如果乘客预订了一张航班机票，并且航班上还有空座位，则乘客的机票将 <strong>得到确认</strong> 。然而，如果航班已经满员，乘客将被列入 <strong>等候名单</strong> 。</p>

<p>编写解决方案来确定每个乘客航班机票的当前状态。</p>

<p>按 <code>passenger_id</code> <strong>升序排序&nbsp;</strong>返回结果表。</p>

<p>查询结果的格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>
Flights 表:
+-----------+----------+
| flight_id | capacity |
+-----------+----------+
| 1         | 2        |
| 2         | 2        |
| 3         | 1        |
+-----------+----------+
Passengers 表:
+--------------+-----------+---------------------+
| passenger_id | flight_id | booking_time        |
+--------------+-----------+---------------------+
| 101          | 1         | 2023-07-10 16:30:00 |
| 102          | 1         | 2023-07-10 17:45:00 |
| 103          | 1         | 2023-07-10 12:00:00 |
| 104          | 2         | 2023-07-05 13:23:00 |
| 105          | 2         | 2023-07-05 09:00:00 |
| 106          | 3         | 2023-07-08 11:10:00 |
| 107          | 3         | 2023-07-08 09:10:00 |
+--------------+-----------+---------------------+
<b>输出：</b>
+--------------+-----------+
| passenger_id | Status    |
+--------------+-----------+
| 101          | Confirmed | 
| 102          | Waitlist  | 
| 103          | Confirmed | 
| 104          | Confirmed | 
| 105          | Confirmed | 
| 106          | Waitlist  | 
| 107          | Confirmed | 
+--------------+-----------+
<b>解释：</b>
- 航班 1 的容量为 2 位乘客。乘客 101 和乘客 103 是最先预订机票的，已经确认他们的预订。然而，乘客 102 是第三位预订该航班的乘客，这意味着没有更多的可用座位。乘客 102 现在被列入等候名单。
- 航班 2 的容量为 2 位乘客，已经有两位乘客预订了机票，乘客 104 和乘客 105。由于预订机票的乘客数与可用座位数相符，这两个预订都得到了确认。
- 航班 3 的容量为 1 位乘客，乘客 107 先预订并获得了唯一的可用座位，确认了他们的预订。预订时间在乘客 107 之后的乘客 106 被列入等候名单。</pre>

## 解法

### 方法一：Rank() 窗口函数

注意，如果多个人在同一时间预定了同一个航班，只要有空位，就都可以确认预定。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    passenger_id,
    IF(
        (
            RANK() OVER (
                PARTITION BY flight_id
                ORDER BY booking_time
            )
        ) <= capacity,
        'Confirmed',
        'Waitlist'
    ) AS Status
FROM
    Passengers
    JOIN Flights USING (flight_id)
ORDER BY passenger_id;
```

<!-- tabs:end -->

<!-- end -->
