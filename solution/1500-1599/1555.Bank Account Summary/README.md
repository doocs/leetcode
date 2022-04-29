# [1555. 银行账户概要](https://leetcode.cn/problems/bank-account-summary)

[English Version](/solution/1500-1599/1555.Bank%20Account%20Summary/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>用户表：&nbsp;<code>Users</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| user_id      | int     |
| user_name    | varchar |
| credit       | int     |
+--------------+---------+
user_id 是这个表的主键。
表中的每一列包含每一个用户当前的额度信息。</pre>

<p>&nbsp;</p>

<p>交易表：<code>Transactions</code></p>

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
trans_id 是这个表的主键。
表中的每一列包含银行的交易信息。
ID 为 paid_by 的用户给 ID 为 paid_to 的用户转账。
</pre>

<p>&nbsp;</p>

<p>力扣银行 (LCB) 帮助程序员们完成虚拟支付。我们的银行在表&nbsp;<em>Transaction</em>&nbsp;中记录每条交易信息，我们要查询每个用户的当前余额，并检查他们是否已透支（当前额度小于 0）。</p>

<p>写一条 SQL 语句，查询：</p>

<ul>
	<li><code>user_id</code>&nbsp;用户 ID</li>
	<li><code>user_name</code>&nbsp;用户名</li>
	<li><code>credit</code>&nbsp;完成交易后的余额</li>
	<li><code>credit_limit_breached</code>&nbsp;检查是否透支 （"Yes" 或&nbsp;"No"）</li>
</ul>

<p>以<strong>任意</strong>顺序返回结果表。</p>

<p>查询格式见如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<code><strong>输入：</strong>
Users</code> 表：
+------------+--------------+-------------+
| user_id    | user_name    | credit      |
+------------+--------------+-------------+
| 1          | Moustafa     | 100         |
| 2          | Jonathan     | 200         |
| 3          | Winston      | 10000       |
| 4          | Luis         | 800         | 
+------------+--------------+-------------+

<code>Transactions</code> 表：
+------------+------------+------------+----------+---------------+
| trans_id   | paid_by    | paid_to    | amount   | transacted_on |
+------------+------------+------------+----------+---------------+
| 1          | 1          | 3          | 400      | 2020-08-01    |
| 2          | 3          | 2          | 500      | 2020-08-02    |
| 3          | 2          | 1          | 200      | 2020-08-03    |
+------------+------------+------------+----------+---------------+

<strong>输出：</strong>
+------------+------------+------------+-----------------------+
| <code>user_id </code>   | <code>user_name</code>  | <code>credit </code>    | <code>credit_limit_breached</code> |
+------------+------------+------------+-----------------------+
| 1          | Moustafa   | -100       | Yes                   | 
| 2          | Jonathan   | 500        | No                    |
| 3          | Winston    | 9900       | No                    |
| 4          | Luis       | 800        | No                    |
+------------+------------+------------+-----------------------+
Moustafa 在 "2020-08-01" 支付了 $400 并在 "2020-08-03" 收到了 $200 ，当前额度 (100 -400 +200) = -$100
Jonathan 在 "2020-08-02" 收到了 $500 并在 "2020-08-08" 支付了 $200 ，当前额度 (200 +500 -200) = $500
Winston 在 "2020-08-01" 收到了 $400 并在 "2020-08-03" 支付了 $500 ，当前额度 (10000 +400 -500) = $9900
Luis 未收到任何转账信息，额度 = $800</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
