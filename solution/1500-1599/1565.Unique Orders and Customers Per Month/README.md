---
comments: true
difficulty: 简单
tags:
    - 数据库
---

<!-- problem:start -->

# [1565. 按月统计订单数与顾客数 🔒](https://leetcode.cn/problems/unique-orders-and-customers-per-month)

[English Version](/solution/1500-1599/1565.Unique%20Orders%20and%20Customers%20Per%20Month/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Orders</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| order_id      | int     |
| order_date    | date    |
| customer_id   | int     |
| invoice       | int     |
+---------------+---------+
order_id 是该表具有唯一值的列<sub>。</sub>
这张表包含顾客(customer_id)所下订单的信息<sub>。</sub>
</pre>

<p>&nbsp;</p>

<p>写一个查询语句来 <strong>按月 </strong>统计金额（invoice）<strong>大于 $20 </strong>的唯一 <strong>订单数</strong> 和唯一 <strong>顾客数 。</strong></p>

<p>查询结果无排序要求。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<code><strong>输入：</strong>
Orders</code>
+----------+------------+-------------+------------+
| order_id | order_date | customer_id | invoice    |
+----------+------------+-------------+------------+
| 1        | 2020-09-15 | 1           | 30         |
| 2        | 2020-09-17 | 2           | 90         |
| 3        | 2020-10-06 | 3           | 20         |
| 4        | 2020-10-20 | 3           | 21         |
| 5        | 2020-11-10 | 1           | 10         |
| 6        | 2020-11-21 | 2           | 15         |
| 7        | 2020-12-01 | 4           | 55         |
| 8        | 2020-12-03 | 4           | 77         |
| 9        | 2021-01-07 | 3           | 31         |
| 10       | 2021-01-15 | 2           | 20         |
+----------+------------+-------------+------------+
<strong>输出：</strong>
+---------+-------------+----------------+
| month   | order_count | customer_count |
+---------+-------------+----------------+
| 2020-09 | 2           | 2              |
| 2020-10 | 1           | 1              |
| 2020-12 | 2           | 1              |
| 2021-01 | 1           | 1              |
+---------+-------------+----------------+
<strong>解释：</strong>
在 2020 年 09 月<sub>，</sub>有 2 份来自 2 位不同顾客的金额大于 $20 的订单<sub>。</sub>
在 2020 年 10 月<sub>，</sub>有 2 份来自 1 位顾客的订单<sub>，</sub>并且只有其中的 1 份订单金额大于 $20 <sub>。</sub>
在 2020 年 11 月<sub>，</sub>有 2 份来自 2 位不同顾客的订单<sub>，</sub>但由于金额都小于 $20 <sub>，</sub>所以我们的查询结果中不包含这个月的数据<sub>。</sub>
在 2020 年 12 月<sub>，</sub>有 2 份来自 1 位顾客的订单<sub>，</sub>且 2 份订单金额都大于 $20<sub> 。</sub>
在 2021 年 01 月<sub>，</sub>有 2 份来自 2 位不同顾客的订单<sub>，</sub>但只有其中一份订单金额大于 $20 <sub>。</sub></pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：条件筛选 + 分组统计

<!-- thinking:start -->

> **思考**
>
> 按月统计发票额大于 $20$ 的订单数与去重顾客数。日期带日，直接分组会按天拆开。
>
> 先过滤 $invoice>20$，再把日期格式化到年月，按月分组：$COUNT$ 订单，$COUNT(DISTINCT\ customer\_id)$ 顾客。Pandas 路径用 $to\_period$ 与 $nunique$ 表达同一聚合。

<!-- thinking:end -->

我们可以先筛选出金额大于 $20$ 的订单，然后按月份进行分组统计订单数和顾客数。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    DATE_FORMAT(order_date, '%Y-%m') AS month,
    COUNT(order_id) AS order_count,
    COUNT(DISTINCT customer_id) AS customer_count
FROM Orders
WHERE invoice > 20
GROUP BY 1;
```

#### Pandas

```python
import pandas as pd


def unique_orders_and_customers(orders: pd.DataFrame) -> pd.DataFrame:
    filtered_orders = orders[orders["invoice"] > 20]
    filtered_orders["month"] = (
        filtered_orders["order_date"].dt.to_period("M").astype(str)
    )
    result = (
        filtered_orders.groupby("month")
        .agg(
            order_count=("order_id", "count"), customer_count=("customer_id", "nunique")
        )
        .reset_index()
    )
    return result
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
