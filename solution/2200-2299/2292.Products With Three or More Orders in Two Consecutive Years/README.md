---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2292.Products%20With%20Three%20or%20More%20Orders%20in%20Two%20Consecutive%20Years/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2292. 连续两年有 3 个及以上订单的产品 🔒](https://leetcode.cn/problems/products-with-three-or-more-orders-in-two-consecutive-years)

[English Version](/solution/2200-2299/2292.Products%20With%20Three%20or%20More%20Orders%20in%20Two%20Consecutive%20Years/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Orders</code></p>

<pre>
+---------------+------+
| Column Name   | Type |
+---------------+------+
| order_id      | int  |
| product_id    | int  |
| quantity      | int  |
| purchase_date | date |
+---------------+------+
order_id 包含唯一值。
该表中的每一行都包含订单 ID、购买的产品 ID、数量和购买日期。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，获取连续两年订购三次或三次以上的所有产品的 id。</p>

<p data-group="1-1">以&nbsp;<strong>任意顺序&nbsp;</strong>返回结果表。</p>

<p>结果格式示例如下。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Orders 表:
+----------+------------+----------+---------------+
| order_id | product_id | quantity | purchase_date |
+----------+------------+----------+---------------+
| 1        | 1          | 7        | 2020-03-16    |
| 2        | 1          | 4        | 2020-12-02    |
| 3        | 1          | 7        | 2020-05-10    |
| 4        | 1          | 6        | 2021-12-23    |
| 5        | 1          | 5        | 2021-05-21    |
| 6        | 1          | 6        | 2021-10-11    |
| 7        | 2          | 6        | 2022-10-11    |
+----------+------------+----------+---------------+
<strong>输出:</strong> 
+------------+
| product_id |
+------------+
| 1          |
+------------+
<strong>解释:</strong> 
产品 1 在 2020 年和 2021 年都分别订购了三次。由于连续两年订购了三次，所以我们将其包含在答案中。
产品 2 在 2022 年订购了一次。我们不把它包括在答案中。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 要找出连续两年都至少有三笔订单的商品。按商品和年份分组后，需要判断相邻两年是否都达标。自连接比窗口函数更直观：先打上「该年是否 $\ge 3$ 笔」的标记，再把年份相差 $1$ 且两边标记都为真的行连起来。
>
> CTE 按 $(\textit{product\_id}, \textit{YEAR})$ 聚合得到 $\textit{mark}$，然后 $p_1.y = p_2.y-1$ 且同一商品、两行 $\textit{mark}$ 均为真，最后对商品去重。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    P AS (
        SELECT product_id, YEAR(purchase_date) AS y, COUNT(1) >= 3 AS mark
        FROM Orders
        GROUP BY 1, 2
    )
SELECT DISTINCT p1.product_id
FROM
    P AS p1
    JOIN P AS p2 ON p1.y = p2.y - 1 AND p1.product_id = p2.product_id
WHERE p1.mark AND p2.mark;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 方法一把所有年份都留在 CTE 里再用 $\textit{mark}$ 过滤。也可以在分组时用 $\textit{HAVING COUNT}(1)\ge 3$ 只保留达标年份，自连接条件更短，语义相同。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    P AS (
        SELECT product_id, YEAR(purchase_date) AS y
        FROM Orders
        GROUP BY 1, 2
        HAVING COUNT(1) >= 3
    )
SELECT DISTINCT p1.product_id
FROM
    P AS p1
    JOIN P AS p2 ON p1.y = p2.y - 1 AND p1.product_id = p2.product_id;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
