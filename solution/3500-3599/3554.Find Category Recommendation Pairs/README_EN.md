---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3554.Find%20Category%20Recommendation%20Pairs/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3554. Find Category Recommendation Pairs](https://leetcode.com/problems/find-category-recommendation-pairs)

[中文文档](/solution/3500-3599/3554.Find%20Category%20Recommendation%20Pairs/README.md)

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
(user_id, product_id) is the unique identifier for this table. 
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
product_id is the unique identifier for this table.
Each row assigns a category and price to a product.
</pre>

<p>Amazon wants to understand shopping patterns across product categories. Write a solution to:</p>

<ol>
	<li>Find all <strong>category pairs</strong> (where <code>category1</code> &lt; <code>category2</code>)</li>
	<li>For <strong>each category pair</strong>, determine the number of <strong>unique</strong> <strong>customers</strong> who purchased products from <strong>both</strong> categories</li>
</ol>

<p>A category pair is considered <strong>reportable</strong> if at least <code>3</code> different customers have purchased products from both categories.</p>

<p>Return <em>the result table of reportable category pairs ordered by <strong>customer_count</strong> in <strong>descending</strong> order, and in case of a tie, by <strong>category1</strong> in <strong>ascending</strong> order lexicographically, and then by <strong>category2</strong> in <strong>ascending</strong> order.</em></p>

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
| 1       | 201        | 3        |
| 1       | 301        | 1        |
| 2       | 101        | 1        |
| 2       | 102        | 2        |
| 2       | 103        | 1        |
| 2       | 201        | 5        |
| 3       | 101        | 2        |
| 3       | 103        | 1        |
| 3       | 301        | 4        |
| 3       | 401        | 2        |
| 4       | 101        | 1        |
| 4       | 201        | 3        |
| 4       | 301        | 1        |
| 4       | 401        | 2        |
| 5       | 102        | 2        |
| 5       | 103        | 1        |
| 5       | 201        | 2        |
| 5       | 202        | 3        |
+---------+------------+----------+
</pre>

<p>ProductInfo table:</p>

<pre class="example-io">
+------------+-------------+-------+
| product_id | category    | price |
+------------+-------------+-------+
| 101        | Electronics | 100   |
| 102        | Books       | 20    |
| 103        | Books       | 35    |
| 201        | Clothing    | 45    |
| 202        | Clothing    | 60    |
| 301        | Sports      | 75    |
| 401        | Kitchen     | 50    |
+------------+-------------+-------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+-------------+-------------+----------------+
| category1   | category2   | customer_count |
+-------------+-------------+----------------+
| Books       | Clothing    | 3              |
| Books       | Electronics | 3              |
| Clothing    | Electronics | 3              |
| Electronics | Sports      | 3              |
+-------------+-------------+----------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>Books-Clothing</strong>:

    <ul>
    	<li>User 1 purchased products from Books (102) and Clothing (201)</li>
    	<li>User 2 purchased products from Books (102, 103) and Clothing (201)</li>
    	<li>User 5 purchased products from Books (102, 103) and Clothing (201, 202)</li>
    	<li>Total: 3 customers purchased from both categories</li>
    </ul>
    </li>
    <li><strong>Books-Electronics</strong>:
    <ul>
    	<li>User 1 purchased products from Books (102) and Electronics (101)</li>
    	<li>User 2 purchased products from Books (102, 103) and Electronics (101)</li>
    	<li>User 3 purchased products from Books (103) and Electronics (101)</li>
    	<li>Total: 3 customers purchased from both categories</li>
    </ul>
    </li>
    <li><strong>Clothing-Electronics</strong>:
    <ul>
    	<li>User 1 purchased products from Clothing (201) and Electronics (101)</li>
    	<li>User 2 purchased products from Clothing (201) and Electronics (101)</li>
    	<li>User 4 purchased products from Clothing (201) and Electronics (101)</li>
    	<li>Total: 3 customers purchased from both categories</li>
    </ul>
    </li>
    <li><strong>Electronics-Sports</strong>:
    <ul>
    	<li>User 1 purchased products from Electronics (101) and Sports (301)</li>
    	<li>User 3 purchased products from Electronics (101) and Sports (301)</li>
    	<li>User 4 purchased products from Electronics (101) and Sports (301)</li>
    	<li>Total: 3 customers purchased from both categories</li>
    </ul>
    </li>
    <li>Other category pairs like Clothing-Sports (only 2 customers: Users 1 and 4) and Books-Kitchen (only 1 customer: User 3) have fewer than 3 shared customers and are not included in the result.</li>

</ul>

<p>The result is ordered by customer_count in descending order. Since all pairs have the same customer_count of 3, they are ordered by category1 (then category2) in ascending order.</p>
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
