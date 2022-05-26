# [1158. 市场分析 I](https://leetcode.cn/problems/market-analysis-i)

[English Version](/solution/1100-1199/1158.Market%20Analysis%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Users</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| user_id        | int     |
| join_date      | date    |
| favorite_brand | varchar |
+----------------+---------+
此表主键是 user_id。
表中描述了购物网站的用户信息，用户可以在此网站上进行商品买卖。
</pre>

<p>&nbsp;</p>

<p>Table: <code>Orders</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| order_id      | int     |
| order_date    | date    |
| item_id       | int     |
| buyer_id      | int     |
| seller_id     | int     |
+---------------+---------+
此表主键是 order_id。
外键是 item_id 和（buyer_id，seller_id）。
</pre>

<p>&nbsp;</p>

<p>Table: <code>Items</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| item_id       | int     |
| item_brand    | varchar |
+---------------+---------+
此表主键是 item_id。
</pre>

<p>&nbsp;</p>

<p>请写出一条SQL语句以查询每个用户的注册日期和在 <strong><code>2019</code> </strong>年作为买家的订单总数。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>查询结果格式如下。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Users 表:
+---------+------------+----------------+
| user_id | join_date  | favorite_brand |
+---------+------------+----------------+
| 1       | 2018-01-01 | Lenovo         |
| 2       | 2018-02-09 | Samsung        |
| 3       | 2018-01-19 | LG             |
| 4       | 2018-05-21 | HP             |
+---------+------------+----------------+
Orders 表:
+----------+------------+---------+----------+-----------+
| order_id | order_date | item_id | buyer_id | seller_id |
+----------+------------+---------+----------+-----------+
| 1        | 2019-08-01 | 4       | 1        | 2         |
| 2        | 2018-08-02 | 2       | 1        | 3         |
| 3        | 2019-08-03 | 3       | 2        | 3         |
| 4        | 2018-08-04 | 1       | 4        | 2         |
| 5        | 2018-08-04 | 1       | 3        | 4         |
| 6        | 2019-08-05 | 2       | 2        | 4         |
+----------+------------+---------+----------+-----------+
Items 表:
+---------+------------+
| item_id | item_brand |
+---------+------------+
| 1       | Samsung    |
| 2       | Lenovo     |
| 3       | LG         |
| 4       | HP         |
+---------+------------+
<strong>输出：</strong>
+-----------+------------+----------------+
| buyer_id  | join_date  | orders_in_2019 |
+-----------+------------+----------------+
| 1         | 2018-01-01 | 1              |
| 2         | 2018-02-09 | 2              |
| 3         | 2018-01-19 | 0              |
| 4         | 2018-05-21 | 0              |
+-----------+------------+----------------+</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT user_id AS buyer_id,
    join_date,
    COUNT(order_id) AS orders_in_2019
FROM users AS u
    LEFT JOIN orders AS o ON u.user_id = o.buyer_id
    AND YEAR(order_date) = 2019
GROUP BY user_id;
```

```sql
SELECT
    user_id AS buyer_id,
    join_date,
    (
        SELECT
            COUNT(*)
        FROM
            orders AS o
        WHERE
            u.user_id = o.buyer_id AND YEAR(order_date) = 2019
    ) AS orders_in_2019
FROM
    users AS u;
```

<!-- tabs:end -->
