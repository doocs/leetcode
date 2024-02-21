# [1384. Total Sales Amount by Year](https://leetcode.com/problems/total-sales-amount-by-year)

[中文文档](/solution/1300-1399/1384.Total%20Sales%20Amount%20by%20Year/README.md)

<!-- tags:Database -->

## Description

<p>Table: <code>Product</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| product_name  | varchar |
+---------------+---------+
product_id is the primary key (column with unique values) for this table.
product_name is the name of the product.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Sales</code></p>

<pre>
+---------------------+---------+
| Column Name         | Type    |
+---------------------+---------+
| product_id          | int     |
| period_start        | date    |
| period_end          | date    |
| average_daily_sales | int     |
+---------------------+---------+
product_id is the primary key (column with unique values) for this table. 
period_start and period_end indicate the start and end date for the sales period, and both dates are inclusive.
The average_daily_sales column holds the average daily sales amount of the items for the period.
The dates of the sales years are between 2018 to 2020.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report the total sales amount of each item for each year, with corresponding <code>product_name</code>, <code>product_id</code>, <code>report_year</code>, and <code>total_amount</code>.</p>

<p>Return the result table <strong>ordered</strong> by <code>product_id</code> and <code>report_year</code>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Product table:
+------------+--------------+
| product_id | product_name |
+------------+--------------+
| 1          | LC Phone     |
| 2          | LC T-Shirt   |
| 3          | LC Keychain  |
+------------+--------------+
Sales table:
+------------+--------------+-------------+---------------------+
| product_id | period_start | period_end  | average_daily_sales |
+------------+--------------+-------------+---------------------+
| 1          | 2019-01-25   | 2019-02-28  | 100                 |
| 2          | 2018-12-01   | 2020-01-01  | 10                  |
| 3          | 2019-12-01   | 2020-01-31  | 1                   |
+------------+--------------+-------------+---------------------+
<strong>Output:</strong> 
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
<strong>Explanation:</strong> 
LC Phone was sold for the period of 2019-01-25 to 2019-02-28, and there are 35 days for this period. Total amount 35*100 = 3500. 
LC T-shirt was sold for the period of 2018-12-01 to 2020-01-01, and there are 31, 365, 1 days for years 2018, 2019 and 2020 respectively.
LC Keychain was sold for the period of 2019-12-01 to 2020-01-31, and there are 31, 31 days for years 2019 and 2020 respectively.
</pre>

## Solutions

### Solution 1

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
