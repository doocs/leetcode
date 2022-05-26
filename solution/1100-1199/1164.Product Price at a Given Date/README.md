# [1164. 指定日期的产品价格](https://leetcode.cn/problems/product-price-at-a-given-date)

[English Version](/solution/1100-1199/1164.Product%20Price%20at%20a%20Given%20Date/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>产品数据表: <code>Products</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| new_price     | int     |
| change_date   | date    |
+---------------+---------+
这张表的主键是 (product_id, change_date)。
这张表的每一行分别记录了 某产品 在某个日期 更改后 的新价格。</pre>

<p>&nbsp;</p>

<p>写一段 SQL来查找在&nbsp;<code>2019-08-16</code><strong> </strong>时全部产品的价格，假设所有产品在修改前的价格都是&nbsp;<code>10</code><strong> 。</strong></p>

<p>以 <strong>任意顺序 </strong>返回结果表。</p>

<p>查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<code><strong>输入：</strong>
Products</code> 表:
+------------+-----------+-------------+
| product_id | new_price | change_date |
+------------+-----------+-------------+
| 1          | 20        | 2019-08-14  |
| 2          | 50        | 2019-08-14  |
| 1          | 30        | 2019-08-15  |
| 1          | 35        | 2019-08-16  |
| 2          | 65        | 2019-08-17  |
| 3          | 20        | 2019-08-18  |
+------------+-----------+-------------+
<strong>输出：</strong>
+------------+-------+
| product_id | price |
+------------+-------+
| 2          | 50    |
| 1          | 35    |
| 3          | 10    |
+------------+-------+</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
