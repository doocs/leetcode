---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3657.Find%20Loyal%20Customers/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3657. 寻找忠实客户](https://leetcode.cn/problems/find-loyal-customers)

[English Version](/solution/3600-3699/3657.Find%20Loyal%20Customers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>customer_transactions</code></p>

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
transaction_id 是这张表的唯一主键。
transaction_type 可以是 “purchase” 或 “refund”。
</pre>

<p>编写一个解决方案来查找 <strong>忠实客户</strong>。如果满足下述所有条件，可以认为该客户是 <strong>忠实</strong> 客户：</p>

<ul>
	<li>进行了 <strong>至少</strong>&nbsp;<code><font face="monospace">3</font></code>&nbsp;次购买交易。</li>
	<li>活跃了&nbsp;<strong>至少</strong>&nbsp;<code>30</code>&nbsp;天。</li>
	<li>他们的 <strong>退款率</strong>&nbsp;少于&nbsp;<code>20%</code>。</li>
</ul>

<p>返回结果表以&nbsp;<code>customer_id</code> <strong>升序</strong>&nbsp;排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>customer_transactions 表：</p>

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

<p><strong>输出：</strong></p>

<pre class="example-io">
+-------------+
| customer_id |
+-------------+
| 101         |
| 104         |
+-------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>客户 101</strong>:

    <ul>
    	<li>购买交易：4 (IDs: 1, 2, 3, 4)&nbsp;</li>
    	<li>退款交易：0</li>
    	<li>退款率：0/4 = 0%（少于 20%）</li>
    	<li>活跃时期：1 月&nbsp;5 日到 2 月 20 日 = 46 天（至少 30 天）</li>
    	<li>符合忠诚客户条件</li>
    </ul>
    </li>
    <li><strong>客户 102</strong>:
    <ul>
    	<li>购买交易：3 (IDs: 5, 6, 9)&nbsp;</li>
    	<li>退款交易：2 (IDs: 7, 8)</li>
    	<li>退款率：2/5 = 40% (超过&nbsp;20%)&nbsp;</li>
    	<li>不符合忠诚客户条件</li>
    </ul>
    </li>
    <li><strong>客户 103</strong>:
    <ul>
    	<li>购买交易：3 (IDs: 10, 11, 12)&nbsp;</li>
    	<li>退款交易：0</li>
    	<li>退款率：0/3 = 0%（少于 20%）</li>
    	<li>活跃时期：1 月 1 日到 1 月 3 日 =&nbsp;2 天（少于 30 天）</li>
    	<li>不符合忠诚客户条件</li>
    </ul>
    </li>
    <li><strong>客户 104</strong>:
    <ul>
    	<li>购买交易：5 (IDs: 13, 14, 15, 16, 17)&nbsp;</li>
    	<li>退款交易：1 (ID: 18)</li>
    	<li>退款率：1/6 = 16.67%（少于 20%）</li>
    	<li>活跃时期：1 月 1 日到 3 月 15 日 = 73 天（至少 30 天）</li>
    	<li>符合忠诚客户条件</li>
    </ul>
    </li>

</ul>

<p>结果表以 customer_id 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
