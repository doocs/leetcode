---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3415.Find%20Products%20with%20Three%20Consecutive%20Digits/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3415. Find Products with Three Consecutive Digits 🔒](https://leetcode.cn/problems/find-products-with-three-consecutive-digits)

[English Version](/solution/3400-3499/3415.Find%20Products%20with%20Three%20Consecutive%20Digits/README_EN.md)

## 题目描述

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

<p>Write a solution to find all <strong>products</strong> whose names contain a <strong>sequence of exactly three digits in a row</strong>.&nbsp;</p>

<p>Return <em>the result table ordered by</em> <code>product_id</code> <em>in <strong>ascending</strong> order.</em></p>

<p>The result format is in the following example.</p>

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

## 解法

<!-- solution:start -->

### 方法一：正则匹配

我们可以使用正则表达式来匹配包含三个连续数字的产品名称。

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
