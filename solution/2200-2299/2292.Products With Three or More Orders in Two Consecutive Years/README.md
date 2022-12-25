# [2292. 连续两年有 3 个及以上订单的产品](https://leetcode.cn/problems/products-with-three-or-more-orders-in-two-consecutive-years)

[English Version](/solution/2200-2299/2292.Products%20With%20Three%20or%20More%20Orders%20in%20Two%20Consecutive%20Years/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Orders</code></p>

<pre>
+---------------+------+
| Column Name   | Type |
+---------------+------+
| order_id      | int  |
| product_id    | int  |
| quantity      | int  |
| purchase_date | date |
+---------------+------+
order_id 是该表的主键。
该表中的每一行都包含订单 ID、购买的产品 ID、数量和购买日期。
</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询，获取连续两年订购三次或三次以上的所有产品的 id。</p>

<p data-group="1-1">以&nbsp;<strong>任意顺序&nbsp;</strong>返回结果表。</p>

<p>查询结果格式示例如下。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Orders 表:
+----------+------------+----------+---------------+
| order_id | product_id | quantity | purchase_date |
+----------+------------+----------+---------------+
| 1        | 1          | 7        | 2020-03-16    |
| 2        | 1          | 4        | 2020-12-02    |
| 3        | 1          | 7        | 2020-05-10    |
| 4        | 1          | 6        | 2021-12-23    |
| 5        | 1          | 5        | 2021-05-21    |
| 6        | 1          | 6        | 2021-10-11    |
| 7        | 2          | 6        | 2022-10-11    |
+----------+------------+----------+---------------+
<strong>输出:</strong> 
+------------+
| product_id |
+------------+
| 1          |
+------------+
<strong>解释:</strong> 
产品 1 在 2020 年和 2021 年都分别订购了三次。由于连续两年订购了三次，所以我们将其包含在答案中。
产品 2 在 2022 年订购了一次。我们不把它包括在答案中。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
