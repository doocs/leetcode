---
comments: true
difficulty: 中等
tags:
    - 数据库
---

<!-- problem:start -->

# [2314. 每个城市最高气温的第一天 🔒](https://leetcode.cn/problems/the-first-day-of-the-maximum-recorded-degree-in-each-city)

[English Version](/solution/2300-2399/2314.The%20First%20Day%20of%20the%20Maximum%20Recorded%20Degree%20in%20Each%20City/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Weather</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| city_id     | int  |
| day         | date |
| degree      | int  |
+-------------+------+
(city_id, day) 是该表的主键（具有唯一值的列的组合）。
该表中的每一行都包含某一天某个城市的天气程度。
所有的学位都是在 2022 年获得的。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，找出每个城市中有最高温度记录的日子。如果同一城市多次记录最高气温，则返回其中最早的一天。</p>

<p>返回按 <code>city_id</code> <strong>升序排序&nbsp;</strong>的结果表。</p>

<p>查询结果格式示例如下。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Weather 表:
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
<strong>输出:</strong> 
+---------+------------+--------+
| city_id | day        | degree |
+---------+------------+--------+
| 1       | 2022-07-07 | 24     |
| 2       | 2022-08-07 | 37     |
| 3       | 2022-12-07 | -6     |
+---------+------------+--------+
<strong>解释:</strong> 
城市 1 的最高气温出现在 2022-07-07，为24度。
城市 2 的最高气温出现在 2022-08-07 和 2022-08-17，为37度。我们选择较早的日期 (2022-08-07)。
城市 3 的最高气温记录在 2022-12-07 年，为-6 度。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 每个城市需要气温最高的那天；若并列则取日期最早者。分组后排序可以完成，但窗口函数一次即可同时比较气温与日期。
>
> 按 $city\_id$ 分区，以气温降序、日期升序编号，名次为 $1$ 的行即所求。最后按城市编号输出。

<!-- thinking:end -->

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
