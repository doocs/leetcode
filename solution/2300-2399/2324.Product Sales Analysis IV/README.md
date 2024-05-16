---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2324.Product%20Sales%20Analysis%20IV/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2324. 产品销售分析 IV 🔒](https://leetcode.cn/problems/product-sales-analysis-iv)

[English Version](/solution/2300-2399/2324.Product%20Sales%20Analysis%20IV/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Sales</code></p>

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
product_id 是 product 表的外键。
该表的每一行都显示了产品的 ID 和用户购买的数量。
</pre>

<p>&nbsp;</p>

<p>表: <code>Product</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| product_id  | int  |
| price       | int  |
+-------------+------+
product_id 包含唯一值。
该表的每一行都表示每种产品的价格。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，为每个用户获取其消费最多的产品 id。如果同一用户在两个或多个产品上花费了最多的钱，请获取所有花费了最多的钱的产品。</p>

<p data-group="1-1">以 <strong>任意顺序</strong> 返回结果表。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Sales 表:
+---------+------------+---------+----------+
| sale_id | product_id | user_id | quantity |
+---------+------------+---------+----------+
| 1       | 1          | 101     | 10       |
| 2       | 3          | 101     | 7        |
| 3       | 1          | 102     | 9        |
| 4       | 2          | 102     | 6        |
| 5       | 3          | 102     | 10       |
| 6       | 1          | 102     | 6        |
+---------+------------+---------+----------+
Product 表:
+------------+-------+
| product_id | price |
+------------+-------+
| 1          | 10    |
| 2          | 25    |
| 3          | 15    |
+------------+-------+
<strong>输出:</strong> 
+---------+------------+
| user_id | product_id |
+---------+------------+
| 101     | 3          |
| 102     | 1          |
| 102     | 2          |
| 102     | 3          |
+---------+------------+ 
<strong>解释:</strong> 
用户 101:
    - 在产品 1 上花费 10 * 10 = 100。
    - 在产品 3 上花费 7 * 15 = 105。
用户101在产品3上花的钱最多。
用户 102:
    - 在产品 1 上花费 (9 + 7)* 10 = 150
    - 在产品 2 上花费 6 * 25 = 150
    - 在产品 3 上花费 10 * 15 = 150。
用户 102 在产品 1、2、3 上花的钱最多。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            user_id,
            product_id,
            RANK() OVER (
                PARTITION BY user_id
                ORDER BY SUM(quantity * price) DESC
            ) AS rk
        FROM
            Sales
            JOIN Product USING (product_id)
        GROUP BY 1, 2
    )
SELECT user_id, product_id
FROM T
WHERE rk = 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
