---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1083.Sales%20Analysis%20II/README.md
tags:
    - 数据库
---

# [1083. 销售分析 II 🔒](https://leetcode.cn/problems/sales-analysis-ii)

[English Version](/solution/1000-1099/1083.Sales%20Analysis%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Product</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| product_id   | int     |
| product_name | varchar |
| unit_price   | int     |
+--------------+---------+
Product_id 是该表的主键(具有唯一值的列)。
该表的每一行表示每种产品的名称和价格。
</pre>

<p>表：<code>Sales</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| seller_id   | int     |
| product_id  | int     |
| buyer_id    | int     |
| sale_date   | date    |
| quantity    | int     |
| price       | int     |
+------ ------+---------+
这个表可能有重复的行。
product_id 是 Product 表的外键(reference 列)。
buyer_id 永远不会是 NULL。
sale_date 永远不会是 NULL。
该表的每一行都包含一次销售的一些信息。
</pre>

<p>&nbsp;</p>

<p>编写一个解决方案，报告那些买了 <em>S8</em> 而没有买 <em>iPhone</em> 的 <strong>买家</strong>。注意，<em>S8</em> 和 <em>iPhone</em> 是 <code>Product</code> 表中显示的产品。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Product table:
+------------+--------------+------------+
| product_id | product_name | unit_price |
+------------+--------------+------------+
| 1          | S8           | 1000       |
| 2          | G4           | 800        |
| 3          | iPhone       | 1400       |
+------------+--------------+------------+
<code>Sales </code>table:
+-----------+------------+----------+------------+----------+-------+
| seller_id | product_id | buyer_id | sale_date  | quantity | price |
+-----------+------------+----------+------------+----------+-------+
| 1         | 1          | 1        | 2019-01-21 | 2        | 2000  |
| 1         | 2          | 2        | 2019-02-17 | 1        | 800   |
| 2         | 1          | 3        | 2019-06-02 | 1        | 800   |
| 3         | 3          | 3        | 2019-05-13 | 2        | 2800  |
+-----------+------------+----------+------------+----------+-------+
<strong>输出：</strong>
+-------------+
| buyer_id    |
+-------------+
| 1           |
+-------------+
解释：
id 为 1 的买家购买了一部 S8，但是却没有购买 iPhone，而 id 为 3 的买家却同时购买了这 2 部手机。
</pre>

## 解法

### 方法一：JOIN + GROUP BY + HAVING

我们先将 `Sales` 表和 `Product` 表连接起来，然后根据 `buyer_id` 分组，最后用 `HAVING` 子句筛选出购买了 S8 却没有购买 iPhone 的买家。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT buyer_id
FROM
    Sales
    JOIN Product USING (product_id)
GROUP BY 1
HAVING SUM(product_name = 'S8') > 0 AND SUM(product_name = 'iPhone') = 0;
```

<!-- tabs:end -->

<!-- end -->
