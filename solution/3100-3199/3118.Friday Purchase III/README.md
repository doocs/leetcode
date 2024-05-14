---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3118.Friday%20Purchase%20III/README.md
tags:
    - 数据库
---

# [3118. Friday Purchase III 🔒](https://leetcode.cn/problems/friday-purchase-iii)

[English Version](/solution/3100-3199/3118.Friday%20Purchase%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Purchases</code></p>

<pre>
+---------------+------+
| Column Name   | Type |
+---------------+------+
| user_id       | int  |
| purchase_date | date |
| amount_spend  | int  |
+---------------+------+
(user_id, purchase_date, amount_spend) is the primary key (combination of columns with unique values) for this table.
purchase_date will range from November 1, 2023, to November 30, 2023, inclusive of both dates.
Each row contains user_id, purchase_date, and amount_spend.
</pre>

<p>Table: <code>Users</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| user_id     | int  |
| membership  | enum |
+-------------+------+
user_id is the primary key for this table.
membership is an ENUM (category) type of (&#39;Standard&#39;, &#39;Premium&#39;, &#39;VIP&#39;).
Each row of this table indicates the user_id, membership type.
</pre>

<p>Write a solution to calculate the <strong>total spending</strong> by <code>Premium</code>&nbsp;and <code>VIP</code> members on <strong>each Friday of every week</strong> in November 2023.&nbsp; If there are <strong>no purchases</strong> on a <strong>particular Friday</strong> by <code>Premium</code> or <code>VIP</code> members, it should be considered as <code>0</code>.</p>

<p>Return <em>the result table</em>&nbsp;<em>ordered by week of the month,&nbsp; and </em><code>membership</code><em> in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>Purchases table:</p>

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

<p>Users table:</p>

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

<p><strong>Output:</strong></p>

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

<p><strong>Explanation:</strong></p>

<ul>
	<li>During the first week of November 2023, a transaction occurred on Friday, 2023-11-03, by a Premium member amounting to $1,126. No transactions were made by VIP members on this day, resulting in a value of 0.</li>
	<li>For the second week of November 2023, there was a transaction on Friday, 2023-11-10, and it was made by a VIP member, amounting to $7,473. Since there were no purchases by Premium members that Friday, the output shows 0 for Premium members.</li>
	<li>Similarly, during the third week of November 2023, no transactions by Premium or VIP members occurred on Friday, 2023-11-17, which shows 0 for both categories in this week.</li>
	<li>In the fourth week of November 2023, transactions occurred on Friday, 2023-11-24, involving one Premium member purchase of $5,117 and VIP member purchases totaling $14,933 ($9,692 from one and $5,241 from another).</li>
</ul>

<p><strong>Note:</strong> The output table is ordered by week_of_month and membership in ascending order.</p>
</div>

## 解法

### 方法一：递归 + 连接

我们首先创建一个递归表 `T`，其中包含 `week_of_month` 列，表示月份的第几周。然后创建一个表 `M`，包含 `membership` 列，表示会员类型，取值为 `'Premium'` 和 `'VIP'`。

接着创建一个表 `P`，包含 `week_of_month`、`membership` 和 `amount_spend` 列，筛选出每个会员在每个月的第几周的周五的消费金额。最后，我们将 `T` 和 `M` 表连接，再左连接 `P` 表，并且按照 `week_of_month` 和 `membership` 列进行分组，计算每周每种会员的总消费金额。

<!-- tabs:start -->

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

<!-- end -->
