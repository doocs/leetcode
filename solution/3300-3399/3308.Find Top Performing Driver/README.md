---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3308.Find%20Top%20Performing%20Driver/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3308. Find Top Performing Driver 🔒](https://leetcode.cn/problems/find-top-performing-driver)

[English Version](/solution/3300-3399/3308.Find%20Top%20Performing%20Driver/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Table: <font face="monospace"><code>Drivers</code></font></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| driver_id    | int     |
| name         | varchar |
| age          | int     |
| experience   | int     |
| accidents    | int     |
+--------------+---------+
(driver_id) is the unique key for this table.
Each row includes a driver&#39;s ID, their name, age, years of driving experience, and the number of accidents they&rsquo;ve had.
</pre>

<p>Table: <font face="monospace"><code>Vehicles</code></font></p>

<pre>
+--------------+---------+
| vehicle_id   | int     |
| driver_id    | int     |
| model        | varchar |
| fuel_type    | varchar |
| mileage      | int     |
+--------------+---------+
(vehicle_id, driver_id, fuel_type) is the unique key for this table.
Each row includes the vehicle&#39;s ID, the driver who operates it, the model, fuel type, and mileage.
</pre>

<p>Table: <font face="monospace"><code>Trips</code></font></p>

<pre>
+--------------+---------+
| trip_id      | int     |
| vehicle_id   | int     |
| distance     | int     |
| duration     | int     |
| rating       | int     |
+--------------+---------+
(trip_id) is the unique key for this table.
Each row includes a trip&#39;s ID, the vehicle used, the distance covered (in miles), the trip duration (in minutes), and the passenger&#39;s rating (1-5).
</pre>

<p>Uber is analyzing drivers based on their trips. Write a solution to find the <strong>top-performing driver</strong> for <strong>each fuel type</strong> based on the following criteria:</p>

<ol>
	<li>A driver&#39;s performance is calculated as the <strong>average rating</strong> across all their trips. Average rating should be rounded to <code>2</code> decimal places.</li>
	<li>If two drivers have the same average rating, the driver with the <strong>longer total distance</strong> traveled should be ranked higher.</li>
	<li>If there is <strong>still a tie</strong>, choose the driver with the <strong>fewest accidents</strong>.</li>
</ol>

<p>Return <em>the result table ordered by</em> <code>fuel_type</code> <em>in </em><strong>ascending</strong><em> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p><code>Drivers</code> table:</p>

<pre class="example-io">
+-----------+----------+-----+------------+-----------+
| driver_id | name     | age | experience | accidents |
+-----------+----------+-----+------------+-----------+
| 1         | Alice    | 34  | 10         | 1         |
| 2         | Bob      | 45  | 20         | 3         |
| 3         | Charlie  | 28  | 5          | 0         |
+-----------+----------+-----+------------+-----------+
</pre>

<p><code>Vehicles</code> table:</p>

<pre class="example-io">
+------------+-----------+---------+-----------+---------+
| vehicle_id | driver_id | model   | fuel_type | mileage |
+------------+-----------+---------+-----------+---------+
| 100        | 1         | Sedan   | Gasoline  | 20000   |
| 101        | 2         | SUV     | Electric  | 30000   |
| 102        | 3         | Coupe   | Gasoline  | 15000   |
+------------+-----------+---------+-----------+---------+
</pre>

<p><code>Trips</code> table:</p>

<pre class="example-io">
+---------+------------+----------+----------+--------+
| trip_id | vehicle_id | distance | duration | rating |
+---------+------------+----------+----------+--------+
| 201     | 100        | 50       | 30       | 5      |
| 202     | 100        | 30       | 20       | 4      |
| 203     | 101        | 100      | 60       | 4      |
| 204     | 101        | 80       | 50       | 5      |
| 205     | 102        | 40       | 30       | 5      |
| 206     | 102        | 60       | 40       | 5      |
+---------+------------+----------+----------+--------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+-----------+-----------+--------+----------+
| fuel_type | driver_id | rating | distance |
+-----------+-----------+--------+----------+
| Electric  | 2         | 4.50   | 180      |
| Gasoline  | 3         | 5.00   | 100      |
+-----------+-----------+--------+----------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For fuel type <code>Gasoline</code>, both Alice (Driver 1) and Charlie (Driver 3) have trips. Charlie has an average rating of 5.0, while Alice has 4.5. Therefore, Charlie is selected.</li>
	<li>For fuel type <code>Electric</code>, Bob (Driver 2) is the only driver with an average rating of 4.5, so he is selected.</li>
</ul>

<p>The output table is ordered by <code>fuel_type</code> in ascending order.</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：等值连接 + 分组 + 窗口函数

我们可以使用等值连接，将 `Drivers` 表和 `Vehicles` 表按照 `driver_id` 连接，再与 `Trips` 表按照 `vehicle_id` 连接，然后按照 `fuel_type`、`driver_id` 分组，计算每个司机的平均评分、总行驶里程、总事故次数，然后使用 `RANK()` 窗口函数，将每种燃料类型的司机按照评分降序、总行驶里程降序、总事故次数升序排名，最后筛选出每种燃料类型的排名为 1 的司机。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            fuel_type,
            driver_id,
            ROUND(AVG(rating), 2) rating,
            SUM(distance) distance,
            SUM(accidents) accidents
        FROM
            Drivers
            JOIN Vehicles USING (driver_id)
            JOIN Trips USING (vehicle_id)
        GROUP BY fuel_type, driver_id
    ),
    P AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY fuel_type
                ORDER BY rating DESC, distance DESC, accidents
            ) rk
        FROM T
    )
SELECT fuel_type, driver_id, rating, distance
FROM P
WHERE rk = 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
