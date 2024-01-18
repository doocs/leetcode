# [2986. 找到第三笔交易](https://leetcode.cn/problems/find-third-transaction)

[English Version](/solution/2900-2999/2986.Find%20Third%20Transaction/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：&nbsp;<code>Transactions</code></p>

<pre>
+------------------+----------+
| Column Name      | Type     |
+------------------+----------+
| user_id          | int      |
| spend            | decimal  |
| transaction_date | datetime |
+------------------+----------+
(user_id, transaction_date) 是这张表具有唯一值的列。
该表包含 user_id, spend,和 transaction_date。
</pre>

<p>编写一个查询，找到每个用户的 <strong>第三笔交易</strong> （如果他们有至少三笔交易），其中 <strong>前两笔交易</strong> 的花费&nbsp;<strong>低于&nbsp;第三笔交易</strong>的花费。</p>

<p>返回&nbsp;<em>按 <strong>升序</strong>&nbsp;<code>user_id</code>&nbsp;排序的结果表。</em></p>

<p>结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<b>输入：</b>
Transactions table:
+---------+--------+---------------------+
| user_id | spend  | transaction_date    | 
+---------+--------+---------------------+
| 1       | 65.56  | 2023-11-18 13:49:42 | 
| 1       | 96.0   | 2023-11-30 02:47:26 |     
| 1       | 7.44   | 2023-11-02 12:15:23 | 
| 1       | 49.78  | 2023-11-12 00:13:46 | 
| 2       | 40.89  | 2023-11-21 04:39:15 |  
| 2       | 100.44 | 2023-11-20 07:39:34 | 
| 3       | 37.33  | 2023-11-03 06:22:02 | 
| 3       | 13.89  | 2023-11-11 16:00:14 | 
| 3       | 7.0    | 2023-11-29 22:32:36 | 
+---------+--------+---------------------+
<b>输出</b>
+---------+-------------------------+------------------------+
| user_id | third_transaction_spend | third_transaction_date | 
+---------+-------------------------+------------------------+
| 1       | 65.56                   | 2023-11-18 13:49:42    |  
+---------+-------------------------+------------------------+
<b>解释</b>
- 对于 user_id 1，他们的第三笔交易发生在 2023-11-18 13:49:42，金额为 $65.56，超过了前两笔交易的支出，分别是 2023-11-02 12:15:23 的 $7.44 和 2023-11-12 00:13:46 的 $49.78。因此，此第三笔交易将包含在输出表中。
- user_id 2 只有总共 2 笔交易，因此没有第三笔交易。
- 对于 user_id 3，第三笔交易的金额 $7.0 少于前两笔交易，因此不会包含在内。
输出表按升序按 user_id 排序。

</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY user_id
                ORDER BY transaction_date
            ) AS rk,
            spend > (
                LAG(spend) OVER (
                    PARTITION BY user_id
                    ORDER BY transaction_date
                )
            )
            AND spend > (
                LAG(spend, 2) OVER (
                    PARTITION BY user_id
                    ORDER BY transaction_date
                )
            ) AS st
        FROM Transactions
    )
SELECT user_id, spend AS third_transaction_spend, transaction_date AS third_transaction_date
FROM T
WHERE rk = 3 AND st = 1;
```

<!-- tabs:end -->

<!-- end -->
