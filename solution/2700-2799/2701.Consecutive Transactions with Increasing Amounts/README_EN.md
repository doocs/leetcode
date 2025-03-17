---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2701.Consecutive%20Transactions%20with%20Increasing%20Amounts/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [2701. Consecutive Transactions with Increasing Amounts 🔒](https://leetcode.com/problems/consecutive-transactions-with-increasing-amounts)

[中文文档](/solution/2700-2799/2701.Consecutive%20Transactions%20with%20Increasing%20Amounts/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Transactions</code></p>

<pre>
+------------------+------+
| Column Name      | Type |
+------------------+------+
| transaction_id   | int  |
| customer_id      | int  |
| transaction_date | date |
| amount           | int  |
+------------------+------+
transaction_id is the primary key of this table. 
Each row contains information about transactions that includes unique (customer_id, transaction_date) along with the corresponding customer_id and amount.  
</pre>

<p>Write an SQL query to find the customers who have made consecutive transactions with increasing <code>amount</code>&nbsp;for at least three consecutive days. Include the <code>customer_id</code>,&nbsp;start date of the consecutive transactions&nbsp;period and the end date of the consecutive transactions period. There can be multiple consecutive transactions by a customer.</p>

<p>Return <em>the result table ordered by</em> <code>customer_id, consecutive_start, consecutive_end</code> <em>in <strong>ascending</strong> order.</em></p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong>&nbsp;
Transactions table:
+----------------+-------------+------------------+--------+
| transaction_id | customer_id | transaction_date | amount |
+----------------+-------------+------------------+--------+
| 1 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 101 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-01 &nbsp; &nbsp; &nbsp; | 100 &nbsp; &nbsp;|
| 2 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 101 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-02 &nbsp; &nbsp; &nbsp; | 150 &nbsp; &nbsp;|
| 3 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 101 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-03 &nbsp; &nbsp; &nbsp; | 200 &nbsp; &nbsp;|
| 4 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 102 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-01 &nbsp; &nbsp; &nbsp; | 50 &nbsp; &nbsp; |
| 5 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 102 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-03 &nbsp; &nbsp; &nbsp; | 100 &nbsp; &nbsp;|
| 6 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 102 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-04 &nbsp; &nbsp; &nbsp; | 200 &nbsp; &nbsp;|
| 7 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 105 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-01 &nbsp; &nbsp; &nbsp; | 100 &nbsp; &nbsp;|
| 8 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 105 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-02 &nbsp; &nbsp; &nbsp; | 150 &nbsp; &nbsp;|
| 9 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 105 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-03 &nbsp; &nbsp; &nbsp; | 200 &nbsp; &nbsp;|
| 10 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; | 105 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-04 &nbsp; &nbsp; &nbsp; | 300 &nbsp; &nbsp;|
| 11 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; | 105 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-12 &nbsp; &nbsp; &nbsp; | 250 &nbsp; &nbsp;|
| 12 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; | 105 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-13 &nbsp; &nbsp; &nbsp; | 260 &nbsp; &nbsp;|
| 13 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; | 105 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-14 &nbsp; &nbsp; &nbsp; | 270 &nbsp; &nbsp;|
+----------------+-------------+------------------+--------+
<strong>Output:</strong>&nbsp;
+-------------+-------------------+-----------------+
| customer_id | consecutive_start | consecutive_end |&nbsp;
+-------------+-------------------+-----------------+
| 101 &nbsp; &nbsp; &nbsp; &nbsp; |&nbsp; 2023-05-01 &nbsp; &nbsp; &nbsp; |&nbsp;2023-05-03 &nbsp; &nbsp; &nbsp;|&nbsp;
| 105 &nbsp; &nbsp; &nbsp; &nbsp; |&nbsp; 2023-05-01 &nbsp; &nbsp; &nbsp; |&nbsp;2023-05-04 &nbsp; &nbsp; &nbsp;|
| 105 &nbsp; &nbsp; &nbsp; &nbsp; |&nbsp; 2023-05-12 &nbsp; &nbsp; &nbsp; |&nbsp;2023-05-14 &nbsp; &nbsp; &nbsp;|&nbsp;
+-------------+-------------------+-----------------+
<strong>Explanation:</strong>&nbsp;
- customer_id 101 has made consecutive transactions with increasing amounts from May 1st, 2023, to May 3rd, 2023
- customer_id 102 does not have any consecutive transactions for at least 3 days.&nbsp;
- customer_id 105 has two sets of consecutive transactions: from May 1st, 2023, to May 4th, 2023, and from May 12th, 2023, to May 14th, 2023.&nbsp;
customer_id is sorted in ascending order.
</pre>

<p>&nbsp;</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            t1.*,
            SUM(
                CASE
                    WHEN t2.customer_id IS NULL THEN 1
                    ELSE 0
                END
            ) OVER (ORDER BY customer_id, transaction_date) AS s
        FROM
            Transactions AS t1
            LEFT JOIN Transactions AS t2
                ON t1.customer_id = t2.customer_id
                AND t1.amount > t2.amount
                AND DATEDIFF(t1.transaction_date, t2.transaction_date) = 1
    )
SELECT
    customer_id,
    MIN(transaction_date) AS consecutive_start,
    MAX(transaction_date) AS consecutive_end
FROM T
GROUP BY customer_id, s
HAVING COUNT(1) >= 3
ORDER BY customer_id;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
