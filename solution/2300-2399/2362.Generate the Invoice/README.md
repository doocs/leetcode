# [2362. 生成发票](https://leetcode.cn/problems/generate-the-invoice)

[English Version](/solution/2300-2399/2362.Generate%20the%20Invoice/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Products</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| product_id  | int  |
| price       | int  |
+-------------+------+
product_id 是该表的主键。
该表中的每一行显示了一个产品的 ID 和一个单位的价格。
</pre>

<p>&nbsp;</p>

<p>表: <code>Purchases</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| invoice_id  | int  |
| product_id  | int  |
| quantity    | int  |
+-------------+------+
(invoice_id, product_id) 是该表的主键
该表中的每一行都显示了从发票中的一种产品订购的数量。
</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询来显示价格最高的发票的详细信息。如果两个或多个发票具有相同的价格，则返回 <code>invoice_id</code> 最小的发票的详细信息。</p>

<p data-group="1-1">以 <strong>任意顺序</strong> 返回结果表。</p>

<p>查询结果格式示例如下。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Products 表:
+------------+-------+
| product_id | price |
+------------+-------+
| 1          | 100   |
| 2          | 200   |
+------------+-------+
Purchases 表:
+------------+------------+----------+
| invoice_id | product_id | quantity |
+------------+------------+----------+
| 1          | 1          | 2        |
| 3          | 2          | 1        |
| 2          | 2          | 3        |
| 2          | 1          | 4        |
| 4          | 1          | 10       |
+------------+------------+----------+
<strong>输出:</strong> 
+------------+----------+-------+
| product_id | quantity | price |
+------------+----------+-------+
| 2          | 3        | 600   |
| 1          | 4        | 400   |
+------------+----------+-------+
<strong>解释:</strong> 
发票 1: price = (2 * 100) = $200
发票 2: price = (4 * 100) + (3 * 200) = $1000
发票 3: price = (1 * 200) = $200
发票 4: price = (10 * 100) = $1000

最高价格是 1000 美元，最高价格的发票是 2 和 4。我们返回 ID 最小的发票 2 的详细信息。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
