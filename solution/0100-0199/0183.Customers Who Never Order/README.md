# [183. 从不订购的客户](https://leetcode.cn/problems/customers-who-never-order)

[English Version](/solution/0100-0199/0183.Customers%20Who%20Never%20Order/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>某网站包含两个表，<code>Customers</code> 表和 <code>Orders</code> 表。编写一个 SQL 查询，找出所有从不订购任何东西的客户。</p>

<p><code>Customers</code> 表：</p>

<pre>+----+-------+
| Id | Name  |
+----+-------+
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |
+----+-------+
</pre>

<p><code>Orders</code> 表：</p>

<pre>+----+------------+
| Id | CustomerId |
+----+------------+
| 1  | 3          |
| 2  | 1          |
+----+------------+
</pre>

<p>例如给定上述表格，你的查询应返回：</p>

<pre>+-----------+
| Customers |
+-----------+
| Henry     |
| Max       |
+-----------+
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：**

列举所有已存在订单的客户 ID，使用 `NOT IN` 找到不存在其中的客户。

**方法二：**

使用 `LEFT JOIN` 连接表格，返回 `CustomerId` 为 `NULL` 的数据。

<!-- tabs:start -->

### **SQL**

```sql
select Name as Customers
from Customers
where id not in (
        select CustomerId
        from Orders
    );
```

```sql
SELECT
    c.Name AS Customers
FROM
    customers AS c
    LEFT JOIN orders AS o ON c.Id  = o.CustomerId
WHERE
    o.CustomerId IS NULL;
```

<!-- tabs:end -->
