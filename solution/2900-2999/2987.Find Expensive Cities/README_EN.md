---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2987.Find%20Expensive%20Cities/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [2987. Find Expensive Cities 🔒](https://leetcode.com/problems/find-expensive-cities)

[中文文档](/solution/2900-2999/2987.Find%20Expensive%20Cities/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Listings</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| listing_id  | int     |
| city        | varchar |
| price       | int     |
+-------------+---------+
listing_id is column of unique values for this table.
This table contains listing_id, city, and price.
</pre>

<p>Write a solution to find <strong>cities </strong>where the <strong>average home prices</strong> exceed the <strong>national</strong> average home price.</p>

<p>Return <em>the result table sorted by </em><code>city</code><em> in <strong>ascending</strong> order</em><em>.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Listings table:
+------------+--------------+---------+
| listing_id | city         | price   | 
+------------+--------------+---------+
| 113        | LosAngeles   | 7560386 | 
| 136        | SanFrancisco | 2380268 |     
| 92         | Chicago      | 9833209 | 
| 60         | Chicago      | 5147582 | 
| 8          | Chicago      | 5274441 |  
| 79         | SanFrancisco | 8372065 | 
| 37         | Chicago      | 7939595 | 
| 53         | LosAngeles   | 4965123 | 
| 178        | SanFrancisco | 999207  | 
| 51         | NewYork      | 5951718 | 
| 121        | NewYork      | 2893760 | 
+------------+--------------+---------+
<strong>Output</strong>
+------------+
| city       | 
+------------+
| Chicago    | 
| LosAngeles |  
+------------+
<strong>Explanation</strong>
The national average home price is $6,122,059.45. Among the cities listed:
- Chicago has an average price of $7,048,706.75
- Los Angeles has an average price of $6,277,754.5
- San Francisco has an average price of $3,900,513.33
- New York has an average price of $4,422,739
Only Chicago and Los Angeles have average home prices exceeding the national average. Therefore, these two cities are included in the output table. The output table is sorted in ascending order based on the city names.

</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Grouping Aggregation + Subquery

We group the `Listings` table by `city`, then calculate the average house price for each city, and finally filter out the cities where the average house price is greater than the national average house price.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT city
FROM Listings
GROUP BY city
HAVING AVG(price) > (SELECT AVG(price) FROM Listings)
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
