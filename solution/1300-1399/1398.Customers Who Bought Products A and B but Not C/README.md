# [1398. 购买了产品 A 和产品 B 却没有购买产品 C 的顾客 🔒](https://leetcode.cn/problems/customers-who-bought-products-a-and-b-but-not-c)

[English Version](/solution/1300-1399/1398.Customers%20Who%20Bought%20Products%20A%20and%20B%20but%20Not%20C/README_EN.md)

<!-- tags:数据库 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>&nbsp;<code>Customers</code>&nbsp;表：</p>

<pre>
+---------------------+---------+
| Column Name         | Type    |
+---------------------+---------+
| customer_id         | int     |
| customer_name       | varchar |
+---------------------+---------+
customer_id 是这张表中具有唯一值的列。
customer_name 是顾客的名称。</pre>

<p>&nbsp;</p>

<p><code>Orders</code>&nbsp;表：</p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| order_id      | int     |
| customer_id   | int     |
| product_name  | varchar |
+---------------+---------+
order_id 是这张表中具有唯一值的列。
customer_id 是购买了名为 "product_name" 产品顾客的id。
</pre>

<p>&nbsp;</p>

<p>请你编写解决方案，报告购买了产品 <strong>"A"</strong>，<strong>"B"</strong> 但没有购买产品 <strong>"C"</strong> 的客户的 customer_id 和 customer_name，因为我们想推荐他们购买这样的产品。</p>

<p>返回按 <code>customer_id</code> <strong>排序</strong> 的结果表。</p>

<p>返回结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Customers table:
+-------------+---------------+
| customer_id | customer_name |
+-------------+---------------+
| 1           | Daniel        |
| 2           | Diana         |
| 3           | Elizabeth     |
| 4           | Jhon          |
+-------------+---------------+

Orders table:
+------------+--------------+---------------+
| order_id   | customer_id  | product_name  |
+------------+--------------+---------------+
| 10         |     1        |     A         |
| 20         |     1        |     B         |
| 30         |     1        |     D         |
| 40         |     1        |     C         |
| 50         |     2        |     A         |
| 60         |     3        |     A         |
| 70         |     3        |     B         |
| 80         |     3        |     D         |
| 90         |     4        |     C         |
+------------+--------------+---------------+
<strong>输出：</strong>
+-------------+---------------+
| customer_id | customer_name |
+-------------+---------------+
| 3           | Elizabeth     |
+-------------+---------------+
<strong>解释：</strong>
只有 customer_id 为 3 的顾客购买了产品 A 和产品 B ，却没有购买产品 C 。</pre>

## 解法

### 方法一：LEFT JOIN + GROUP BY + HAVING

我们可以用 `LEFT JOIN` 将 `Customers` 表和 `Orders` 表连接起来，然后按照 `customer_id` 进行分组，最后筛选出购买了产品 A 和产品 B 却没有购买产品 C 的顾客 🔒。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT customer_id, customer_name
FROM
    Customers
    LEFT JOIN Orders USING (customer_id)
GROUP BY 1
HAVING SUM(product_name = 'A') > 0 AND SUM(product_name = 'B') > 0 AND SUM(product_name = 'C') = 0
ORDER BY 1;
```

<!-- tabs:end -->

<!-- end -->
