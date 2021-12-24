# [1587. Bank Account Summary II](https://leetcode.com/problems/bank-account-summary-ii)

[中文文档](/solution/1500-1599/1587.Bank%20Account%20Summary%20II/README.md)

## Description

<p>Table: <code>Users</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| account      | int     |
| name         | varchar |
+--------------+---------+
account is the primary key for this table.
Each row of this table contains the account number of each user in the bank.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Transactions</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| trans_id      | int     |
| account       | int     |
| amount        | int     |
| transacted_on | date    |
+---------------+---------+
trans_id is the primary key for this table.
Each row of this table contains all changes made to all accounts.
amount is positive if the user received money and negative if they transferred money.
All accounts start with a balance 0.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the name and balance of users with a balance higher than 10000. The balance of an account is equal to the sum of the&nbsp;amounts of all transactions involving that account.</p>

<p>Return the result table in <strong>any</strong> order.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>

<pre>
<code>Users</code> table:
+------------+--------------+
| account    | name         |
+------------+--------------+
| 900001     | Alice        |
| 900002     | Bob          |
| 900003     | Charlie      |
+------------+--------------+

<code>Transactions</code> table:
+------------+------------+------------+---------------+
| trans_id   | account    | amount     | transacted_on |
+------------+------------+------------+---------------+
| 1          | 900001     | 7000       |  2020-08-01   |
| 2          | 900001     | 7000       |  2020-09-01   |
| 3          | 900001     | -3000      |  2020-09-02   |
| 4          | 900002     | 1000       |  2020-09-12   |
| 5          | 900003     | 6000       |  2020-08-07   |
| 6          | 900003     | 6000       |  2020-09-07   |
| 7          | 900003     | -4000      |  2020-09-11   |
+------------+------------+------------+---------------+

Result table:
+------------+------------+
| <code>name    </code>   | <code>balance  </code>  |
+------------+------------+
| Alice      | 11000      |
+------------+------------+
Alice&#39;s balance is (7000 + 7000 - 3000) = 11000.
Bob&#39;s balance is 1000.
Charlie&#39;s balance is (6000 + 6000 - 4000) = 8000.
</pre>

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
