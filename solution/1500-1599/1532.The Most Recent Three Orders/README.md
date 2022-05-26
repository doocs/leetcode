# [1532. 最近的三笔订单](https://leetcode.cn/problems/the-most-recent-three-orders)

[English Version](/solution/1500-1599/1532.The%20Most%20Recent%20Three%20Orders/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Customers</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| customer_id   | int     |
| name          | varchar |
+---------------+---------+
customer_id 是该表主键
该表包含消费者的信息
</pre>

<p> </p>

<p>表：<code>Orders</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| order_id      | int     |
| order_date    | date    |
| customer_id   | int     |
| cost          | int     |
+---------------+---------+
order_id 是该表主键
该表包含id为customer_id的消费者的订单信息
每一个消费者<strong> 每天一笔订单</strong>
</pre>

<p> </p>

<p>写一个 SQL 语句，找到每个用户的最近三笔订单。如果用户的订单少于 3 笔，则返回他的全部订单。</p>

<p>返回的结果按照 <code>customer_name</code> <strong>升序</strong>排列。如果排名有相同，则继续按照 <code>customer_id</code> <strong>升序</strong>排列。如果排名还有相同，则继续按照 <code>order_date</code> <strong>降序</strong>排列。</p>

<p>查询结果格式如下例所示：</p>

<pre>
<code>Customers</code>
+-------------+-----------+
| customer_id | name      |
+-------------+-----------+
| 1           | Winston   |
| 2           | Jonathan  |
| 3           | Annabelle |
| 4           | Marwan    |
| 5           | Khaled    |
+-------------+-----------+

<code>Orders</code>
+----------+------------+-------------+------+
| order_id | order_date | customer_id | cost |
+----------+------------+-------------+------+
| 1        | 2020-07-31 | 1           | 30   |
| 2        | 2020-07-30 | 2           | 40   |
| 3        | 2020-07-31 | 3           | 70   |
| 4        | 2020-07-29 | 4           | 100  |
| 5        | 2020-06-10 | 1           | 1010 |
| 6        | 2020-08-01 | 2           | 102  |
| 7        | 2020-08-01 | 3           | 111  |
| 8        | 2020-08-03 | 1           | 99   |
| 9        | 2020-08-07 | 2           | 32   |
| 10       | 2020-07-15 | 1           | 2    |
+----------+------------+-------------+------+

Result table：
+---------------+-------------+----------+------------+
| customer_name | customer_id | order_id | order_date |
+---------------+-------------+----------+------------+
| Annabelle     | 3           | 7        | 2020-08-01 |
| Annabelle     | 3           | 3        | 2020-07-31 |
| Jonathan      | 2           | 9        | 2020-08-07 |
| Jonathan      | 2           | 6        | 2020-08-01 |
| Jonathan      | 2           | 2        | 2020-07-30 |
| Marwan        | 4           | 4        | 2020-07-29 |
| Winston       | 1           | 8        | 2020-08-03 |
| Winston       | 1           | 1        | 2020-07-31 |
| Winston       | 1           | 10       | 2020-07-15 |
+---------------+-------------+----------+------------+
Winston 有 4 笔订单, 排除了 "2020-06-10" 的订单, 因为它是最老的订单。
Annabelle 只有 2 笔订单, 全部返回。
Jonathan 恰好有 3 笔订单。
Marwan 只有 1 笔订单。
结果表我们按照 customer_name 升序排列，customer_id 升序排列，order_date 降序排列。
</pre>

<p> </p>

<p><strong>进阶：</strong></p>

<ul>
	<li>你能写出来最近 <code>n</code> 笔订单的通用解决方案吗?</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
