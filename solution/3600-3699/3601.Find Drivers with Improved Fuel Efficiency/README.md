---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3601.Find%20Drivers%20with%20Improved%20Fuel%20Efficiency/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3601. Find Drivers with Improved Fuel Efficiency](https://leetcode.cn/problems/find-drivers-with-improved-fuel-efficiency)

[English Version](/solution/3600-3699/3601.Find%20Drivers%20with%20Improved%20Fuel%20Efficiency/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Table: <code>drivers</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| driver_id   | int     |
| driver_name | varchar |
+-------------+---------+
driver_id is the unique identifier for this table.
Each row contains information about a driver.
</pre>

<p>Table: <code>trips</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| trip_id       | int     |
| driver_id     | int     |
| trip_date     | date    |
| distance_km   | decimal |
| fuel_consumed | decimal |
+---------------+---------+
trip_id is the unique identifier for this table.
Each row represents a trip made by a driver, including the distance traveled and fuel consumed for that trip.
</pre>

<p>Write a solution to find drivers whose <strong>fuel efficiency has improved</strong> by <strong>comparing</strong> their average fuel efficiency in the<strong> first half</strong> of the year with the <strong>second half</strong> of the year.</p>

<ul>
	<li>Calculate <strong>fuel efficiency</strong> as <code>distance_km / fuel_consumed</code> for <strong>each</strong> trip</li>
	<li><strong>First half</strong>: January to June, <strong>Second half</strong>: July to December</li>
	<li>Only include drivers who have trips in <strong>both halves</strong> of the year</li>
	<li>Calculate the <strong>efficiency improvement</strong> as (<code>second_half_avg - first_half_avg</code>)</li>
	<li><strong>Round </strong>all<strong> </strong>results<strong> </strong>to<strong> <code>2</code> </strong>decimal<strong> </strong>places</li>
</ul>

<p>Return <em>the result table ordered by efficiency improvement in <strong>descending</strong> order, then by driver name in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>drivers table:</p>

<pre class="example-io">
+-----------+---------------+
| driver_id | driver_name   |
+-----------+---------------+
| 1         | Alice Johnson |
| 2         | Bob Smith     |
| 3         | Carol Davis   |
| 4         | David Wilson  |
| 5         | Emma Brown    |
+-----------+---------------+
</pre>

<p>trips table:</p>

<pre class="example-io">
+---------+-----------+------------+-------------+---------------+
| trip_id | driver_id | trip_date  | distance_km | fuel_consumed |
+---------+-----------+------------+-------------+---------------+
| 1       | 1         | 2023-02-15 | 120.5       | 10.2          |
| 2       | 1         | 2023-03-20 | 200.0       | 16.5          |
| 3       | 1         | 2023-08-10 | 150.0       | 11.0          |
| 4       | 1         | 2023-09-25 | 180.0       | 12.5          |
| 5       | 2         | 2023-01-10 | 100.0       | 9.0           |
| 6       | 2         | 2023-04-15 | 250.0       | 22.0          |
| 7       | 2         | 2023-10-05 | 200.0       | 15.0          |
| 8       | 3         | 2023-03-12 | 80.0        | 8.5           |
| 9       | 3         | 2023-05-18 | 90.0        | 9.2           |
| 10      | 4         | 2023-07-22 | 160.0       | 12.8          |
| 11      | 4         | 2023-11-30 | 140.0       | 11.0          |
| 12      | 5         | 2023-02-28 | 110.0       | 11.5          |
+---------+-----------+------------+-------------+---------------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+-----------+---------------+------------------+-------------------+------------------------+
| driver_id | driver_name   | first_half_avg   | second_half_avg   | efficiency_improvement |
+-----------+---------------+------------------+-------------------+------------------------+
| 2         | Bob Smith     | 11.24            | 13.33             | 2.10                   |
| 1         | Alice Johnson | 11.97            | 14.02             | 2.05                   |
+-----------+---------------+------------------+-------------------+------------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>Alice Johnson (driver_id = 1):</strong>

    <ul>
    	<li>First half trips (Jan-Jun): Feb 15 (120.5/10.2 = 11.81), Mar 20 (200.0/16.5 = 12.12)</li>
    	<li>First half average efficiency: (11.81 + 12.12) / 2 = 11.97</li>
    	<li>Second half trips (Jul-Dec): Aug 10 (150.0/11.0 = 13.64), Sep 25 (180.0/12.5 = 14.40)</li>
    	<li>Second half average efficiency: (13.64 + 14.40) / 2 = 14.02</li>
    	<li>Efficiency improvement: 14.02 - 11.97 = 2.05</li>
    </ul>
    </li>
    <li><strong>Bob Smith (driver_id = 2):</strong>
    <ul>
    	<li>First half trips: Jan 10 (100.0/9.0 = 11.11), Apr 15 (250.0/22.0 = 11.36)</li>
    	<li>First half average efficiency: (11.11 + 11.36) / 2 = 11.24</li>
    	<li>Second half trips: Oct 5 (200.0/15.0 = 13.33)</li>
    	<li>Second half average efficiency: 13.33</li>
    	<li>Efficiency improvement: 13.33 - 11.24 = 2.09</li>
    </ul>
    </li>
    <li><strong>Drivers not included:</strong>
    <ul>
    	<li>Carol Davis (driver_id = 3): Only has trips in first half (Mar, May)</li>
    	<li>David Wilson (driver_id = 4): Only has trips in second half (Jul, Nov)</li>
    	<li>Emma Brown (driver_id = 5): Only has trips in first half (Feb)</li>
    </ul>
    </li>

</ul>

<p>The output table is ordered by efficiency improvement in descending order then by name in ascending order.</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
