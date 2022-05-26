# [1084. 销售分析 III](https://leetcode.cn/problems/sales-analysis-iii)

[English Version](/solution/1000-1099/1084.Sales%20Analysis%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table:&nbsp;<code>Product</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| product_id   | int     |
| product_name | varchar |
| unit_price   | int     |
+--------------+---------+
Product_id是该表的主键。
该表的每一行显示每个产品的名称和价格。
</pre>

<p>Table:&nbsp;<code>Sales</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| seller_id   | int     |
| product_id  | int     |
| buyer_id    | int     |
| sale_date   | date    |
| quantity    | int     |
| price       | int     |
+------ ------+---------+
这个表没有主键，它可以有重复的行。
product_id 是 Product 表的外键。
该表的每一行包含关于一个销售的一些信息。
</pre>

<p>&nbsp;</p>

<p>编写一个SQL查询，报告<code>2019年春季</code>才售出的产品。即<strong>仅</strong>在<code><strong>2019-01-01</strong></code>至<code><strong>2019-03-31</strong></code>（含）之间出售的商品。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Product table:
+------------+--------------+------------+
| product_id | product_name | unit_price |
+------------+--------------+------------+
| 1          | S8           | 1000       |
| 2          | G4           | 800        |
| 3          | iPhone       | 1400       |
+------------+--------------+------------+
<code>Sales </code>table:
+-----------+------------+----------+------------+----------+-------+
| seller_id | product_id | buyer_id | sale_date  | quantity | price |
+-----------+------------+----------+------------+----------+-------+
| 1         | 1          | 1        | 2019-01-21 | 2        | 2000  |
| 1         | 2          | 2        | 2019-02-17 | 1        | 800   |
| 2         | 2          | 3        | 2019-06-02 | 1        | 800   |
| 3         | 3          | 4        | 2019-05-13 | 2        | 2800  |
+-----------+------------+----------+------------+----------+-------+
<strong>输出：</strong>
+-------------+--------------+
| product_id  | product_name |
+-------------+--------------+
| 1           | S8           |
+-------------+--------------+
<strong>解释:</strong>
id为1的产品仅在2019年春季销售。
id为2的产品在2019年春季销售，但也在2019年春季之后销售。
id 3的产品在2019年春季之后销售。
我们只退回产品1，因为它是2019年春季才销售的产品。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT p.product_id,
    P.product_name
FROM product AS p
    JOIN sales AS s ON p.product_id = s.product_id
GROUP BY p.product_id
HAVING SUM(sale_date < '2019-01-01') = 0
    AND SUM(sale_date > '2019-03-31') = 0;
```

<!-- tabs:end -->
