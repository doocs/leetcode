# [1069. 产品销售分析 II](https://leetcode.cn/problems/product-sales-analysis-ii)

[English Version](/solution/1000-1099/1069.Product%20Sales%20Analysis%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>销售表：<code>Sales</code></p>

<pre>
+-------------+-------+
| Column Name | Type  |
+-------------+-------+
| sale_id     | int   |
| product_id  | int   |
| year        | int   |
| quantity    | int   |
| price       | int   |
+-------------+-------+
sale_id 是这个表的主键（具有唯一值的列）。
product_id 是 Product 表的外键（reference 列）。
该表的每一行显示产品product_id在某一年的销售情况。
请注意价格是每单位的。
</pre>

<p>产品表：<code>Product</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| product_id   | int     |
| product_name | varchar |
+--------------+---------+
product_id 是这个表的主键（具有唯一值的列）。
该表的每一行表示每种产品的产品名称。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，统计每个产品的销售总量。</p>

<p>返回结果表 <strong>无顺序要求</strong> 。</p>

<p>结果格式如下例子所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<code><strong>输入：</strong>
Sales</code> 表：
+---------+------------+------+----------+-------+
| sale_id | product_id | year | quantity | price |
+---------+------------+------+----------+-------+ 
| 1       | 100        | 2008 | 10       | 5000  |
| 2       | 100        | 2009 | 12       | 5000  |
| 7       | 200        | 2011 | 15       | 9000  |
+---------+------------+------+----------+-------+
Product 表：
+------------+--------------+
| product_id | product_name |
+------------+--------------+
| 100        | Nokia        |
| 200        | Apple        |
| 300        | Samsung      |
+------------+--------------+
<strong>输出：</strong>
+--------------+----------------+
| product_id   | total_quantity |
+--------------+----------------+
| 100          | 22             |
| 200          | 15             |
+--------------+----------------+</pre>

## 解法

### 方法一：使用 `GROUP BY`

我们可以使用 `GROUP BY`，按照 `product_id` 分组，然后每一组对 `quantity` 求和。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT product_id, SUM(quantity) AS total_quantity
FROM Sales
GROUP BY 1;
```

<!-- tabs:end -->

<!-- end -->
