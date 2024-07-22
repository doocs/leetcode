---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3230.Customer%20Purchasing%20Behavior%20Analysis/README.md
---

<!-- problem:start -->

# [3230. Customer Purchasing Behavior Analysis 🔒](https://leetcode.cn/problems/customer-purchasing-behavior-analysis)

[English Version](/solution/3200-3299/3230.Customer%20Purchasing%20Behavior%20Analysis/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Table: <code>Transactions</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| transaction_id   | int     |
| customer_id      | int     |
| product_id       | int     |
| transaction_date | date    |
| amount           | decimal |
+------------------+---------+
transaction_id is the unique identifier for this table.
Each row of this table contains information about a transaction, including the customer ID, product ID, date, and amount spent.
</pre>

<p>Table: <code>Products</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| product_id  | int     |
| category    | varchar |
| price       | decimal |
+-------------+---------+
product_id is the unique identifier for this table.
Each row of this table contains information about a product, including its category and price.
</pre>

<p>Write a solution to analyze customer purchasing behavior. For <strong>each customer</strong>, calculate:</p>

<ul>
	<li>The total amount spent.</li>
	<li>The number of transactions.</li>
	<li>The number of <strong>unique</strong> product categories purchased.</li>
	<li>The average amount spent.&nbsp;</li>
	<li>The <strong>most frequently</strong> purchased product category&nbsp;(if there is a tie, choose the one with the most recent transaction).</li>
	<li>A <strong>loyalty score</strong>&nbsp;defined as: (Number of transactions * 10) + (Total amount spent / 100).</li>
</ul>

<p>Round <code>total_amount</code>, <code>avg_transaction_amount</code>, and <code>loyalty_score</code> to <code>2</code> decimal places.</p>

<p>Return <em>the result table ordered by</em> <code>loyalty_score</code> <em>in <strong>descending</strong> order</em>, <em>then by </em><code>customer_id</code><em> in <strong>ascending</strong> order</em>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p><code>Transactions</code> table:</p>

<pre class="example-io">
+----------------+-------------+------------+------------------+--------+
| transaction_id | customer_id | product_id | transaction_date | amount |
+----------------+-------------+------------+------------------+--------+
| 1              | 101         | 1          | 2023-01-01       | 100.00 |
| 2              | 101         | 2          | 2023-01-15       | 150.00 |
| 3              | 102         | 1          | 2023-01-01       | 100.00 |
| 4              | 102         | 3          | 2023-01-22       | 200.00 |
| 5              | 101         | 3          | 2023-02-10       | 200.00 |
+----------------+-------------+------------+------------------+--------+
</pre>

<p><code>Products</code> table:</p>

<pre class="example-io">
+------------+----------+--------+
| product_id | category | price  |
+------------+----------+--------+
| 1          | A        | 100.00 |
| 2          | B        | 150.00 |
| 3          | C        | 200.00 |
+------------+----------+--------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+-------------+--------------+-------------------+-------------------+------------------------+--------------+---------------+
| customer_id | total_amount | transaction_count | unique_categories | avg_transaction_amount | top_category | loyalty_score |
+-------------+--------------+-------------------+-------------------+------------------------+--------------+---------------+
| 101         | 450.00       | 3                 | 3                 | 150.00                 | C            | 34.50         |
| 102         | 300.00       | 2                 | 2                 | 150.00                 | C            | 23.00         |
+-------------+--------------+-------------------+-------------------+------------------------+--------------+---------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For customer 101:
	<ul>
		<li>Total amount spent: 100.00 + 150.00 + 200.00 = 450.00</li>
		<li>Number of transactions: 3</li>
		<li>Unique categories: A, B, C (3 categories)</li>
		<li>Average transaction amount: 450.00 / 3 = 150.00</li>
		<li>Top category: C (Customer 101 made 1 purchase each in categories A, B, and C. Since the count is the same for all categories, we choose the most recent transaction, which is category C on 2023-02-10)</li>
		<li>Loyalty score: (3 * 10) + (450.00 / 100) = 34.50</li>
	</ul>
	</li>
	<li>For customer 102:
	<ul>
		<li>Total amount spent: 100.00 + 200.00 = 300.00</li>
		<li>Number of transactions: 2</li>
		<li>Unique categories: A, C (2 categories)</li>
		<li>Average transaction amount: 300.00 / 2 = 150.00</li>
		<li>Top category: C (Customer 102 made 1 purchase each in categories A and C. Since the count is the same for both categories, we choose the most recent transaction, which is category C on 2023-01-22)</li>
		<li>Loyalty score: (2 * 10) + (300.00 / 100) = 23.00</li>
	</ul>
	</li>
</ul>

<p><strong>Note:</strong> The output is ordered by loyalty_score in descending order, then by customer_id in ascending order.</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组 + 窗口函数 + 连接

我们首先将 `Transactions` 表和 `Products` 表连接起来，记录在临时表 `T` 中。

然后，我们使用 `T` 表计算每个用户在每个类别下的交易次数以及最近的交易日期，将结果保存在临时表 `P` 中。

接着，我们使用 `P` 表计算每个用户在每个类别下的交易次数的排名，将结果保存在临时表 `R` 中。

最后，我们使用 `T` 表和 `R` 表计算每个用户的总交易金额、交易次数、唯一类别数、平均交易金额、最常购买的类别、忠诚度分数，并按照忠诚度分数降序、用户 ID 升序的顺序返回结果。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT *
        FROM
            Transactions
            JOIN Products USING (product_id)
    ),
    P AS (
        SELECT
            customer_id,
            category,
            COUNT(1) cnt,
            MAX(transaction_date) max_date
        FROM T
        GROUP BY 1, 2
    ),
    R AS (
        SELECT
            customer_id,
            category,
            RANK() OVER (
                PARTITION BY customer_id
                ORDER BY cnt DESC, max_date DESC
            ) rk
        FROM P
    )
SELECT
    t.customer_id,
    ROUND(SUM(amount), 2) total_amount,
    COUNT(1) transaction_count,
    COUNT(DISTINCT t.category) unique_categories,
    ROUND(AVG(amount), 2) avg_transaction_amount,
    r.category top_category,
    ROUND(COUNT(1) * 10 + SUM(amount) / 100, 2) loyalty_score
FROM
    T t
    JOIN R r ON t.customer_id = r.customer_id AND r.rk = 1
GROUP BY 1
ORDER BY 7 DESC, 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
