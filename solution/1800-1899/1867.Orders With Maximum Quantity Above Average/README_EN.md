# [1867. Orders With Maximum Quantity Above Average](https://leetcode.com/problems/orders-with-maximum-quantity-above-average)

[中文文档](/solution/1800-1899/1867.Orders%20With%20Maximum%20Quantity%20Above%20Average/README.md)

## Description

<p>Table: <code>OrdersDetails</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| order_id    | int  |
| product_id  | int  |
| quantity    | int  |
+-------------+------+
(order_id, product_id) is the primary key for this table.
A single order is represented as multiple rows, one row for each product in the order.
Each row of this table contains the quantity ordered of the product product_id in the order order_id.
</pre>

<p>&nbsp;</p>

<p>You are running an e-commerce site that is looking for <strong>imbalanced orders</strong>. An <strong>imbalanced order</strong> is one whose <strong>maximum</strong> quantity is <strong>strictly greater</strong> than the <strong>average</strong> quantity of <strong>every order (including itself)</strong>.</p>

<p>The <strong>average </strong>quantity of an order is calculated as <code>(total quantity of all products in the order) / (number of different products in the order)</code>. The <strong>maximum</strong> quantity of an order is the highest <code>quantity</code> of any single product in the order.</p>

<p>Write an SQL query to find the <code>order_id</code> of all <strong>imbalanced orders</strong>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
OrdersDetails table:
+----------+------------+----------+
| order_id | product_id | quantity |
+----------+------------+----------+
| 1        | 1          | 12       |
| 1        | 2          | 10       |
| 1        | 3          | 15       |
| 2        | 1          | 8        |
| 2        | 4          | 4        |
| 2        | 5          | 6        |
| 3        | 3          | 5        |
| 3        | 4          | 18       |
| 4        | 5          | 2        |
| 4        | 6          | 8        |
| 5        | 7          | 9        |
| 5        | 8          | 9        |
| 3        | 9          | 20       |
| 2        | 9          | 4        |
+----------+------------+----------+
<strong>Output:</strong> 
+----------+
| order_id |
+----------+
| 1        |
| 3        |
+----------+
<strong>Explanation:</strong> 
The average quantity of each order is:
- order_id=1: (12+10+15)/3 = 12.3333333
- order_id=2: (8+4+6+4)/4 = 5.5
- order_id=3: (5+18+20)/3 = 14.333333
- order_id=4: (2+8)/2 = 5
- order_id=5: (9+9)/2 = 9

The maximum quantity of each order is:
- order_id=1: max(12, 10, 15) = 15
- order_id=2: max(8, 4, 6, 4) = 8
- order_id=3: max(5, 18, 20) = 20
- order_id=4: max(2, 8) = 8
- order_id=5: max(9, 9) = 9

Orders 1 and 3 are imbalanced because they have a maximum quantity that exceeds the average quantity of every order.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
