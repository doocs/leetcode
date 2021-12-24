# [1555. Bank Account Summary](https://leetcode.com/problems/bank-account-summary)

[中文文档](/solution/1500-1599/1555.Bank%20Account%20Summary/README.md)

## Description

<p>Table:&nbsp;<code>Users</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| user_id      | int     |
| user_name    | varchar |
| credit       | int     |
+--------------+---------+
user_id is the primary key for this table.
Each row of this table contains the current credit information for each user.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Transactions</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| trans_id      | int     |
| paid_by       | int     |
| paid_to       | int     |
| amount        | int     |
| transacted_on | date    |
+---------------+---------+
trans_id is the primary key for this table.
Each row of this table contains the information about the transaction in the bank.
User with id (paid_by) transfer money to user with id (paid_to).
</pre>

<p>&nbsp;</p>

<p>Leetcode&nbsp;Bank (LCB) helps its coders in making virtual&nbsp;payments. Our bank records all transactions in the table <em>Transaction</em>, we want&nbsp;to find out the current balance of all users and check wheter they have breached their credit limit (If their current credit is less than 0).</p>

<p>Write an SQL query to report.</p>

<ul>
	<li><code>user_id</code></li>
	<li><code>user_name</code></li>
	<li><code>credit</code>, current balance after performing transactions.&nbsp;&nbsp;</li>
	<li><code>credit_limit_breached</code>, check&nbsp;credit_limit&nbsp;(&quot;Yes&quot; or &quot;No&quot;)</li>
</ul>

<p>Return the result table in <strong>any</strong> order.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>

<pre>
<code>Users</code> table:
+------------+--------------+-------------+
| user_id    | user_name    | credit      |
+------------+--------------+-------------+
| 1          | Moustafa     | 100         |
| 2          | Jonathan     | 200         |
| 3          | Winston      | 10000       |
| 4          | Luis         | 800         | 
+------------+--------------+-------------+

<code>Transactions</code> table:
+------------+------------+------------+----------+---------------+
| trans_id   | paid_by    | paid_to    | amount   | transacted_on |
+------------+------------+------------+----------+---------------+
| 1          | 1          | 3          | 400      | 2020-08-01    |
| 2          | 3          | 2          | 500      | 2020-08-02    |
| 3          | 2          | 1          | 200      | 2020-08-03    |
+------------+------------+------------+----------+---------------+

Result table:
+------------+------------+------------+-----------------------+
| <code>user_id </code>   | <code>user_name</code>  | <code>credit </code>    | <code>credit_limit_breached</code> |
+------------+------------+------------+-----------------------+
| 1          | Moustafa   | -100       | Yes                   | 
| 2          | Jonathan   | 500        | No                    |
| 3          | Winston    | 9900       | No                    |
| 4          | Luis       | 800        | No                    |
+------------+------------+------------+-----------------------+
Moustafa paid $400 on &quot;2020-08-01&quot; and received $200 on &quot;2020-08-03&quot;, credit (100 -400 +200) = -$100
Jonathan received $500 on &quot;2020-08-02&quot; and paid $200 on &quot;2020-08-08&quot;, credit (200 +500 -200) = $500
Winston received $400 on &quot;2020-08-01&quot; and paid $500 on &quot;2020-08-03&quot;, credit (10000 +400 -500) = $9990
Luis didn&#39;t received any transfer, credit = $800</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
