---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2752.Customers%20with%20Maximum%20Number%20of%20Transactions%20on%20Consecutive%20Days/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2752. 在连续天数上进行了最多交易次数的顾客 🔒](https://leetcode.cn/problems/customers-with-maximum-number-of-transactions-on-consecutive-days)

[English Version](/solution/2700-2799/2752.Customers%20with%20Maximum%20Number%20of%20Transactions%20on%20Consecutive%20Days/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：&nbsp;<code>Transactions</code></p>

<pre>
+------------------+------+
| 列名             | 类型 |
+------------------+------+
| transaction_id   | int  |
| customer_id      | int  |
| transaction_date | date |
| amount           | int  |
+------------------+------+
transaction_id 是这个表的具有唯一值的列。 
每行包含有关交易的信息，包括唯一的（customer_id，transaction_date）以及相应的 customer_id 和 amount。
</pre>

<p>编写一个解决方案，找到连续天数上进行了最多交易的所有 <code>customer_id</code> 。</p>

<p>返回所有具有最大连续交易次数的 <code>customer_id</code> 。结果表按 <code>customer_id</code> 的 <strong>升序</strong> 排序。</p>

<p>结果的格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>
Transactions 表:
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
<b>输出：</b>
+-------------+
| customer_id | 
+-------------+
| 101         | 
| 105         | 
+-------------+
<b>解释：</b>
- customer_id 为 101 共有 3 次交易，且全部是连续的。
- customer_id 为 102 共有 3 次交易，但只有其中 2 次是连续的。
- customer_id 为 105 共有 3 次交易，且全部是连续的。 
总的来说，最大连续交易次数为 3，由 customer_id 为 101 和 105 的完成。customer_id 按升序排序。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    s AS (
        SELECT
            customer_id,
            DATE_SUB(
                transaction_date,
                INTERVAL ROW_NUMBER() OVER (
                    PARTITION BY customer_id
                    ORDER BY transaction_date
                ) DAY
            ) AS transaction_date
        FROM Transactions
    ),
    t AS (
        SELECT customer_id, transaction_date, COUNT(1) AS cnt
        FROM s
        GROUP BY 1, 2
    )
SELECT customer_id
FROM t
WHERE cnt = (SELECT MAX(cnt) FROM t)
ORDER BY customer_id;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
