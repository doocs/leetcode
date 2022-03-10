# [1045. Customers Who Bought All Products](https://leetcode.com/problems/customers-who-bought-all-products)

[中文文档](/solution/1000-1099/1045.Customers%20Who%20Bought%20All%20Products/README.md)

## Description

<p>Table: <code>Customer</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| customer_id | int     |
| product_key | int     |
+-------------+---------+
There is no primary key for this table. It may contain duplicates.
product_key is a foreign key to <code>Product</code> table.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Product</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| product_key | int     |
+-------------+---------+
product_key is the primary key column for this table.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the customer ids from the <code>Customer</code> table that bought all the products in the <code>Product</code> table.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Customer table:
+-------------+-------------+
| customer_id | product_key |
+-------------+-------------+
| 1           | 5           |
| 2           | 6           |
| 3           | 5           |
| 3           | 6           |
| 1           | 6           |
+-------------+-------------+
Product table:
+-------------+
| product_key |
+-------------+
| 5           |
| 6           |
+-------------+
<strong>Output:</strong> 
+-------------+
| customer_id |
+-------------+
| 1           |
| 3           |
+-------------+
<strong>Explanation:</strong> 
The customers who bought all the products (5 and 6) are customers with IDs 1 and 3.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    customer_id
FROM
    Customer
GROUP BY
    customer_id
HAVING
    COUNT(DISTINCT(product_key)) = (
        SELECT
            COUNT(1)
        FROM
            Product
    );
```

<!-- tabs:end -->
