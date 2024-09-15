---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1251.Average%20Selling%20Price/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1251. Average Selling Price](https://leetcode.com/problems/average-selling-price)

[中文文档](/solution/1200-1299/1251.Average%20Selling%20Price/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Prices</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| start_date    | date    |
| end_date      | date    |
| price         | int     |
+---------------+---------+
(product_id, start_date, end_date) is the primary key (combination of columns with unique values) for this table.
Each row of this table indicates the price of the product_id in the period from start_date to end_date.
For each product_id there will be no two overlapping periods. That means there will be no two intersecting periods for the same product_id.
</pre>

<p>&nbsp;</p>

<p>Table: <code>UnitsSold</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| purchase_date | date    |
| units         | int     |
+---------------+---------+
This table may contain duplicate rows.
Each row of this table indicates the date, units, and product_id of each product sold. 
</pre>

<p>&nbsp;</p>

<p>Write a solution to find the average selling price for each product. <code>average_price</code> should be <strong>rounded to 2 decimal places</strong>. If a product does not have any sold units, its average selling price is assumed to be 0.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Prices table:
+------------+------------+------------+--------+
| product_id | start_date | end_date   | price  |
+------------+------------+------------+--------+
| 1          | 2019-02-17 | 2019-02-28 | 5      |
| 1          | 2019-03-01 | 2019-03-22 | 20     |
| 2          | 2019-02-01 | 2019-02-20 | 15     |
| 2          | 2019-02-21 | 2019-03-31 | 30     |
+------------+------------+------------+--------+
UnitsSold table:
+------------+---------------+-------+
| product_id | purchase_date | units |
+------------+---------------+-------+
| 1          | 2019-02-25    | 100   |
| 1          | 2019-03-01    | 15    |
| 2          | 2019-02-10    | 200   |
| 2          | 2019-03-22    | 30    |
+------------+---------------+-------+
<strong>Output:</strong> 
+------------+---------------+
| product_id | average_price |
+------------+---------------+
| 1          | 6.96          |
| 2          | 16.96         |
+------------+---------------+
<strong>Explanation:</strong> 
Average selling price = Total Price of Product / Number of products sold.
Average selling price for product 1 = ((100 * 5) + (15 * 20)) / 115 = 6.96
Average selling price for product 2 = ((200 * 15) + (30 * 30)) / 230 = 16.96
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Left Join + Grouping

We can use a left join to join the `Prices` table and the `UnitsSold` table on `product_id`, and the condition that `purchase_date` is between `start_date` and `end_date`. Then, we can use `GROUP BY` to group by `product_id` for aggregation, and use the `AVG` function to calculate the average price. Note that if a product has no sales records, the `AVG` function will return `NULL`, so we can use the `IFNULL` function to convert it to $0$.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    p.product_id,
    IFNULL(ROUND(SUM(price * units) / SUM(units), 2), 0) AS average_price
FROM
    Prices AS p
    LEFT JOIN UnitsSold AS u
        ON p.product_id = u.product_id AND purchase_date BETWEEN start_date AND end_date
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
