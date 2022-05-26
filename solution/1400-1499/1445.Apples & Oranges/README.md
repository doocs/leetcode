# [1445. 苹果和桔子](https://leetcode.cn/problems/apples-oranges)

[English Version](/solution/1400-1499/1445.Apples%20%26%20Oranges/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Sales</code></p>

<pre>+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| sale_date     | date    |
| fruit         | enum    | 
| sold_num      | int     | 
+---------------+---------+
(sale_date,fruit) 是该表主键.
该表包含了每一天中&quot;苹果&quot; 和 &quot;桔子&quot;的销售情况.
</pre>

<p>&nbsp;</p>

<p>写一个 SQL&nbsp;查询,&nbsp;报告每一天&nbsp;<strong>苹果</strong>&nbsp;和&nbsp;<strong>桔子</strong>&nbsp;销售的数目的差异.</p>

<p>返回的结果表,&nbsp;按照格式为&nbsp;(&#39;YYYY-MM-DD&#39;) 的 <code>sale_date</code> 排序.</p>

<p>查询结果表如下例所示:</p>

<p>&nbsp;</p>

<pre><code>Sales</code> 表:
+------------+------------+-------------+
| sale_date  | fruit      | sold_num    |
+------------+------------+-------------+
| 2020-05-01 | apples     | 10          |
| 2020-05-01 | oranges    | 8           |
| 2020-05-02 | apples     | 15          |
| 2020-05-02 | oranges    | 15          |
| 2020-05-03 | apples     | 20          |
| 2020-05-03 | oranges    | 0           |
| 2020-05-04 | apples     | 15          |
| 2020-05-04 | oranges    | 16          |
+------------+------------+-------------+

Result 表:
+------------+--------------+
| sale_date  | diff         |
+------------+--------------+
| 2020-05-01 | 2            |
| 2020-05-02 | 0            |
| 2020-05-03 | 20           |
| 2020-05-04 | -1           |
+------------+--------------+

在 2020-05-01, 卖了 10 个苹果 和 8 个桔子 (差异为 10 - 8 = 2).
在 2020-05-02, 卖了 15 个苹果 和 15 个桔子 (差异为 15 - 15 = 0).
在 2020-05-03, 卖了 20 个苹果 和 0 个桔子 (差异为 20 - 0 = 20).
在 2020-05-04, 卖了 15 个苹果 和 16 个桔子 (差异为 15 - 16 = -1).
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

`CASE WHEN` + `GROUP BY`。

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    sale_date AS SALE_DATE,
    sum(
        CASE WHEN fruit = 'oranges' THEN -sold_num ELSE sold_num END
    ) AS DIFF
FROM
    Sales
GROUP BY sale_date
ORDER BY sale_date;
```

<!-- tabs:end -->
