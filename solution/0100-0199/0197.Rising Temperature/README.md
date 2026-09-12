---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0197.Rising%20Temperature/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [197. 上升的温度](https://leetcode.cn/problems/rising-temperature)

[English Version](/solution/0100-0199/0197.Rising%20Temperature/README_EN.md)

## 题目描述

<!-- description:start -->

<div class="original__bRMd">
<div>
<p>表：&nbsp;<code>Weather</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| recordDate    | date    |
| temperature   | int     |
+---------------+---------+
id 是该表具有唯一值的列。
没有具有相同 recordDate 的不同行。
该表包含特定日期的温度信息</pre>

<p>&nbsp;</p>

<p>编写解决方案，找出与之前（昨天的）日期相比温度更高的所有日期的 <code>id</code> 。</p>

<p>返回结果 <strong>无顺序要求</strong> 。</p>

<p>结果格式如下例子所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Weather 表：
+----+------------+-------------+
| id | recordDate | Temperature |
+----+------------+-------------+
| 1  | 2015-01-01 | 10          |
| 2  | 2015-01-02 | 25          |
| 3  | 2015-01-03 | 20          |
| 4  | 2015-01-04 | 30          |
+----+------------+-------------+
<strong>输出：</strong>
+----+
| id |
+----+
| 2  |
| 4  |
+----+
<strong>解释：</strong>
2015-01-02 的温度比前一天高（10 -&gt; 25）
2015-01-04 的温度比前一天高（20 -&gt; 30）</pre>
</div>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：自连接 + DATEDIFF/SUBDATE 函数

<!-- thinking:start -->

> **思考**
>
> 找出比「前一天」更热的日期，要求日期恰好相差一天，而不是表中的上一行。自连接两行天气，用 $\textit{DATEDIFF}$ 或日期差约束为 $1$，再比较温度。

<!-- thinking:end -->

我们可以通过自连接的方式，将 `Weather` 表中的每一行与它的前一行进行比较，如果温度更高，并且日期相差一天，那么就是我们要找的结果。

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def rising_temperature(weather: pd.DataFrame) -> pd.DataFrame:
    weather.sort_values(by="recordDate", inplace=True)
    return weather[
        (weather.temperature.diff() > 0) & (weather.recordDate.diff().dt.days == 1)
    ][["id"]]
```

#### MySQL

```sql
# Write your MySQL query statement below
SELECT w1.id
FROM
    Weather AS w1
    JOIN Weather AS w2
        ON DATEDIFF(w1.recordDate, w2.recordDate) = 1 AND w1.temperature > w2.temperature;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 方法一用日期差函数。连接条件写成 $\textit{SUBDATE}(w1.\textit{recordDate},1)=w2.\textit{recordDate}$，直接对齐「昨天」那一行，语义相同，便于走日期上的等值连接。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT w1.id
FROM
    Weather AS w1
    JOIN Weather AS w2
        ON SUBDATE(w1.recordDate, 1) = w2.recordDate AND w1.temperature > w2.temperature;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
