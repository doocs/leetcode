# [607. 销售员](https://leetcode-cn.com/problems/sales-person)

[English Version](/solution/0600-0699/0607.Sales%20Person/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>描述</strong></p>

<p>给定 3 个表：&nbsp;<code>salesperson</code>，&nbsp;<code>company</code>，&nbsp;<code>orders</code>。<br>
输出所有表&nbsp;<code>salesperson</code>&nbsp;中，没有向公司 &#39;RED&#39; 销售任何东西的销售员。</p>

<p><strong>示例：</strong><br>
<strong>输入</strong></p>

<p>表：&nbsp;<code>salesperson</code></p>

<pre>+----------+------+--------+-----------------+-----------+
| sales_id | name | salary | commission_rate | hire_date |
+----------+------+--------+-----------------+-----------+
|   1      | John | 100000 |     6           | 4/1/2006  |
|   2      | Amy  | 120000 |     5           | 5/1/2010  |
|   3      | Mark | 65000  |     12          | 12/25/2008|
|   4      | Pam  | 25000  |     25          | 1/1/2005  |
|   5      | Alex | 50000  |     10          | 2/3/2007  |
+----------+------+--------+-----------------+-----------+
</pre>

<p>表&nbsp;<code>salesperson</code> 存储了所有销售员的信息。每个销售员都有一个销售员编号&nbsp;<strong>sales_id</strong> 和他的名字&nbsp;<strong>name&nbsp;</strong>。</p>

<p>表：&nbsp;<code>company</code></p>

<pre>+---------+--------+------------+
| com_id  |  name  |    city    |
+---------+--------+------------+
|   1     |  RED   |   Boston   |
|   2     | ORANGE |   New York |
|   3     | YELLOW |   Boston   |
|   4     | GREEN  |   Austin   |
+---------+--------+------------+
</pre>

<p>表&nbsp;<code>company</code>&nbsp;存储了所有公司的信息。每个公司都有一个公司编号&nbsp;<strong>com_id</strong>&nbsp;和它的名字 <strong>name</strong>&nbsp;。</p>

<p>表：&nbsp;<code>orders</code></p>

<pre>+----------+------------+---------+----------+--------+
| order_id | order_date | com_id  | sales_id | amount |
+----------+------------+---------+----------+--------+
| 1        |   1/1/2014 |    3    |    4     | 100000 |
| 2        |   2/1/2014 |    4    |    5     | 5000   |
| 3        |   3/1/2014 |    1    |    1     | 50000  |
| 4        |   4/1/2014 |    1    |    4     | 25000  |
+----------+----------+---------+----------+--------+
</pre>

<p>表&nbsp;<code>orders</code>&nbsp;存储了所有的销售数据，包括销售员编号 <strong>sales_id </strong>和公司编号 <strong>com_id</strong>&nbsp;。</p>

<p><strong>输出</strong></p>

<pre>+------+
| name | 
+------+
| Amy  | 
| Mark | 
| Alex |
+------+
</pre>

<p><strong>解释</strong></p>

<p>根据表&nbsp;<code>orders</code>&nbsp;中的订单 &#39;3&#39; 和 &#39;4&#39; ，容易看出只有 &#39;John&#39; 和 &#39;Pam&#39; 两个销售员曾经向公司 &#39;RED&#39; 销售过。</p>

<p>所以我们需要输出表&nbsp;<code>salesperson</code>&nbsp;中所有其他人的名字。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
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

<!-- tabs:end -->
