---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3465.Find%20Products%20with%20Valid%20Serial%20Numbers/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3465. Find Products with Valid Serial Numbers](https://leetcode.com/problems/find-products-with-valid-serial-numbers)

[中文文档](/solution/3400-3499/3465.Find%20Products%20with%20Valid%20Serial%20Numbers/README.md)

## Description

<!-- description:start -->

<p>Table: <code>products</code></p>

<pre>
+--------------+------------+
| Column Name  | Type       |
+--------------+------------+
| product_id   | int        |
| product_name | varchar    |
| description  | varchar    |
+--------------+------------+
(product_id) is the unique key for this table.
Each row in the table represents a product with its unique ID, name, and description.
</pre>

<p>Write a solution to find all products whose description <strong>contains a valid serial number</strong> pattern. A valid serial number follows these rules:</p>

<ul>
	<li>It starts with the letters <strong>SN</strong>&nbsp;(case-sensitive).</li>
	<li>Followed by exactly <code>4</code> digits.</li>
	<li>It must have a hyphen (-) <strong>followed by exactly</strong> <code>4</code> digits.</li>
	<li>The serial number must be within the description (it may not necessarily start at the beginning).</li>
</ul>

<p>Return <em>the result table&nbsp;ordered by</em> <code>product_id</code> <em>in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>products table:</p>

<pre class="example-io">
+------------+--------------+------------------------------------------------------+
| product_id | product_name | description                                          |
+------------+--------------+------------------------------------------------------+
| 1          | Widget A     | This is a sample product with SN1234-5678            |
| 2          | Widget B     | A product with serial SN9876-1234 in the description |
| 3          | Widget C     | Product SN1234-56789 is available now                |
| 4          | Widget D     | No serial number here                                |
| 5          | Widget E     | Check out SN4321-8765 in this description            |
+------------+--------------+------------------------------------------------------+
    </pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+------------+--------------+------------------------------------------------------+
| product_id | product_name | description                                          |
+------------+--------------+------------------------------------------------------+
| 1          | Widget A     | This is a sample product with SN1234-5678            |
| 2          | Widget B     | A product with serial SN9876-1234 in the description |
| 5          | Widget E     | Check out SN4321-8765 in this description            |
+------------+--------------+------------------------------------------------------+
    </pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>Product 1:</strong> Valid serial number SN1234-5678</li>
	<li><strong>Product 2:</strong> Valid serial number SN9876-1234</li>
	<li><strong>Product 3:</strong> Invalid serial number SN1234-56789 (contains 5 digits after the hyphen)</li>
	<li><strong>Product 4:</strong> No serial number in the description</li>
	<li><strong>Product 5:</strong> Valid serial number SN4321-8765</li>
</ul>

<p>The result table is ordered by product_id in ascending order.</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Regex Matching

According to the problem statement, we need to find all products that contain a valid serial number, and the rules for a valid serial number are:

- Starts with `SN` (case-sensitive).
- Followed by 4 digits.
- Must have a hyphen `-`, followed by 4 digits.

Based on the above rules, we can use a regular expression to match valid serial numbers, then filter out the products that contain valid serial numbers, and finally sort them in ascending order by `product_id`.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT product_id, product_name, description
FROM products
WHERE description REGEXP '(?-i)\\bSN[0-9]{4}-[0-9]{4}\\b'
ORDER BY 1;
```

#### Pandas

```python
import pandas as pd


def find_valid_serial_products(products: pd.DataFrame) -> pd.DataFrame:
    valid_pattern = r"\bSN[0-9]{4}-[0-9]{4}\b"
    valid_products = products[
        products["description"].str.contains(valid_pattern, regex=True)
    ]
    valid_products = valid_products.sort_values(by="product_id")
    return valid_products
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
