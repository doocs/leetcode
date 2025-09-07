---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1294.Weather%20Type%20in%20Each%20Country/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1294. 不同国家的天气类型 🔒](https://leetcode.cn/problems/weather-type-in-each-country)

[English Version](/solution/1200-1299/1294.Weather%20Type%20in%20Each%20Country/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Countries</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| country_id    | int     |
| country_name  | varchar |
+---------------+---------+
country_id 是这张表的主键(具有唯一值的列)。
该表的每行有 country_id 和 country_name 两列。
</pre>

<p>&nbsp;</p>

<p>表：<code>Weather</code></p>

<pre>
+---------------+------+
| Column Name   | Type |
+---------------+------+
| country_id    | int  |
| weather_state | int  |
| day           | date |
+---------------+------+
(country_id, day) 是该表的复合主键(具有唯一值的列)。
该表的每一行记录了某个国家某一天的天气情况。
</pre>

<p>&nbsp;</p>

<p>编写解决方案找到表中每个国家在 2019 年 11 月的天气类型。</p>

<p>天气类型的定义如下：</p>

<ul>
	<li>当 <code>weather_state</code> 的平均值小于或等于 <code>15</code> 返回 <strong>Cold</strong>，</li>
	<li>当 <code>weather_state</code> 的平均值大于或等于 <code>25</code> 返回 <strong>Hot</strong>，</li>
	<li>否则返回&nbsp;<strong>Warm</strong>。</li>
</ul>

<p>以 <strong>任意顺序</strong> 返回你的查询结果。</p>

<p>返回结果格式如下所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Countries table:
+------------+--------------+
| country_id | country_name |
+------------+--------------+
| 2          | USA          |
| 3          | Australia    |
| 7          | Peru         |
| 5          | China        |
| 8          | Morocco      |
| 9          | Spain        |
+------------+--------------+
Weather table:
+------------+---------------+------------+
| country_id | weather_state | day        |
+------------+---------------+------------+
| 2          | 15            | 2019-11-01 |
| 2          | 12            | 2019-10-28 |
| 2          | 12            | 2019-10-27 |
| 3          | -2            | 2019-11-10 |
| 3          | 0             | 2019-11-11 |
| 3          | 3             | 2019-11-12 |
| 5          | 16            | 2019-11-07 |
| 5          | 18            | 2019-11-09 |
| 5          | 21            | 2019-11-23 |
| 7          | 25            | 2019-11-28 |
| 7          | 22            | 2019-12-01 |
| 7          | 20            | 2019-12-02 |
| 8          | 25            | 2019-11-05 |
| 8          | 27            | 2019-11-15 |
| 8          | 31            | 2019-11-25 |
| 9          | 7             | 2019-10-23 |
| 9          | 3             | 2019-12-23 |
+------------+---------------+------------+
<strong>输出：</strong>
+--------------+--------------+
| country_name | weather_type |
+--------------+--------------+
| USA          | Cold         |
| Austraila    | Cold         |
| Peru         | Hot          |
| China        | Warm         |
| Morocco      | Hot          |
+--------------+--------------+
<strong>解释：</strong>
USA 11 月的平均 weather_state 为 (15) / 1 = 15 所以天气类型为 Cold。
Australia 11 月的平均 weather_state 为 (-2 + 0 + 3) / 3 = 0.333 所以天气类型为 Cold。
Peru 11 月的平均 weather_state 为 (25) / 1 = 25 所以天气类型为 Hot。
China 11 月的平均 weather_state 为 (16 + 18 + 21) / 3 = 18.333 所以天气类型为 Warm。
Morocco 11 月的平均 weather_state 为 (25 + 27 + 31) / 3 = 27.667 所以天气类型为 Hot。
我们并不知道 Spain 在 11 月的 weather_state 情况所以无需将他包含在结果中。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    country_name,
    CASE
        WHEN AVG(weather_state) <= 15 THEN 'Cold'
        WHEN AVG(weather_state) >= 25 THEN 'Hot'
        ELSE 'Warm'
    END AS weather_type
FROM
    Weather AS w
    JOIN Countries USING (country_id)
WHERE DATE_FORMAT(day, '%Y-%m') = '2019-11'
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
