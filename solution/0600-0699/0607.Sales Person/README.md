# [607. 销售员](https://leetcode.cn/problems/sales-person)

[English Version](/solution/0600-0699/0607.Sales%20Person/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表:&nbsp;<code>SalesPerson</code></p>

<pre>
+-----------------+---------+
| Column Name     | Type    |
+-----------------+---------+
| sales_id        | int     |
| name            | varchar |
| salary          | int     |
| commission_rate | int     |
| hire_date       | date    |
+-----------------+---------+
sales_id 是该表的主键列。
该表的每一行都显示了销售人员的姓名和 ID ，以及他们的工资、佣金率和雇佣日期。
</pre>

<p>&nbsp;</p>

<p>表:&nbsp;<code>Company</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| com_id      | int     |
| name        | varchar |
| city        | varchar |
+-------------+---------+
com_id 是该表的主键列。
该表的每一行都表示公司的名称和 ID ，以及公司所在的城市。
</pre>

<p>&nbsp;</p>

<p>表:&nbsp;<code>Orders</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| order_id    | int  |
| order_date  | date |
| com_id      | int  |
| sales_id    | int  |
| amount      | int  |
+-------------+------+
order_id 是该表的主键列。
com_id 是 Company 表中 com_id 的外键。
sales_id 是来自销售员表 sales_id 的外键。
该表的每一行包含一个订单的信息。这包括公司的 ID 、销售人员的 ID 、订单日期和支付的金额。
</pre>

<p>&nbsp;</p>

<p>编写一个SQL查询，报告没有任何与名为 <strong>“RED”</strong> 的公司相关的订单的所有销售人员的姓名。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
SalesPerson 表:
+----------+------+--------+-----------------+------------+
| sales_id | name | salary | commission_rate | hire_date  |
+----------+------+--------+-----------------+------------+
| 1        | John | 100000 | 6               | 4/1/2006   |
| 2        | Amy  | 12000  | 5               | 5/1/2010   |
| 3        | Mark | 65000  | 12              | 12/25/2008 |
| 4        | Pam  | 25000  | 25              | 1/1/2005   |
| 5        | Alex | 5000   | 10              | 2/3/2007   |
+----------+------+--------+-----------------+------------+
Company 表:
+--------+--------+----------+
| com_id | name   | city     |
+--------+--------+----------+
| 1      | RED    | Boston   |
| 2      | ORANGE | New York |
| 3      | YELLOW | Boston   |
| 4      | GREEN  | Austin   |
+--------+--------+----------+
Orders 表:
+----------+------------+--------+----------+--------+
| order_id | order_date | com_id | sales_id | amount |
+----------+------------+--------+----------+--------+
| 1        | 1/1/2014   | 3      | 4        | 10000  |
| 2        | 2/1/2014   | 4      | 5        | 5000   |
| 3        | 3/1/2014   | 1      | 1        | 50000  |
| 4        | 4/1/2014   | 1      | 4        | 25000  |
+----------+------------+--------+----------+--------+
<strong>输出：</strong>
+------+
| name |
+------+
| Amy  |
| Mark |
| Alex |
+------+
<strong>解释：</strong>
根据表&nbsp;<code>orders</code>&nbsp;中的订单 '3' 和 '4' ，容易看出只有 'John' 和 'Pam' 两个销售员曾经向公司 'RED' 销售过。
所以我们需要输出表&nbsp;<code>salesperson</code>&nbsp;中所有其他人的名字。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT name
FROM salesperson
WHERE sales_id
NOT IN (
    SELECT s.sales_id FROM orders o
    INNER JOIN salesperson s ON o.sales_id = s.sales_id
    INNER JOIN company c ON o.com_id = c.com_id
    WHERE c.name = 'RED'
);
```

```sql
SELECT
    name
FROM
    SalesPerson AS s
WHERE
    0 = (
        SELECT
            COUNT(*)
        FROM
            Orders AS o
            JOIN Company AS c ON o.com_id = c.com_id
        WHERE
            o.sales_id = s.sales_id AND c.name = 'RED'
    );
```

<!-- tabs:end -->
