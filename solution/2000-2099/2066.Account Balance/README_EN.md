# [2066. Account Balance](https://leetcode.com/problems/account-balance)

[中文文档](/solution/2000-2099/2066.Account%20Balance/README.md)

## Description

<p>Table: <code>Transactions</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| account_id  | int  |
| day         | date |
| type        | ENUM |
| amount      | int  |
+-------------+------+
(account_id, day) is the primary key for this table.
Each row contains information about one transaction including the transaction type and the amount.
type is ENUM of the type (&#39;Deposit&#39;,&#39;Withdraw&#39;) 
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the balance of each user after each transaction. You may assume that the balance of each account before any transaction is <code>0</code> and you may assume that the balance will not be below <code>0</code> at any moment.</p>

<p>Return the result table <strong>ordered</strong> by <code>account_id</code> and <code>day</code> <strong>in ascending order</strong>.</p>

<p>The query result format is in the following example.</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
