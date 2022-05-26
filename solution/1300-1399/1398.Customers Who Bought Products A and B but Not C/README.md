# [1398. 购买了产品 A 和产品 B 却没有购买产品 C 的顾客](https://leetcode.cn/problems/customers-who-bought-products-a-and-b-but-not-c)

[English Version](/solution/1300-1399/1398.Customers%20Who%20Bought%20Products%20A%20and%20B%20but%20Not%20C/README_EN.md)

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
customer_id 是这张表的主键。
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
order_id 是这张表的主键。
customer_id 是购买了名为 &quot;product_name&quot; 产品顾客的id。
</pre>

<p>&nbsp;</p>

<p>请你设计 SQL 查询来报告购买了产品 A 和产品 B 却没有购买产品 C 的顾客的 ID 和姓名（ <code>customer_id</code> 和&nbsp;<code>customer_name</code> ），我们将基于此结果为他们推荐产品 C 。<br />
您返回的查询结果需要按照&nbsp;<code>customer_id</code> <strong>排序</strong>。</p>

<p>&nbsp;</p>

<p>查询结果如下例所示。</p>

<pre>
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

Result table:
+-------------+---------------+
| customer_id | customer_name |
+-------------+---------------+
| 3           | Elizabeth     |
+-------------+---------------+
只有 customer_id 为 3 的顾客购买了产品 A 和产品 B ，却没有购买产品 C 。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
