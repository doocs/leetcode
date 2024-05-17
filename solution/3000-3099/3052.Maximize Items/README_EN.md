---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3052.Maximize%20Items/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3052. Maximize Items 🔒](https://leetcode.com/problems/maximize-items)

[中文文档](/solution/3000-3099/3052.Maximize%20Items/README.md)

## Description

<!-- description:start -->

<p>Table: <font face="monospace"><code>Inventory</code></font></p>

<pre>
+----------------+---------+ 
| Column Name    | Type    | 
+----------------+---------+ 
| item_id        | int     | 
| item_type      | varchar |
| item_category  | varchar |
| square_footage | decimal |
+----------------+---------+
item_id is the column of unique values for this table.
Each row includes item id, item type, item category and sqaure footage.
</pre>

<p>Leetcode warehouse wants to maximize the number of items it can stock in a <code>500,000</code> square feet warehouse. It wants to stock as many <strong>prime</strong> items as possible, and afterwards use the <strong>remaining</strong> square footage to stock the most number of <strong>non-prime</strong> items.</p>

<p>Write a solution to find the number of <strong>prime</strong> and <strong>non-prime</strong> items that can be <strong>stored</strong> in the <code>500,000</code> square feet warehouse. Output the item type with <code>prime_eligible</code> followed by <code>not_prime</code> and the maximum number of items that can be stocked.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>Item <strong>count</strong> must be a whole number (integer).</li>
	<li>If the count for the <strong>not_prime</strong> category is <code>0</code>, you should <strong>output</strong> <code>0</code> for that particular category.</li>
</ul>

<p>Return <em>the result table ordered by item count in <strong>ascending order</strong></em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Inventory table:
+---------+----------------+---------------+----------------+
| item_id | item_type      | item_category | square_footage | 
+---------+----------------+---------------+----------------+
| 1374    | prime_eligible | Watches       | 68.00          | 
| 4245    | not_prime      | Art           | 26.40          | 
| 5743    | prime_eligible | Software      | 325.00         | 
| 8543    | not_prime      | Clothing      | 64.50          |  
| 2556    | not_prime      | Shoes         | 15.00          |
| 2452    | prime_eligible | Scientific    | 85.00          |
| 3255    | not_prime      | Furniture     | 22.60          | 
| 1672    | prime_eligible | Beauty        | 8.50           |  
| 4256    | prime_eligible | Furniture     | 55.50          |
| 6325    | prime_eligible | Food          | 13.20          | 
+---------+----------------+---------------+----------------+
<strong>Output:</strong> 
+----------------+-------------+
| item_type      | item_count  | 
+----------------+-------------+
| prime_eligible | 5400        | 
| not_prime      | 8           | 
+----------------+-------------+
<strong>Explanation:</strong> 
- The prime-eligible category comprises a total of 6 items, amounting to a combined square footage of 555.20 (68 + 325 + 85 + 8.50 + 55.50 + 13.20). It is possible to store 900 combinations of these 6 items, totaling 5400 items and occupying 499,680 square footage.
- In the not_prime category, there are a total of 4 items with a combined square footage of 128.50. After deducting the storage used by prime-eligible items (500,000 - 499,680 = 320), there is room for 2 combinations of non-prime items, accommodating a total of 8 non-prime items within the available 320 square footage.
Output table is ordered by item count in descending order.</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Join Query + Union All

First, we calculate the total area of all items of type `prime_eligible` and record it in the `s` field of table `T`.

Next, we calculate the number of items of type `prime_eligible` and `not_prime` respectively. For items of type `prime_eligible`, the number of portions we can store is $\lfloor \frac{500000}{s} \rfloor$. For items of type `not_prime`, the number of portions we can store is $\lfloor \frac{500000 \mod s}{\sum \text{s1}} \rfloor$. Where $\sum \text{s1}$ is the total area of all items of type `not_prime`. Multiplying by the number of items of type `prime_eligible` and `not_prime` respectively gives us our result.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT SUM(square_footage) AS s
        FROM Inventory
        WHERE item_type = 'prime_eligible'
    )
SELECT
    'prime_eligible' AS item_type,
    COUNT(1) * FLOOR(500000 / s) AS item_count
FROM
    Inventory
    JOIN T
WHERE item_type = 'prime_eligible'
UNION ALL
SELECT
    'not_prime',
    IFNULL(COUNT(1) * FLOOR(IF(s = 0, 500000, 500000 % s) / SUM(square_footage)), 0)
FROM
    Inventory
    JOIN T
WHERE item_type = 'not_prime';
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
