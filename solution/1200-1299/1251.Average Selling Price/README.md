---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1251.Average%20Selling%20Price/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1251. 平均售价](https://leetcode.cn/problems/average-selling-price)

[English Version](/solution/1200-1299/1251.Average%20Selling%20Price/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Prices</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| start_date    | date    |
| end_date      | date    |
| price         | int     |
+---------------+---------+
(product_id，start_date，end_date) 是 <code>prices</code> 表的主键（具有唯一值的列的组合）。
<code>prices</code> 表的每一行表示的是某个产品在一段时期内的价格。
每个产品的对应时间段是不会重叠的，这也意味着同一个产品的价格时段不会出现交叉。</pre>

<p>&nbsp;</p>

<p>表：<code>UnitsSold</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| purchase_date | date    |
| units         | int     |
+---------------+---------+
<span style="white-space: pre-wrap;">该表可能包含重复数据</span>。
<span style="white-space: pre-wrap;">该</span>表的每一行表示的是每种产品的出售日期，单位和产品 id。</pre>

<p>&nbsp;</p>

<p>编写解决方案以查找每种产品的平均售价。<code>average_price</code> 应该 <strong>四舍五入到小数点后两位</strong>。如果产品没有任何售出，则假设其平均售价为 0。</p>

<p>返回结果表 <strong>无顺序要求</strong> 。</p>

<p>结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
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
<strong>输出：</strong>
+------------+---------------+
| product_id | average_price |
+------------+---------------+
| 1          | 6.96          |
| 2          | 16.96         |
+------------+---------------+
<strong>解释：</strong>
平均售价 = 产品总价 / 销售的产品数量。
产品 1 的平均售价 = ((100 * 5)+(15 * 20) )/ 115 = 6.96
产品 2 的平均售价 = ((200 * 15)+(30 * 30) )/ 230 = 16.96</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：左连接 + 分组统计

我们可以使用左连接，将 `Prices` 表和 `UnitsSold` 表连接起来，连接条件为 `product_id` 相等，并且 `purchase_date` 在 `start_date` 和 `end_date` 之间。然后使用 `GROUP BY` 子句对 `product_id` 进行分组，使用 `AVG` 函数计算平均价格。注意，如果某个产品没有销售记录，那么 `AVG` 函数会返回 `NULL`，因此我们可以使用 `IFNULL` 函数将其转换为 $0$。

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
