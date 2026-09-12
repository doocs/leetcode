---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1777.Product%27s%20Price%20for%20Each%20Store/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1777. 每家商店的产品价格 🔒](https://leetcode.cn/problems/products-price-for-each-store)

[English Version](/solution/1700-1799/1777.Product%27s%20Price%20for%20Each%20Store/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Products</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| product_id  | int     |
| store       | enum    |
| price       | int     |
+-------------+---------+
在 SQL 中，(product_id,store) 是这个表的主键。
store 字段是枚举类型，它的取值为以下三种 ('store1', 'store2', 'store3') 。
price 是该商品在这家商店中的价格。</pre>

<p>&nbsp;</p>

<p>找出每种产品在各个商店中的价格。</p>

<p>可以以 <strong>任何顺序</strong> 输出结果。</p>

<p>返回结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Products 表：
+-------------+--------+-------+
| product_id  | store  | price |
+-------------+--------+-------+
| 0           | store1 | 95    |
| 0           | store3 | 105   |
| 0           | store2 | 100   |
| 1           | store1 | 70    |
| 1           | store3 | 80    |
+-------------+--------+-------+
<strong>输出：</strong>
+-------------+--------+--------+--------+
| product_id  | store1 | store2 | store3 |
+-------------+--------+--------+--------+
| 0           | 95     | 100    | 105    |
| 1           | 70     | null   | 80     |
+-------------+--------+--------+--------+
<strong>解释：</strong>
产品 0 的价格在商店 1 为 95 ，商店 2 为 100 ，商店 3 为 105 。
产品 1 的价格在商店 1 为 70 ，商店 3 的产品 1 价格为 80 ，但在商店 2 中没有销售。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 行存「商品、店铺、价格」，要转成每个商品一行、三家店各一列。缺货店铺应为空。
>
> 按 $\textit{product\_id}$ 分组，用条件求和把三家店的价格摊到三列；$\mathrm{IF}$ 不匹配则贡献 $\mathrm{NULL}$。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    product_id,
    SUM(IF(store = 'store1', price, NULL)) AS store1,
    SUM(IF(store = 'store2', price, NULL)) AS store2,
    SUM(IF(store = 'store3', price, NULL)) AS store3
FROM Products
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
