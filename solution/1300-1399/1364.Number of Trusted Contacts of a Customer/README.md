---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1364.Number%20of%20Trusted%20Contacts%20of%20a%20Customer/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1364. 顾客的可信联系人数量 🔒](https://leetcode.cn/problems/number-of-trusted-contacts-of-a-customer)

[English Version](/solution/1300-1399/1364.Number%20of%20Trusted%20Contacts%20of%20a%20Customer/README_EN.md)

## 题目描述

<!-- description:start -->

<p>顾客表：<code>Customers</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| customer_id   | int     |
| customer_name | varchar |
| email         | varchar |
+---------------+---------+
customer_id 是这张表具有唯一值的列。
此表的每一行包含了某在线商店顾客的姓名和电子邮件。
</pre>

<p>&nbsp;</p>

<p>联系方式表：<code>Contacts</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | id      |
| contact_name  | varchar |
| contact_email | varchar |
+---------------+---------+
(user_id, contact_email) 是这张表的主键（具有唯一值的列的组合）。
此表的每一行表示编号为 user_id 的顾客的某位联系人的姓名和电子邮件。
此表包含每位顾客的联系人信息，但顾客的联系人不一定存在于顾客表中。
</pre>

<p>&nbsp;</p>

<p>发票表：<code>Invoices</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| invoice_id   | int     |
| price        | int     |
| user_id      | int     |
+--------------+---------+
invoice_id 是这张表具有唯一值的列。
此表的每一行分别表示编号为 user_id 的顾客拥有有一张编号为 invoice_id、价格为 price 的发票。
</pre>

<p>&nbsp;</p>

<p>为每张发票 <code>invoice_id</code> 编写一个查询方案以查找以下内容：</p>

<ul>
	<li><code>customer_name</code>：与发票相关的顾客名称。</li>
	<li><code>price</code>：发票的价格。</li>
	<li><code>contacts_cnt</code>：该顾客的联系人数量</li>
	<li><code>trusted_contacts_cnt</code>：可信联系人的数量：既是该顾客的联系人又是商店顾客的联系人数量（即：可信联系人的电子邮件存在于 <meta charset="UTF-8" />&nbsp;<code>Customers</code>&nbsp;表中）。</li>
</ul>

<p>返回结果按照&nbsp;<code>invoice_id</code>&nbsp;<strong>排序</strong>。</p>

<p>结果的格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
<code>Customers</code> table:
+-------------+---------------+--------------------+
| customer_id | customer_name | email              |
+-------------+---------------+--------------------+
| 1           | Alice         | alice@leetcode.com |
| 2           | Bob           | bob@leetcode.com   |
| 13          | John          | john@leetcode.com  |
| 6           | Alex          | alex@leetcode.com  |
+-------------+---------------+--------------------+
Contacts table:
+-------------+--------------+--------------------+
| user_id     | contact_name | contact_email      |
+-------------+--------------+--------------------+
| 1           | Bob          | bob@leetcode.com   |
| 1           | John         | john@leetcode.com  |
| 1           | Jal          | jal@leetcode.com   |
| 2           | Omar         | omar@leetcode.com  |
| 2           | Meir         | meir@leetcode.com  |
| 6           | Alice        | alice@leetcode.com |
+-------------+--------------+--------------------+
Invoices table:
+------------+-------+---------+
| invoice_id | price | user_id |
+------------+-------+---------+
| 77         | 100   | 1       |
| 88         | 200   | 1       |
| 99         | 300   | 2       |
| 66         | 400   | 2       |
| 55         | 500   | 13      |
| 44         | 60    | 6       |
+------------+-------+---------+
<strong>输出：</strong>
+------------+---------------+-------+--------------+----------------------+
| invoice_id | customer_name | price | contacts_cnt | trusted_contacts_cnt |
+------------+---------------+-------+--------------+----------------------+
| 44         | Alex          | 60    | 1            | 1                    |
| 55         | John          | 500   | 0            | 0                    |
| 66         | Bob           | 400   | 2            | 0                    |
| 77         | Alice         | 100   | 3            | 2                    |
| 88         | Alice         | 200   | 3            | 2                    |
| 99         | Bob           | 300   | 2            | 0                    |
+------------+---------------+-------+--------------+----------------------+
<strong>解释：</strong>
Alice 有三位联系人，其中两位(Bob 和 John)是可信联系人。
Bob 有两位联系人, 他们中的任何一位都不是可信联系人。
Alex 只有一位联系人(Alice)，并是一位可信联系人。
John 没有任何联系人。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    invoice_id,
    t2.customer_name,
    price,
    COUNT(t3.user_id) AS contacts_cnt,
    COUNT(t4.email) AS trusted_contacts_cnt
FROM
    Invoices AS t1
    LEFT JOIN Customers AS t2 ON t1.user_id = t2.customer_id
    LEFT JOIN Contacts AS t3 ON t1.user_id = t3.user_id
    LEFT JOIN Customers AS t4 ON t3.contact_email = t4.email
GROUP BY invoice_id
ORDER BY invoice_id;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
