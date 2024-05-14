---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1205.Monthly%20Transactions%20II/README.md
tags:
    - 数据库
---

# [1205. 每月交易 II 🔒](https://leetcode.cn/problems/monthly-transactions-ii)

[English Version](/solution/1200-1299/1205.Monthly%20Transactions%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>Transactions</code> 记录表</p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| id             | int     |
| country        | varchar |
| state          | enum    |
| amount         | int     |
| trans_date     | date    |
+----------------+---------+
id 是这个表的主键。
该表包含有关传入事务的信息。
状态列是枚举类型，值为 [approved、declined] 其中之一的列。</pre>

<p><code>Chargebacks</code> 表</p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| trans_id       | int     |
| trans_date     | date    |
+----------------+---------+
退单包含有关放置在事务表中的某些事务的传入退单的基本信息。
trans_id 是 transactions 表的 id 列的外键（reference 列）。
每项退单都对应于之前进行的交易，即使未经批准。</pre>

<p>&nbsp;</p>

<p>编写一个解决方案，找出每个国家/地区的每月交易信息：已批准交易的数量及其总金额、退单的数量及其总金额。</p>

<p><strong>注意：</strong>在你的解决方案中，只需显示给定月份和国家，忽略所有为零的行。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Transactions 表：
+-----+---------+----------+--------+------------+
| id  | country | state    | amount | trans_date |
+-----+---------+----------+--------+------------+
| 101 | US      | approved | 1000   | 2019-05-18 |
| 102 | US      | declined | 2000   | 2019-05-19 |
| 103 | US      | approved | 3000   | 2019-06-10 |
| 104 | US      | declined | 4000   | 2019-06-13 |
| 105 | US      | approved | 5000   | 2019-06-15 |
+-----+---------+----------+--------+------------+
Chargebacks 表：
+----------+------------+
| trans_id | trans_date |
+----------+------------+
| 102      | 2019-05-29 |
| 101      | 2019-06-30 |
| 105      | 2019-09-18 |
+----------+------------+
<strong>输出：</strong>
+---------+---------+----------------+-----------------+------------------+-------------------+
| month   | country | approved_count | approved_amount | chargeback_count | chargeback_amount |
+---------+---------+----------------+-----------------+------------------+-------------------+
| 2019-05 | US      | 1              | 1000            | 1                | 2000              |
| 2019-06 | US      | 2              | 8000            | 1                | 1000              |
| 2019-09 | US      | 0              | 0               | 1                | 5000              |
+---------+---------+----------------+-----------------+------------------+-------------------+</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT * FROM Transactions
        UNION
        SELECT id, country, 'chargeback', amount, c.trans_date
        FROM
            Transactions AS t
            JOIN Chargebacks AS c ON t.id = c.trans_id
    )
SELECT
    DATE_FORMAT(trans_date, '%Y-%m') AS month,
    country,
    SUM(state = 'approved') AS approved_count,
    SUM(IF(state = 'approved', amount, 0)) AS approved_amount,
    SUM(state = 'chargeback') AS chargeback_count,
    SUM(IF(state = 'chargeback', amount, 0)) AS chargeback_amount
FROM T
GROUP BY 1, 2
HAVING approved_amount OR chargeback_amount;
```

<!-- tabs:end -->

<!-- end -->
