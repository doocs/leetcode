---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3705.Find%20Golden%20Hour%20Customers/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3705. 寻找黄金时段客户](https://leetcode.cn/problems/find-golden-hour-customers)

[English Version](/solution/3700-3799/3705.Find%20Golden%20Hour%20Customers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>restaurant_orders</code></p>

<pre>
+------------------+----------+
| Column Name      | Type     | 
+------------------+----------+
| order_id         | int      |
| customer_id      | int      |
| order_timestamp  | datetime |
| order_amount     | decimal  |
| payment_method   | varchar  |
| order_rating     | int      |
+------------------+----------+
order_id 是这张表的唯一主键。
payment_method 可以是 cash，card 或 app。
order_rating 在 1 到 5 之间，其中 5 是最佳（如果没有评分则是 NULL）。
order_timestamp 同时包含日期和时间信息。
</pre>

<p>编写一个解决方案来寻找 <strong>黄金时间客户</strong>&nbsp;- 高峰时段持续订购且满意度高的客户。客户若满足以下所有条件，则被视为 <strong>黄金时段客户</strong>：</p>

<ul>
	<li>进行 <strong>至少</strong>&nbsp;<code>3</code>&nbsp;笔订单。</li>
	<li>他们有&nbsp;<strong>至少</strong>&nbsp;<code>60%</code>&nbsp;的订单在 <strong>高峰时间</strong>&nbsp;中（<code>11:00</code>-<code>14:00</code> 或&nbsp;<code>18:00</code>-<code>21:00</code>）。</li>
	<li>他们的 <strong>平均评分</strong> 至少为 <code>4.0</code>，四舍五入到小数点后 <code>2</code> 位。</li>
	<li>已评价至少 <code>50%</code> 的订单。</li>
</ul>

<p>返回结果表按&nbsp;<code>average_rating</code> <strong>降序</strong>&nbsp;排序，然后按&nbsp;<code>customer_id</code> <strong>降序&nbsp;</strong>排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><b>输入：</b></p>

<p>restaurant_orders 表：</p>

<pre class="example-io">
+----------+-------------+---------------------+--------------+----------------+--------------+
| order_id | customer_id | order_timestamp     | order_amount | payment_method | order_rating |
+----------+-------------+---------------------+--------------+----------------+--------------+
| 1        | 101         | 2024-03-01 12:30:00 | 25.50        | card           | 5            |
| 2        | 101         | 2024-03-02 19:15:00 | 32.00        | app            | 4            |
| 3        | 101         | 2024-03-03 13:45:00 | 28.75        | card           | 5            |
| 4        | 101         | 2024-03-04 20:30:00 | 41.00        | app            | NULL         |
| 5        | 102         | 2024-03-01 11:30:00 | 18.50        | cash           | 4            |
| 6        | 102         | 2024-03-02 12:00:00 | 22.00        | card           | 3            |
| 7        | 102         | 2024-03-03 15:30:00 | 19.75        | cash           | NULL         |
| 8        | 103         | 2024-03-01 19:00:00 | 55.00        | app            | 5            |
| 9        | 103         | 2024-03-02 20:45:00 | 48.50        | app            | 4            |
| 10       | 103         | 2024-03-03 18:30:00 | 62.00        | card           | 5            |
| 11       | 104         | 2024-03-01 10:00:00 | 15.00        | cash           | 3            |
| 12       | 104         | 2024-03-02 09:30:00 | 18.00        | cash           | 2            |
| 13       | 104         | 2024-03-03 16:00:00 | 20.00        | card           | 3            |
| 14       | 105         | 2024-03-01 12:15:00 | 30.00        | app            | 4            |
| 15       | 105         | 2024-03-02 13:00:00 | 35.50        | app            | 5            |
| 16       | 105         | 2024-03-03 11:45:00 | 28.00        | card           | 4            |
+----------+-------------+---------------------+--------------+----------------+--------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-------------+--------------+----------------------+----------------+
| customer_id | total_orders | peak_hour_percentage | average_rating |
+-------------+--------------+----------------------+----------------+
| 103         | 3            | 100                  | 4.67           |
| 101         | 4            | 100                  | 4.67           |
| 105         | 3            | 100                  | 4.33           |
+-------------+--------------+----------------------+----------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>客户 101：</strong>

    <ul>
    	<li>总订单数：4（至少 3 笔）</li>
    	<li>高峰时间订单：4 笔中有 4 笔（12:30，19:15，13:45 和 20:30 在高峰时间）</li>
    	<li>高峰时间占比：100%（至少 60%）</li>
    	<li>已评分的订单：4 笔中有 3 笔（75% 评分完成率）</li>
    	<li>平均评分：(5+4+5)/3 = 4.67（至少 4.0）</li>
    	<li>结果：<strong>黄金时段客户</strong></li>
    </ul>
    </li>
    <li><strong>客户 102</strong>:
    <ul>
    	<li>总订单数：3（至少 3 笔）</li>
    	<li>高峰时间订单：3 笔中有 2 笔（11:30，12:00 都在高峰时间，但 15:30 不是）</li>
    	<li>高峰时间占比：2/3 = 66.67%（至少 60%）</li>
    	<li>已评分的订单：3 笔中有 2 笔（66.67% 评分完成率）</li>
    	<li>平均评分：(4+3)/2 = 3.5（少于 4.0）</li>
    	<li>结果：<strong>不是黄金时段客户</strong>（平均评分太低）</li>
    </ul>
    </li>
    <li><strong>客户 103</strong>:
    <ul>
    	<li>总订单数：3（至少 3 笔）</li>
    	<li>高峰时间订单：3 笔中有 3 （19:00，20:45，18:30 都在傍晚高峰时间）</li>
    	<li>高峰时间占比：3/3 = 100%（至少 60%）</li>
    	<li>已评分的订单：3 笔中有 3 笔（100% 评分完成率）</li>
    	<li>平均评分：(5+4+5)/3 = 4.67（至少 4.0）</li>
    	<li>结果：<strong>黄金时段客户</strong></li>
    </ul>
    </li>
    <li><strong>客户 104</strong>:
    <ul>
    	<li>总订单数：3（至少 3 笔）</li>
    	<li>高峰时间订单：3 笔中有 0 笔（10:00，09:30，16:00 都不在高峰时间）</li>
    	<li>高峰时间占比：0/3 = 0%（至少 60%）</li>
    	<li>结果：<strong>不是黄金时段客户</strong>（高峰时段订单不足）</li>
    </ul>
    </li>
    <li><strong>客户 105</strong>:
    <ul>
    	<li>总订单数：3（至少 3 笔）</li>
    	<li>高峰时间订单：3 笔中有 3 笔（12:15，13:00，11:45 都在中午高峰时间）</li>
    	<li>高峰时间占比：3/3 = 100%（至少 60%）</li>
    	<li>已评分的订单：3 笔中有 3 笔（100% 评分完成率）</li>
    	<li>平均评分：(4+5+4)/3 = 4.33（至少 4.0）</li>
    	<li>结果：<strong>黄金时段客户</strong></li>
    </ul>
    </li>

</ul>

<p>结果表按 average_rating 降序排序，然后按 customer_id 降序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组统计

我们可以将订单按照 `customer_id` 进行分组，统计每个顾客的总订单数、峰值时段订单数、评分订单数和平均评分，然后根据题目中的条件进行筛选，最后按照平均评分降序、顾客 ID 降序排序。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    customer_id,
    COUNT(1) total_orders,
    ROUND(
        SUM(
            TIME(order_timestamp) BETWEEN '11:00:00' AND '14:00:00'
            OR TIME(order_timestamp) BETWEEN '18:00:00' AND '21:00:00'
        ) / COUNT(1) * 100
    ) peak_hour_percentage,
    ROUND(AVG(order_rating), 2) average_rating
FROM restaurant_orders
GROUP BY customer_id
HAVING
    total_orders >= 3
    AND peak_hour_percentage >= 60
    AND average_rating >= 4.0
    AND SUM(order_rating IS NOT NULL) / total_orders >= 0.5
ORDER BY average_rating DESC, customer_id DESC;
```

#### Pandas

```python
import pandas as pd
import numpy as np


def find_golden_hour_customers(restaurant_orders: pd.DataFrame) -> pd.DataFrame:
    df = restaurant_orders.copy()
    df["order_timestamp"] = pd.to_datetime(df["order_timestamp"])
    df["is_peak_hour"] = df["order_timestamp"].dt.time.between(
        pd.to_datetime("11:00:00").time(), pd.to_datetime("14:00:00").time()
    ) | df["order_timestamp"].dt.time.between(
        pd.to_datetime("18:00:00").time(), pd.to_datetime("21:00:00").time()
    )
    grouped = (
        df.groupby("customer_id")
        .agg(
            total_orders=("order_timestamp", "count"),
            peak_hour_count=("is_peak_hour", "sum"),
            average_rating=("order_rating", lambda x: x.dropna().mean()),
            non_null_rating_count=("order_rating", lambda x: x.notna().sum()),
        )
        .reset_index()
    )
    grouped["average_rating"] = grouped["average_rating"].round(2)
    grouped["peak_hour_percentage"] = (
        grouped["peak_hour_count"] / grouped["total_orders"] * 100
    ).round()
    filtered = grouped[
        (grouped["total_orders"] >= 3)
        & (grouped["peak_hour_percentage"] >= 60)
        & (grouped["average_rating"] >= 4.0)
        & (grouped["non_null_rating_count"] / grouped["total_orders"] >= 0.5)
    ]
    filtered = filtered.sort_values(
        by=["average_rating", "customer_id"], ascending=[False, False]
    )
    return filtered[
        ["customer_id", "total_orders", "peak_hour_percentage", "average_rating"]
    ]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
