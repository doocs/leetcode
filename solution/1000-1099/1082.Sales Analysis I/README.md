---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1082.Sales%20Analysis%20I/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1082. 销售分析 I 🔒](https://leetcode.cn/problems/sales-analysis-i)

[English Version](/solution/1000-1099/1082.Sales%20Analysis%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>产品表：<code>Product</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| product_id   | int     |
| product_name | varchar |
| unit_price   | int     |
+--------------+---------+
product_id 是这个表的主键(具有唯一值的列)。
该表的每一行显示每个产品的名称和价格。
</pre>

<p>销售表：<code>Sales</code></p>

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
这个表它可以有重复的行。 
product_id 是 Product 表的外键(reference 列)。
该表的每一行包含关于一个销售的一些信息。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，找出总销售额最高的销售者，如果有并列的，就都展示出来。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>返回结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Product 表：
+------------+--------------+------------+
| product_id | product_name | unit_price |
+------------+--------------+------------+
| 1          | S8           | 1000       |
| 2          | G4           | 800        |
| 3          | iPhone       | 1400       |
+------------+--------------+------------+
<code>Sales </code>表：
+-----------+------------+----------+------------+----------+-------+
| seller_id | product_id | buyer_id | sale_date  | quantity | price |
+-----------+------------+----------+------------+----------+-------+
| 1         | 1          | 1        | 2019-01-21 | 2        | 2000  |
| 1         | 2          | 2        | 2019-02-17 | 1        | 800   |
| 2         | 2          | 3        | 2019-06-02 | 1        | 800   |
| 3         | 3          | 4        | 2019-05-13 | 2        | 2800  |
+-----------+------------+----------+------------+----------+-------+
<strong>输出：</strong>
+-------------+
| seller_id   |
+-------------+
| 1           |
| 3           |
+-------------+
<strong>解释：</strong>Id 为 1 和 3 的销售者，销售总金额都为最高的 2800。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT seller_id
FROM Sales
GROUP BY seller_id
HAVING
    SUM(price) >= ALL(
        SELECT SUM(price)
        FROM Sales
        GROUP BY seller_id
    );
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            seller_id,
            SUM(price) AS tot,
            RANK() OVER (ORDER BY SUM(price) DESC) AS rk
        FROM Sales
        GROUP BY seller_id
    )
SELECT seller_id
FROM T
WHERE rk = 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
