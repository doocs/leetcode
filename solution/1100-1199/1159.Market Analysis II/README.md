# [1159. 市场分析 II](https://leetcode.cn/problems/market-analysis-ii)

[English Version](/solution/1100-1199/1159.Market%20Analysis%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Users</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| user_id        | int     |
| join_date      | date    |
| favorite_brand | varchar |
+----------------+---------+
user_id 是该表的主键(具有唯一值的列)。
表中包含一位在线购物网站用户的个人信息，用户可以在该网站出售和购买商品。
</pre>

<p>表: <code>Orders</code></p>

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
order_id 是该表的主键(具有唯一值的列)。
item_id 是 Items 表的外键(reference 列)。
buyer_id 和 seller_id 是 Users 表的外键。
</pre>

<p>表: <code>Items</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| item_id       | int     |
| item_brand    | varchar |
+---------------+---------+
item_id 是该表的主键(具有唯一值的列)。
</pre>

<p>&nbsp;</p>

<p>编写一个解决方案，查找每个用户的加入日期和他们作为买家在 <code>2019</code> 年下的订单数。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>返回结果格式如下例所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Users table:
+---------+------------+----------------+
| user_id | join_date  | favorite_brand |
+---------+------------+----------------+
| 1       | 2019-01-01 | Lenovo         |
| 2       | 2019-02-09 | Samsung        |
| 3       | 2019-01-19 | LG             |
| 4       | 2019-05-21 | HP             |
+---------+------------+----------------+
Orders table:
+----------+------------+---------+----------+-----------+
| order_id | order_date | item_id | buyer_id | seller_id |
+----------+------------+---------+----------+-----------+
| 1        | 2019-08-01 | 4       | 1        | 2         |
| 2        | 2019-08-02 | 2       | 1        | 3         |
| 3        | 2019-08-03 | 3       | 2        | 3         |
| 4        | 2019-08-04 | 1       | 4        | 2         |
| 5        | 2019-08-04 | 1       | 3        | 4         |
| 6        | 2019-08-05 | 2       | 2        | 4         |
+----------+------------+---------+----------+-----------+
Items table:
+---------+------------+
| item_id | item_brand |
+---------+------------+
| 1       | Samsung    |
| 2       | Lenovo     |
| 3       | LG         |
| 4       | HP         |
+---------+------------+
<strong>输出：</strong>
+-----------+--------------------+
| seller_id | 2nd_item_fav_brand |
+-----------+--------------------+
| 1         | no                 |
| 2         | yes                |
| 3         | yes                |
| 4         | no                 |
+-----------+--------------------+
<strong>解释：</strong>
id 为 1 的用户的查询结果是 no，因为他什么也没有卖出
id为 2 和 3 的用户的查询结果是 yes，因为他们卖出的第二件商品的品牌是他们最喜爱的品牌
id为 4 的用户的查询结果是 no，因为他卖出的第二件商品的品牌不是他最喜爱的品牌
</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    u.user_id AS seller_id,
    CASE
        WHEN u.favorite_brand = i.item_brand THEN 'yes'
        ELSE 'no'
    END AS 2nd_item_fav_brand
FROM
    users AS u
    LEFT JOIN (
        SELECT
            order_date,
            item_id,
            seller_id,
            RANK() OVER (
                PARTITION BY seller_id
                ORDER BY order_date
            ) AS rk
        FROM orders
    ) AS o
        ON u.user_id = o.seller_id AND o.rk = 2
    LEFT JOIN items AS i ON o.item_id = i.item_id;
```

<!-- tabs:end -->

<!-- end -->
