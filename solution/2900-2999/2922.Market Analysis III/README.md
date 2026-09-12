---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2922.Market%20Analysis%20III/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2922. 市场分析 III 🔒](https://leetcode.cn/problems/market-analysis-iii)

[English Version](/solution/2900-2999/2922.Market%20Analysis%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：&nbsp;<code>Users</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| seller_id      | int     |
| join_date      | date    |
| favorite_brand | varchar |
+----------------+---------+
seller_id 是该表具有唯一值的列。
该表包含卖家的 ID, 加入日期以及最喜欢的品牌。
</pre>

<p>表：&nbsp;<code>Items</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| item_id       | int     |
| item_brand    | varchar |
+---------------+---------+
item_id 是该表具有唯一值的列。
该表包含商品 ID 和商品品牌。</pre>

<p>表：&nbsp;<code>Orders</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| order_id      | int     |
| order_date    | date    |
| item_id       | int     |
| seller_id     | int     |
+---------------+---------+
order_id 是该表具有唯一值的列。
item_id 是指向 Items 表的外键。
seller_id 是指向 Users 表的外键。
该表包含订单 ID、下单日期、商品 ID 和卖家 ID。
</pre>

<p>编写一个解决方案，找到卖出数量 <strong>最多&nbsp;</strong>的&nbsp;<strong>非</strong> 最喜欢的品牌的 <strong>不同</strong> 商品的顶级卖家。如果有多个卖家有相同的最高卖出数量，返回所有这些卖家。</p>

<p>返回按&nbsp;<code>seller_id</code><em>&nbsp;<strong>升序排序</strong>&nbsp;的结果表。</em></p>

<p>结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<b>输入：</b>
Users table:
+-----------+------------+----------------+
| seller_id | join_date  | favorite_brand |
+-----------+------------+----------------+
| 1         | 2019-01-01 | Lenovo         |
| 2         | 2019-02-09 | Samsung        |
| 3         | 2019-01-19 | LG             |
+-----------+------------+----------------+
Orders table:
+----------+------------+---------+-----------+
| order_id | order_date | item_id | seller_id |
+----------+------------+---------+-----------+
| 1        | 2019-08-01 | 4       | 2         |
| 2        | 2019-08-02 | 2       | 3         |
| 3        | 2019-08-03 | 3       | 3         |
| 4        | 2019-08-04 | 1       | 2         |
| 5        | 2019-08-04 | 4       | 2         |
+----------+------------+---------+-----------+
Items table:
+---------+------------+
| item_id | item_brand |
+---------+------------+
| 1       | Samsung    |
| 2       | Lenovo     |
| 3       | LG         |
| 4       | HP         |
+---------+------------+
<b>输出：</b>
+-----------+-----------+
| seller_id | num_items |
+-----------+-----------+
| 2         | 1         |
| 3         | 1         |
+-----------+-----------+
<b>解释：</b>
- 卖家 ID 为 2 的用户销售了三件商品，但只有两件不是他最喜欢的商品。由于这两个商品相同，所以我们只计数 1。 
- 卖家 ID 为 3 的用户销售了两件商品，但只有一件不是他最喜欢的商品。我们将只把非最喜欢商品列入计数中。
因为卖家 ID 为 2 和 3 的卖家都有一件商品列入计数，所以他们都将显示在输出中。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：等值连接 + 分组 + 子查询

<!-- thinking:start -->

> **思考**
>
> 要统计每位卖家卖出的、品牌异于其偏好的不同商品数，再取出该数目的最大值对应的卖家。三次等值连接把订单、用户与商品对齐，用 $item_brand \neq favorite_brand$ 过滤后按 $seller_id$ 分组。
>
> $COUNT(DISTINCT item_id)$ 得到 $num_items$。外层再与子查询 $MAX(num_items)$ 比较，避免在同一层聚合中既求最大又筛行。最后按卖家编号排序。

<!-- thinking:end -->

我们可以使用等值连接，将 `Orders` 表和 `Users` 表按照 `seller_id` 进行连接，接着再按照 `item_id` 连接 `Items`，筛选出 `item_brand` 不等于 `favorite_brand` 的记录，然后按照 `seller_id` 进行分组，统计每个 `seller_id` 对应的 `item_id` 的个数，最后再使用子查询，找出 `item_id` 个数最多的 `seller_id`。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT seller_id, COUNT(DISTINCT item_id) AS num_items
        FROM
            Orders
            JOIN Users USING (seller_id)
            JOIN Items USING (item_id)
        WHERE item_brand != favorite_brand
        GROUP BY 1
    )
SELECT seller_id, num_items
FROM T
WHERE num_items = (SELECT MAX(num_items) FROM T)
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
