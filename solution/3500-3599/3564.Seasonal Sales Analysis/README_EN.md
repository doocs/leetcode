---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3564.Seasonal%20Sales%20Analysis/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3564. Seasonal Sales Analysis](https://leetcode.com/problems/seasonal-sales-analysis)

[中文文档](/solution/3500-3599/3564.Seasonal%20Sales%20Analysis/README.md)

## Description

<!-- description:start -->

<p>Table: <code>sales</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| sale_id       | int     |
| product_id    | int     |
| sale_date     | date    |
| quantity      | int     |
| price         | decimal |
+---------------+---------+
sale_id is the unique identifier for this table.
Each row contains information about a product sale including the product_id, date of sale, quantity sold, and price per unit.
</pre>

<p>Table: <code>products</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| product_name  | varchar |
| category      | varchar |
+---------------+---------+
product_id is the unique identifier for this table.
Each row contains information about a product including its name and category.
</pre>

<p>Write a solution to find the most popular product category for each season. The seasons are defined as:</p>

<ul>
	<li><strong>Winter</strong>: December, January, February</li>
	<li><strong>Spring</strong>: March, April, May</li>
	<li><strong>Summer</strong>: June, July, August</li>
	<li><strong>Fall</strong>: September, October, November</li>
</ul>

<p>The <strong>popularity</strong> of a <strong>category</strong> is determined by the <strong>total quantity sold</strong> in that <strong>season</strong>. If there is a <strong>tie</strong>, select the category with the highest <strong>total revenue</strong> (<code>quantity &times; price</code>). If there is still a tie, return the lexicographically smaller category.</p>

<p>Return <em>the result table ordered by season in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>sales table:</p>

<pre class="example-io">
+---------+------------+------------+----------+-------+
| sale_id | product_id | sale_date  | quantity | price |
+---------+------------+------------+----------+-------+
| 1       | 1          | 2023-01-15 | 5        | 10.00 |
| 2       | 2          | 2023-01-20 | 4        | 15.00 |
| 3       | 3          | 2023-03-10 | 3        | 18.00 |
| 4       | 4          | 2023-04-05 | 1        | 20.00 |
| 5       | 1          | 2023-05-20 | 2        | 10.00 |
| 6       | 2          | 2023-06-12 | 4        | 15.00 |
| 7       | 5          | 2023-06-15 | 5        | 12.00 |
| 8       | 3          | 2023-07-24 | 2        | 18.00 |
| 9       | 4          | 2023-08-01 | 5        | 20.00 |
| 10      | 5          | 2023-09-03 | 3        | 12.00 |
| 11      | 1          | 2023-09-25 | 6        | 10.00 |
| 12      | 2          | 2023-11-10 | 4        | 15.00 |
| 13      | 3          | 2023-12-05 | 6        | 18.00 |
| 14      | 4          | 2023-12-22 | 3        | 20.00 |
| 15      | 5          | 2024-02-14 | 2        | 12.00 |
+---------+------------+------------+----------+-------+
</pre>

<p>products table:</p>

<pre class="example-io">
+------------+-----------------+----------+
| product_id | product_name    | category |
+------------+-----------------+----------+
| 1          | Warm Jacket     | Apparel  |
| 2          | Designer Jeans  | Apparel  |
| 3          | Cutting Board   | Kitchen  |
| 4          | Smart Speaker   | Tech     |
| 5          | Yoga Mat        | Fitness  |
+------------+-----------------+----------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+---------+----------+----------------+---------------+
| season  | category | total_quantity | total_revenue |
+---------+----------+----------------+---------------+
| Fall    | Apparel  | 10             | 120.00        |
| Spring  | Kitchen  | 3              | 54.00         |
| Summer  | Tech     | 5              | 100.00        |
| Winter  | Apparel  | 9              | 110.00        |
+---------+----------+----------------+---------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>Fall (Sep, Oct, Nov):</strong>

    <ul>
    	<li>Apparel: 10 items sold (6 Jackets in Sep, 4 Jeans in Nov), revenue $120.00 (6&times;$10.00 + 4&times;$15.00)</li>
    	<li>Fitness: 3 Yoga Mats sold in Sep, revenue $36.00</li>
    	<li>Most popular: Apparel with highest total quantity (10)</li>
    </ul>
    </li>
    <li><strong>Spring (Mar, Apr, May):</strong>
    <ul>
    	<li>Kitchen: 3 Cutting Boards sold in Mar, revenue $54.00</li>
    	<li>Tech: 1 Smart Speaker sold in Apr, revenue $20.00</li>
    	<li>Apparel: 2 Warm Jackets sold in May, revenue $20.00</li>
    	<li>Most popular: Kitchen with highest total quantity (3) and highest revenue ($54.00)</li>
    </ul>
    </li>
    <li><strong>Summer (Jun, Jul, Aug):</strong>
    <ul>
    	<li>Apparel: 4 Designer Jeans sold in Jun, revenue $60.00</li>
    	<li>Fitness: 5 Yoga Mats sold in Jun, revenue $60.00</li>
    	<li>Kitchen: 2 Cutting Boards sold in Jul, revenue $36.00</li>
    	<li>Tech: 5 Smart Speakers sold in Aug, revenue $100.00</li>
    	<li>Most popular: Tech and Fitness both have 5 items, but Tech has higher revenue ($100.00 vs $60.00)</li>
    </ul>
    </li>
    <li><strong>Winter (Dec, Jan, Feb):</strong>
    <ul>
    	<li>Apparel: 9 items sold (5 Jackets in Jan, 4 Jeans in Jan), revenue $110.00</li>
    	<li>Kitchen: 6 Cutting Boards sold in Dec, revenue $108.00</li>
    	<li>Tech: 3 Smart Speakers sold in Dec, revenue $60.00</li>
    	<li>Fitness: 2 Yoga Mats sold in Feb, revenue $24.00</li>
    	<li>Most popular: Apparel with highest total quantity (9) and highest revenue ($110.00)</li>
    </ul>
    </li>

</ul>

<p>The result table is ordered by season in ascending order.</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Equi Join + Group Aggregation + Window Function

We can perform an equi join between the `sales` table and the `products` table to obtain the product category for each sales record. Next, we determine the season based on the month of the sales date, and then group by season and category to calculate the total quantity sold and total revenue. Finally, we use a window function to rank the categories within each season and select the top-ranked category.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    SeasonalSales AS (
        SELECT
            CASE
                WHEN MONTH(sale_date) IN (12, 1, 2) THEN 'Winter'
                WHEN MONTH(sale_date) IN (3, 4, 5) THEN 'Spring'
                WHEN MONTH(sale_date) IN (6, 7, 8) THEN 'Summer'
                WHEN MONTH(sale_date) IN (9, 10, 11) THEN 'Fall'
            END AS season,
            category,
            SUM(quantity) AS total_quantity,
            SUM(quantity * price) AS total_revenue
        FROM
            sales
            JOIN products USING (product_id)
        GROUP BY 1, 2
    ),
    TopCategoryPerSeason AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY season
                ORDER BY total_quantity DESC, total_revenue DESC
            ) AS rk
        FROM SeasonalSales
    )
