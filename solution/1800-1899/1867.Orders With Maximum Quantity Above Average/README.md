---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1867.Orders%20With%20Maximum%20Quantity%20Above%20Average/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1867. 最大数量高于平均水平的订单 🔒](https://leetcode.cn/problems/orders-with-maximum-quantity-above-average)

[English Version](/solution/1800-1899/1867.Orders%20With%20Maximum%20Quantity%20Above%20Average/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>OrdersDetails</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| order_id    | int  |
| product_id  | int  |
| quantity    | int  |
+-------------+------+
(order_id, product_id) 是该表的主键（由多个列组合而成的唯一值）。
一个订单由多行表示，每行对应订单中的一个产品。
该表中的每一行包含订单 order_id 中产品 product_id 的订购数量 quantity。
</pre>

<p>&nbsp;</p>

<p>你正在运营一个电子商务网站，目前正在寻找&nbsp;<strong>不平衡订单</strong>。 <strong>不平衡订单&nbsp;</strong>是指这样一个订单：其&nbsp;<strong>最大&nbsp;</strong>商品数量&nbsp;<strong>严格大于&nbsp;</strong>所有订单（包括该订单自身）的&nbsp;<strong>平均&nbsp;</strong>商品数量。</p>

<p>一个订单的&nbsp;<strong>平均&nbsp;</strong>商品数量计算方式为：<code>（该订单所有商品的总数量）/（该订单中不同商品的数量）</code>。 一个订单的&nbsp;<strong>最大&nbsp;</strong>商品数量是该订单中任意单个商品的 <code>quantity</code> 中的最大值。</p>

<p>编写一个解决方案，找出所有&nbsp;<strong>不平衡订单&nbsp;</strong>的 <code>order_id</code>。</p>

<p>按 <b>任何顺序</b>&nbsp;返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：
</strong>
OrdersDetails 表：
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

<strong>输出：
</strong>
+----------+
| order_id |
+----------+
| 1        |
| 3        |
+----------+

<strong>解释：
</strong>
每个订单的平均商品数量为：
- order_id=1：(12+10+15)/3 = 12.3333333
- order_id=2：(8+4+6+4)/4 = 5.5
- order_id=3：(5+18+20)/3 = 14.333333
- order_id=4：(2+8)/2 = 5
- order_id=5：(9+9)/2 = 9

每个订单的最大商品数量为：
- order_id=1：max(12, 10, 15) = 15
- order_id=2：max(8, 4, 6, 4) = 8
- order_id=3：max(5, 18, 20) = 20
- order_id=4：max(2, 8) = 8
- order_id=5：max(9, 9) = 9

订单 1 和 3 是不平衡订单，因为它们的最大商品数量大于所有订单的平均商品数量。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            order_id,
            MAX(quantity) AS max_quantity,
            SUM(quantity) / COUNT(1) AS avg_quantity
        FROM OrdersDetails
        GROUP BY order_id
    )
SELECT order_id
FROM t
WHERE max_quantity > (SELECT MAX(avg_quantity) FROM t);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
