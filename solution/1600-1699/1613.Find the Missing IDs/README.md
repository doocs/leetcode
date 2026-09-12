---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1613.Find%20the%20Missing%20IDs/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1613. 找到遗失的ID 🔒](https://leetcode.cn/problems/find-the-missing-ids)

[English Version](/solution/1600-1699/1613.Find%20the%20Missing%20IDs/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Customers</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| customer_id   | int     |
| customer_name | varchar |
+---------------+---------+
customer_id 是该表主键.
该表第一行包含了顾客的名字和 id.
</pre>

<p>&nbsp;</p>

<p>编写一个解决方案,&nbsp;找到所有遗失的顾客 id。遗失的顾客 id 是指那些不在&nbsp;<code>Customers</code>&nbsp;表中,&nbsp;值却处于&nbsp;<code>1</code>&nbsp;和表中&nbsp;<strong>最大</strong>&nbsp;<code>customer_id</code>&nbsp;之间的 id.</p>

<p><strong>注意:&nbsp;</strong>最大的&nbsp;<code>customer_id</code>&nbsp;值不会超过&nbsp;<code>100</code>.</p>

<p>返回结果按&nbsp;<code>ids</code> <strong>升序&nbsp;</strong>排列</p>

<p>查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<code><strong>输入：</strong>
Customers</code> 表:
+-------------+---------------+
| customer_id | customer_name |
+-------------+---------------+
| 1           | Alice         |
| 4           | Bob           |
| 5           | Charlie       |
+-------------+---------------+
<strong>输出：</strong>
+-----+
| <code>ids </code>|
+-----+
| 2   |
| 3   |
+-----+
<strong>解释：</strong>
表中最大的 customer_id 是 5, 所以在范围 [1,5] 内, ID2 和 3 从表中遗失.</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

<!-- thinking:start -->

> **思考**
>
> 缺失编号是小于最大 $\texttt{customer\_id}$ 且未出现在表中的正整数。SQL 没有现成的连续整数表，需要先构造候选全集。
>
> 题面保证编号不超过 $100$，用递归 CTE 生成 $1$ 到 $100$，再去掉已出现的编号并限制小于最大值。
>
> 外层查询选取 $n < \texttt{MAX}(\texttt{customer\_id})$ 且 $n \texttt{ NOT IN }$ 已有编号的行作为 $\texttt{ids}$。

<!-- thinking:end -->

利用 `recursive` 关键字，递归生成 `[1, 100]` 的序列，然后排除已有的 `customer_id`，即可得到结果。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH RECURSIVE
    t AS (
        SELECT
            1 AS n
        UNION ALL
        SELECT
            n + 1
        FROM t
        WHERE n < 100
    )
SELECT
    n AS ids
FROM t
WHERE
    n < (
        SELECT
            MAX(customer_id)
        FROM Customers
    )
    AND n NOT IN (
        SELECT
            customer_id
        FROM Customers
    );
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
