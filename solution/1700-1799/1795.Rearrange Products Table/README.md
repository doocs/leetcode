---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1795.Rearrange%20Products%20Table/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1795. 每个产品在不同商店的价格](https://leetcode.cn/problems/rearrange-products-table)

[English Version](/solution/1700-1799/1795.Rearrange%20Products%20Table/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Products</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| product_id  | int     |
| store1      | int     |
| store2      | int     |
| store3      | int     |
+-------------+---------+
在 SQL 中，这张表的主键是 product_id（产品Id）。
每行存储了这一产品在不同商店 store1, store2, store3 的价格。
如果这一产品在商店里没有出售，则值将为 null。
</pre>

<p>&nbsp;</p>

<p>请你重构 <code>Products</code> 表，查询每个产品在不同商店的价格，使得输出的格式变为<code>(product_id, store, price)</code> 。如果这一产品在商店里没有出售，则不输出这一行。</p>

<p>输出结果表中的 <strong>顺序不作要求</strong> 。</p>

<p>查询输出格式请参考下面示例。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Products table:
+------------+--------+--------+--------+
| product_id | store1 | store2 | store3 |
+------------+--------+--------+--------+
| 0          | 95     | 100    | 105    |
| 1          | 70     | null   | 80     |
+------------+--------+--------+--------+
<strong>输出：</strong>
+------------+--------+-------+
| product_id | store  | price |
+------------+--------+-------+
| 0          | store1 | 95    |
| 0          | store2 | 100   |
| 0          | store3 | 105   |
| 1          | store1 | 70    |
| 1          | store3 | 80    |
+------------+--------+-------+
<strong>解释：</strong>
产品 0 在 store1、store2、store3 的价格分别为 95、100、105。
产品 1 在 store1、store3 的价格分别为 70、80。在 store2 无法买到。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：合并

我们可以筛选出每个商店的产品和价格，然后使用 `UNION` 合并即可。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT product_id, 'store1' AS store, store1 AS price FROM Products WHERE store1 IS NOT NULL
UNION
SELECT product_id, 'store2' AS store, store2 AS price FROM Products WHERE store2 IS NOT NULL
UNION
SELECT product_id, 'store3' AS store, store3 AS price FROM Products WHERE store3 IS NOT NULL;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
