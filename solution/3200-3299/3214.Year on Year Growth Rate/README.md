---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3214.Year%20on%20Year%20Growth%20Rate/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3214. Year on Year Growth Rate 🔒](https://leetcode.cn/problems/year-on-year-growth-rate)

[English Version](/solution/3200-3299/3214.Year%20on%20Year%20Growth%20Rate/README_EN.md)

## 题目描述

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

## 解法

<!-- solution:start -->

### 方法一：分组统计 + 窗口函数

我们可以先按照年份和产品 ID 进行分组统计每个产品每年的总花费，记录在 `T` 表中。然后使用窗口函数 `LAG` 计算出上一年的总花费，记录在 `S` 表中。最后根据公式计算出年增长率。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT YEAR(transaction_date) year, product_id, SUM(spend) tot_spend
        FROM user_transactions
        GROUP BY 1, 2
    ),
    S AS (
        SELECT
            year,
            product_id,
            tot_spend curr_year_spend,
            LAG(tot_spend) OVER (
                PARTITION BY product_id
                ORDER BY year
            ) prev_year_spend
        FROM T
    )
SELECT
    *,
    ROUND((curr_year_spend - prev_year_spend) / prev_year_spend * 100, 2) yoy_rate
FROM S;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
