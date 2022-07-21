# [2324. Product Sales Analysis IV](https://leetcode.com/problems/product-sales-analysis-iv)

[中文文档](/solution/2300-2399/2324.Product%20Sales%20Analysis%20IV/README.md)

## Description

<p>Table: <code>Sales</code></p>

<pre>
+-------------+-------+
| Column Name | Type  |
+-------------+-------+
| sale_id     | int   |
| product_id  | int   |
| user_id     | int   |
| quantity    | int   |
+-------------+-------+
sale_id is the primary key of this table.
product_id is a foreign key to <code>Product</code> table.
Each row of this table shows the ID of the product and the quantity purchased by a user.
</pre>

<p></p>

<p>Table: <code>Product</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| product_id  | int  |
| price       | int  |
+-------------+------+
product_id is the primary key of this table.
Each row of this table indicates the price of each product.
</pre>

<p></p>

<p>Write an SQL query that reports for each user the product id on which the user spent the most money. In case the same user spent the most money on two or more products, report all of them.</p>

<p>Return the resulting table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Sales table:
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
Product table:
+------------+-------+
| product_id | price |
+------------+-------+
| 1          | 10    |
| 2          | 25    |
| 3          | 15    |
+------------+-------+
<strong>Output:</strong> 
+---------+------------+
| user_id | product_id |
+---------+------------+
| 101     | 3          |
| 102     | 1          |
| 102     | 2          |
| 102     | 3          |
+---------+------------+ 
<strong>Explanation:</strong> 
User 101:
    - Spent 10 * 10 = 100 on product 1.
    - Spent 7 * 15 = 105 on product 3.
User 101 spent the most money on product 3.
User 102:
    - Spent (9 + 7) * 10 = 150 on product 1.
    - Spent 6 * 25 = 150 on product 2.
    - Spent 10 * 15 = 150 on product 3.
User 102 spent the most money on products 1, 2, and 3.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
