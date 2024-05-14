# [2986. Find Third Transaction 🔒](https://leetcode.com/problems/find-third-transaction)

[中文文档](/solution/2900-2999/2986.Find%20Third%20Transaction/README.md)

<!-- tags:Database -->

<!-- difficulty:Medium -->

## Description

<p>Table: <code>Transactions</code></p>

<pre>
+------------------+----------+
| Column Name      | Type     |
+------------------+----------+
| user_id          | int      |
| spend            | decimal  |
| transaction_date | datetime |
+------------------+----------+
(user_id, transaction_date) is column of unique values for this table.
This table contains user_id, spend, and transaction_date.
</pre>

<p>Write a solution to find the <strong>third transaction </strong>(if they have at least three transactions) of every user, where the <strong>spending</strong> on the preceding <strong>two transactions</strong> is <strong>lower</strong> than the spending on the <strong>third</strong> transaction.</p>

<p>Return <em>the result table by </em><code>user_id</code><em> in <strong>ascending</strong> order</em><em>.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Transactions table:
+---------+--------+---------------------+
| user_id | spend  | transaction_date    | 
+---------+--------+---------------------+
| 1       | 65.56  | 2023-11-18 13:49:42 | 
| 1       | 96.0   | 2023-11-30 02:47:26 |     
| 1       | 7.44   | 2023-11-02 12:15:23 | 
| 1       | 49.78  | 2023-11-12 00:13:46 | 
| 2       | 40.89  | 2023-11-21 04:39:15 |  
| 2       | 100.44 | 2023-11-20 07:39:34 | 
| 3       | 37.33  | 2023-11-03 06:22:02 | 
| 3       | 13.89  | 2023-11-11 16:00:14 | 
| 3       | 7.0    | 2023-11-29 22:32:36 | 
+---------+--------+---------------------+
<strong>Output</strong>
+---------+-------------------------+------------------------+
| user_id | third_transaction_spend | third_transaction_date | 
+---------+-------------------------+------------------------+
| 1       | 65.56                   | 2023-11-18 13:49:42    |  
+---------+-------------------------+------------------------+
<strong>Explanation</strong>
- For user_id 1, their third transaction occurred on 2023-11-18 at 13:49:42 with an amount of $65.56, surpassing the expenditures of the previous two transactions which were $7.44 on 2023-11-02 at 12:15:23 and $49.78 on 2023-11-12 at 00:13:46. Thus, this third transaction will be included in the output table.
- user_id 2 only has a total of 2 transactions, so there isn&#39;t a third transaction to consider.
- For user_id 3, the amount of $7.0 for their third transaction is less than that of the preceding two transactions, so it won&#39;t be included.
Output table is ordered by user_id in ascending order.

</pre>

## Solutions

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY user_id
                ORDER BY transaction_date
            ) AS rk,
            spend > (
                LAG(spend) OVER (
                    PARTITION BY user_id
                    ORDER BY transaction_date
                )
            )
            AND spend > (
                LAG(spend, 2) OVER (
                    PARTITION BY user_id
                    ORDER BY transaction_date
                )
            ) AS st
        FROM Transactions
    )
SELECT user_id, spend AS third_transaction_spend, transaction_date AS third_transaction_date
FROM T
WHERE rk = 3 AND st = 1;
```

<!-- tabs:end -->

<!-- end -->
