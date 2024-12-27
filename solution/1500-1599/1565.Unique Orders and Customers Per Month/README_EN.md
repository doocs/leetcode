---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1565.Unique%20Orders%20and%20Customers%20Per%20Month/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1565. Unique Orders and Customers Per Month 🔒](https://leetcode.com/problems/unique-orders-and-customers-per-month)

[中文文档](/solution/1500-1599/1565.Unique%20Orders%20and%20Customers%20Per%20Month/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Orders</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| order_id      | int     |
| order_date    | date    |
| customer_id   | int     |
| invoice       | int     |
+---------------+---------+
order_id is the column with unique values for this table.
This table contains information about the orders made by customer_id.
</pre>

<p>&nbsp;</p>

<p>Write a solution to find the number of <strong>unique orders</strong> and the number of <strong>unique customers</strong> with invoices <strong>&gt; $20</strong> for each <strong>different month</strong>.</p>

<p>Return the result table sorted in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Orders table:
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
<strong>Output:</strong> 
+---------+-------------+----------------+
| month   | order_count | customer_count |
+---------+-------------+----------------+
| 2020-09 | 2           | 2              |
| 2020-10 | 1           | 1              |
| 2020-12 | 2           | 1              |
| 2021-01 | 1           | 1              |
+---------+-------------+----------------+
<strong>Explanation:</strong> 
In September 2020 we have two orders from 2 different customers with invoices &gt; $20.
In October 2020 we have two orders from 1 customer, and only one of the two orders has invoice &gt; $20.
In November 2020 we have two orders from 2 different customers but invoices &lt; $20, so we don&#39;t include that month.
In December 2020 we have two orders from 1 customer both with invoices &gt; $20.
In January 2021 we have two orders from 2 different customers, but only one of them with invoice &gt; $20.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Conditional Filtering + Grouping Statistics

We can first filter out orders with an amount greater than $20$, and then group by month to count the number of orders and customers.

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
