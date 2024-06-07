---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1068.Product%20Sales%20Analysis%20I/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1068. Product Sales Analysis I](https://leetcode.com/problems/product-sales-analysis-i)

[中文文档](/solution/1000-1099/1068.Product%20Sales%20Analysis%20I/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Sales</code></p>

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
(sale_id, year) is the primary key (combination of columns with unique values) of this table.
product_id is a foreign key (reference column) to <code>Product</code> table.
Each row of this table shows a sale on the product product_id in a certain year.
Note that the price is per unit.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Product</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| product_id   | int     |
| product_name | varchar |
+--------------+---------+
product_id is the primary key (column with unique values) of this table.
Each row of this table indicates the product name of each product.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report the <code>product_name</code>, <code>year</code>, and <code>price</code> for each <code>sale_id</code> in the <code>Sales</code> table.</p>

<p>Return the resulting table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Sales table:
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
<strong>Output:</strong> 
+--------------+-------+-------+
| product_name | year  | price |
+--------------+-------+-------+
| Nokia        | 2008  | 5000  |
| Nokia        | 2009  | 5000  |
| Apple        | 2011  | 9000  |
+--------------+-------+-------+
<strong>Explanation:</strong> 
From sale_id = 1, we can conclude that Nokia was sold for 5000 in the year 2008.
From sale_id = 2, we can conclude that Nokia was sold for 5000 in the year 2009.
From sale_id = 7, we can conclude that Apple was sold for 9000 in the year 2011.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT product_name, year, price
FROM
    Sales
    JOIN Product USING (product_id);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
