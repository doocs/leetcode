# [1587. 银行账户概要 II](https://leetcode.cn/problems/bank-account-summary-ii)

[English Version](/solution/1500-1599/1587.Bank%20Account%20Summary%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Users</code></p>

<pre>+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| account      | int     |
| name         | varchar |
+--------------+---------+
account 是该表的主键.
表中的每一行包含银行里中每一个用户的账号.
</pre>

<p>&nbsp;</p>

<p>表: <code>Transactions</code></p>

<pre>+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| trans_id      | int     |
| account       | int     |
| amount        | int     |
| transacted_on | date    |
+---------------+---------+
trans_id 是该表主键.
该表的每一行包含了所有账户的交易改变情况.
如果用户收到了钱, 那么金额是正的; 如果用户转了钱, 那么金额是负的.
所有账户的起始余额为 0.
</pre>

<p>&nbsp;</p>

<p>写一个 SQL,&nbsp;&nbsp;报告余额高于 10000 的所有用户的名字和余额.&nbsp;账户的余额等于包含该账户的所有交易的总和.</p>

<p>返回结果表单没有顺序要求.</p>

<p>查询结果格式如下例所示.</p>

<p>&nbsp;</p>

<pre><code>Users</code> table:
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
Alice 的余额为(7000 + 7000 - 3000) = 11000.
Bob 的余额为1000.
Charlie 的余额为(6000 + 6000 - 4000) = 8000.
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql
SELECT
    u.name,
    SUM(t.amount) AS balance
FROM
    users AS u
    JOIN transactions AS t ON u.account = t.account
GROUP BY
    name
HAVING
    SUM(t.amount) > 10000;
```

<!-- tabs:end -->
