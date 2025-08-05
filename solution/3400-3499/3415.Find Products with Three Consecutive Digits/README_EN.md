---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3415.Find%20Products%20with%20Three%20Consecutive%20Digits/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3415. Find Products with Three Consecutive Digits 🔒](https://leetcode.com/problems/find-products-with-three-consecutive-digits)

[中文文档](/solution/3400-3499/3415.Find%20Products%20with%20Three%20Consecutive%20Digits/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Products</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| product_id  | int     |
| name        | varchar |
+-------------+---------+
product_id is the unique key for this table.
Each row of this table contains the ID and name of a product.
</pre>

<p>Write a solution to find all <strong>products</strong> whose names contain a <strong>sequence of exactly three consecutive digits in a row</strong>.&nbsp;</p>

<p>Return <em>the result table ordered by</em> <code>product_id</code> <em>in <strong>ascending</strong> order.</em></p>

<p>The result format is in the following example.</p>

<p><strong>Note</strong> that the name may contain multiple such sequences, but each should have length three.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>products table:</p>

<pre class="example-io">
+-------------+--------------------+
| product_id  | name               |
+-------------+--------------------+
| 1           | ABC123XYZ          |
| 2           | A12B34C            |
| 3           | Product56789       |
| 4           | NoDigitsHere       |
| 5           | 789Product         |
| 6           | Item003Description |
| 7           | Product12X34       |
+-------------+--------------------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+-------------+--------------------+
| product_id  | name               |
+-------------+--------------------+
| 1           | ABC123XYZ          |
| 5           | 789Product         |
| 6           | Item003Description |
+-------------+--------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Product 1: ABC123XYZ contains the digits 123.</li>
	<li>Product 5: 789Product&nbsp;contains the digits 789.</li>
	<li>Product 6: Item003Description&nbsp;contains 003, which is exactly three digits.</li>
</ul>

<p><strong>Note:</strong></p>

<ul>
	<li>Results are ordered by <code>product_id</code> in ascending order.</li>
	<li>Only products with exactly three consecutive digits in their names are included in the result.</li>
</ul>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Regex Matching

We can use regular expressions to match product names that contain three consecutive digits.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT product_id, name
FROM Products
WHERE name REGEXP '(^|[^0-9])[0-9]{3}([^0-9]|$)'
ORDER BY 1;
```

#### Pandas

```python
import pandas as pd


def find_products(products: pd.DataFrame) -> pd.DataFrame:
    filtered = products[
        products["name"].str.contains(r"(^|[^0-9])[0-9]{3}([^0-9]|$)", regex=True)
    ]
    return filtered.sort_values(by="product_id")
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
