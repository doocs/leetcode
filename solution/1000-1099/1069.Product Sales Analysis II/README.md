# [1069. 产品销售分析 II](https://leetcode.cn/problems/product-sales-analysis-ii)

[English Version](/solution/1000-1099/1069.Product%20Sales%20Analysis%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>销售表：<code>Sales</code></p>

<pre>+-------------+-------+
| Column Name | Type  |
+-------------+-------+
| sale_id     | int   |
| product_id  | int   |
| year        | int   |
| quantity    | int   |
| price       | int   |
+-------------+-------+
sale_id 是这个表的主键。
product_id 是 Product 表的外键。
请注意价格是每单位的。
</pre>

<p>产品表：<code>Product</code></p>

<pre>+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| product_id   | int     |
| product_name | varchar |
+--------------+---------+
product_id 是这个表的主键。
</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询，按产品 id <code>product_id</code> 来统计每个产品的销售总量。</p>

<p>&nbsp;</p>

<p>查询结果格式如下面例子所示:</p>

<pre><code>Sales</code> 表：
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

Result 表：
+--------------+----------------+
| product_id   | total_quantity |
+--------------+----------------+
| 100          | 22             |
| 200          | 15             |
+--------------+----------------+</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    product_id,
    sum(quantity) AS total_quantity
FROM
    Sales
GROUP BY
    product_id;
```

<!-- tabs:end -->
