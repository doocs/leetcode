---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1831.Maximum%20Transaction%20Each%20Day/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1831. 每天的最大交易 🔒](https://leetcode.cn/problems/maximum-transaction-each-day)

[English Version](/solution/1800-1899/1831.Maximum%20Transaction%20Each%20Day/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Transactions</code></p>

<pre>
+----------------+----------+
| Column Name    | Type     |
+----------------+----------+
| transaction_id | int      |
| day            | datetime |
| amount         | int      |
+----------------+----------+
transaction_id 是该表具有唯一值的列。
每行包括了该次交易的信息。
</pre>

<p>&nbsp;</p>

<p>编写一个解决方案，报告每天交易金额 <code>amount</code> <strong>最大</strong> 的交易 ID 。如果一天中有多个这样的交易，返回这些交易的 ID 。</p>

<p><span style="">返回结果根据 </span><code>transaction_id</code>&nbsp;<strong>升序排列</strong>。</p>

<p>返回格式如下示例所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Transactions table:
+----------------+--------------------+--------+
| transaction_id | day                | amount |
+----------------+--------------------+--------+
| 8              | 2021-4-3 15:57:28  | 57     |
| 9              | 2021-4-28 08:47:25 | 21     |
| 1              | 2021-4-29 13:28:30 | 58     |
| 5              | 2021-4-28 16:39:59 | 40     |
| 6              | 2021-4-29 23:39:28 | 58     |
+----------------+--------------------+--------+
<strong>输出：</strong>
+----------------+
| transaction_id |
+----------------+
| 1              |
| 5              |
| 6              |
| 8              |
+----------------+
<strong>解释：</strong>
"2021-4-3"  --&gt; 有一个 id 是 8 的交易，因此，把它加入结果表。 
"2021-4-28" --&gt; 有两个交易，id 是 5 和 9 ，交易 5 的金额是 40 ，而交易 9 的数量是 21 。只需要将交易 5 加入结果表，因为它是当天金额最大的交易。
"2021-4-29" --&gt; 有两个交易，id 是 1 和 6 ，这两个交易的金额都是 58 ，因此需要把它们都写入结果表。
最后，把交易 id 按照升序排列。</pre>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以不使用&nbsp;<code>MAX()</code>&nbsp;函数解决这道题目吗?</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：窗口函数

我们可以使用窗口函数 `RANK`，按照每天的交易金额 `amount` 降序排列，然后选择排名为 $1$ 的交易。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            transaction_id,
            RANK() OVER (
                PARTITION BY DAY(day)
                ORDER BY amount DESC
            ) AS rk
        FROM Transactions
    )
SELECT transaction_id
FROM T
WHERE rk = 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
