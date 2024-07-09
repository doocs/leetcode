---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3214.Year%20on%20Year%20Growth%20Rate/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3214. Year on Year Growth Rate 🔒](https://leetcode.com/problems/year-on-year-growth-rate)

[中文文档](/solution/3200-3299/3214.Year%20on%20Year%20Growth%20Rate/README.md)

## Description

<!-- description:start -->

<p>Table: <code>user_transactions</code></p>

<pre>
+------------------+----------+
| Column Name      | Type     | 
+------------------+----------+
| transaction_id   | integer  |
| product_id       | integer  |
| spend            | decimal  |
| transaction_date | datetime |
+------------------+----------+
The transaction_id column uniquely identifies each row in this table.
Each row of this table contains the transaction ID, product ID, the spend amount, and the transaction date.
</pre>

<p>Write a solution to calculate the <strong>year-on-year growth rate</strong> for the total spend <strong>for each product</strong>.</p>

<p>The result table should include the following columns:</p>

<ul>
	<li><code>year</code>: The year of the transaction.</li>
	<li><code>product_id</code>: The ID of the product.</li>
	<li><code>curr_year_spend</code>: The total spend for the current year.</li>
	<li><code>prev_year_spend</code>: The total spend for the previous year.</li>
	<li><code>yoy_rate</code>: The year-on-year growth rate percentage, rounded to <code>2</code> decimal places.</li>
</ul>

<p>Return <em>the result table ordered by</em>&nbsp;<code>product_id</code>,<code>year</code> <em>in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p><code>user_transactions</code> table:</p>

<pre class="example-io">
+----------------+------------+---------+---------------------+
| transaction_id | product_id | spend   | transaction_date    |
+----------------+------------+---------+---------------------+
| 1341           | 123424     | 1500.60 | 2019-12-31 12:00:00 |
| 1423           | 123424     | 1000.20 | 2020-12-31 12:00:00 |
| 1623           | 123424     | 1246.44 | 2021-12-31 12:00:00 |
| 1322           | 123424     | 2145.32 | 2022-12-31 12:00:00 |
+----------------+------------+---------+---------------------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+------+------------+----------------+----------------+----------+
| year | product_id | curr_year_spend| prev_year_spend| yoy_rate |
+------+------------+----------------+----------------+----------+
| 2019 | 123424     | 1500.60        | NULL           | NULL     |
| 2020 | 123424     | 1000.20        | 1500.60        | -33.35   |
| 2021 | 123424     | 1246.44        | 1000.20        | 24.62    |
| 2022 | 123424     | 2145.32        | 1246.44        | 72.12    |
+------+------------+----------------+----------------+----------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For product ID 123424:
	<ul>
		<li>In 2019:
		<ul>
			<li>Current year&#39;s spend is 1500.60</li>
			<li>No previous year&#39;s spend recorded</li>
			<li>YoY growth rate: NULL</li>
		</ul>
		</li>
		<li>In 2020:
		<ul>
			<li>Current year&#39;s spend is 1000.20</li>
			<li>Previous year&#39;s spend is 1500.60</li>
			<li>YoY growth rate: ((1000.20 - 1500.60) / 1500.60) * 100 = -33.35%</li>
		</ul>
		</li>
		<li>In 2021:
		<ul>
			<li>Current year&#39;s spend is 1246.44</li>
			<li>Previous year&#39;s spend is 1000.20</li>
			<li>YoY growth rate: ((1246.44 - 1000.20) / 1000.20) * 100 = 24.62%</li>
		</ul>
		</li>
		<li>In 2022:
		<ul>
			<li>Current year&#39;s spend is 2145.32</li>
			<li>Previous year&#39;s spend is 1246.44</li>
			<li>YoY growth rate: ((2145.32 - 1246.44) / 1246.44) * 100 = 72.12%</li>
		</ul>
		</li>
	</ul>
	</li>
</ul>

<p><strong>Note:</strong> Output table is ordered by <code>product_id</code> and <code>year</code> in ascending order.</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Grouping Statistics + Left Join

We can first group by `product_id` and `year(transaction_date)` to perform the statistics, then use a left join to associate the statistics of the current year with those of the previous year, and finally calculate the year-on-year growth rate.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT product_id, YEAR(transaction_date) year, SUM(spend) curr_year_spend
        FROM user_transactions
        GROUP BY 1, 2
    ),
    S AS (
        SELECT t1.year, t1.product_id, t1.curr_year_spend, t2.curr_year_spend prev_year_spend
        FROM
            T t1
            LEFT JOIN T t2 ON t1.product_id = t2.product_id AND t1.year = t2.year + 1
    )
SELECT
    *,
    ROUND((curr_year_spend - prev_year_spend) / prev_year_spend * 100, 2) yoy_rate
FROM S
ORDER BY 2, 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
