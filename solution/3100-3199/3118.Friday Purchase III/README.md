---
comments: true
difficulty: 中等
tags:
    - 数据库
---

<!-- problem:start -->

# [3118. 发生在周五的交易 III 🔒](https://leetcode.cn/problems/friday-purchase-iii)

[English Version](/solution/3100-3199/3118.Friday%20Purchase%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Purchases</code></p>

<pre>
+---------------+------+
| Column Name   | Type |
+---------------+------+
| user_id       | int  |
| purchase_date | date |
| amount_spend  | int  |
+---------------+------+
(user_id, purchase_date, amount_spend) 是该表的主键(具有唯一值的列)。
purchase_date 的范围从 2023 年 11 月 1 日到 2023 年 11 月 30 日，并包括这两个日期。
每一行包含 user_id, purchase_date，和 amount_spend。
</pre>

<p>表：<code>Users</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| user_id     | int  |
| membership  | enum |
+-------------+------+
user_id 是这张表的主键。
membership 是 ('Standard', 'Premium', 'VIP') 的枚举类型。
这张表的每一行表示 user_id 和会员类型。
</pre>

<p>编写一个解决方案来计算&nbsp;<code>Premium</code>&nbsp;和&nbsp;<code>VIP</code>&nbsp;会员在 2023 年 11 月&nbsp;<strong>每周的周五</strong>&nbsp;的 <strong>总花费</strong>。如果某个周五没有&nbsp;<code>Premium</code> 或&nbsp;<code>VIP</code> 会员购买，把它当作&nbsp;<code>0</code>。</p>

<p>按照每月的周次序&nbsp;<strong>升序</strong>&nbsp;排列结果表，然后以&nbsp;<code>membership</code>&nbsp;<strong>升序&nbsp;</strong>排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>Purchases 表：</p>

<pre class="example-io">
+---------+---------------+--------------+
| user_id | purchase_date | amount_spend |
+---------+---------------+--------------+
| 11      | 2023-11-03    | 1126         |
| 15      | 2023-11-10    | 7473         |
| 17      | 2023-11-17    | 2414         |
| 12      | 2023-11-24    | 9692         |
| 8       | 2023-11-24    | 5117         |
| 1       | 2023-11-24    | 5241         |
| 10      | 2023-11-22    | 8266         |
| 13      | 2023-11-21    | 12000        |
+---------+---------------+--------------+
</pre>

<p>Users 表：</p>

<pre class="example-io">
+---------+------------+
| user_id | membership |
+---------+------------+
| 11      | Premium    |
| 15      | VIP        |
| 17      | Standard   |
| 12      | VIP        |
| 8       | Premium    |
| 1       | VIP        |
| 10      | Standard   |
| 13      | Premium    |
+---------+------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+---------------+-------------+--------------+
| week_of_month | membership  | total_amount |
+---------------+-------------+--------------+
| 1             | Premium     | 1126         |
| 1             | VIP         | 0            |
| 2             | Premium     | 0            |
| 2             | VIP         | 7473         |
| 3             | Premium     | 0            |
| 3             | VIP         | 0            |
| 4             | Premium     | 5117         |
| 4             | VIP         | 14933        |
+---------------+-------------+--------------+
        </pre>

<p><strong>解释：</strong></p>

<ul>
	<li>在 2023 年 11 月的第一周，周五有一笔交易，2023-11-03，由一个&nbsp;Premium 会员花费了 $1,126。这天没有 VIP 会员交易，所以值为 0。</li>
	<li>在 2023 年 11 月的第二周，周五有一笔交易，2023-11-10，由一个 VIP 会员花费了 $7,473。因为这条没有&nbsp;Premium 会员交易，Premium 会员的输出为 0。</li>
	<li>相似地，在 2023 年 11 月的第三周，周五没有&nbsp;Premium 或 VIP 会员交易，2023-11-17，所以这周两种分类都输出 0。</li>
	<li>在 2023 年 11 月的第四周，周五存在交易，2023-11-24，有一名 Premium 会员购买了 $5,117 以及 VIP 会员购买了总共 $14,933（一个花费 $9,692，另一个花费 $5,241）。</li>
</ul>

<p><strong>注意：</strong>输出表以&nbsp;week_of_month 和 membership 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归 + 连接

<!-- thinking:start -->

> **思考**
>
> 需要按月份第几周与会员类型汇总周五消费，即使某组合没有记录也要输出 $0$。仅扫描购买表会漏掉空组合。
>
> 周次只有 $1$ 到 $4$，会员只有 Premium 与 VIP，先用递归 CTE 与 `UNION` 生成完整笛卡尔底表，再左连实际周五消费。
>
> 因此构造 `T`、`M` 与筛选后的 `P`，将 `T` 与 `M` 交叉后左连 `P`，按周与会员分组并对金额求和，空值用 $0$ 填充。

<!-- thinking:end -->

我们首先创建一个递归表 `T`，其中包含 `week_of_month` 列，表示月份的第几周。然后创建一个表 `M`，包含 `membership` 列，表示会员类型，取值为 `'Premium'` 和 `'VIP'`。

接着创建一个表 `P`，包含 `week_of_month`、`membership` 和 `amount_spend` 列，筛选出每个会员在每个月的第几周的周五的消费金额。最后，我们将 `T` 和 `M` 表连接，再左连接 `P` 表，并且按照 `week_of_month` 和 `membership` 列进行分组，计算每周每种会员的总消费金额。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH RECURSIVE
    T AS (
        SELECT 1 AS week_of_month
        UNION
        SELECT week_of_month + 1
        FROM T
        WHERE week_of_month < 4
    ),
    M AS (
        SELECT 'Premium' AS membership
        UNION
        SELECT 'VIP'
    ),
    P AS (
        SELECT CEIL(DAYOFMONTH(purchase_date) / 7) AS week_of_month, membership, amount_spend
        FROM
            Purchases
            JOIN Users USING (user_id)
        WHERE DAYOFWEEK(purchase_date) = 6
    )
SELECT week_of_month, membership, IFNULL(SUM(amount_spend), 0) AS total_amount
FROM
    T
    JOIN M
    LEFT JOIN P USING (week_of_month, membership)
GROUP BY 1, 2
ORDER BY 1, 2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
