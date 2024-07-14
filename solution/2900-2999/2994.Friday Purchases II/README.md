---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2994.Friday%20Purchases%20II/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2994. 发生在周五的交易 II 🔒](https://leetcode.cn/problems/friday-purchases-ii)

[English Version](/solution/2900-2999/2994.Friday%20Purchases%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：&nbsp;<code>Purchases</code></p>

<pre>
+---------------+------+
| Column Name   | Type |
+---------------+------+
| user_id       | int  |
| purchase_date | date |
| amount_spend  | int  |
+---------------+------+
<meta charset="UTF-8" />(user_id, purchase_date, amount_spend) 是该表的主键(具有唯一值的列)。
purchase_date 的范围从 2023 年 11 月 1 日到 2023 年 11 月 30 日，并包括这两个日期。
每一行包含 user id, purchase date，和 amount spend。</pre>

<p>编写一个解决方案，计算用户在 <strong>2023 年 11</strong> 月的 <strong>每个星期五</strong> 的 <strong>总花费</strong>。如果在&nbsp;<strong>某个星期的星期五</strong> <strong>没有</strong> 购买记录，则将其视为花费金额为 <code>0</code>。</p>

<p><meta charset="UTF-8" /></p>

<p>按照每月的周次序&nbsp;<strong>升序</strong>&nbsp;排列结果表。</p>

<p>结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>
Purchases table:
+---------+---------------+--------------+
| user_id | purchase_date | amount_spend |
+---------+---------------+--------------+
| 11      | 2023-11-07    | 1126         |
| 15      | 2023-11-30    | 7473         |
| 17      | 2023-11-14    | 2414         |
| 12      | 2023-11-24    | 9692         |
| 8       | 2023-11-03    | 5117         |
| 1       | 2023-11-16    | 5241         |
| 10      | 2023-11-12    | 8266         |
| 13      | 2023-11-24    | 12000        |
+---------+---------------+--------------+
<b>输出：</b> 
+---------------+---------------+--------------+
| week_of_month | purchase_date | total_amount |
+---------------+---------------+--------------+
| 1             | 2023-11-03    | 5117         |
| 2             | 2023-11-10    | 0            |
| 3             | 2023-11-17    | 0            |
| 4             | 2023-11-24    | 21692        |
+---------------+---------------+--------------+ 
<b>解释：</b>
- 在 2023 年 11 月的第一周的周五（即 2023-11-03），共发生了总计 $5,117 的交易。
- 在 2023 年 11 月的第二周的周五（即 2023-11-10），当天没有交易，因此在输出表中该天的值为 0。
- 类似地，在 2023 年 11 月的第三周的周五（即 2023-11-17），当天没有交易，因此在输出表中该天的值为 0。
- 在 2023 年 11 月的第四周的周五（即 2023-11-24），当天发生了两笔交易，分别为 $12,000 和 $9,692，总计 $21,692。
输出表按照 week_of_month 按升序排序。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归 + 左连接 + 日期函数

我们可以使用递归生成一个包含 2023 年 11 月所有日期的表 `T`，然后使用左连接将 `T` 与 `Purchases` 表按照日期进行连接，最后按照题目要求进行分组求和即可。

<!-- tabs:start -->

#### MySQL

```sql
WITH RECURSIVE
    T AS (
        SELECT '2023-11-01' AS purchase_date
        UNION
        SELECT purchase_date + INTERVAL 1 DAY
        FROM T
        WHERE purchase_date < '2023-11-30'
    )
SELECT
    CEIL(DAYOFMONTH(purchase_date) / 7) AS week_of_month,
    purchase_date,
    IFNULL(SUM(amount_spend), 0) AS total_amount
FROM
    T
    LEFT JOIN Purchases USING (purchase_date)
WHERE DAYOFWEEK(purchase_date) = 6
GROUP BY 2
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
