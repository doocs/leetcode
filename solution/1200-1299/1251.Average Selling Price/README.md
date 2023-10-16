# [1251. 平均售价](https://leetcode.cn/problems/average-selling-price)

[English Version](/solution/1200-1299/1251.Average%20Selling%20Price/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Prices</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| start_date    | date    |
| end_date      | date    |
| price         | int     |
+---------------+---------+
(product_id，start_date，end_date) 是 <code>prices</code> 表的主键（具有唯一值的列的组合）。
<code>prices</code> 表的每一行表示的是某个产品在一段时期内的价格。
每个产品的对应时间段是不会重叠的，这也意味着同一个产品的价格时段不会出现交叉。</pre>

<p>&nbsp;</p>

<p>表：<code>UnitsSold</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| purchase_date | date    |
| units         | int     |
+---------------+---------+
<span style="white-space: pre-wrap;">该表可能包含重复数据</span>。
<span style="white-space: pre-wrap;">该</span>表的每一行表示的是每种产品的出售日期，单位和产品 id。</pre>

<p>&nbsp;</p>

<p>编写解决方案以查找每种产品的平均售价。<code>average_price</code> 应该 <strong>四舍五入到小数点后两位</strong>。</p>

<p>返回结果表 <strong>无顺序要求</strong> 。</p>

<p>结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
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
<strong>输出：</strong>
+------------+---------------+
| product_id | average_price |
+------------+---------------+
| 1          | 6.96          |
| 2          | 16.96         |
+------------+---------------+
<strong>解释：</strong>
平均售价 = 产品总价 / 销售的产品数量。
产品 1 的平均售价 = ((100 * 5)+(15 * 20) )/ 115 = 6.96
产品 2 的平均售价 = ((200 * 15)+(30 * 30) )/ 230 = 16.96</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT
    p.product_id,
    IFNULL(ROUND(SUM(units * price) / SUM(units), 2), 0) AS average_price
FROM
    Prices AS p
    LEFT JOIN UnitsSold AS u
        ON p.product_id = u.product_id AND purchase_date BETWEEN start_date AND end_date
GROUP BY product_id;
```

<!-- tabs:end -->
