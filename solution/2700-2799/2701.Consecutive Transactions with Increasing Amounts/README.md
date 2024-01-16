# [2701. 连续递增交易](https://leetcode.cn/problems/consecutive-transactions-with-increasing-amounts)

[English Version](/solution/2700-2799/2701.Consecutive%20Transactions%20with%20Increasing%20Amounts/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Transactions</code></p>

<pre>
+------------------+------+
| 字段名            | 类型 |
+------------------+------+
| transaction_id   | int  |
| customer_id      | int  |
| transaction_date | date |
| amount           | int  |
+------------------+------+
transaction_id 是该表的主键。 
每行包含有关交易的信息，包括唯一的 (customer_id, transaction_date)，以及相应的 customer_id 和 amount。 
</pre>

<p>编写一个 SQL 查询，找出至少连续三天 <code>amount</code> 递增的客户。并包括 <code>customer_id</code>&nbsp;、连续交易期的起始日期和结束日期。一个客户可以有多个连续的交易。</p>

<p>返回结果并按照 <code>customer_id</code> <strong>升序&nbsp;</strong>排列。</p>

<p>查询结果的格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>
Transactions 表:
+----------------+-------------+------------------+--------+
| transaction_id | customer_id | transaction_date | amount |
+----------------+-------------+------------------+--------+
| 1 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 101 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-01 &nbsp; &nbsp; &nbsp; | 100 &nbsp; &nbsp;|
| 2 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 101 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-02 &nbsp; &nbsp; &nbsp; | 150 &nbsp; &nbsp;|
| 3 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 101 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-03 &nbsp; &nbsp; &nbsp; | 200 &nbsp; &nbsp;|
| 4 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 102 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-01 &nbsp; &nbsp; &nbsp; | 50 &nbsp; &nbsp; |
| 5 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 102 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-03 &nbsp; &nbsp; &nbsp; | 100 &nbsp; &nbsp;|
| 6 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 102 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-04 &nbsp; &nbsp; &nbsp; | 200 &nbsp; &nbsp;|
| 7 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 105 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-01 &nbsp; &nbsp; &nbsp; | 100 &nbsp; &nbsp;|
| 8 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 105 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-02 &nbsp; &nbsp; &nbsp; | 150 &nbsp; &nbsp;|
| 9 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 105 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-03 &nbsp; &nbsp; &nbsp; | 200 &nbsp; &nbsp;|
| 10 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; | 105 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-04 &nbsp; &nbsp; &nbsp; | 300 &nbsp; &nbsp;|
| 11 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; | 105 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-12 &nbsp; &nbsp; &nbsp; | 250 &nbsp; &nbsp;|
| 12 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; | 105 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-13 &nbsp; &nbsp; &nbsp; | 260 &nbsp; &nbsp;|
| 13 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; | 105 &nbsp; &nbsp; &nbsp; &nbsp; | 2023-05-14 &nbsp; &nbsp; &nbsp; | 270 &nbsp; &nbsp;|
+----------------+-------------+------------------+--------+
<b>输出：</b>
+-------------+-------------------+-----------------+
| customer_id | consecutive_start | consecutive_end |&nbsp;
+-------------+-------------------+-----------------+
| 101 &nbsp; &nbsp; &nbsp; &nbsp; |&nbsp; 2023-05-01 &nbsp; &nbsp; &nbsp; |&nbsp;2023-05-03 &nbsp; &nbsp; &nbsp;|&nbsp;
| 105 &nbsp; &nbsp; &nbsp; &nbsp; |&nbsp; 2023-05-01 &nbsp; &nbsp; &nbsp; |&nbsp;2023-05-04 &nbsp; &nbsp; &nbsp;|
| 105 &nbsp; &nbsp; &nbsp; &nbsp; |&nbsp; 2023-05-12 &nbsp; &nbsp; &nbsp; |&nbsp;2023-05-14 &nbsp; &nbsp; &nbsp;|&nbsp;
+-------------+-------------------+-----------------+
<strong>解释：</strong>&nbsp;
- customer_id 为 101 的客户在 2023年5月1日 至 2023年5月3日 期间进行了连续递增金额的交易。
- customer_id 为 102 的客户没有至少连续三天的交易。
- customer_id 为 105 的客户有两组连续交易：从 2023年5月1日 至 2023年5月4日，以及 2023年5月12日 至 2023年5月14日。结果按 customer_id 升序排序
</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            t1.*,
            SUM(
                CASE
                    WHEN t2.customer_id IS NULL THEN 1
                    ELSE 0
                END
            ) OVER (ORDER BY customer_id, transaction_date) AS s
        FROM
            Transactions AS t1
            LEFT JOIN Transactions AS t2
                ON t1.customer_id = t2.customer_id
                AND t1.amount > t2.amount
                AND DATEDIFF(t1.transaction_date, t2.transaction_date) = 1
    )
SELECT
    customer_id,
    MIN(transaction_date) AS consecutive_start,
    MAX(transaction_date) AS consecutive_end
FROM T
GROUP BY customer_id, s
HAVING COUNT(1) >= 3
ORDER BY customer_id;
```

<!-- tabs:end -->

<!-- end -->
