---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1211.Queries%20Quality%20and%20Percentage/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1211. Queries Quality and Percentage](https://leetcode.com/problems/queries-quality-and-percentage)

[中文文档](/solution/1200-1299/1211.Queries%20Quality%20and%20Percentage/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Queries</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| query_name  | varchar |
| result      | varchar |
| position    | int     |
| rating      | int     |
+-------------+---------+
This table may have duplicate rows.
This table contains information collected from some queries on a database.
The <code>position</code> column has a value from <strong>1</strong> to <strong>500</strong>.
The <code>rating</code> column has a value from <strong>1</strong> to <strong>5</strong>. Query with <code>rating</code> less than 3 is a poor query.
</pre>

<p>&nbsp;</p>

<p>We define query <code>quality</code> as:</p>

<blockquote>
<p>The average of the ratio between query rating and its position.</p>
</blockquote>

<p>We also define <code>poor query percentage</code> as:</p>

<blockquote>
<p>The percentage of all queries with rating less than 3.</p>
</blockquote>

<p>Write a solution to find each <code>query_name</code>, the <code>quality</code> and <code>poor_query_percentage</code>.</p>

<p>Both <code>quality</code> and <code>poor_query_percentage</code> should be <strong>rounded to 2 decimal places</strong>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Queries table:
+------------+-------------------+----------+--------+
| query_name | result            | position | rating |
+------------+-------------------+----------+--------+
| Dog        | Golden Retriever  | 1        | 5      |
| Dog        | German Shepherd   | 2        | 5      |
| Dog        | Mule              | 200      | 1      |
| Cat        | Shirazi           | 5        | 2      |
| Cat        | Siamese           | 3        | 3      |
| Cat        | Sphynx            | 7        | 4      |
+------------+-------------------+----------+--------+
<strong>Output:</strong> 
+------------+---------+-----------------------+
| query_name | quality | poor_query_percentage |
+------------+---------+-----------------------+
| Dog        | 2.50    | 33.33                 |
| Cat        | 0.66    | 33.33                 |
+------------+---------+-----------------------+
<strong>Explanation:</strong> 
Dog queries quality is ((5 / 1) + (5 / 2) + (1 / 200)) / 3 = 2.50
Dog queries poor_ query_percentage is (1 / 3) * 100 = 33.33

Cat queries quality equals ((2 / 5) + (3 / 3) + (4 / 7)) / 3 = 0.66
Cat queries poor_ query_percentage is (1 / 3) * 100 = 33.33
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Grouping and Aggregation

We can group the query results by `query_name`, and then use the `AVG` and `ROUND` functions to calculate `quality` and `poor_query_percentage`.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    query_name,
    ROUND(AVG(rating / position), 2) AS quality,
    ROUND(AVG(rating < 3) * 100, 2) AS poor_query_percentage
FROM Queries
WHERE query_name IS NOT NULL
GROUP BY 1;
```

```sql
# Write your MySQL query statement below
SELECT
    IFNULL(query_name, 'null') AS query_name,
    ROUND(AVG(CAST(rating AS DECIMAL) / position), 2) AS quality,
    ROUND(
        (
            SUM(
                CASE
                    WHEN rating < 3 THEN 1
                    ELSE 0
                END
            ) / NULLIF(COUNT(*), 0)
        ) * 100,
        2
    ) AS poor_query_percentage
FROM Queries
GROUP BY query_name WITH ROLLUP
HAVING query_name IS NOT NULL;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
