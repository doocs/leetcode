---
comments: true
difficulty: 简单
tags:
    - 数据库
---

<!-- problem:start -->

# [1607. 没有卖出的卖家 🔒](https://leetcode.cn/problems/sellers-with-no-sales)

[English Version](/solution/1600-1699/1607.Sellers%20With%20No%20Sales/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Customer</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| customer_id   | int     |
| customer_name | varchar |
+---------------+---------+
customer_id 是该表具有唯一值的列。
该表的每行包含网上商城的每一位顾客的信息。
</pre>

<p>&nbsp;</p>

<p>表: <code>Orders</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| order_id      | int     |
| sale_date     | date    |
| order_cost    | int     |
| customer_id   | int     |
| seller_id     | int     |
+---------------+---------+
order_id 是该表具有唯一值的列。
该表的每行包含网上商城的所有订单的信息.
sale_date 是顾客 customer_id 和卖家 seller_id 之间交易的日期.
</pre>

<p>&nbsp;</p>

<p>表: <code>Seller</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| seller_id     | int     |
| seller_name   | varchar |
+---------------+---------+
seller_id 是该表主具有唯一值的列。
该表的每行包含每一位卖家的信息.
</pre>

<p>&nbsp;</p>

<p>写一个解决方案,&nbsp;报告所有在&nbsp;<code>2020</code>&nbsp;年度没有任何卖出的卖家的名字。</p>

<p>返回结果按照&nbsp;<code>seller_name</code>&nbsp;<strong>升序排列。</strong></p>

<p>查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<code><strong>输入：</strong>
Customer</code> 表:
+--------------+---------------+
| customer_id  | customer_name |
+--------------+---------------+
| 101          | Alice         |
| 102          | Bob           |
| 103          | Charlie       |
+--------------+---------------+
<code>Orders</code> 表:
+-------------+------------+--------------+-------------+-------------+
| order_id    | sale_date  | order_cost   | customer_id | seller_id   |
+-------------+------------+--------------+-------------+-------------+
| 1           | 2020-03-01 | 1500         | 101         | 1           |
| 2           | 2020-05-25 | 2400         | 102         | 2           |
| 3           | 2019-05-25 | 800          | 101         | 3           |
| 4           | 2020-09-13 | 1000         | 103         | 2           |
| 5           | 2019-02-11 | 700          | 101         | 2           |
+-------------+------------+--------------+-------------+-------------+
<code>Seller</code> 表:
+-------------+-------------+
| seller_id   | seller_name |
+-------------+-------------+
| 1           | Daniel      |
| 2           | Elizabeth   |
| 3           | Frank       |
+-------------+-------------+
<code><strong>输出：</strong></code>
+-------------+
| <code>seller_name </code>|
+-------------+
| Frank       |
+-------------+
<code><strong>解释：</strong></code>
Daniel 在 2020 年 3 月卖出 1 次。
Elizabeth 在 2020 年卖出 2 次, 在 2019 年卖出 1 次。
Frank 在 2019 年卖出 1 次, 在 2020 年没有卖出。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：左连接 + 分组 + 筛选

<!-- thinking:start -->

> **思考**
>
> 要列出 $2020$ 年没有任何订单的卖家，内连接会丢掉全年无单的人，因此必须从 $\texttt{Seller}$ 出发保留全部卖家。
>
> 左连接 $\texttt{Orders}$ 后按 $\texttt{seller\_id}$ 分组，用 $\texttt{YEAR}(\texttt{sale\_date})=2020$ 在组内计数；无订单时聚合结果为 $\texttt{NULL}$，需当成 $0$。
>
> 条件 $\texttt{IFNULL}(\texttt{SUM}(\texttt{YEAR}(\texttt{sale\_date})=2020),0)=0$ 筛出目标，再按姓名排序。

<!-- thinking:end -->

我们可以使用左连接，将 `Seller` 表与 `Orders` 表按照字段 `seller_id` 连接，然后按照 `seller_id` 分组，统计每个卖家在 $2020$ 年的卖出次数，最后筛选出卖出次数为 $0$ 的卖家。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT seller_name
FROM
    Seller
    LEFT JOIN Orders USING (seller_id)
GROUP BY seller_id
HAVING IFNULL(SUM(YEAR(sale_date) = 2020), 0) = 0
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
