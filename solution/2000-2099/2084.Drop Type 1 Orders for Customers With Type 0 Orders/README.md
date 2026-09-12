---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2084.Drop%20Type%201%20Orders%20for%20Customers%20With%20Type%200%20Orders/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2084. 为订单类型为 0 的客户删除类型为 1 的订单 🔒](https://leetcode.cn/problems/drop-type-1-orders-for-customers-with-type-0-orders)

[English Version](/solution/2000-2099/2084.Drop%20Type%201%20Orders%20for%20Customers%20With%20Type%200%20Orders/README_EN.md)

## 题目描述

<!-- description:start -->

<p>活动表: <code>Orders</code></p>

<pre>+-------------+------+
| Column Name | Type |
+-------------+------+
| order_id    | int  | 
| customer_id | int  |
| order_type  | int  | 
+-------------+------+
order_id是此表的主键列。
此表的每一行都表示订单的ID、订购该订单的客户的ID以及订单类型。
订单可以是类型0或类型1。
</pre>

<p>&nbsp;</p>

<p>编写SQL查询以根据以下条件报告所有订单：</p>

<ul>
	<li>如果客户至少有一个类型为0的订单，则不要报告该客户的任何类型为1的订单。</li>
	<li>否则，报告客户的所有订单。</li>
</ul>

<p>按任意顺序返回结果表。</p>

<p>查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> 
Orders table:
+----------+-------------+------------+
| order_id | customer_id | order_type |
+----------+-------------+------------+
| 1        | 1           | 0          |
| 2        | 1           | 0          |
| 11       | 2           | 0          |
| 12       | 2           | 1          |
| 21       | 3           | 1          |
| 22       | 3           | 0          |
| 31       | 4           | 1          |
| 32       | 4           | 1          |
+----------+-------------+------------+
<strong>输出:</strong> 
+----------+-------------+------------+
| order_id | customer_id | order_type |
+----------+-------------+------------+
| 31       | 4           | 1          |
| 32       | 4           | 1          |
| 1        | 1           | 0          |
| 2        | 1           | 0          |
| 11       | 2           | 0          |
| 22       | 3           | 0          |
+----------+-------------+------------+
<strong>解释:</strong> 
客户1有两个类型为0的订单。我们两个都返回。
客户2的订单类型为0，订单类型为1。我们只返回类型为0的订单。
客户3的订单类型为0，订单类型为1。我们只返回类型为0的订单。
客户4有两个类型1的订单。我们两个都返回。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 有 0 类订单的顾客，其 1 类订单全部丢弃；其余顾客订单保留。先求出存在 0 类订单的顾客集合，再选「本身是 0 类，或不在该集合」。
>
> CTE 加 `NOT EXISTS` 表达该过滤。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT DISTINCT customer_id
        FROM Orders
        WHERE order_type = 0
    )
SELECT *
FROM Orders AS o
WHERE order_type = 0 OR NOT EXISTS (SELECT 1 FROM T AS t WHERE t.customer_id = o.customer_id);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 方法一依赖子查询。自连接同一顾客的不同类型订单：若能连上 0 类，则当前 1 类应丢弃。保留「没有异型配对」或「配对那侧是 1 类」（意味着自己是 0 类）的行，再去重。
>
> 与方法一结果相同，改用 `LEFT JOIN`。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
SELECT DISTINCT
    a.order_id,
    a.customer_id,
    a.order_type
FROM
    Orders AS a
    LEFT JOIN Orders AS b ON a.customer_id = b.customer_id AND a.order_type != b.order_type
WHERE b.order_type IS NULL OR b.order_type = 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
