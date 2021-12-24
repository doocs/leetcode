# [1070. 产品销售分析 III](https://leetcode-cn.com/problems/product-sales-analysis-iii)

[English Version](/solution/1000-1099/1070.Product%20Sales%20Analysis%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>销售表&nbsp;<code>Sales</code>：</p>

<pre>+-------------+-------+
| Column Name | Type  |
+-------------+-------+
| sale_id     | int   |
| product_id  | int   |
| year        | int   |
| quantity    | int   |
| price       | int   |
+-------------+-------+
sale_id 是此表的主键。
product_id 是产品表的外键。
请注意，价格是按每单位计的。
</pre>

<p>产品表&nbsp;<code>Product</code>：</p>

<pre>+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| product_id   | int     |
| product_name | varchar |
+--------------+---------+
product_id 是此表的主键。</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询，选出每个销售产品的 <strong>第一年</strong> 的 <strong>产品 id</strong>、<strong>年份</strong>、<strong>数量&nbsp;</strong>和 <strong>价格</strong>。</p>

<p>查询结果格式如下：</p>

<pre><code>Sales</code> table:
+---------+------------+------+----------+-------+
| sale_id | product_id | year | quantity | price |
+---------+------------+------+----------+-------+ 
| 1       | 100        | 2008 | 10       | 5000  |
| 2       | 100        | 2009 | 12       | 5000  |
| 7       | 200        | 2011 | 15       | 9000  |
+---------+------------+------+----------+-------+

Product table:
+------------+--------------+
| product_id | product_name |
+------------+--------------+
| 100        | Nokia        |
| 200        | Apple        |
| 300        | Samsung      |
+------------+--------------+

Result table:
+------------+------------+----------+-------+
| product_id | first_year | quantity | price |
+------------+------------+----------+-------+ 
| 100        | 2008       | 10       | 5000  |
| 200        | 2011       | 15       | 9000  |
+------------+------------+----------+-------+
</pre>

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
