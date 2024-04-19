# [1549. The Most Recent Orders for Each Product 🔒](https://leetcode.com/problems/the-most-recent-orders-for-each-product)

[中文文档](/solution/1500-1599/1549.The%20Most%20Recent%20Orders%20for%20Each%20Product/README.md)

<!-- tags:Database -->

## Description

<p>Table: <code>Customers</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| customer_id   | int     |
| name          | varchar |
+---------------+---------+
customer_id is the column with unique values for this table.
This table contains information about the customers.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Orders</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| order_id      | int     |
| order_date    | date    |
| customer_id   | int     |
| product_id    | int     |
+---------------+---------+
order_id is the column with unique values for this table.
This table contains information about the orders made by customer_id.
There will be no product ordered by the same user <strong>more than once</strong> in one day.</pre>

<p>&nbsp;</p>

<p>Table: <code>Products</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| product_name  | varchar |
| price         | int     |
+---------------+---------+
product_id is the column with unique values for this table.
This table contains information about the Products.
</pre>

<p>&nbsp;</p>

<p>Write a solution to find the most recent order(s) of each product.</p>

<p>Return the result table ordered by <code>product_name</code> in ascending order and in case of a tie by the <code>product_id</code> in <strong>ascending order</strong>. If there still a tie, order them by <code>order_id</code> in <strong>ascending order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Customers table:
+-------------+-----------+
| customer_id | name      |
+-------------+-----------+
| 1           | Winston   |
| 2           | Jonathan  |
| 3           | Annabelle |
| 4           | Marwan    |
| 5           | Khaled    |
+-------------+-----------+
Orders table:
+----------+------------+-------------+------------+
| order_id | order_date | customer_id | product_id |
+----------+------------+-------------+------------+
| 1        | 2020-07-31 | 1           | 1          |
| 2        | 2020-07-30 | 2           | 2          |
| 3        | 2020-08-29 | 3           | 3          |
| 4        | 2020-07-29 | 4           | 1          |
| 5        | 2020-06-10 | 1           | 2          |
| 6        | 2020-08-01 | 2           | 1          |
| 7        | 2020-08-01 | 3           | 1          |
| 8        | 2020-08-03 | 1           | 2          |
| 9        | 2020-08-07 | 2           | 3          |
| 10       | 2020-07-15 | 1           | 2          |
+----------+------------+-------------+------------+
Products table:
+------------+--------------+-------+
| product_id | product_name | price |
+------------+--------------+-------+
| 1          | keyboard     | 120   |
| 2          | mouse        | 80    |
| 3          | screen       | 600   |
| 4          | hard disk    | 450   |
+------------+--------------+-------+
<strong>Output:</strong> 
+--------------+------------+----------+------------+
| product_name | product_id | order_id | order_date |
+--------------+------------+----------+------------+
| keyboard     | 1          | 6        | 2020-08-01 |
| keyboard     | 1          | 7        | 2020-08-01 |
| mouse        | 2          | 8        | 2020-08-03 |
| screen       | 3          | 3        | 2020-08-29 |
+--------------+------------+----------+------------+
<strong>Explanation:</strong> 
keyboard&#39;s most recent order is in 2020-08-01, it was ordered two times this day.
mouse&#39;s most recent order is in 2020-08-03, it was ordered only once this day.
screen&#39;s most recent order is in 2020-08-29, it was ordered only once this day.
The hard disk was never ordered and we do not include it in the result table.
</pre>

## Solutions

### Solution 1: Equi-Join + Window Function

We can use an equi-join to join the `Orders` table and the `Products` table based on `product_id`, and then use the window function `rank()`, which assigns a rank to each `product_id` in the `Orders` table based on its `order_date` in descending order. Finally, we can select the rows with a rank of $1$ for each `product_id`.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY product_id
                ORDER BY order_date DESC
            ) AS rk
        FROM
            Orders
            JOIN Products USING (product_id)
    )
SELECT product_name, product_id, order_id, order_date
FROM T
WHERE rk = 1
ORDER BY 1, 2, 3;
```

<!-- tabs:end -->

<!-- end -->
