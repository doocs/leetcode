---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3293.Calculate%20Product%20Final%20Price/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3293. Calculate Product Final Price 🔒](https://leetcode.com/problems/calculate-product-final-price)

[中文文档](/solution/3200-3299/3293.Calculate%20Product%20Final%20Price/README.md)

## Description

<!-- description:start -->

<p>Table: <font face="monospace"><code>Products</code></font></p>

<pre>
+------------+---------+ 
| Column Name| Type    | 
+------------+---------+ 
| product_id | int     | 
| category   | varchar |
| price      | decimal |
+------------+---------+
product_id is the unique key for this table.
Each row includes the product&#39;s ID, its category, and its price.
</pre>

<p>Table: <font face="monospace"><code>Discounts</code></font></p>

<pre>
+------------+---------+ 
| Column Name| Type    | 
+------------+---------+ 
| category   | varchar |
| discount   | int     |
+------------+---------+
category is the primary key for this table.
Each row contains a product category and the percentage discount applied to that category (values range from 0 to 100).
</pre>

<p>Write a solution to find the <strong>final price</strong> of each product after applying the <strong>category discount</strong>. If a product&#39;s category has <strong>no</strong> <strong>associated</strong> <strong>discount</strong>, its price remains <strong>unchanged</strong>.</p>

<p>Return <em>the result table ordered by</em> <code>product_id</code><em> in <strong>ascending</strong> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p><code>Products</code> table:</p>

<pre class="example-io">
+------------+-------------+-------+
| product_id | category    | price |
+------------+-------------+-------+
| 1          | Electronics | 1000  |
| 2          | Clothing    | 50    |
| 3          | Electronics | 1200  | 
| 4          | Home        | 500   |
+------------+-------------+-------+
  </pre>

<p><code>Discounts</code> table:</p>

<pre class="example-io">
+------------+----------+
| category   | discount |
+------------+----------+
| Electronics| 10       |
| Clothing   | 20       |
+------------+----------+
  </pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+------------+------------+-------------+
| product_id | final_price| category    |
+------------+------------+-------------+
| 1          | 900        | Electronics |
| 2          | 40         | Clothing    |
| 3          | 1080       | Electronics |
| 4          | 500        | Home        |
+------------+------------+-------------+
  </pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For product 1, it belongs to the Electronics&nbsp;category which has a 10% discount, so the final price is 1000 - (10% of 1000) = 900.</li>
	<li>For product 2, it belongs to the Clothing&nbsp;category which has a 20% discount, so the final price is 50 - (20% of 50) = 40.</li>
	<li>For product 3, it belongs to the Electronics&nbsp;category and receives a 10% discount, so the final price is 1200 - (10% of 1200) = 1080.</li>
	<li>For product 4, no discount is available for the Home&nbsp;category, so the final price remains 500.</li>
</ul>
Result table is ordered by product_id in ascending order.</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Left Join

We can perform a left join between the `Products` table and the `Discounts` table on the `category` column, then calculate the final price. If a product's category does not have an associated discount, its price remains unchanged.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    product_id,
    price * (100 - IFNULL(discount, 0)) / 100 final_price,
    category
FROM
    Products
    LEFT JOIN Discounts USING (category)
ORDER BY 1;
```

#### Pandas

```python
import pandas as pd


def calculate_final_prices(
    products: pd.DataFrame, discounts: pd.DataFrame
) -> pd.DataFrame:
    # Perform a left join on the 'category' column
    merged_df = pd.merge(products, discounts, on="category", how="left")

    # Calculate the final price
    merged_df["final_price"] = (
        merged_df["price"] * (100 - merged_df["discount"].fillna(0)) / 100
    )

    # Select the necessary columns and sort by 'product_id'
    result_df = merged_df[["product_id", "final_price", "category"]].sort_values(
        "product_id"
    )

    return result_df
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
