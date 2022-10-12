# [2329. Product Sales Analysis V](https://leetcode.com/problems/product-sales-analysis-v)

[中文文档](/solution/2300-2399/2329.Product%20Sales%20Analysis%20V/README.md)

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

<p>&nbsp;</p>

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

<p>&nbsp;</p>

<p>Write an SQL query that reports the spending of each user.</p>

<p>Return the resulting table ordered by <code>spending</code> in <strong>descending order</strong>. In case of a tie, order them by <code>user_id</code> in ascending order.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Sales table:
+---------+------------+---------+----------+
| sale_id | product_id | user_id | quantity |
+---------+------------+---------+----------+
| 1       | 1          | 101     | 10       |
| 2       | 2          | 101     | 1        |
| 3       | 3          | 102     | 3        |
| 4       | 3          | 102     | 2        |
| 5       | 2          | 103     | 3        |
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
+---------+----------+
| user_id | spending |
+---------+----------+
| 101     | 125      |
| 102     | 75       |
| 103     | 75       |
+---------+----------+
<strong>Explanation:</strong> 
User 101 spent 10 * 10 + 1 * 25 = 125.
User 102 spent 3 * 15 + 2 * 15 = 75.
User 103 spent 3 * 25 = 75.
Users 102 and 103 spent the same amount and we break the tie by their ID while user 101 is on the top.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
