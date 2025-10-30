---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3657.Find%20Loyal%20Customers/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3657. Find Loyal Customers](https://leetcode.com/problems/find-loyal-customers)

[中文文档](/solution/3600-3699/3657.Find%20Loyal%20Customers/README.md)

## Description

<!-- description:start -->

<p>Table: <code>customer_transactions</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    | 
+------------------+---------+
| transaction_id   | int     |
| customer_id      | int     |
| transaction_date | date    |
| amount           | decimal |
| transaction_type | varchar |
+------------------+---------+
transaction_id is the unique identifier for this table.
transaction_type can be either &#39;purchase&#39; or &#39;refund&#39;.
</pre>

<p>Write a solution to find <strong>loyal customers</strong>. A customer is considered <strong>loyal</strong> if they meet ALL the following criteria:</p>

<ul>
	<li>Made <strong>at least</strong>&nbsp;<code><font face="monospace">3</font></code>&nbsp;purchase transactions.</li>
	<li>Have been active for <strong>at least</strong> <code>30</code> days.</li>
	<li>Their <strong>refund rate</strong> is less than <code>20%</code> .</li>
</ul>

<p><em>Refund rate</em> is the proportion of transactions that are refunds, calculated as the number of refund transactions divided by the total number of transactions (purchases plus refunds).</p>

<p>Return <em>the result table&nbsp;ordered by</em> <code>customer_id</code> <em>in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>customer_transactions table:</p>

<pre class="example-io">
+----------------+-------------+------------------+--------+------------------+
| transaction_id | customer_id | transaction_date | amount | transaction_type |
+----------------+-------------+------------------+--------+------------------+
| 1              | 101         | 2024-01-05       | 150.00 | purchase         |
| 2              | 101         | 2024-01-15       | 200.00 | purchase         |
| 3              | 101         | 2024-02-10       | 180.00 | purchase         |
| 4              | 101         | 2024-02-20       | 250.00 | purchase         |
| 5              | 102         | 2024-01-10       | 100.00 | purchase         |
| 6              | 102         | 2024-01-12       | 120.00 | purchase         |
| 7              | 102         | 2024-01-15       | 80.00  | refund           |
| 8              | 102         | 2024-01-18       | 90.00  | refund           |
| 9              | 102         | 2024-02-15       | 130.00 | purchase         |
| 10             | 103         | 2024-01-01       | 500.00 | purchase         |
| 11             | 103         | 2024-01-02       | 450.00 | purchase         |
| 12             | 103         | 2024-01-03       | 400.00 | purchase         |
| 13             | 104         | 2024-01-01       | 200.00 | purchase         |
| 14             | 104         | 2024-02-01       | 250.00 | purchase         |
| 15             | 104         | 2024-02-15       | 300.00 | purchase         |
| 16             | 104         | 2024-03-01       | 350.00 | purchase         |
| 17             | 104         | 2024-03-10       | 280.00 | purchase         |
| 18             | 104         | 2024-03-15       | 100.00 | refund           |
+----------------+-------------+------------------+--------+------------------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+-------------+
| customer_id |
+-------------+
| 101         |
| 104         |
+-------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>Customer 101</strong>:

    <ul>
    	<li>Purchase transactions: 4 (IDs: 1, 2, 3, 4)&nbsp;</li>
    	<li>Refund transactions: 0</li>
    	<li>Refund rate: 0/4 = 0% (less than 20%)&nbsp;</li>
    	<li>Active period: Jan 5 to Feb 20 = 46 days (at least 30 days)&nbsp;</li>
    	<li>Qualifies as loyal&nbsp;</li>
    </ul>
    </li>
    <li><strong>Customer 102</strong>:
    <ul>
    	<li>Purchase transactions: 3 (IDs: 5, 6, 9)&nbsp;</li>
    	<li>Refund transactions: 2 (IDs: 7, 8)</li>
    	<li>Refund rate: 2/5 = 40% (exceeds 20%)&nbsp;</li>
    	<li>Not loyal&nbsp;</li>
    </ul>
    </li>
    <li><strong>Customer 103</strong>:
    <ul>
    	<li>Purchase transactions: 3 (IDs: 10, 11, 12)&nbsp;</li>
    	<li>Refund transactions: 0</li>
    	<li>Refund rate: 0/3 = 0% (less than 20%)&nbsp;</li>
    	<li>Active period: Jan 1 to Jan 3 = 2 days (less than 30 days)&nbsp;</li>
    	<li>Not loyal&nbsp;</li>
    </ul>
    </li>
    <li><strong>Customer 104</strong>:
    <ul>
    	<li>Purchase transactions: 5 (IDs: 13, 14, 15, 16, 17)&nbsp;</li>
    	<li>Refund transactions: 1 (ID: 18)</li>
    	<li>Refund rate: 1/6 = 16.67% (less than 20%)&nbsp;</li>
    	<li>Active period: Jan 1 to Mar 15 = 73 days (at least 30 days)&nbsp;</li>
    	<li>Qualifies as loyal&nbsp;</li>
    </ul>
    </li>

</ul>

<p>The result table is ordered by customer_id in ascending order.</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT customer_id
FROM customer_transactions
GROUP BY 1
HAVING
    COUNT(1) >= 3
    AND SUM(transaction_type = 'refund') / COUNT(1) < 0.2
    AND DATEDIFF(MAX(transaction_date), MIN(transaction_date)) >= 30
ORDER BY 1;
```

#### Pandas

```python
import pandas as pd


def find_loyal_customers(customer_transactions: pd.DataFrame) -> pd.DataFrame:
    customer_transactions["transaction_date"] = pd.to_datetime(
        customer_transactions["transaction_date"]
    )
    grouped = customer_transactions.groupby("customer_id")
    agg_df = grouped.agg(
        total_transactions=("transaction_type", "size"),
        refund_count=("transaction_type", lambda x: (x == "refund").sum()),
        min_date=("transaction_date", "min"),
        max_date=("transaction_date", "max"),
    ).reset_index()
    agg_df["date_diff"] = (agg_df["max_date"] - agg_df["min_date"]).dt.days
    agg_df["refund_ratio"] = agg_df["refund_count"] / agg_df["total_transactions"]
    result = (
        agg_df[
            (agg_df["total_transactions"] >= 3)
            & (agg_df["refund_ratio"] < 0.2)
            & (agg_df["date_diff"] >= 30)
        ][["customer_id"]]
        .sort_values("customer_id")
        .reset_index(drop=True)
    )
    return result
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
