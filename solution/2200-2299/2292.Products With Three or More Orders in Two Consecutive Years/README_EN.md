# [2292. Products With Three or More Orders in Two Consecutive Years](https://leetcode.com/problems/products-with-three-or-more-orders-in-two-consecutive-years)

[中文文档](/solution/2200-2299/2292.Products%20With%20Three%20or%20More%20Orders%20in%20Two%20Consecutive%20Years/README.md)

## Description

<p>Table: <code>Orders</code></p>

<pre>
+---------------+------+
| Column Name   | Type |
+---------------+------+
| order_id      | int  |
| product_id    | int  |
| quantity      | int  |
| purchase_date | date |
+---------------+------+
order_id is the primary key for this table.
Each row in this table contains the ID of an order, the id of the product purchased, the quantity, and the purchase date.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the IDs of all the products that were ordered three or more times in two consecutive years.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is shown in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Orders table:
+----------+------------+----------+---------------+
| order_id | product_id | quantity | purchase_date |
+----------+------------+----------+---------------+
| 1        | 1          | 7        | 2020-03-16    |
| 2        | 1          | 4        | 2020-12-02    |
| 3        | 1          | 7        | 2020-05-10    |
| 4        | 1          | 6        | 2021-12-23    |
| 5        | 1          | 5        | 2021-05-21    |
| 6        | 1          | 6        | 2021-10-11    |
| 7        | 2          | 6        | 2022-10-11    |
+----------+------------+----------+---------------+
<strong>Output:</strong> 
+------------+
| product_id |
+------------+
| 1          |
+------------+
<strong>Explanation:</strong> 
Product 1 was ordered in 2020 three times and in 2021 three times. Since it was ordered three times in two consecutive years, we include it in the answer.
Product 2 was ordered one time in 2022. We do not include it in the answer.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
