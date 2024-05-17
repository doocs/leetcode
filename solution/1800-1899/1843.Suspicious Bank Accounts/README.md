---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1843.Suspicious%20Bank%20Accounts/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1843. 可疑银行账户 🔒](https://leetcode.cn/problems/suspicious-bank-accounts)

[English Version](/solution/1800-1899/1843.Suspicious%20Bank%20Accounts/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Accounts</code></p>

<pre>
+----------------+------+
| Column Name    | Type |
+----------------+------+
| account_id     | int  |
| max_income     | int  |
+----------------+------+
account_id 是这张表具有唯一值的列。
每行包含一个银行账户每月最大收入的信息。
</pre>

<p>&nbsp;</p>

<p>表: <code>Transactions</code></p>

<pre>
+----------------+----------+
| Column Name    | Type     |
+----------------+----------+
| transaction_id | int      |
| account_id     | int      |
| type           | ENUM     |
| amount         | int      |
| day            | datetime |
+----------------+----------+
transaction_id 是这张表具有唯一值的列。
每行包含一条转账信息。
type 是枚举类型（包含'Creditor','Debtor'），其中 'Creditor' 表示用户向其账户存入资金，'Debtor' 表示用户从其账户取出资金。
amount 是交易过程中的存入/取出的金额。
</pre>

<p>&nbsp;</p>

<p>如果一个账户在&nbsp;<strong>连续两个及以上&nbsp;</strong>月份的&nbsp;<strong>总收入&nbsp;</strong>超过最大收入（<code>max_income</code>），那么认为这个账户&nbsp;<strong>可疑</strong>。&nbsp; 账户当月&nbsp;<strong>总收入&nbsp;</strong>是当月存入资金总数（即 transactions 表中 type 字段的&nbsp;<code>'Creditor'</code>）。</p>

<p>编写一个解决方案，报告所有的&nbsp;<strong>可疑&nbsp;</strong>账户。</p>

<p>以 <strong>任意顺序</strong> 返回结果表</p>

<p>返回结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Accounts 表:
+------------+------------+
| account_id | max_income |
+------------+------------+
| 3          | 21000      |
| 4          | 10400      |
+------------+------------+
Transactions 表:
+----------------+------------+----------+--------+---------------------+
| transaction_id | account_id | type     | amount | day                 |
+----------------+------------+----------+--------+---------------------+
| 2              | 3          | Creditor | 107100 | 2021-06-02 11:38:14 |
| 4              | 4          | Creditor | 10400  | 2021-06-20 12:39:18 |
| 11             | 4          | Debtor   | 58800  | 2021-07-23 12:41:55 |
| 1              | 4          | Creditor | 49300  | 2021-05-03 16:11:04 |
| 15             | 3          | Debtor   | 75500  | 2021-05-23 14:40:20 |
| 10             | 3          | Creditor | 102100 | 2021-06-15 10:37:16 |
| 14             | 4          | Creditor | 56300  | 2021-07-21 12:12:25 |
| 19             | 4          | Debtor   | 101100 | 2021-05-09 15:21:49 |
| 8              | 3          | Creditor | 64900  | 2021-07-26 15:09:56 |
| 7              | 3          | Creditor | 90900  | 2021-06-14 11:23:07 |
+----------------+------------+----------+--------+---------------------+
<strong>输出：</strong>
+------------+
| account_id |
+------------+
| 3          |
+------------+
<strong>解释：</strong>
对于账户 3：
- 在 2021年6月，用户收入为 107100 + 102100 + 90900 = 300100。
- 在 2021年7月，用户收入为 64900。
可见收入连续两月超过21000的最大收入，因此账户3列入结果表中。

对于账户 4：
- 在 2021年5月，用户收入为 49300。
- 在 2021年6月，用户收入为 10400。
- 在 2021年7月，用户收入为 56300。
可见收入在5月与7月超过了最大收入，但6月没有。因为账户没有没有连续两月超过最大收入，账户4不列入结果表中。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    S AS (
        SELECT DISTINCT
            t.account_id,
            DATE_FORMAT(day, '%Y-%m-01') AS day,
            transaction_id AS tx,
            SUM(amount) OVER (
                PARTITION BY account_id, DATE_FORMAT(day, '%Y-%m-01')
            ) > max_income AS marked
        FROM
            Transactions AS t
            LEFT JOIN Accounts AS a ON t.account_id = a.account_id
        WHERE type = 'Creditor'
    )
SELECT DISTINCT s1.account_id
FROM
    S AS s1
    LEFT JOIN S AS s2 ON s1.account_id = s2.account_id AND TIMESTAMPDIFF(Month, s1.day, s2.day) = 1
WHERE s1.marked = 1 AND s2.marked = 1
ORDER BY s1.tx;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    S AS (
        SELECT
            account_id,
            DATE_FORMAT(day, '%Y%m') AS yearmonth,
            transaction_id AS tx
        FROM
            Transactions
            JOIN Accounts USING (account_id)
        WHERE type = 'Creditor'
        GROUP BY account_id, yearmonth
        HAVING SUM(amount) > AVG(max_income)
    )
SELECT DISTINCT account_id
FROM S
WHERE (account_id, PERIOD_ADD(yearmonth, 1)) IN (SELECT account_id, yearmonth FROM S)
ORDER BY tx;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
