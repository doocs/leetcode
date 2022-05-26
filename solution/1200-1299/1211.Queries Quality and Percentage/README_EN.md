# [1211. Queries Quality and Percentage](https://leetcode.com/problems/queries-quality-and-percentage)

[中文文档](/solution/1200-1299/1211.Queries%20Quality%20and%20Percentage/README.md)

## Description

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
There is no primary key for this table, it may have duplicate rows.
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

<p>Write an SQL query to find each <code>query_name</code>, the <code>quality</code> and <code>poor_query_percentage</code>.</p>

<p>Both <code>quality</code> and <code>poor_query_percentage</code> should be <strong>rounded to 2 decimal places</strong>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

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

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
