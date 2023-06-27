# [2752. Customers with Maximum Number of Transactions on Consecutive Days](https://leetcode.cn/problems/customers-with-maximum-number-of-transactions-on-consecutive-days)

[English Version](/solution/2700-2799/2752.Customers%20with%20Maximum%20Number%20of%20Transactions%20on%20Consecutive%20Days/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

<p>Write an SQL query to find all&nbsp;<code>customer_id</code>&nbsp;who made the maximum number of transactions on consecutive days.</p>

<p>Return <em>all</em> <code>customer_id</code>&nbsp;<em>with the maximum number of consecutive transactions.&nbsp;</em><em>Order the result table by</em>&nbsp;<code>customer_id</code> <em>in <strong>ascending</strong> order.</em></p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Transactions table:
+----------------+-------------+------------------+--------+
| transaction_id | customer_id | transaction_date | amount |
+----------------+-------------+------------------+--------+
| 1              | 101         | 2023-05-01       | 100    |
| 2              | 101         | 2023-05-02       | 150    |
| 3              | 101         | 2023-05-03       | 200    |
| 4              | 102         | 2023-05-01       | 50     |
| 5              | 102         | 2023-05-03       | 100    |
| 6              | 102         | 2023-05-04       | 200    |
| 7              | 105         | 2023-05-01       | 100    |
| 8              | 105         | 2023-05-02       | 150    |
| 9              | 105         | 2023-05-03       | 200    |
+----------------+-------------+------------------+--------+
<strong>Output:</strong> 
+-------------+
| customer_id | 
+-------------+
| 101         | 
| 105         | 
+-------------+
<strong>Explanation:</strong> 
- customer_id 101 has a total of 3 transactions, and all of them are consecutive.
- customer_id 102 has a total of 3 transactions, but only 2 of them are consecutive. 
- customer_id 105 has a total of 3 transactions, and all of them are consecutive.
In total, the highest number of consecutive transactions is 3, achieved by customer_id 101 and 105. The customer_id are sorted in ascending order.
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
