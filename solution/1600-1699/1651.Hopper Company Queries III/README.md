# [1651. Hopper 公司查询 III 🔒](https://leetcode.cn/problems/hopper-company-queries-iii)

[English Version](/solution/1600-1699/1651.Hopper%20Company%20Queries%20III/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表：&nbsp;<code>Drivers</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| driver_id   | int     |
| join_date   | date    |
+-------------+---------+
driver_id 是该表具有唯一值的列。
该表的每一行均包含驾驶员的 ID 以及他们加入 Hopper 公司的日期。</pre>

<p>&nbsp;</p>

<p>表：<code>Rides</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| ride_id      | int     |
| user_id      | int     |
| requested_at | date    |
+--------------+---------+
ride_id 是该表具有唯一值的列。 
该表的每一行均包含行程 ID(ride_id)，用户 ID(user_id) 以及该行程的日期(requested_at)。 
该表中可能有一些不被接受的乘车请求。
</pre>

<p>&nbsp;</p>

<p><font color="#333333" face="Helvetica Neue, Helvetica, Arial, sans-serif"><span style="font-size: 14px; background-color: rgb(255, 255, 255);">表：</span></font><code>AcceptedRides</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| ride_id       | int     |
| driver_id     | int     |
| ride_distance | int     |
| ride_duration | int     |
+---------------+---------+
ride_id 是该表具有唯一值的列。 
该表的每一行都包含已接受的行程信息。 
表中的行程信息都在 "<code>Rides</code>" 表中存在。</pre>

<p>&nbsp;</p>

<p>编写一个解决方案，计算出从&nbsp;<strong>2020 年 1 月至 3 月 至 2020 年 10 月至 12 月&nbsp;</strong>的每三个月窗口的&nbsp;<code>average_ride_distance</code>&nbsp;和&nbsp;<code>average_ride_duration</code>&nbsp;。并将&nbsp;<code>average_ride_distance</code>&nbsp;和&nbsp;<code>average_ride_duration</code>&nbsp;四舍五入至 <strong>小数点后两位</strong> 。<br />
通过将三个月的总&nbsp;<code>ride_distance</code>&nbsp;相加并除以 <code>3</code> 来计算&nbsp;<code>average_ride_distance</code>&nbsp;。<code>average_ride_duration</code>&nbsp;的计算方法与此类似。<br />
返回按&nbsp;<code>month</code>&nbsp;升序排列的结果表，其中&nbsp;<code>month</code>&nbsp;是起始月份的编号（一月为 1，二月为 2 ...）。</p>

<p>查询结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> 
Drivers table:
+-----------+------------+
| driver_id | join_date  |
+-----------+------------+
| 10        | 2019-12-10 |
| 8         | 2020-1-13  |
| 5         | 2020-2-16  |
| 7         | 2020-3-8   |
| 4         | 2020-5-17  |
| 1         | 2020-10-24 |
| 6         | 2021-1-5   |
+-----------+------------+
Rides table:
+---------+---------+--------------+
| ride_id | user_id | requested_at |
+---------+---------+--------------+
| 6       | 75      | 2019-12-9    |
| 1       | 54      | 2020-2-9     |
| 10      | 63      | 2020-3-4     |
| 19      | 39      | 2020-4-6     |
| 3       | 41      | 2020-6-3     |
| 13      | 52      | 2020-6-22    |
| 7       | 69      | 2020-7-16    |
| 17      | 70      | 2020-8-25    |
| 20      | 81      | 2020-11-2    |
| 5       | 57      | 2020-11-9    |
| 2       | 42      | 2020-12-9    |
| 11      | 68      | 2021-1-11    |
| 15      | 32      | 2021-1-17    |
| 12      | 11      | 2021-1-19    |
| 14      | 18      | 2021-1-27    |
+---------+---------+--------------+
AcceptedRides table:
+---------+-----------+---------------+---------------+
| ride_id | driver_id | ride_distance | ride_duration |
+---------+-----------+---------------+---------------+
| 10      | 10        | 63            | 38            |
| 13      | 10        | 73            | 96            |
| 7       | 8         | 100           | 28            |
| 17      | 7         | 119           | 68            |
| 20      | 1         | 121           | 92            |
| 5       | 7         | 42            | 101           |
| 2       | 4         | 6             | 38            |
| 11      | 8         | 37            | 43            |
| 15      | 8         | 108           | 82            |
| 12      | 8         | 38            | 34            |
| 14      | 1         | 90            | 74            |
+---------+-----------+---------------+---------------+
<strong>输出:</strong> 
+-------+-----------------------+-----------------------+
| month | average_ride_distance | average_ride_duration |
+-------+-----------------------+-----------------------+
| 1     | 21.00                 | 12.67                 |
| 2     | 21.00                 | 12.67                 |
| 3     | 21.00                 | 12.67                 |
| 4     | 24.33                 | 32.00                 |
| 5     | 57.67                 | 41.33                 |
| 6     | 97.33                 | 64.00                 |
| 7     | 73.00                 | 32.00                 |
| 8     | 39.67                 | 22.67                 |
| 9     | 54.33                 | 64.33                 |
| 10    | 56.33                 | 77.00                 |
+-------+-----------------------+-----------------------+
<strong>解释:</strong> 
到1月底--&gt;平均骑行距离=（0+0+63）/3=21，平均骑行持续时间=（0+0+38）/3=12.67
到2月底--&gt;平均骑行距离=（0+63+0）/3=21，平均骑行持续时间=（0+38+0）/3=12.67
到3月底--&gt;平均骑行距离=（63+0+0）/3=21，平均骑行持续时间=（38+0+0）/3=12.67
到4月底--&gt;平均骑行距离=（0+0+73）/3=24.33，平均骑行持续时间=（0+0+96）/3=32.00
到5月底--&gt;平均骑行距离=（0+73+100）/3=57.67，平均骑行持续时间=（0+96+28）/3=41.33
到6月底--&gt;平均骑行距离=（73+100+119）/3=97.33，平均骑行持续时间=（96+28+68）/3=64.00
到7月底--&gt;平均骑行距离=（100+119+0）/3=73.00，平均骑行持续时间=（28+68+0）/3=32.00
到8月底--&gt;平均骑行距离=（119+0+0）/3=39.67，平均骑行持续时间=（68+0+0）/3=22.67
9月底--&gt;平均骑行距离=（0+0+163）/3=54.33，平均骑行持续时间=（0+0+193）/3=64.33
到10月底--&gt;平均骑行距离=（0+163+6）/3=56.33，平均骑行持续时间=（0+193+38）/3=77.00</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH RECURSIVE
    Months AS (
        SELECT 1 AS month
        UNION ALL
        SELECT month + 1
        FROM Months
        WHERE month < 12
    ),
    Ride AS (
        SELECT
            month,
            SUM(IFNULL(ride_distance, 0)) AS ride_distance,
            SUM(IFNULL(ride_duration, 0)) AS ride_duration
        FROM
            Months AS m
            LEFT JOIN Rides AS r ON month = MONTH(requested_at) AND YEAR(requested_at) = 2020
            LEFT JOIN AcceptedRides AS a ON r.ride_id = a.ride_id
        GROUP BY month
    )
SELECT
    month,
    ROUND(
        AVG(ride_distance) OVER (ROWS BETWEEN CURRENT ROW AND 2 FOLLOWING),
        2
    ) AS average_ride_distance,
    ROUND(
        AVG(ride_duration) OVER (ROWS BETWEEN CURRENT ROW AND 2 FOLLOWING),
        2
    ) AS average_ride_duration
FROM Ride
ORDER BY month
LIMIT 10;
```

<!-- tabs:end -->

<!-- end -->
