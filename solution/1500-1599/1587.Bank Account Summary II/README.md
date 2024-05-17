---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1587.Bank%20Account%20Summary%20II/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1587. 银行账户概要 II](https://leetcode.cn/problems/bank-account-summary-ii)

[English Version](/solution/1500-1599/1587.Bank%20Account%20Summary%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Users</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| account      | int     |
| name         | varchar |
+--------------+---------+
account 是该表的主键(具有唯一值的列)。
该表的每一行都包含银行中每个用户的帐号。
表中不会有两个用户具有相同的名称。
</pre>

<p>&nbsp;</p>

<p>表: <code>Transactions</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| trans_id      | int     |
| account       | int     |
| amount        | int     |
| transacted_on | date    |
+---------------+---------+
trans_id 是该表主键(具有唯一值的列)。
该表的每一行包含了所有账户的交易改变情况。
如果用户收到了钱, 那么金额是正的; 如果用户转了钱, 那么金额是负的。
所有账户的起始余额为 0。
</pre>

<p>&nbsp;</p>

<p>编写解决方案,&nbsp;&nbsp;报告余额高于 10000 的所有用户的名字和余额.&nbsp;账户的余额等于包含该账户的所有交易的总和。</p>

<p>返回结果表单 <strong>无顺序要求</strong> 。</p>

<p>查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<code><strong>输入：</strong>
Users</code> table:
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
<strong>输出：</strong>
+------------+------------+
| <code>name    </code>   | <code>balance  </code>  |
+------------+------------+
| Alice      | 11000      |
+------------+------------+
<strong>解释：</strong>
Alice 的余额为(7000 + 7000 - 3000) = 11000.
Bob 的余额为1000.
Charlie 的余额为(6000 + 6000 - 4000) = 8000.
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：等值连接 + 分组求和

我们可以使用等值连接，将 `Users` 和 `Transactions` 表按照 `account` 列连接起来，然后按照 `account` 列分组求和，最后筛选出余额大于 $10000$ 的用户。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    name,
    SUM(amount) AS balance
FROM
    Users
    JOIN Transactions USING (account)
GROUP BY account
HAVING balance > 10000;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
