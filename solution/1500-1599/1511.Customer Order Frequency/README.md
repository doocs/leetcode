# [1511. 消费者下单频率](https://leetcode.cn/problems/customer-order-frequency)

[English Version](/solution/1500-1599/1511.Customer%20Order%20Frequency/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Customers</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| customer_id   | int     |
| name          | varchar |
| country       | varchar |
+---------------+---------+
customer_id 是该表主键.
该表包含公司消费者的信息.
</pre>

<p>&nbsp;</p>

<p>表: <code>Product</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| description   | varchar |
| price         | int     |
+---------------+---------+
product_id 是该表主键.
该表包含公司产品的信息.
price 是本产品的花销.</pre>

<p>&nbsp;</p>

<p>表: <code>Orders</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| order_id      | int     |
| customer_id   | int     |
| product_id    | int     |
| order_date    | date    |
| quantity      | int     |
+---------------+---------+
order_id 是该表主键.
该表包含消费者下单的信息.
customer_id 是买了数量为"quantity", id为"product_id"产品的消费者的 id.
Order_date 是订单发货的日期, 格式为('YYYY-MM-DD').</pre>

<p>&nbsp;</p>

<p>写一个 SQL 查询，报告在&nbsp;<strong>2020 年 6 月和 7 月&nbsp;</strong>每个月至少花费 $100 的客户的 customer_id 和 customer_name 。</p>

<p>以<strong>任意顺序</strong>返回结果表.</p>

<p>查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<code><strong>输入：</strong>
Customers table:</code>
+--------------+-----------+-------------+
| customer_id  | name &nbsp;    | country &nbsp; &nbsp; |
+--------------+-----------+-------------+
| 1    &nbsp;       | Winston  &nbsp;| USA        &nbsp;|
| 2          &nbsp; | Jonathan  | Peru       &nbsp;|
| 3          &nbsp; | Moustafa &nbsp;| Egypt      &nbsp;|
+--------------+-----------+-------------+

<code>Product table:</code>
+--------------+-------------+-------------+
| product_id   | description | price   &nbsp; &nbsp; |
+--------------+-------------+-------------+
| 10   &nbsp;       | LC Phone &nbsp;  | 300        &nbsp;|
| 20         &nbsp; | LC T-Shirt  | 10         &nbsp;|
| 30         &nbsp; | LC Book    &nbsp;| 45         &nbsp;|
| 40           | LC Keychain&nbsp;| 2         &nbsp; |
+--------------+-------------+-------------+

<code>Orders table:</code>
+--------------+-------------+-------------+-------------+-----------+
| order_id     | customer_id | product_id  | order_date  | quantity  |
+--------------+-------------+-------------+-------------+-----------+
| 1    &nbsp;       | 1        &nbsp;  | 10         &nbsp;| 2020-06-10  | 1         |
| 2          &nbsp; | 1           | 20         &nbsp;| 2020-07-01  | 1         |
| 3          &nbsp; | 1           | 30         &nbsp;| 2020-07-08  | 2         |
| 4    &nbsp;       | 2        &nbsp;  | 10         &nbsp;| 2020-06-15  | 2         |
| 5          &nbsp; | 2           | 40         &nbsp;| 2020-07-01  | 10        |
| 6          &nbsp; | 3           | 20         &nbsp;| 2020-06-24  | 2         |
| 7    &nbsp;       | 3        &nbsp;  | 30         &nbsp;| 2020-06-25  | 2         |
| 9          &nbsp; | 3           | 30         &nbsp;| 2020-05-08  | 3         |
+--------------+-------------+-------------+-------------+-----------+

<strong>输出：</strong>
+--------------+------------+
| customer_id  | name       |  
+--------------+------------+
| 1            | Winston    |
+--------------+------------+ 
解释：
Winston 在2020年6月花费了$300(300 * 1), 在7月花费了$100(10 * 1 + 45 * 2).
Jonathan 在2020年6月花费了$600(300 * 2), 在7月花费了$20(2 * 10).
Moustafa 在2020年6月花费了$110 (10 * 2 + 45 * 2), 在7月花费了$0.</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    c.customer_id AS CUSTOMER_ID,
    c.name AS NAME
FROM
    Customers c, Product p, Orders o
WHERE
    c.customer_id = o.customer_id
AND p.product_id = o.product_id
GROUP BY c.customer_id
HAVING sum(if(month(o.order_date)=6, price*quantity, 0)) >= 100
AND sum(if(month(o.order_date)=7, price*quantity, 0)) >= 100;
```

<!-- tabs:end -->
