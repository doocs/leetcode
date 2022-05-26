# [1070. 产品销售分析 III](https://leetcode.cn/problems/product-sales-analysis-iii)

[English Version](/solution/1000-1099/1070.Product%20Sales%20Analysis%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>销售表&nbsp;<code>Sales</code>：</p>

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
(sale_id, year) 是这张表的主键。
product_id 是产品表的外键。
这张表的每一行都表示：编号 product_id 的产品在某一年的销售额。
请注意，价格是按每单位计的。
</pre>

<p>&nbsp;</p>

<p>产品表&nbsp;<code>Product</code>：</p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| product_id   | int     |
| product_name | varchar |
+--------------+---------+
product_id 是这张表的主键。
这张表的每一行都标识：每个产品的 id 和 产品名称。</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询，选出每个销售产品&nbsp;<strong>第一年</strong> 销售的 <strong>产品 id</strong>、<strong>年份</strong>、<strong>数量&nbsp;</strong>和 <strong>价格</strong>。</p>

<p>结果表中的条目可以按 <strong>任意顺序</strong> 排列。</p>

<p>查询结果格式如下例所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Sales 表：
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
+------------+------------+----------+-------+
| product_id | first_year | quantity | price |
+------------+------------+----------+-------+ 
| 100        | 2008       | 10       | 5000  |
| 200        | 2011       | 15       | 9000  |
+------------+------------+----------+-------+</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    product_id,
    year as first_year,
    quantity,
    price
FROM
    Sales
WHERE
    (product_id, year) IN (
        SELECT
            product_id, min(year) year
        FROM
            Sales
        GROUP BY product_id
    );
```

<!-- tabs:end -->
