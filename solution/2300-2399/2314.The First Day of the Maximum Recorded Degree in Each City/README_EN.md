---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2314.The%20First%20Day%20of%20the%20Maximum%20Recorded%20Degree%20in%20Each%20City/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [2314. The First Day of the Maximum Recorded Degree in Each City 🔒](https://leetcode.com/problems/the-first-day-of-the-maximum-recorded-degree-in-each-city)

[中文文档](/solution/2300-2399/2314.The%20First%20Day%20of%20the%20Maximum%20Recorded%20Degree%20in%20Each%20City/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Weather</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| city_id     | int  |
| day         | date |
| degree      | int  |
+-------------+------+
(city_id, day) is the primary key (combination of columns with unique values) for this table.
Each row in this table contains the degree of the weather of a city on a certain day.
All the degrees are recorded in the year 2022.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report the day that has the maximum recorded degree in each city. If the maximum degree was recorded for the same city multiple times, return the earliest day among them.</p>

<p>Return the result table ordered by <code>city_id</code> in <strong>ascending order</strong>.</p>

<p>The&nbsp;result format is shown in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Weather table:
+---------+------------+--------+
| city_id | day        | degree |
+---------+------------+--------+
| 1       | 2022-01-07 | -12    |
| 1       | 2022-03-07 | 5      |
| 1       | 2022-07-07 | 24     |
| 2       | 2022-08-07 | 37     |
| 2       | 2022-08-17 | 37     |
| 3       | 2022-02-07 | -7     |
| 3       | 2022-12-07 | -6     |
+---------+------------+--------+
<strong>Output:</strong> 
+---------+------------+--------+
| city_id | day        | degree |
+---------+------------+--------+
| 1       | 2022-07-07 | 24     |
| 2       | 2022-08-07 | 37     |
| 3       | 2022-12-07 | -6     |
+---------+------------+--------+
<strong>Explanation:</strong> 
For city 1, the maximum degree was recorded on 2022-07-07 with 24 degrees.
For city 2, the maximum degree was recorded on 2022-08-07 and 2022-08-17 with 37 degrees. We choose the earlier date (2022-08-07).
For city 3, the maximum degree was recorded on 2022-12-07 with -6 degrees.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY city_id
                ORDER BY degree DESC, day
            ) AS rk
        FROM Weather
    )
SELECT city_id, day, degree
FROM T
WHERE rk = 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
