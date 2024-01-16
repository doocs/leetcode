# [1164. 指定日期的产品价格](https://leetcode.cn/problems/product-price-at-a-given-date)

[English Version](/solution/1100-1199/1164.Product%20Price%20at%20a%20Given%20Date/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>产品数据表: <code>Products</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| new_price     | int     |
| change_date   | date    |
+---------------+---------+
(product_id, change_date) 是此表的主键（具有唯一值的列组合）。
这张表的每一行分别记录了 某产品 在某个日期 更改后 的新价格。</pre>

<p>&nbsp;</p>

<p>编写一个解决方案，找出在&nbsp;<code>2019-08-16</code><strong> </strong>时全部产品的价格，假设所有产品在修改前的价格都是&nbsp;<code>10</code><strong> 。</strong></p>

<p>以 <strong>任意顺序 </strong>返回结果表。</p>

<p>结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Products 表:
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
<strong>输出：</strong>
+------------+-------+
| product_id | price |
+------------+-------+
| 2          | 50    |
| 1          | 35    |
| 3          | 10    |
+------------+-------+</pre>

## 解法

### 方法一：子查询 + 连接

我们可以使用子查询，找出每个产品在给定日期之前最后一次价格变更的价格，记录在 `P` 表中。然后，我们再找出所有产品的 `product_id`，记录在 `T` 表中。最后，我们将 `T` 表和 `P` 表按照 `product_id` 进行左连接，即可得到最终结果。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (SELECT DISTINCT product_id FROM Products),
    P AS (
        SELECT product_id, new_price AS price
        FROM Products
        WHERE
            (product_id, change_date) IN (
                SELECT product_id, MAX(change_date) AS change_date
                FROM Products
                WHERE change_date <= '2019-08-16'
                GROUP BY 1
            )
    )
SELECT product_id, IFNULL(price, 10) AS price
FROM
    T
    LEFT JOIN P USING (product_id);
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

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
            RANK() OVER (
                PARTITION BY product_id
                ORDER BY change_date DESC
            ) AS rk
        FROM P
    )
SELECT product_id, IFNULL(new_price, 10) AS price
FROM T
WHERE rk = 1;
```

<!-- tabs:end -->

<!-- end -->
