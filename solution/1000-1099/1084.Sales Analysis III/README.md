---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1084.Sales%20Analysis%20III/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1084. 销售分析 III](https://leetcode.cn/problems/sales-analysis-iii)

[English Version](/solution/1000-1099/1084.Sales%20Analysis%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：&nbsp;<code>Product</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| product_id   | int     |
| product_name | varchar |
| unit_price   | int     |
+--------------+---------+
product_id 是该表的主键（具有唯一值的列）。
该表的每一行显示每个产品的名称和价格。
</pre>

<p>表：<code>Sales</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| seller_id   | int     |
| product_id  | int     |
| buyer_id    | int     |
| sale_date   | date    |
| quantity    | int     |
| price       | int     |
+------ ------+---------+
这个表可能有重复的行。
product_id 是 Product 表的外键（reference 列）。
该表的每一行包含关于一个销售的一些信息。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，报告&nbsp;<code>2019年春季</code>&nbsp;才售出的产品。即&nbsp;<strong>仅&nbsp;</strong>在&nbsp;<code><strong>2019-01-01</strong></code><strong>&nbsp;</strong>（含）至&nbsp;<code><strong>2019-03-31</strong></code><strong>&nbsp;</strong>（含）之间出售的商品。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Product table:
+------------+--------------+------------+
| product_id | product_name | unit_price |
+------------+--------------+------------+
| 1          | S8           | 1000       |
| 2          | G4           | 800        |
| 3          | iPhone       | 1400       |
+------------+--------------+------------+
<code>Sales </code>table:
+-----------+------------+----------+------------+----------+-------+
| seller_id | product_id | buyer_id | sale_date  | quantity | price |
+-----------+------------+----------+------------+----------+-------+
| 1         | 1          | 1        | 2019-01-21 | 2        | 2000  |
| 1         | 2          | 2        | 2019-02-17 | 1        | 800   |
| 2         | 2          | 3        | 2019-06-02 | 1        | 800   |
| 3         | 3          | 4        | 2019-05-13 | 2        | 2800  |
+-----------+------------+----------+------------+----------+-------+
<strong>输出：</strong>
+-------------+--------------+
| product_id  | product_name |
+-------------+--------------+
| 1           | S8           |
+-------------+--------------+
<strong>解释:</strong>
id 为 1 的产品仅在 2019 年春季销售。
id 为 2 的产品在 2019 年春季销售，但也在 2019 年春季之后销售。
id 为 3 的产品在 2019 年春季之后销售。
我们只返回 id 为 1 的产品，因为它是 2019 年春季才销售的产品。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：JOIN + GROUP BY + HAVING

我们可以通过 `JOIN` 将 `Sales` 表和 `Product` 表连接起来，然后通过 `GROUP BY` 和 `HAVING` 来筛选出符合条件的产品。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT product_id, product_name
FROM
    Sales
    JOIN Product USING (product_id)
GROUP BY 1
HAVING COUNT(1) = SUM(sale_date BETWEEN '2019-01-01' AND '2019-03-31');
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
