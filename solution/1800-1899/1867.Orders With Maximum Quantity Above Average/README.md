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

<p>&nbsp;<code>OrdersDetails</code> 表</p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| order_id    | int  |
| product_id  | int  |
| quantity    | int  |
+-------------+------+
(order_id, product_id) 是此表的主键。
单个订单表示为多行，订单中的每个产品对应一行。
此表的每一行都包含订单id中产品id的订购数量。
</pre>

<p>&nbsp;</p>

<p>您正在运行一个电子商务网站，该网站正在寻找不平衡的订单。不平衡订单的订单最大数量严格大于每个订单（包括订单本身）的平均数量。</p>

<p>订单的平均数量计算为（订单中所有产品的总数量）/（订单中不同产品的数量）。订单的最大数量是订单中任何单个产品的最高数量。</p>

<p>编写SQL查询以查找所有不平衡订单的订单id。</p>

<p>按任意顺序返回结果表。</p>

<p>查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre>
<strong>输入:</strong> 
OrdersDetails 表:
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
<strong>输出:</strong> 
+----------+
| order_id |
+----------+
| 1        |
| 3        |
+----------+
<strong>解释:</strong> 
每份订单的平均数量为:
- order_id=1: (12+10+15)/3 = 12.3333333
- order_id=2: (8+4+6+4)/4 = 5.5
- order_id=3: (5+18+20)/3 = 14.333333
- order_id=4: (2+8)/2 = 5
- order_id=5: (9+9)/2 = 9

每个订单的最大数量为:
- order_id=1: max(12, 10, 15) = 15
- order_id=2: max(8, 4, 6, 4) = 8
- order_id=3: max(5, 18, 20) = 20
- order_id=4: max(2, 8) = 8
- order_id=5: max(9, 9) = 9

订单1和订单3是不平衡的，因为它们的最大数量超过了它们订单的平均数量。
</pre>

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
