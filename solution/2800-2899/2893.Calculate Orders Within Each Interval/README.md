---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2893.Calculate%20Orders%20Within%20Each%20Interval/README.md
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

### 方法一：窗口函数

我们可以用窗口函数 `sum() over()` 来计算每 $6$ 分钟的订单总数，然后每条记录中的 `minute` 能被 $6$ 整除的记录。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            minute,
            SUM(order_count) OVER (
                ORDER BY minute
                ROWS 5 PRECEDING
            ) AS total_orders
        FROM Orders
    )
SELECT minute / 6 AS interval_no, total_orders
FROM T
WHERE minute % 6 = 0;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

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
