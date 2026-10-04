---
comments: true
difficulty: 中等
tags:
    - 数据库
---

<!-- problem:start -->

# [2893. 计算每个区间内的订单 🔒](https://leetcode.cn/problems/calculate-orders-within-each-interval)

[English Version](/solution/2800-2899/2893.Calculate%20Orders%20Within%20Each%20Interval/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：&nbsp;<code><font face="monospace">Orders</font></code></p>

<pre>
+-------------+------+ 
| Column Name | Type | 
+-------------+------+ 
| minute      | int  | 
| order_count | int  |
+-------------+------+
minute 是该表的主键。
该表的每一行包含分钟数以及在特定分钟数内收到的订单数量。总行数将是 6 的倍数。</pre>

<p>编写一个查询，计算每个&nbsp;<strong>区间</strong><b>&nbsp;</b>内的&nbsp;<b>总订单数量。</b>&nbsp;每个区间被定义为&nbsp;<code>6</code>&nbsp;分钟的组合。</p>

<ul>
	<li>&nbsp;<code>1</code>&nbsp;到&nbsp;<code>6</code>&nbsp;分钟属于第&nbsp;<code>1</code>&nbsp;个区间，而&nbsp;<code>7</code>&nbsp;到&nbsp;<code>12</code>&nbsp;分钟属于第&nbsp;<code>2</code>&nbsp;个区间，以此类推。</li>
</ul>

<p>按 <em><strong>升序顺序</strong></em> <em>返回</em><em>结果表，</em>按<em>&nbsp;<strong>interval_no</strong>&nbsp;排序。</em></p>

<p>结果表的格式如下示例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<b>输入：</b>
Orders table:
+--------+-------------+
| minute | order_count | 
+--------+-------------+
| 1      | 0           |
| 2      | 2           | 
| 3      | 4           | 
| 4      | 6           | 
| 5      | 1           | 
| 6      | 4           | 
| 7      | 1           | 
| 8      | 2           | 
| 9      | 4           | 
| 10     | 1           | 
| 11     | 4           | 
| 12     | 6           | 
+--------+-------------+
<b>输出：</b>
+-------------+--------------+
| interval_no | total_orders | 
+-------------+--------------+
| 1           | 17           | 
| 2           | 18           |    
+-------------+--------------+
<b>解释：</b>
- 区间号 1 包括从 1 到 6 分钟的时间。这 6 分钟内的总订单数量为 (0 + 2 + 4 + 6 + 1 + 4) = 17。
- 区间号 2 包括从 7 到 12 分钟的时间。这 6 分钟内的总订单数量为 (1 + 2 + 4 + 1 + 4 + 6) = 18。
按升序顺序返回结果表，按 interval_no 排序。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：按区间分组

<!-- thinking:start -->

> **思考**
>
> 区间按分钟的取值划分，$1$ 到 $6$ 为区间 $1$，$7$ 到 $12$ 为区间 $2$。六行窗口默认分钟从 $1$ 起连续，并且每一段的右端落在 $6$ 的倍数上。一旦出现空缺，或这一段并不以 $6$ 的倍数结束，窗口就会漏掉区间，或把相邻区间加在一起。
>
> $\lfloor (minute+5)/6 \rfloor$ 把每个分钟送进所属区间。按这个值分组并对 `order_count` 求和，得到该区间的订单总数，再按区间号升序输出。

<!-- thinking:end -->

按 $\lfloor (minute + 5) / 6 \rfloor$ 把每分钟划入对应区间，再对区间内的 `order_count` 求和，并按区间号升序输出。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    (minute + 5) DIV 6 AS interval_no,
    SUM(order_count) AS total_orders
FROM Orders
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 窗口函数仍要扫描前缀。`minute` 连续时可用 $\lfloor(minute+5)/6\rfloor$ 直接分组，组内 `SUM` 即为该区间订单数，写法更短。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
SELECT
    FLOOR((minute + 5) / 6) AS interval_no,
    SUM(order_count) AS total_orders
FROM Orders
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
