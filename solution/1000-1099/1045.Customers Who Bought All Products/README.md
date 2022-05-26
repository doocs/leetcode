# [1045. 买下所有产品的客户](https://leetcode.cn/problems/customers-who-bought-all-products)

[English Version](/solution/1000-1099/1045.Customers%20Who%20Bought%20All%20Products/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>Customer</code>&nbsp;表：</p>

<pre>+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| customer_id | int     |
| product_key | int     |
+-------------+---------+
product_key 是 <code>Customer 表的外键</code>。
</pre>

<p><code>Product</code>&nbsp;表：</p>

<pre>+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| product_key | int     |
+-------------+---------+
product_key 是这张表的主键。
</pre>

<p>&nbsp;</p>

<p>写一条 SQL 查询语句，从 <code>Customer</code> 表中查询购买了 <code>Product</code> 表中所有产品的客户的 id。</p>

<p><strong>示例：</strong></p>

<pre>Customer 表：
+-------------+-------------+
| customer_id | product_key |
+-------------+-------------+
| 1           | 5           |
| 2           | 6           |
| 3           | 5           |
| 3           | 6           |
| 1           | 6           |
+-------------+-------------+

Product 表：
+-------------+
| product_key |
+-------------+
| 5           |
| 6           |
+-------------+

Result 表：
+-------------+
| customer_id |
+-------------+
| 1           |
| 3           |
+-------------+
购买了所有产品（5 和 6）的客户的 id 是 1 和 3 。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    customer_id
FROM
    Customer
GROUP BY
    customer_id
HAVING
    COUNT(DISTINCT(product_key)) = (
        SELECT
            COUNT(1)
        FROM
            Product
    );
```

<!-- tabs:end -->
