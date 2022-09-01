# [2372. 计算每个销售人员的影响力](https://leetcode.cn/problems/calculate-the-influence-of-each-salesperson)

[English Version](/solution/2300-2399/2372.Calculate%20the%20Influence%20of%20Each%20Salesperson/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：&nbsp;<code>Salesperson</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| salesperson_id | int     |
| name           | varchar |
+----------------+---------+
sales_person_id 是这个表的主键。
这个表中的每一行都显示一个销售人员的 ID。
</pre>

<p>&nbsp;</p>

<p>表：<code>Customer</code></p>

<pre>
+----------------+------+
| Column Name    | Type |
+----------------+------+
| customer_id    | int  |
| salesperson_id | int  |
+----------------+------+
customer_id 是这个表的主键。
salesperson_id 是一个来自于 <code>Salesperson 表的外键</code>
<code>Customer </code>表中的每一行都显示了一个客户的 ID 和销售人员的 ID。
</pre>

<p>&nbsp;</p>

<p>表：<code>Sales</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| sale_id     | int  |
| customer_id | int  |
| price       | int  |
+-------------+------+
sale_id 是这个表的主键。
customer_id 是一个来自于 Customer 表的外键。
<code>Sales </code>表中的每一行都显示了一个客户的 ID 以及他们在 sale_id 指代的交易中所支付的金额。
</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询用来报告每个销售人员的客户所支付的价格总和。如果销售人员没有任何客户，则总值应该为 <code>0</code> 。<br />
以 <strong>任意顺序</strong> 返回结果表。<br />
查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Salesperson 表:
+----------------+-------+
| salesperson_id | name  |
+----------------+-------+
| 1              | Alice |
| 2              | Bob   |
| 3              | Jerry |
+----------------+-------+
Customer 表:
+-------------+----------------+
| customer_id | salesperson_id |
+-------------+----------------+
| 1           | 1              |
| 2           | 1              |
| 3           | 2              |
+-------------+----------------+
Sales 表:
+---------+-------------+-------+
| sale_id | customer_id | price |
+---------+-------------+-------+
| 1       | 2           | 892   |
| 2       | 1           | 354   |
| 3       | 3           | 988   |
| 4       | 3           | 856   |
+---------+-------------+-------+
<strong>输出:</strong> 
+----------------+-------+-------+
| salesperson_id | name  | total |
+----------------+-------+-------+
| 1              | Alice | 1246  |
| 2              | Bob   | 1844  |
| 3              | Jerry | 0     |
+----------------+-------+-------+
<strong>解释:</strong> 
Alice 是客户 1 和客户 2 的销售人员。
  - 客户 1 一次购买花费了 354。
  - 客户 2 一次购买花费了 892。
Alice 的总数是 354 + 892 = 1246。

Bob 是客户 3 的销售人员。
  - 客户 3 一次购买花费了 988，另一次购买花费了 856。
Bob 的总数是 988 + 856 = 1844。

Jerry 没有客户。
Jerry 的总数是 0。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
