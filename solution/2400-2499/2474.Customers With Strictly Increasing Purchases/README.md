---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2474.Customers%20With%20Strictly%20Increasing%20Purchases/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2474. 购买量严格增加的客户 🔒](https://leetcode.cn/problems/customers-with-strictly-increasing-purchases)

[English Version](/solution/2400-2499/2474.Customers%20With%20Strictly%20Increasing%20Purchases/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Orders</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| order_id     | int  |
| customer_id  | int  |
| order_date   | date |
| price        | int  |
+--------------+------+
order_id 是该表的主键。
每行包含订单的 id、订购该订单的客户 id、订单日期和价格。
</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询，报告&nbsp;<strong>总购买量&nbsp;</strong>每年严格增加的客户 id。</p>

<ul>
	<li>客户在一年内的&nbsp;<strong>总购买量&nbsp;</strong>是该年订单价格的总和。如果某一年客户没有下任何订单，我们认为总购买量为 <code>0</code>。</li>
	<li>对于每个客户，要考虑的第一个年是他们&nbsp;<strong>第一次下单&nbsp;</strong>的年份。</li>
	<li>对于每个客户，要考虑的最后一年是他们&nbsp;<strong>最后一次下单&nbsp;</strong>的年份。</li>
</ul>

<p>以&nbsp;<strong>任意顺序&nbsp;</strong>返回结果表。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Orders 表:
+----------+-------------+------------+-------+
| order_id | customer_id | order_date | price |
+----------+-------------+------------+-------+
| 1        | 1           | 2019-07-01 | 1100  |
| 2        | 1           | 2019-11-01 | 1200  |
| 3        | 1           | 2020-05-26 | 3000  |
| 4        | 1           | 2021-08-31 | 3100  |
| 5        | 1           | 2022-12-07 | 4700  |
| 6        | 2           | 2015-01-01 | 700   |
| 7        | 2           | 2017-11-07 | 1000  |
| 8        | 3           | 2017-01-01 | 900   |
| 9        | 3           | 2018-11-07 | 900   |
+----------+-------------+------------+-------+
<strong>输出:</strong> 
+-------------+
| customer_id |
+-------------+
| 1           |
+-------------+
<strong>解释:</strong> 
客户 1: 第一年是 2019 年，最后一年是 2022 年
  - 2019: 1100 + 1200 = 2300
  - 2020: 3000
  - 2021: 3100
  - 2022: 4700
  我们可以看到总购买量每年都在严格增加，所以我们在答案中包含了客户 1。

客户 2: 第一年是2015年，最后一年是2017年
  - 2015: 700
  - 2016: 0
  - 2017: 1000
  我们没有把客户 2 包括在答案中，因为总的购买量并没有严格地增加。请注意，客户 2 在 2016 年没有购买任何物品。

客户 3: 第一年是 2017 年，最后一年是 2018 年
  - 2017: 900
  - 2018: 900</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 要找每年消费总额严格递增的顾客。按顾客与年份聚合 $SUM(price)$ 后，若金额序列严格递增，则「年份减去按金额排序的名次」在各年应相同。
>
> 窗口函数 $RANK()$ 按总额排序，计算 $YEAR-RANK$ 并按顾客去重：仅一种差值说明名次与年份同步上升。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    customer_id
FROM
    (
        SELECT
            customer_id,
            YEAR(order_date),
            SUM(price) AS total,
            YEAR(order_date) - RANK() OVER (
                PARTITION BY customer_id
                ORDER BY SUM(price)
            ) AS rk
        FROM Orders
        GROUP BY customer_id, YEAR(order_date)
    ) AS t
GROUP BY customer_id
HAVING COUNT(DISTINCT rk) = 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
