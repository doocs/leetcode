# [1164. Product Price at a Given Date](https://leetcode.com/problems/product-price-at-a-given-date)

[中文文档](/solution/1100-1199/1164.Product%20Price%20at%20a%20Given%20Date/README.md)

## Description

<p>Table: <code>Products</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| new_price     | int     |
| change_date   | date    |
+---------------+---------+
(product_id, change_date) is the primary key of this table.
Each row of this table indicates that the price of some product was changed to a new price at some date.</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find the prices of all products on <code>2019-08-16</code>. Assume the price of all products before any change is <code>10</code>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Products table:
+------------+-----------+-------------+
| product_id | new_price | change_date |
+------------+-----------+-------------+
| 1          | 20        | 2019-08-14  |
| 2          | 50        | 2019-08-14  |
| 1          | 30        | 2019-08-15  |
| 1          | 35        | 2019-08-16  |
| 2          | 65        | 2019-08-17  |
| 3          | 20        | 2019-08-18  |
+------------+-----------+-------------+
<strong>Output:</strong> 
+------------+-------+
| product_id | price |
+------------+-------+
| 2          | 50    |
| 1          | 35    |
| 3          | 10    |
+------------+-------+
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    p1.product_id AS product_id,
    ifnull(p2.price, 10) AS price
FROM
    (
        SELECT DISTINCT
            (product_id) AS product_id
        FROM Products
    ) AS p1
    LEFT JOIN (
        SELECT
            t1.product_id,
            t1.new_price AS price
        FROM
            Products AS t1
            JOIN (
                SELECT
                    product_id,
                    max(change_date) AS change_date
                FROM Products
                WHERE change_date <= '2019-08-16'
                GROUP BY product_id
            ) AS t2
                ON t1.product_id = t2.product_id AND t1.change_date = t2.change_date
    ) AS p2
        ON p1.product_id = p2.product_id;
```

```sql
# Write your MySQL query statement below
WITH
    P AS (
        SELECT p1.product_id, new_price, change_date
        FROM
            (
                SELECT DISTINCT product_id
                FROM Products
            ) AS p1
            LEFT JOIN Products AS p2
                ON p1.product_id = p2.product_id AND p2.change_date <= '2019-08-16'
    ),
    T AS (
        SELECT
            *,
            rank() OVER (
                PARTITION BY product_id
                ORDER BY change_date DESC
            ) AS rk
        FROM P
    )
SELECT product_id, ifnull(new_price, 10) AS price
FROM T
WHERE rk = 1;
```

<!-- tabs:end -->
