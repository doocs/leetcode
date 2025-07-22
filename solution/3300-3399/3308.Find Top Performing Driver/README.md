---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3308.Find%20Top%20Performing%20Driver/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3308. 寻找表现最佳的司机 🔒](https://leetcode.cn/problems/find-top-performing-driver)

[English Version](/solution/3300-3399/3308.Find%20Top%20Performing%20Driver/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<font face="monospace"><code>Drivers</code></font></p>

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
(driver_id) 是这张表的唯一主键。
每一行包含一个司机 ID，他们的名字，年龄，驾龄年数，以及事故数。
</pre>

<p>表：<font face="monospace"><code>Vehicles</code></font></p>

<pre>
+--------------+---------+
| vehicle_id   | int     |
| driver_id    | int     |
| model        | varchar |
| fuel_type    | varchar |
| mileage      | int     |
+--------------+---------+
(vehicle_id, driver_id, fuel_type) 是这张表的唯一主键。
每一行包含机动车 ID，驾驶员，车型，动力类型和里程数。
</pre>

<p>表：<font face="monospace"><code>Trips</code></font></p>

<pre>
+--------------+---------+
| trip_id      | int     |
| vehicle_id   | int     |
| distance     | int     |
| duration     | int     |
| rating       | int     |
+--------------+---------+
(trip_id) 是这张表的唯一主键。
每一行包含行程 ID，使用的机动车，覆盖的距离（以米计），行程市场（以分钟计），以及乘客评分（1-5）。
</pre>

<p>优步正在基于司机的行程分析他们的情况。编写一个解决方案，根据下列标准来找到 <strong>每种动力类型</strong> 中&nbsp;<strong>表现最好的司机</strong>：</p>

<ol>
	<li>一个司机的表现由他们行程的 <strong>平均评分</strong>&nbsp;计算。平均评分应该舍入到&nbsp;<code>2</code>&nbsp;位小数。</li>
	<li>如果两个司机有相同的平均评分，<strong>里程数更多</strong>&nbsp;的司机评分更高。</li>
	<li>如果 <strong>依旧持平</strong>，选择 <strong>事故数最少</strong>&nbsp;的司机。</li>
</ol>

<p>返回结果表以&nbsp;<code>fuel_type</code> <strong>升序&nbsp;</strong>排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p><code>Drivers</code> 表：</p>

<pre class="example-io">
+-----------+----------+-----+------------+-----------+
| driver_id | name     | age | experience | accidents |
+-----------+----------+-----+------------+-----------+
| 1         | Alice    | 34  | 10         | 1         |
| 2         | Bob      | 45  | 20         | 3         |
| 3         | Charlie  | 28  | 5          | 0         |
+-----------+----------+-----+------------+-----------+
</pre>

<p><code>Vehicles</code> 表：</p>

<pre class="example-io">
+------------+-----------+---------+-----------+---------+
| vehicle_id | driver_id | model   | fuel_type | mileage |
+------------+-----------+---------+-----------+---------+
| 100        | 1         | Sedan   | Gasoline  | 20000   |
| 101        | 2         | SUV     | Electric  | 30000   |
| 102        | 3         | Coupe   | Gasoline  | 15000   |
+------------+-----------+---------+-----------+---------+
</pre>

<p><code>Trips</code> 表：</p>

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

<p><strong>输出：</strong></p>

<pre class="example-io">
+-----------+-----------+--------+----------+
| fuel_type | driver_id | rating | distance |
+-----------+-----------+--------+----------+
| Electric  | 2         | 4.50   | 180      |
| Gasoline  | 3         | 5.00   | 100      |
+-----------+-----------+--------+----------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>对于动力类型&nbsp;<code>Gasoline</code>，Alice（司机 1）和&nbsp;Charlie（司机 3）有行程。Charlie 平均评分为 5.0，而 Alice 为 4.5。因此，选择 Charlie。</li>
	<li>对于动力类型&nbsp;<code>Electric</code>，Bob（司机 2）是唯一的司机，评分为 4.5，因此选择他。</li>
</ul>

<p>输出表以&nbsp;<code>fuel_type</code>&nbsp;升序排序。</p>
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
