# [1173. 即时食物配送 I](https://leetcode.cn/problems/immediate-food-delivery-i)

[English Version](/solution/1100-1199/1173.Immediate%20Food%20Delivery%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>配送表: <code>Delivery</code></p>

<pre>
+-----------------------------+---------+
| Column Name                 | Type    |
+-----------------------------+---------+
| delivery_id                 | int     |
| customer_id                 | int     |
| order_date                  | date    |
| customer_pref_delivery_date | date    |
+-----------------------------+---------+
delivery_id 是表的主键(具有唯一值的列)。
该表保存着顾客的食物配送信息，顾客在某个日期下了订单，并指定了一个期望的配送日期（和下单日期相同或者在那之后）。
</pre>

<p>&nbsp;</p>

<p>如果顾客期望的配送日期和下单日期相同，则该订单称为 「即时订单」，否则称为「计划订单」。</p>

<p>编写解决方案统计即时订单所占的百分比，&nbsp;<strong>保留两位小数。</strong></p>

<p>返回结果如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Delivery 表:
+-------------+-------------+------------+-----------------------------+
| delivery_id | customer_id | order_date | customer_pref_delivery_date |
+-------------+-------------+------------+-----------------------------+
| 1           | 1           | 2019-08-01 | 2019-08-02                  |
| 2           | 5           | 2019-08-02 | 2019-08-02                  |
| 3           | 1           | 2019-08-11 | 2019-08-11                  |
| 4           | 3           | 2019-08-24 | 2019-08-26                  |
| 5           | 4           | 2019-08-21 | 2019-08-22                  |
| 6           | 2           | 2019-08-11 | 2019-08-13                  |
+-------------+-------------+------------+-----------------------------+
<strong>输出：</strong>
+----------------------+
| immediate_percentage |
+----------------------+
| 33.33                |
+----------------------+
<strong>解释：</strong>2 和 3 号订单为即时订单，其他的为计划订单。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：求和**

我们可以用 `sum` 函数来统计即时订单的数量，然后除以总订单数即可。由于题目求的是百分比，所以需要乘以 100，最后我们用 `round` 函数保留两位小数。

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    ROUND(SUM(order_date = customer_pref_delivery_date) / COUNT(1) * 100, 2) AS immediate_percentage
FROM Delivery;
```

<!-- tabs:end -->
