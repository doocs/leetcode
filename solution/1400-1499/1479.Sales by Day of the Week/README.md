---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1479.Sales%20by%20Day%20of%20the%20Week/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1479. 周内每天的销售情况 🔒](https://leetcode.cn/problems/sales-by-day-of-the-week)

[English Version](/solution/1400-1499/1479.Sales%20by%20Day%20of%20the%20Week/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Orders</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| order_id      | int     |
| customer_id   | int     |
| order_date    | date    | 
| item_id       | varchar |
| quantity      | int     |
+---------------+---------+
(order_id, item_id) 是该表主键(具有唯一值的列的组合)
该表包含了订单信息
order_date 是id为 item_id 的商品被id为 customer_id 的消费者订购的日期.</pre>

<p>表：<code>Items</code></p>

<pre>
+---------------------+---------+
| Column Name         | Type    |
+---------------------+---------+
| item_id             | varchar |
| item_name           | varchar |
| item_category       | varchar |
+---------------------+---------+
item_id 是该表主键(具有唯一值的列)
item_name 是商品的名字
item_category&nbsp;是商品的类别
</pre>

<p>&nbsp;</p>

<p>你是企业主，想要获得分类商品和周内每天的销售报告。</p>

<p>编写解决方案，报告 <strong>周内每天 </strong>每个商品类别下订购了多少单位。</p>

<p>返回结果表单<strong> 按商品类别排序 </strong>。</p>

<p>结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<code><strong>输入：</strong>
Orders</code> 表：
+------------+--------------+-------------+--------------+-------------+
| order_id   | customer_id  | order_date  | item_id      | quantity    |
+------------+--------------+-------------+--------------+-------------+
| 1          | 1            | 2020-06-01  | 1            | 10          |
| 2          | 1            | 2020-06-08  | 2            | 10          |
| 3          | 2            | 2020-06-02  | 1            | 5           |
| 4          | 3            | 2020-06-03  | 3            | 5           |
| 5          | 4            | 2020-06-04  | 4            | 1           |
| 6          | 4            | 2020-06-05  | 5            | 5           |
| 7          | 5            | 2020-06-05  | 1            | 10          |
| 8          | 5            | 2020-06-14  | 4            | 5           |
| 9          | 5            | 2020-06-21  | 3            | 5           |
+------------+--------------+-------------+--------------+-------------+

<code>Items</code> 表：
+------------+----------------+---------------+
| item_id    | item_name      | item_category |
+------------+----------------+---------------+
| 1          | LC Alg. Book   | Book          |
| 2          | LC DB. Book    | Book          |
| 3          | LC SmarthPhone | Phone         |
| 4          | LC Phone 2020  | Phone         |
| 5          | LC SmartGlass  | Glasses       |
| 6          | LC T-Shirt XL  | T-Shirt       |
+------------+----------------+---------------+
<strong>输出：</strong>
+------------+-----------+-----------+-----------+-----------+-----------+-----------+-----------+
| Category   | Monday    | Tuesday   | Wednesday | Thursday  | Friday    | Saturday  | Sunday    |
+------------+-----------+-----------+-----------+-----------+-----------+-----------+-----------+
| Book       | 20        | 5         | 0         | 0         | 10        | 0         | 0         |
| Glasses    | 0         | 0         | 0         | 0         | 5         | 0         | 0         |
| Phone      | 0         | 0         | 5         | 1         | 0         | 0         | 10        |
| T-Shirt    | 0         | 0         | 0         | 0         | 0         | 0         | 0         |
+------------+-----------+-----------+-----------+-----------+-----------+-----------+-----------+
<strong>解释：</strong>
在周一(2020-06-01, 2020-06-08)，Book分类(ids: 1, 2)下，总共销售了20个单位(10 + 10)
在周二(2020-06-02)，Book分类(ids: 1, 2)下，总共销售了5个单位
在周三(2020-06-03)，Phone分类(ids: 3, 4)下，总共销售了5个单位
在周四(2020-06-04)，Phone分类(ids: 3, 4)下，总共销售了1个单位
在周五(2020-06-05)，Book分类(ids: 1, 2)下，总共销售了10个单位，Glasses分类(ids: 5)下，总共销售了5个单位
在周六, 没有商品销售
在周天(2020-06-14, 2020-06-21)，Phone分类(ids: 3, 4)下，总共销售了10个单位(5 + 5)
没有销售 T-Shirt 类别的商品</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    item_category AS category,
    SUM(IF(DAYOFWEEK(order_date) = '2', quantity, 0)) AS Monday,
    SUM(IF(DAYOFWEEK(order_date) = '3', quantity, 0)) AS Tuesday,
    SUM(IF(DAYOFWEEK(order_date) = '4', quantity, 0)) AS Wednesday,
    SUM(IF(DAYOFWEEK(order_date) = '5', quantity, 0)) AS Thursday,
    SUM(IF(DAYOFWEEK(order_date) = '6', quantity, 0)) AS Friday,
    SUM(IF(DAYOFWEEK(order_date) = '7', quantity, 0)) AS Saturday,
    SUM(IF(DAYOFWEEK(order_date) = '1', quantity, 0)) AS Sunday
FROM
    Orders AS o
    RIGHT JOIN Items AS i ON o.item_id = i.item_id
GROUP BY category
ORDER BY category;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
