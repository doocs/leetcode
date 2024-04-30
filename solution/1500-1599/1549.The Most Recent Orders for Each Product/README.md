# [1549. 每件商品的最新订单 🔒](https://leetcode.cn/problems/the-most-recent-orders-for-each-product)

[English Version](/solution/1500-1599/1549.The%20Most%20Recent%20Orders%20for%20Each%20Product/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Customers</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| customer_id   | int     |
| name          | varchar |
+---------------+---------+
customer_id 是该表主键.
该表包含消费者的信息.
</pre>

<p>&nbsp;</p>

<p>表: <code>Orders</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| order_id      | int     |
| order_date    | date    |
| customer_id   | int     |
| product_id    | int     |
+---------------+---------+
order_id 是该表主键.
该表包含消费者customer_id产生的订单.
不会有商品被相同的用户在一天内下单<strong>超过一次</strong>.</pre>

<p>&nbsp;</p>

<p>表: <code>Products</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| product_name  | varchar |
| price         | int     |
+---------------+---------+
product_id 是该表主键.
该表包含所有商品的信息.
</pre>

<p>&nbsp;</p>

<p>写一个解决方案,&nbsp;找到每件商品的最新订单(可能有多个).</p>

<p>返回的结果以&nbsp;<code>product_name</code> <strong>升序排列</strong>,&nbsp;如果有排序相同,&nbsp;再以&nbsp;<code>product_id</code> <strong>升序</strong>排列.&nbsp;如果还有排序相同,&nbsp;再以&nbsp;<code>order_id</code> <strong>升序</strong>排列.</p>

<p>查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong>
<code>Customers表：</code>
+-------------+-----------+
| customer_id | name      |
+-------------+-----------+
| 1           | Winston   |
| 2           | Jonathan  |
| 3           | Annabelle |
| 4           | Marwan    |
| 5           | Khaled    |
+-------------+-----------+
<code>Orders表：</code>
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
<code>Products表：</code>
+------------+--------------+-------+
| product_id | product_name | price |
+------------+--------------+-------+
| 1          | keyboard     | 120   |
| 2          | mouse        | 80    |
| 3          | screen       | 600   |
| 4          | hard disk    | 450   |
+------------+--------------+-------+
<strong>输出：</strong>
+--------------+------------+----------+------------+
| product_name | product_id | order_id | order_date |
+--------------+------------+----------+------------+
| keyboard     | 1          | 6        | 2020-08-01 |
| keyboard     | 1          | 7        | 2020-08-01 |
| mouse        | 2          | 8        | 2020-08-03 |
| screen       | 3          | 3        | 2020-08-29 |
+--------------+------------+----------+------------+
<strong>解释：</strong>
keyboard 的最新订单在2020-08-01, 在这天有两次下单.
mouse 的最新订单在2020-08-03, 在这天只有一次下单.
screen 的最新订单在2020-08-29, 在这天只有一次下单.
hard disk 没有被下单, 我们不把它包含在结果表中.</pre>

## 解法

### 方法一：等值连接 + 窗口函数

我们可以使用等值连接，将 `Orders` 表和 `Products` 表按照 `product_id` 连接起来，然后使用窗口函数 `rank()`，对 `Orders` 表中的每个 `product_id` 进行分组，按照 `order_date` 降序排列，然后取出每个分组中排名第一的记录。

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
