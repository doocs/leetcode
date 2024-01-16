# [1384. 按年度列出销售总额](https://leetcode.cn/problems/total-sales-amount-by-year)

[English Version](/solution/1300-1399/1384.Total%20Sales%20Amount%20by%20Year/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>&nbsp;<code>Product</code>&nbsp;表：</p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| product_name  | varchar |
+---------------+---------+
product_id 是这张表的主键(具有唯一值的列)。
product_name 是产品的名称。
</pre>

<p>&nbsp;</p>

<p><code>Sales</code>&nbsp;表：</p>

<pre>
+---------------------+---------+
| Column Name         | Type    |
+---------------------+---------+
| product_id          | int     |
| period_start        | date    |
| period_end          | date    |
| average_daily_sales | int     |
+---------------------+---------+
product_id 是这张表的主键(具有唯一值的列)。
period_start&nbsp;和 period_end&nbsp;是该产品销售期的起始日期和结束日期，且这两个日期包含在销售期内。
average_daily_sales 列存储销售期内该产品的日平均销售额。
销售日期范围为2018年到2020年。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，找出每个产品每年的总销售额，并包含 <code>product_id</code> , <code>product_name</code> ,&nbsp;<code>report_year</code> 以及 <code>total_amount</code>&nbsp;。</p>

<p>返回结果并按&nbsp;<code>product_id</code> 和 <code>report_year</code><strong> 排序</strong>。</p>

<p>返回结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<code><strong>输入：</strong>
Product</code> table:
+------------+--------------+
| product_id | product_name |
+------------+--------------+
| 1          | LC Phone     |
| 2          | LC T-Shirt   |
| 3          | LC Keychain  |
+------------+--------------+
<code>Sales</code> table:
+------------+--------------+-------------+---------------------+
| product_id | period_start | period_end  | average_daily_sales |
+------------+--------------+-------------+---------------------+
| 1          | 2019-01-25   | 2019-02-28  | 100                 |
| 2          | 2018-12-01   | 2020-01-01  | 10                  |
| 3          | 2019-12-01   | 2020-01-31  | 1                   |
+------------+--------------+-------------+---------------------+
<strong>输出：</strong>
+------------+--------------+-------------+--------------+
| product_id | product_name | report_year | total_amount |
+------------+--------------+-------------+--------------+
| 1          | LC Phone     |    2019     | 3500         |
| 2          | LC T-Shirt   |    2018     | 310          |
| 2          | LC T-Shirt   |    2019     | 3650         |
| 2          | LC T-Shirt   |    2020     | 10           |
| 3          | LC Keychain  |    2019     | 31           |
| 3          | LC Keychain  |    2020     | 31           |
+------------+--------------+-------------+--------------+
<strong>解释：</strong>
LC Phone 在 2019-01-25 至 2019-02-28 期间销售，该产品销售时间总计35天。销售总额 35*100 = 3500。
LC T-shirt 在 2018-12-01&nbsp;至 2020-01-01 期间销售，该产品在2018年、2019年、2020年的销售时间分别是31天、365天、1天，2018年、2019年、2020年的销售总额分别是31*10=310、365*10=3650、1*10=10。
LC Keychain 在 2019-12-01&nbsp;至 2020-01-31 期间销售，该产品在2019年、2020年的销售时间分别是：31天、31天，2019年、2020年的销售总额分别是31*1=31、31*1=31。</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    s.product_id,
    p.product_name,
    y.YEAR report_year,
    s.average_daily_sales * (
        IF(
            YEAR(s.period_end) > y.YEAR,
            y.days_of_year,
            DAYOFYEAR(s.period_end)
        ) - IF(
            YEAR(s.period_start) < y.YEAR,
            1,
            DAYOFYEAR(s.period_start)
        ) + 1
    ) total_amount
FROM
    Sales s
    INNER JOIN (
        SELECT
            '2018' YEAR,
            365 days_of_year
        UNION
        ALL
        SELECT
            '2019' YEAR,
            365 days_of_year
        UNION
        ALL
        SELECT
            '2020' YEAR,
            366 days_of_year
    ) y ON YEAR(s.period_start) <= y.YEAR
    AND YEAR(s.period_end) >= y.YEAR
    INNER JOIN Product p ON p.product_id = s.product_id
ORDER BY
    s.product_id,
    y.YEAR
```

<!-- tabs:end -->

<!-- end -->
