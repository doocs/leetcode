---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3705.Find%20Golden%20Hour%20Customers/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3705. Find Golden Hour Customers](https://leetcode.com/problems/find-golden-hour-customers)

[中文文档](/solution/3700-3799/3705.Find%20Golden%20Hour%20Customers/README.md)

## Description

<!-- description:start -->

<p>Table: <code>restaurant_orders</code></p>

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
order_id is the unique identifier for this table.
payment_method can be cash, card, or app.
order_rating is between 1 and 5, where 5 is the best (NULL if not rated).
order_timestamp contains both date and time information.
</pre>

<p>Write a solution to find <strong>golden hour customers</strong>&nbsp;- customers who consistently order during peak hours and provide high satisfaction. A customer is a <strong>golden hour customer</strong> if they meet ALL the following criteria:</p>

<ul>
	<li>Made <strong>at least</strong> <code>3</code> orders.</li>
	<li><strong>At least</strong> <code>60%</code> of their orders are during <strong>peak hours&nbsp;</strong>(<code>11:00</code>-<code>14:00</code> or <code>18:00</code>-<code>21:00</code>).</li>
	<li>Their <strong>average rating</strong> for rated orders is at least <code>4.0,</code> round it to<code> 2 </code>decimal places.</li>
	<li>Have rated <strong>at least</strong> <code>50%</code> of their orders.</li>
</ul>

<p>Return <em>the result table ordered by</em> <code>average_rating</code> <em>in <strong>descending</strong> order, then by</em> <code>customer_id</code>​​​​​​​ <em>in <strong>descending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>restaurant_orders table:</p>

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

<p><strong>Output:</strong></p>

<pre class="example-io">
+-------------+--------------+----------------------+----------------+
| customer_id | total_orders | peak_hour_percentage | average_rating |
+-------------+--------------+----------------------+----------------+
| 103         | 3            | 100                  | 4.67           |
| 101         | 4            | 75                   | 4.67           |
| 105         | 3            | 100                  | 4.33           |
+-------------+--------------+----------------------+----------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>Customer 101</strong>:

    <ul>
    	<li>Total orders: 4 (at least 3)&nbsp;</li>
    	<li>Peak hour orders: 3 out of 4 (12:30, 19:15, 13:45, and 20:30 are in peak hours)</li>
    	<li>Peak hour percentage: 3/4 = 75% (at least 60%)&nbsp;</li>
    	<li>Rated orders: 3 out of 4 (75% rating completion)&nbsp;</li>
    	<li>Average rating: (5+4+5)/3 = 4.67 (at least 4.0)&nbsp;</li>
    	<li>Result: <strong>Golden hour customer</strong></li>
    </ul>
    </li>
    <li><strong>Customer 102</strong>:
    <ul>
    	<li>Total orders: 3 (at least 3)&nbsp;</li>
    	<li>Peak hour orders: 2 out of 3 (11:30, 12:00 are in peak hours; 15:30 is not)</li>
    	<li>Peak hour percentage: 2/3 = 66.67% (at least 60%)&nbsp;</li>
    	<li>Rated orders: 2 out of 3 (66.67% rating completion)&nbsp;</li>
    	<li>Average rating: (4+3)/2 = 3.5 (less than 4.0)&nbsp;</li>
    	<li>Result: <strong>Not a golden hour customer</strong> (average rating too low)</li>
    </ul>
    </li>
    <li><strong>Customer 103</strong>:
    <ul>
    	<li>Total orders: 3 (at least 3)&nbsp;</li>
    	<li>Peak hour orders: 3 out of 3 (19:00, 20:45, 18:30 all in evening peak)</li>
    	<li>Peak hour percentage: 3/3 = 100% (at least 60%)&nbsp;</li>
    	<li>Rated orders: 3 out of 3 (100% rating completion)&nbsp;</li>
    	<li>Average rating: (5+4+5)/3 = 4.67 (at least 4.0)&nbsp;</li>
    	<li>Result: <strong>Golden hour customer</strong></li>
    </ul>
    </li>
    <li><strong>Customer 104</strong>:
    <ul>
    	<li>Total orders: 3 (at least 3)&nbsp;</li>
    	<li>Peak hour orders: 0 out of 3 (10:00, 09:30, 16:00 all outside peak hours)</li>
    	<li>Peak hour percentage: 0/3 = 0% (less than 60%)&nbsp;</li>
    	<li>Result: <strong>Not a golden hour customer</strong> (insufficient peak hour orders)</li>
    </ul>
    </li>
    <li><strong>Customer 105</strong>:
    <ul>
    	<li>Total orders: 3 (at least 3)&nbsp;</li>
    	<li>Peak hour orders: 3 out of 3 (12:15, 13:00, 11:45 all in lunch peak)</li>
    	<li>Peak hour percentage: 3/3 = 100% (at least 60%)&nbsp;</li>
    	<li>Rated orders: 3 out of 3 (100% rating completion)&nbsp;</li>
    	<li>Average rating: (4+5+4)/3 = 4.33 (at least 4.0)&nbsp;</li>
    	<li>Result: <strong>Golden hour customer</strong></li>
    </ul>
    </li>

</ul>

<p>The results table is ordered by average_rating DESC, then customer_id DESC.</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Grouping and Statistics

We can group the orders by `customer_id` and calculate the total number of orders, number of orders during peak hours, number of rated orders, and average rating for each customer. Then we filter based on the conditions in the problem and sort by average rating in descending order, followed by customer ID in descending order.

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
