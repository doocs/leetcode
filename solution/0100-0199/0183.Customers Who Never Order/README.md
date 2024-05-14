---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0183.Customers%20Who%20Never%20Order/README.md
tags:
    - 数据库
---

# [183. 从不订购的客户](https://leetcode.cn/problems/customers-who-never-order)

[English Version](/solution/0100-0199/0183.Customers%20Who%20Never%20Order/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>Customers</code> 表：</p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
在 SQL 中，id 是该表的主键。
该表的每一行都表示客户的 ID 和名称。</pre>

<p><code>Orders</code> 表：</p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| customerId  | int  |
+-------------+------+
在 SQL 中，id 是该表的主键。
customerId 是 Customers 表中 ID 的外键( Pandas 中的连接键)。
该表的每一行都表示订单的 ID 和订购该订单的客户的 ID。</pre>

<p>&nbsp;</p>

<p>找出所有从不点任何东西的顾客。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>
Customers table:
+----+-------+
| id | name  |
+----+-------+
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |
+----+-------+
Orders table:
+----+------------+
| id | customerId |
+----+------------+
| 1  | 3          |
| 2  | 1          |
+----+------------+
<b>输出：</b>
+-----------+
| Customers |
+-----------+
| Henry     |
| Max       |
+-----------+</pre>

## 解法

### 方法一：NOT IN

列举所有已存在订单的客户 ID，使用 `NOT IN` 找到不存在其中的客户。

<!-- tabs:start -->

```python
import pandas as pd


def find_customers(customers: pd.DataFrame, orders: pd.DataFrame) -> pd.DataFrame:
    # Select the customers whose 'id' is not present in the orders DataFrame's 'customerId' column.
    df = customers[~customers["id"].isin(orders["customerId"])]

    # Build a DataFrame that only contains the 'name' column and rename it as 'Customers'.
    df = df[["name"]].rename(columns={"name": "Customers"})

    return df
```

```sql
# Write your MySQL query statement below
SELECT name AS Customers
FROM Customers
WHERE
    id NOT IN (
        SELECT customerId
        FROM Orders
    );
```

<!-- tabs:end -->

### 方法二：LEFT JOIN

使用 `LEFT JOIN` 连接表格，返回 `CustomerId` 为 `NULL` 的数据。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT name AS Customers
FROM
    Customers AS c
    LEFT JOIN Orders AS o ON c.id = o.customerId
WHERE o.id IS NULL;
```

<!-- tabs:end -->

<!-- end -->
