# [1251. 平均售价](https://leetcode.cn/problems/average-selling-price)

[English Version](/solution/1200-1299/1251.Average%20Selling%20Price/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Prices</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| start_date    | date    |
| end_date      | date    |
| price         | int     |
+---------------+---------+
(product_id，start_date，end_date) 是 <code>Prices </code>表的主键。
<code>Prices </code>表的每一行表示的是某个产品在一段时期内的价格。
每个产品的对应时间段是不会重叠的，这也意味着同一个产品的价格时段不会出现交叉。</pre>

<p>&nbsp;</p>

<p>Table: <code>UnitsSold</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| purchase_date | date    |
| units         | int     |
+---------------+---------+
<code>UnitsSold </code>表没有主键，它可能包含重复项。
<code>UnitsSold </code>表的每一行表示的是每种产品的出售日期，单位和产品 id。</pre>

<p>&nbsp;</p>

<p>编写SQL查询以查找每种产品的平均售价。<br />
<code>average_price</code> 应该四舍五入到小数点后两位。<br />
查询结果格式如下例所示：</p>

<pre>
Prices table:
+------------+------------+------------+--------+
| product_id | start_date | end_date   | price  |
+------------+------------+------------+--------+
| 1          | 2019-02-17 | 2019-02-28 | 5      |
| 1          | 2019-03-01 | 2019-03-22 | 20     |
| 2          | 2019-02-01 | 2019-02-20 | 15     |
| 2          | 2019-02-21 | 2019-03-31 | 30     |
+------------+------------+------------+--------+
 
UnitsSold table:
+------------+---------------+-------+
| product_id | purchase_date | units |
+------------+---------------+-------+
| 1          | 2019-02-25    | 100   |
| 1          | 2019-03-01    | 15    |
| 2          | 2019-02-10    | 200   |
| 2          | 2019-03-22    | 30    |
+------------+---------------+-------+

Result table:
+------------+---------------+
| product_id | average_price |
+------------+---------------+
| 1          | 6.96          |
| 2          | 16.96         |
+------------+---------------+
平均售价 = 产品总价 / 销售的产品数量。
产品 1 的平均售价 = ((100 * 5)+(15 * 20) )/ 115 = 6.96
产品 2 的平均售价 = ((200 * 15)+(30 * 30) )/ 230 = 16.96</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT p.product_id,
       Round(( Sum(u.units * p.price) + 0.0 ) / ( Sum(units) + 0.0 ), 2)
       average_price
FROM   Prices p
       INNER JOIN UnitsSold u
               ON p.product_id = u.product_id
WHERE  u.purchase_date BETWEEN p.start_date AND p.end_date
GROUP  BY p.product_id;
```

<!-- tabs:end -->
