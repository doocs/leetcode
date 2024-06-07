---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2329.Product%20Sales%20Analysis%20V/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2329. 产品销售分析 Ⅴ 🔒](https://leetcode.cn/problems/product-sales-analysis-v)

[English Version](/solution/2300-2399/2329.Product%20Sales%20Analysis%20V/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Sales</code></p>

<pre>
+-------------+-------+
| Column Name | Type  |
+-------------+-------+
| sale_id     | int   |
| product_id  | int   |
| user_id     | int   |
| quantity    | int   |
+-------------+-------+
sale_id 包含唯一值。
product_id 是 Product 表的外键。
这个表中的每一行展示了产品的 ID 以及某个用户购买的数量。 
</pre>

<p>表：<code>Product</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| product_id  | int  |
| price       | int  |
+-------------+------+
product_id 包含唯一值。
这张表中的每一行均表示了某个产品的价格。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，获取每个用户的消费额。</p>

<p>按用户消费额 <code>spending</code> <strong>递减</strong>的顺序返回结果。在消费额相等的情况下，以 <code>user_id</code> 递增的顺序将其排序。</p>

<p>结果的格式如下面例子所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1 ：</strong></p>

<pre>
<strong>输入：</strong>
Sales 表：
+---------+------------+---------+----------+
| sale_id | product_id | user_id | quantity |
+---------+------------+---------+----------+
| 1       | 1          | 101     | 10       |
| 2       | 2          | 101     | 1        |
| 3       | 3          | 102     | 3        |
| 4       | 3          | 102     | 2        |
| 5       | 2          | 103     | 3        |
+---------+------------+---------+----------+
Product 表：
+------------+-------+
| product_id | price |
+------------+-------+
| 1          | 10    |
| 2          | 25    |
| 3          | 15    |
+------------+-------+
<strong>输出：</strong>
+---------+----------+
| user_id | spending |
+---------+----------+
| 101     | 125      |
| 102     | 75       |
| 103     | 75       |
+---------+----------+
<strong>解释：</strong>
用户 101 的消费额为 10 * 10 + 1 * 25 = 125 。
用户 102 的消费额为 3 * 15 + 2 * 15 = 75 。
用户 103 的消费额为 3 * 25 = 75 。
用户 101 排在最前，用户 102 与用户 103 的消费额相同，根据 ID 我们可以进一步确认排名，所以用户 102 排在 103 前面。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT user_id, SUM(quantity * price) AS spending
FROM
    Sales
    JOIN Product USING (product_id)
GROUP BY 1
ORDER BY 2 DESC, 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
