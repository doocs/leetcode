---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3521.Find%20Product%20Recommendation%20Pairs/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3521. Find Product Recommendation Pairs](https://leetcode.com/problems/find-product-recommendation-pairs)

[中文文档](/solution/3500-3599/3521.Find%20Product%20Recommendation%20Pairs/README.md)

## Description

<!-- description:start -->

<p>Table: <code>ProductPurchases</code></p>

<pre>
+-------------+------+
| Column Name | Type | 
+-------------+------+
| user_id     | int  |
| product_id  | int  |
| quantity    | int  |
+-------------+------+
(user_id, product_id) is the unique key for this table.
Each row represents a purchase of a product by a user in a specific quantity.
</pre>

<p>Table: <code>ProductInfo</code></p>

<pre>
+-------------+---------+
| Column Name | Type    | 
+-------------+---------+
| product_id  | int     |
| category    | varchar |
| price       | decimal |
+-------------+---------+
product_id is the primary key for this table.
Each row assigns a category and price to a product.
</pre>

<p>Amazon wants to implement the <strong>Customers who bought this also bought...</strong> feature based on <strong>co-purchase patterns</strong>. Write a solution to :</p>

<ol>
	<li>Identify <strong>distinct</strong> product pairs frequently <strong>purchased together by the same customers</strong> (where <code>product1_id</code> &lt; <code>product2_id</code>)</li>
	<li>For <strong>each product pair</strong>, determine how many customers purchased <strong>both</strong> products</li>
</ol>

<p><strong>A product pair </strong>is considered for recommendation <strong>if</strong> <strong>at least</strong> <code>3</code> <strong>different</strong> customers have purchased <strong>both products</strong>.</p>

<p>Return <em>the </em><em>result table ordered by <strong>customer_count</strong> in <strong>descending</strong> order, and in case of a tie, by </em><code>product1_id</code><em> in <strong>ascending</strong> order, and then by </em><code>product2_id</code><em> in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>ProductPurchases table:</p>

<pre class="example-io">
+---------+------------+----------+
| user_id | product_id | quantity |
+---------+------------+----------+
| 1       | 101        | 2        |
| 1       | 102        | 1        |
| 1       | 103        | 3        |
| 2       | 101        | 1        |
| 2       | 102        | 5        |
| 2       | 104        | 1        |
| 3       | 101        | 2        |
| 3       | 103        | 1        |
| 3       | 105        | 4        |
| 4       | 101        | 1        |
| 4       | 102        | 1        |
| 4       | 103        | 2        |
| 4       | 104        | 3        |
| 5       | 102        | 2        |
| 5       | 104        | 1        |
+---------+------------+----------+
</pre>

<p>ProductInfo table:</p>

<pre class="example-io">
+------------+-------------+-------+
| product_id | category    | price |
+------------+-------------+-------+
| 101        | Electronics | 100   |
| 102        | Books       | 20    |
| 103        | Clothing    | 35    |
| 104        | Kitchen     | 50    |
| 105        | Sports      | 75    |
+------------+-------------+-------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+-------------+-------------+-------------------+-------------------+----------------+
| product1_id | product2_id | product1_category | product2_category | customer_count |
+-------------+-------------+-------------------+-------------------+----------------+
| 101         | 102         | Electronics       | Books             | 3              |
| 101         | 103         | Electronics       | Clothing          | 3              |
| 102         | 104         | Books             | Kitchen           | 3              |
+-------------+-------------+-------------------+-------------------+----------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>Product pair (101, 102):</strong>

    <ul>
    	<li>Purchased by users 1, 2, and 4 (3 customers)</li>
    	<li>Product 101 is in Electronics category</li>
    	<li>Product 102 is in Books category</li>
    </ul>
    </li>
    <li><strong>Product pair (101, 103):</strong>
    <ul>
    	<li>Purchased by users 1, 3, and 4 (3 customers)</li>
    	<li>Product 101 is in Electronics category</li>
    	<li>Product 103 is in Clothing category</li>
    </ul>
    </li>
    <li><strong>Product pair (102, 104):</strong>
    <ul>
    	<li>Purchased by users 2, 4, and 5 (3 customers)</li>
    	<li>Product 102 is in Books category</li>
    	<li>Product 104 is in Kitchen category</li>
    </ul>
    </li>

</ul>

<p>The result is ordered by customer_count in descending order. For pairs with the same customer_count, they are ordered by product1_id and then product2_id in ascending order.</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