SELECT season, category, total_quantity, total_revenue
FROM TopCategoryPerSeason
WHERE rk = 1
ORDER BY 1;
```

#### Pandas

```python
import pandas as pd


def seasonal_sales_analysis(
    products: pd.DataFrame, sales: pd.DataFrame
) -> pd.DataFrame:
    df = sales.merge(products, on="product_id")
    month_to_season = {
        12: "Winter",
        1: "Winter",
        2: "Winter",
        3: "Spring",
        4: "Spring",
        5: "Spring",
        6: "Summer",
        7: "Summer",
        8: "Summer",
        9: "Fall",
        10: "Fall",
        11: "Fall",
    }
    df["season"] = df["sale_date"].dt.month.map(month_to_season)
    seasonal_sales = df.groupby(["season", "category"], as_index=False).agg(
        total_quantity=("quantity", "sum"),
        total_revenue=("quantity", lambda x: (x * df.loc[x.index, "price"]).sum()),
    )
    seasonal_sales["rk"] = (
        seasonal_sales.sort_values(
            ["season", "total_quantity", "total_revenue"],
            ascending=[True, False, False],
        )
        .groupby("season")
        .cumcount()
        + 1
    )
    result = seasonal_sales[seasonal_sales["rk"] == 1].copy()
    return result[
        ["season", "category", "total_quantity", "total_revenue"]
    ].sort_values("season")
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
