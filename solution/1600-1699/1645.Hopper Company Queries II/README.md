# [1645. Hopper 公司查询 II](https://leetcode.cn/problems/hopper-company-queries-ii)

[English Version](/solution/1600-1699/1645.Hopper%20Company%20Queries%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Drivers</code></p>

<pre>
+-------------+---------+
| Column Name | Type &nbsp; &nbsp;|
+-------------+---------+
| driver_id &nbsp; | int &nbsp; &nbsp; |
| join_date &nbsp; | date &nbsp; &nbsp;|
+-------------+---------+
driver_id 是该表具有唯一值的列。
该表的每一行均包含驾驶员的ID以及他们加入 Hopper 公司的日期。
</pre>

<p>&nbsp;</p>

<p>表: <code>Rides</code></p>

<pre>
+--------------+---------+
| Column Name &nbsp;| Type &nbsp; &nbsp;|
+--------------+---------+
| ride_id &nbsp; &nbsp; &nbsp;| int &nbsp; &nbsp; |
| user_id &nbsp; &nbsp; &nbsp;| int &nbsp; &nbsp; |
| requested_at | date &nbsp; &nbsp;|
+--------------+---------+
ride_id 是该表具有唯一值的列。
该表的每一行均包含行程 ID(ride_id)，用户 ID(user_id) 以及该行程的日期 (requested_at)。
该表中可能有一些不被接受的乘车请求。</pre>

<p>&nbsp;</p>

<p>表: <code>AcceptedRides</code></p>

<pre>
+---------------+---------+
| Column Name &nbsp; | Type &nbsp; &nbsp;|
+---------------+---------+
| ride_id &nbsp; &nbsp; &nbsp; | int &nbsp; &nbsp; |
| driver_id &nbsp; &nbsp; | int &nbsp; &nbsp; |
| ride_distance | int &nbsp; &nbsp; |
| ride_duration | int &nbsp; &nbsp; |
+---------------+---------+
ride_id 是该表具有唯一值的列。
该表的每一行都包含已接受的行程信息。
表中的行程信息都在 "Rides" 表中存在。
</pre>

<p>&nbsp;</p>

<p>编写解决方案以报告 <strong>2020</strong> 年每个月的工作驱动因素&nbsp;<strong>百分比</strong>（<code>working_percentage</code>），其中：</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1645.Hopper%20Company%20Queries%20II/images/codecogseqn.png" style="width: 800px; height: 36px;" />
<p>&nbsp;</p>

<p><strong>注意：</strong>如果一个月内可用驾驶员的数量为零，我们认为&nbsp;<code>working_percentage</code>&nbsp;为&nbsp;<code>0</code><strong>。</strong></p>

<p>返回按&nbsp;<code>month</code>&nbsp;<strong>升序&nbsp;</strong>排列的结果表，其中&nbsp;<code>month</code>&nbsp;是月份的编号（一月是&nbsp;<code>1</code>，二月是&nbsp;<code>2</code>，等等）。将&nbsp;<code>working_percentage</code>&nbsp;四舍五入至&nbsp;<strong>小数点后两位</strong>。</p>

<p>结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
表 Drivers:
+-----------+------------+
| driver_id | join_date &nbsp;|
+-----------+------------+
| 10 &nbsp; &nbsp; &nbsp; &nbsp;| 2019-12-10 |
| 8 &nbsp; &nbsp; &nbsp; &nbsp; | 2020-1-13 &nbsp;|
| 5 &nbsp; &nbsp; &nbsp; &nbsp; | 2020-2-16 &nbsp;|
| 7 &nbsp; &nbsp; &nbsp; &nbsp; | 2020-3-8 &nbsp; |
| 4 &nbsp; &nbsp; &nbsp; &nbsp; | 2020-5-17 &nbsp;|
| 1 &nbsp; &nbsp; &nbsp; &nbsp; | 2020-10-24 |
| 6 &nbsp; &nbsp; &nbsp; &nbsp; | 2021-1-5 &nbsp; |
+-----------+------------+

表 Rides:
+---------+---------+--------------+
| ride_id | user_id | requested_at |
+---------+---------+--------------+
| 6 &nbsp; &nbsp; &nbsp; | 75 &nbsp; &nbsp; &nbsp;| 2019-12-9 &nbsp; &nbsp;|
| 1 &nbsp; &nbsp; &nbsp; | 54 &nbsp; &nbsp; &nbsp;| 2020-2-9 &nbsp; &nbsp; |
| 10 &nbsp; &nbsp; &nbsp;| 63 &nbsp; &nbsp; &nbsp;| 2020-3-4 &nbsp; &nbsp; |
| 19 &nbsp; &nbsp; &nbsp;| 39 &nbsp; &nbsp; &nbsp;| 2020-4-6 &nbsp; &nbsp; |
| 3 &nbsp; &nbsp; &nbsp; | 41 &nbsp; &nbsp; &nbsp;| 2020-6-3 &nbsp; &nbsp; |
| 13 &nbsp; &nbsp; &nbsp;| 52 &nbsp; &nbsp; &nbsp;| 2020-6-22 &nbsp; &nbsp;|
| 7 &nbsp; &nbsp; &nbsp; | 69 &nbsp; &nbsp; &nbsp;| 2020-7-16 &nbsp; &nbsp;|
| 17 &nbsp; &nbsp; &nbsp;| 70 &nbsp; &nbsp; &nbsp;| 2020-8-25 &nbsp; &nbsp;|
| 20 &nbsp; &nbsp; &nbsp;| 81 &nbsp; &nbsp; &nbsp;| 2020-11-2 &nbsp; &nbsp;|
| 5 &nbsp; &nbsp; &nbsp; | 57 &nbsp; &nbsp; &nbsp;| 2020-11-9 &nbsp; &nbsp;|
| 2 &nbsp; &nbsp; &nbsp; | 42 &nbsp; &nbsp; &nbsp;| 2020-12-9 &nbsp; &nbsp;|
| 11 &nbsp; &nbsp; &nbsp;| 68 &nbsp; &nbsp; &nbsp;| 2021-1-11 &nbsp; &nbsp;|
| 15 &nbsp; &nbsp; &nbsp;| 32 &nbsp; &nbsp; &nbsp;| 2021-1-17 &nbsp; &nbsp;|
| 12 &nbsp; &nbsp; &nbsp;| 11 &nbsp; &nbsp; &nbsp;| 2021-1-19 &nbsp; &nbsp;|
| 14 &nbsp; &nbsp; &nbsp;| 18 &nbsp; &nbsp; &nbsp;| 2021-1-27 &nbsp; &nbsp;|
+---------+---------+--------------+

表 AcceptedRides:
+---------+-----------+---------------+---------------+
| ride_id | driver_id | ride_distance | ride_duration |
+---------+-----------+---------------+---------------+
| 10 &nbsp; &nbsp; &nbsp;| 10 &nbsp; &nbsp; &nbsp; &nbsp;| 63 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 38 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| 13 &nbsp; &nbsp; &nbsp;| 10 &nbsp; &nbsp; &nbsp; &nbsp;| 73 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 96 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| 7 &nbsp; &nbsp; &nbsp; | 8 &nbsp; &nbsp; &nbsp; &nbsp; | 100 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; | 28 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| 17 &nbsp; &nbsp; &nbsp;| 7 &nbsp; &nbsp; &nbsp; &nbsp; | 119 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; | 68 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| 20 &nbsp; &nbsp; &nbsp;| 1 &nbsp; &nbsp; &nbsp; &nbsp; | 121 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; | 92 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| 5 &nbsp; &nbsp; &nbsp; | 7 &nbsp; &nbsp; &nbsp; &nbsp; | 42 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 101 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |
| 2 &nbsp; &nbsp; &nbsp; | 4 &nbsp; &nbsp; &nbsp; &nbsp; | 6 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; | 38 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| 11 &nbsp; &nbsp; &nbsp;| 8 &nbsp; &nbsp; &nbsp; &nbsp; | 37 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 43 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| 15 &nbsp; &nbsp; &nbsp;| 8 &nbsp; &nbsp; &nbsp; &nbsp; | 108 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; | 82 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| 12 &nbsp; &nbsp; &nbsp;| 8 &nbsp; &nbsp; &nbsp; &nbsp; | 38 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 34 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| 14 &nbsp; &nbsp; &nbsp;| 1 &nbsp; &nbsp; &nbsp; &nbsp; | 90 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 74 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
+---------+-----------+---------------+---------------+
<strong>输出：</strong>
+-------+--------------------+
| month | working_percentage |
+-------+--------------------+
| 1 &nbsp; &nbsp; | 0.00 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |
| 2 &nbsp; &nbsp; | 0.00 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |
| 3 &nbsp; &nbsp; | 25.00 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| 4 &nbsp; &nbsp; | 0.00 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |
| 5 &nbsp; &nbsp; | 0.00 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |
| 6 &nbsp; &nbsp; | 20.00 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| 7 &nbsp; &nbsp; | 20.00 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| 8 &nbsp; &nbsp; | 20.00 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| 9 &nbsp; &nbsp; | 0.00 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |
| 10 &nbsp; &nbsp;| 0.00 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |
| 11 &nbsp; &nbsp;| 33.33 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| 12 &nbsp; &nbsp;| 16.67 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
+-------+--------------------+
<strong>解释：</strong>
截至 1 月底 --&gt; 2 个活跃的驾驶员 (10, 8)，无被接受的行程。百分比是0%。
截至 2 月底 --&gt; 3 个活跃的驾驶员 (10, 8, 5)，无被接受的行程。百分比是0%。
截至 3 月底 --&gt; 4 个活跃的驾驶员 (10, 8, 5, 7)，1 个被接受的行程 (10)。百分比是 (1 / 4) * 100 = 25%。
截至 4 月底 --&gt; 4 个活跃的驾驶员 (10, 8, 5, 7)，无被接受的行程。百分比是 0%。
截至 5 月底 --&gt; 5 个活跃的驾驶员 (10, 8, 5, 7, 4)，无被接受的行程。百分比是 0%。
截至 6 月底 --&gt; 5 个活跃的驾驶员 (10, 8, 5, 7, 4)，1 个被接受的行程 (10)。 百分比是 (1 / 5) * 100 = 20%。
截至 7 月底 --&gt; 5 个活跃的驾驶员 (10, 8, 5, 7, 4)，1 个被接受的行程 (8)。百分比是 (1 / 5) * 100 = 20%。
截至 8 月底 --&gt; 5 个活跃的驾驶员 (10, 8, 5, 7, 4)，1 个被接受的行程 (7)。百分比是 (1 / 5) * 100 = 20%。
截至 9 月底 --&gt; 5 个活跃的驾驶员 (10, 8, 5, 7, 4)，无被接受的行程。百分比是 0%。
截至 10 月底 --&gt; 6 个活跃的驾驶员 (10, 8, 5, 7, 4, 1) 无被接受的行程。百分比是 0%。
截至 11 月底 --&gt; 6 个活跃的驾驶员 (10, 8, 5, 7, 4, 1)，2 个被接受的行程 (1, 7)。百分比是 (2 / 6) * 100 = 33.33%。
截至 12 月底 --&gt; 6 个活跃的驾驶员 (10, 8, 5, 7, 4, 1)，1 个被接受的行程 (4)。百分比是 (1 / 6) * 100 = 16.67%。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归 + 左连接 + 分组**

我们可以使用递归的方法生成 $1 \sim 12$ 月的数据，记录在 `Month` 表中。

接下来，我们用 `Month` 表与 `Drivers` 表进行左连接，连接的条件是 `year(d.join_date) < 2020 or (year(d.join_date) = 2020 and month(d.join_date) <= month)`，这样就可以得到每个月的活跃司机数。

然后，我们再用 `Rides` 表与 `AcceptedRides` 表进行内连接，连接的条件是 `ride_id` 相等，并且我们只查出 `year(requested_at) = 2020` 的数据，这样就可以得到 $2020$ 年被接受的所有行程。

最后，我们将上面两个表进行左连接，连接的条件是 `month` 相等、`driver_id` 相等，并且 `join_date` 小于等于 `requested_at`，这样就可以得到每个月被接受的行程数，按月份进行分组，就可以得到每个月的活跃司机数和被接受的行程数，从而计算出每个月的接单率。

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql
# Write your MySQL query statement below
WITH RECURSIVE
    Month AS (
        SELECT 1 AS month
        UNION
        SELECT month + 1
        FROM Month
        WHERE month < 12
    ),
    S AS (
        SELECT month, driver_id, join_date
        FROM
            Month AS m
            LEFT JOIN Drivers AS d
                ON YEAR(d.join_date) < 2020
                OR (YEAR(d.join_date) = 2020 AND MONTH(d.join_date) <= month)
    ),
    T AS (
        SELECT driver_id, requested_at
        FROM
            Rides
            JOIN AcceptedRides USING (ride_id)
        WHERE YEAR(requested_at) = 2020
    )
SELECT
    month,
    IFNULL(
        ROUND(COUNT(DISTINCT t.driver_id) * 100 / COUNT(DISTINCT s.driver_id), 2),
        0
    ) AS working_percentage
FROM
    S AS s
    LEFT JOIN T AS t
        ON s.driver_id = t.driver_id
        AND s.join_date <= t.requested_at
        AND s.month = MONTH(t.requested_at)
GROUP BY 1;
```

<!-- tabs:end -->
