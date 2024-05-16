---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1158.Market%20Analysis%20I/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1158. 市场分析 I](https://leetcode.cn/problems/market-analysis-i)

[English Version](/solution/1100-1199/1158.Market%20Analysis%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：&nbsp;<code>Users</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| user_id        | int     |
| join_date      | date    |
| favorite_brand | varchar |
+----------------+---------+
user_id 是此表主键（具有唯一值的列）。
表中描述了购物网站的用户信息，用户可以在此网站上进行商品买卖。
</pre>

<p>&nbsp;</p>

<p>表：&nbsp;<code>Orders</code></p>

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
order_id 是此表主键（具有唯一值的列）。
item_id 是 Items 表的外键（reference 列）。
（buyer_id，seller_id）是 User 表的外键。
</pre>

<p>&nbsp;</p>

<p>表：<code>Items</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| item_id       | int     |
| item_brand    | varchar |
+---------------+---------+
item_id 是此表的主键（具有唯一值的列）。
</pre>

<p>&nbsp;</p>

<p>编写解决方案找出每个用户的注册日期和在 <strong><code>2019</code> </strong>年作为买家的订单总数。</p>

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    u.user_id AS buyer_id,
    u.join_date,
    COUNT(order_id) AS orders_in_2019
FROM
    Users AS u
    LEFT JOIN Orders AS o ON u.user_id = o.buyer_id AND YEAR(order_date) = 2019
GROUP BY user_id;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    user_id AS buyer_id,
    join_date,
    IFNULL(SUM(YEAR(order_date) = 2019), 0) AS orders_in_2019
FROM
    Users AS u
    LEFT JOIN Orders AS o ON u.user_id = buyer_id
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
